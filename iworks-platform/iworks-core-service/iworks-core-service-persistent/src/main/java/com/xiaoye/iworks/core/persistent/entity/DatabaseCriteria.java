package com.xiaoye.iworks.core.persistent.entity;

import java.util.List;

/**
 * 通用查询条件
 * @author yehl
 * @date 2018年6月23日
 */
public abstract class DatabaseCriteria {
	
	public abstract Class<? extends Database> geEntityClass();

	/** 是否分页 **/
	protected boolean pagination = false;

	protected Integer offset;

	protected Integer limit;

	/** 排序语句 **/
	protected String orderByClause;
	
	/** 重复过滤 **/
	protected boolean distinct;
	
	/** 分库下标 **/
	protected Integer dbIndex;
	
	/** 分表下标 **/
	protected Integer tbIndex;

	public boolean isPagination() {
		return pagination;
	}

	public DatabaseCriteria setPagination(boolean pagination) {
		this.pagination = pagination;
		return this;
	}

	public Integer getOffset() {
		return offset;
	}

	public DatabaseCriteria setOffset(Integer offset) {
		this.offset = offset;
		return this;
	}

	public Integer getLimit() {
		return limit;
	}

	public DatabaseCriteria setLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public DatabaseCriteria setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
		return this;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public DatabaseCriteria setDistinct(boolean distinct) {
		this.distinct = distinct;
		return this;
	}

	public Integer getDbIndex() {
		return dbIndex;
	}

	public DatabaseCriteria setDbIndex(Integer dbIndex) {
		this.dbIndex = dbIndex;
		return this;
	}

	public Integer getTbIndex() {
		return tbIndex;
	}

	public DatabaseCriteria setTbIndex(Integer tbIndex) {
		this.tbIndex = tbIndex;
		return this;
	}
	
	protected List<Criteria> criterias;
	
    public List<Criteria> getCriterias() {
		return criterias;
	}

	public void clear() {
    	criterias.clear();
        orderByClause = null;
        distinct = false;
    }
	
	public class Criteria {
		
		protected List<Criterion> criterions;

        public boolean isValid() {
            return criterions.size() > 0;
        }

        public List<Criterion> getCriterions() {
            return criterions;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criterions.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criterions.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criterions.add(new Criterion(condition, value1, value2));
        }
    }

    public class Criterion {
    
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public Criterion(String condition) {
            super();
            this.condition = condition;
            this.noValue = true;
        }

        public Criterion(String condition, Object value) {
            super();
            this.condition = condition;
            this.value = value;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        public Criterion(String condition, Object value, Object secondValue) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.betweenValue = true;
        }
    }
}
