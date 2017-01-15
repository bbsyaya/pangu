package org.turing.pangu.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, creatorVisibility = Visibility.NONE)
public class Page {

	public Page(int pageIndex, int pageSize) {
		setPageIndex(pageIndex);
		setPageSize(pageSize);
	}

	/**
	 * 每页条数
	 ***/
	private Integer pageSize;

	@JsonProperty
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize <= 0) {
			this.pageSize = 10;
		} else if (pageSize >= 500) {
			this.pageSize = 500;
		} else {
			this.pageSize = pageSize;
		}
	}

	/**
	 * 页码
	 ***/
	private Integer pageIndex;

	@JsonProperty
	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		if (pageIndex < 0) {
			this.pageIndex = 0;
		} else if (this.totalPageCount != null && pageIndex > this.totalPageCount) {
			this.pageIndex = this.totalPageCount;
		} else {
			this.pageIndex = pageIndex;
		}
	}

	/**
	 * 数据起始
	 */
	private Integer pageStart;

	/**
	 * 数据结束
	 */
	private Integer pageEnd;

	public Integer getPageStart() {
		pageStart=pageIndex * pageSize;
		return pageStart;
	}

	public Integer getPageEnd() {
		return (pageIndex + 1) * pageSize;
	}

	/**
	 * 总数据条数
	 **/
	private long totalCount;

	@JsonProperty
	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		if (totalCount > 0) {
			totalPageCount = (int) (totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1);
		} else {
			totalPageCount = 0;
			pageIndex = 0;
		}
	}

	/**
	 * 总页数
	 */
	private Integer totalPageCount;

	@JsonProperty
	public Integer getTotalPageCount() {
		return totalPageCount;
	}

	/**
	 * 排序
	 */
	private int sort;

	@JsonProperty
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Page() {
		super();
	}

}
