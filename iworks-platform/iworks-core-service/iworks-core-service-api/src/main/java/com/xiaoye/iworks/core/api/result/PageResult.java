package com.xiaoye.iworks.core.api.result;

import java.util.List;

/**
 * 接口请求出参
 * @author yehl
 * @date 2018年6月23日
 */
public class PageResult<T> extends Result {
	private static final long serialVersionUID = 468761447039817939L;

	private Integer offset;
	private Integer limit;
	private Integer total;
	private List<T> datas;

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
}
