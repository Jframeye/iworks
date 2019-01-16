/**
 * 
**/
package com.xiaoye.iworks.persistent.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 数据库实体类
 * @author yehl
 * @date 2018年6月23日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Entity implements Serializable {
	private static final long serialVersionUID = -6035732612851353934L;

	/** 分库下标 **/
	private String dbIndex;
	
	/** 分表下标 **/
	private String tbIndex;
}
