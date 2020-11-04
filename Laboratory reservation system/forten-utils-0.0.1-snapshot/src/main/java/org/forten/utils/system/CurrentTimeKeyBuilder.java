/*
 * Copyright 2003-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.forten.utils.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2012-10-22
 */
public final class CurrentTimeKeyBuilder {
	// PK生成锁,用来限定同一时刻只有一个线程进入PK生成计算
	private final Lock LOCK = new ReentrantLock();

	// 初始化时的毫秒数,因为该时间会随系统时间的改变而改变, 所以计算方法为该时间加上通过 nanoTime 计算出来的时间差
	private final static Long startMilli = System.currentTimeMillis();

	// 初始化时的纳秒数,用来计量时间差,nanoTime不会随着系统时间的修改而改变
	private final static long startNano = System.nanoTime();

	// 记录上一次生成 的 PK,如果新生成的PK和上次相等,则需要再次生成
	// 每次被线程访问时,都强迫从共享内存中重读该成员变量的值.而且,当成员变量发生变化时,强迫线程将变化值回写到共享内存
	private volatile long lastPK = -1;

	// 时间格式
	private final static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	// 返回的long PK的尾数,尾数可以用来在集群环境中判断该PK由哪一个节点产生
	// private static int
	private static int suffix = 1;

	private static final Map<Integer, CurrentTimeKeyBuilder> instanceMap = new HashMap<Integer, CurrentTimeKeyBuilder>();

	// 必须提供正确的参数,以保证 suffix 在集群环境的唯一性
	private CurrentTimeKeyBuilder(final int suffix) {
		CurrentTimeKeyBuilder.suffix = suffix;
	}

	/**
	 * 获得主键生成器实例,校验位从系统配置文件中读出.
	 * 
	 * @return 主键生成器实例
	 */
	public synchronized static CurrentTimeKeyBuilder getInstance() {
		return getInstance(suffix);
	}

	/**
	 * 获得主键生成器实例,校验位通过参数设置
	 * 
	 * 单机环境下,应该保证用相同的 suffix
	 * 
	 * 在集群环境中,不同的机器必须提供不同的 suffix 来保证生成的ID的唯一性
	 * 
	 * @param suffix
	 *            唯一标识
	 * @return 主键生成器实例
	 */
	public synchronized static CurrentTimeKeyBuilder getInstance(int suffix) {
		CurrentTimeKeyBuilder pkgen;
		if (!instanceMap.containsKey(suffix)) {
			pkgen = new CurrentTimeKeyBuilder(suffix);
			instanceMap.put(suffix, pkgen);
		} else {
			pkgen = instanceMap.get(suffix);
		}
		return pkgen;
	}

	/**
	 * 返回下一个 long 型 PK, format: 2007101011023022291 <br>
	 * yyyyMMddHHmmssSSS + Macro Seconds + suffix
	 * 
	 * @return long PK
	 */
	public long nextPK() {
		LOCK.lock();
		try {
			long newPK;
			do {
				long pastNano = System.nanoTime() - startNano; // 纳秒时间差
				long milliTime = pastNano / 1000000; // 取得毫秒差
				long macroTime = (pastNano / 100000) % 10; // 取得微秒第一位,
				// 计算出来的long PK,精度到万分之一秒（百微妙）,加上尾数,一共19位,这是 Long.MAX_VALUE的最大位数了
				newPK = Long.parseLong(dateFormat.format(new Date(startMilli
						+ milliTime))
						+ macroTime + suffix);
			} while (lastPK == newPK); // 如果生成的相同,则再次计算
			lastPK = newPK; // 设置 lastPK
			return newPK;
		} finally {
			LOCK.unlock();
		}
	}
}
