/*
 * Copyright 2001-2014 the original author or authors.
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

import org.apache.log4j.Logger;

/**
 * 分页信息描述类
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2014年1月24日
 */
public class PageInfo {
	private static Logger log = Logger.getLogger(PageInfo.class);

	private int pageNo;// 当前页码
	private int pageSize;// 每页容量
	private long totalQuantity;// 数据总量
	private long firstResultNum;// 当前页第一条数据的序号
	private long lastResultNum;// 当前页最末条数据的序号
	private int totalPage;// 总页数
	private boolean firstPage;// 是否是第一页
	private boolean lastPage;// 是否是最末页

	// 私有构造器
	private PageInfo(int pageNo, int pageSize, long totalQuantity) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalQuantity = totalQuantity;
		this.firstPage = false;
		this.lastPage = false;
	}

	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	private void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @return the totalQuantity
	 */
	public long getTotalQuantity() {
		return totalQuantity;
	}

	/**
	 * @return the firstResultNum
	 */
	public long getFirstResultNum() {
		return firstResultNum;
	}

	/**
	 * @param firstResultNum
	 *            the firstResultNum to set
	 */
	private void setFirstResultNum(long firstResultNum) {
		this.firstResultNum = firstResultNum;
	}

	/**
	 * @return lastResultNum
	 */
	public long getLastResultNum() {
		return lastResultNum;
	}

	/**
	 * @param lastResultNum
	 *            the lastResultNum to set
	 */
	private void setLastResultNum(long lastResultNum) {
		this.lastResultNum = lastResultNum;
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage
	 *            the totalPage to set
	 */
	private void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return the isFirstPage
	 */
	public boolean isFirstPage() {
		return firstPage;
	}

	/**
	 * @param isFirstPage
	 *            the isFirstPage to set
	 */
	private void setFirstPage(boolean isFirstPage) {
		this.firstPage = isFirstPage;
	}

	/**
	 * @return the isLastPage
	 */
	public boolean isLastPage() {
		return lastPage;
	}

	/**
	 * @param isLastPage
	 *            the isLastPage to set
	 */
	private void setLastPage(boolean isLastPage) {
		this.lastPage = isLastPage;
	}

	/**
	 * 获得分页信息实例
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页最大记录数量
	 * @param totalQuantity
	 *            总记录数量
	 * @return 完整的分页信息实例
	 */
	public static PageInfo getInstance(int pageNo, int pageSize,
			long totalQuantity) {
		PageInfo page = new PageInfo(pageNo, pageSize, totalQuantity);

		// 计算总页数
		if ((totalQuantity % pageSize) == 0) {
			page.setTotalPage((int) (totalQuantity / pageSize));
		} else {
			page.setTotalPage((int) (totalQuantity / pageSize) + 1);
		}

		if (pageNo > page.getTotalPage()) {
			page.setPageNo(page.getTotalPage());
		}

		if (pageNo < 1) {
			page.setPageNo(1);
		}

		if (page.getPageNo() == 1) {
			page.setFirstPage(true);
		}
		if (page.getPageNo() == page.getTotalPage()) {
			page.setLastPage(true);
		}

		page.setFirstResultNum(pageSize * (page.getPageNo() - 1));

		if (page.isLastPage()) {
			page.setLastResultNum(page.getTotalQuantity());
		} else {
			page.setLastResultNum(page.getFirstResultNum() + page.getPageSize());
		}

		LogUtil.debug(log, page.toString());
		return page;
	}

	@Override
	public String toString() {
		return "PageInfo [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", totalQuantity=" + totalQuantity + ", firstResultNum="
				+ firstResultNum + ", lastResultNum=" + lastResultNum
				+ ", totalPage=" + totalPage + ", firstPage=" + firstPage
				+ ", lastPage=" + lastPage + "]";
	}
}
