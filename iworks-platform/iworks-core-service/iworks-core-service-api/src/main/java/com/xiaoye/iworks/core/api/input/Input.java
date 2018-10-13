package com.xiaoye.iworks.core.api.input;

import java.io.Serializable;

/**
 * 接口请求入参
 * @author yehl
 * @date 2018年6月23日
 */
public class Input implements Serializable {

	/** **/
	private static final long serialVersionUID = 1L;
	
	private boolean pagnation = false;
	
	private Integer offset = 0;
	
	private Integer limit = 20;

	private String currentUser;
	
	public boolean isPagnation() {
		return pagnation;
	}

	public void setPagnation(boolean pagnation) {
		this.pagnation = pagnation;
	}

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

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
}
