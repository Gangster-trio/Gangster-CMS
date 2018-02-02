package com.ganster.cms.core.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SkinExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SkinExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSkinNameIsNull() {
            addCriterion("skin_name is null");
            return (Criteria) this;
        }

        public Criteria andSkinNameIsNotNull() {
            addCriterion("skin_name is not null");
            return (Criteria) this;
        }

        public Criteria andSkinNameEqualTo(String value) {
            addCriterion("skin_name =", value, "skinName");
            return (Criteria) this;
        }

        public Criteria andSkinNameNotEqualTo(String value) {
            addCriterion("skin_name <>", value, "skinName");
            return (Criteria) this;
        }

        public Criteria andSkinNameGreaterThan(String value) {
            addCriterion("skin_name >", value, "skinName");
            return (Criteria) this;
        }

        public Criteria andSkinNameGreaterThanOrEqualTo(String value) {
            addCriterion("skin_name >=", value, "skinName");
            return (Criteria) this;
        }

        public Criteria andSkinNameLessThan(String value) {
            addCriterion("skin_name <", value, "skinName");
            return (Criteria) this;
        }

        public Criteria andSkinNameLessThanOrEqualTo(String value) {
            addCriterion("skin_name <=", value, "skinName");
            return (Criteria) this;
        }

        public Criteria andSkinNameLike(String value) {
            addCriterion("skin_name like", value, "skinName");
            return (Criteria) this;
        }

        public Criteria andSkinNameNotLike(String value) {
            addCriterion("skin_name not like", value, "skinName");
            return (Criteria) this;
        }

        public Criteria andSkinNameIn(List<String> values) {
            addCriterion("skin_name in", values, "skinName");
            return (Criteria) this;
        }

        public Criteria andSkinNameNotIn(List<String> values) {
            addCriterion("skin_name not in", values, "skinName");
            return (Criteria) this;
        }

        public Criteria andSkinNameBetween(String value1, String value2) {
            addCriterion("skin_name between", value1, value2, "skinName");
            return (Criteria) this;
        }

        public Criteria andSkinNameNotBetween(String value1, String value2) {
            addCriterion("skin_name not between", value1, value2, "skinName");
            return (Criteria) this;
        }

        public Criteria andSkinCreateTimeIsNull() {
            addCriterion("skin_create_time is null");
            return (Criteria) this;
        }

        public Criteria andSkinCreateTimeIsNotNull() {
            addCriterion("skin_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andSkinCreateTimeEqualTo(Date value) {
            addCriterion("skin_create_time =", value, "skinCreateTime");
            return (Criteria) this;
        }

        public Criteria andSkinCreateTimeNotEqualTo(Date value) {
            addCriterion("skin_create_time <>", value, "skinCreateTime");
            return (Criteria) this;
        }

        public Criteria andSkinCreateTimeGreaterThan(Date value) {
            addCriterion("skin_create_time >", value, "skinCreateTime");
            return (Criteria) this;
        }

        public Criteria andSkinCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("skin_create_time >=", value, "skinCreateTime");
            return (Criteria) this;
        }

        public Criteria andSkinCreateTimeLessThan(Date value) {
            addCriterion("skin_create_time <", value, "skinCreateTime");
            return (Criteria) this;
        }

        public Criteria andSkinCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("skin_create_time <=", value, "skinCreateTime");
            return (Criteria) this;
        }

        public Criteria andSkinCreateTimeIn(List<Date> values) {
            addCriterion("skin_create_time in", values, "skinCreateTime");
            return (Criteria) this;
        }

        public Criteria andSkinCreateTimeNotIn(List<Date> values) {
            addCriterion("skin_create_time not in", values, "skinCreateTime");
            return (Criteria) this;
        }

        public Criteria andSkinCreateTimeBetween(Date value1, Date value2) {
            addCriterion("skin_create_time between", value1, value2, "skinCreateTime");
            return (Criteria) this;
        }

        public Criteria andSkinCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("skin_create_time not between", value1, value2, "skinCreateTime");
            return (Criteria) this;
        }

        public Criteria andSkinUpdateTimeIsNull() {
            addCriterion("skin_update_time is null");
            return (Criteria) this;
        }

        public Criteria andSkinUpdateTimeIsNotNull() {
            addCriterion("skin_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andSkinUpdateTimeEqualTo(Date value) {
            addCriterion("skin_update_time =", value, "skinUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSkinUpdateTimeNotEqualTo(Date value) {
            addCriterion("skin_update_time <>", value, "skinUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSkinUpdateTimeGreaterThan(Date value) {
            addCriterion("skin_update_time >", value, "skinUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSkinUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("skin_update_time >=", value, "skinUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSkinUpdateTimeLessThan(Date value) {
            addCriterion("skin_update_time <", value, "skinUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSkinUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("skin_update_time <=", value, "skinUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSkinUpdateTimeIn(List<Date> values) {
            addCriterion("skin_update_time in", values, "skinUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSkinUpdateTimeNotIn(List<Date> values) {
            addCriterion("skin_update_time not in", values, "skinUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSkinUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("skin_update_time between", value1, value2, "skinUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSkinUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("skin_update_time not between", value1, value2, "skinUpdateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

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

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}