package com.ganster.cms.core.pojo;

import java.util.ArrayList;
import java.util.List;

public class CountEntryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CountEntryExample() {
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

        public Criteria andCountIdIsNull() {
            addCriterion("count_id is null");
            return (Criteria) this;
        }

        public Criteria andCountIdIsNotNull() {
            addCriterion("count_id is not null");
            return (Criteria) this;
        }

        public Criteria andCountIdEqualTo(Integer value) {
            addCriterion("count_id =", value, "countId");
            return (Criteria) this;
        }

        public Criteria andCountIdNotEqualTo(Integer value) {
            addCriterion("count_id <>", value, "countId");
            return (Criteria) this;
        }

        public Criteria andCountIdGreaterThan(Integer value) {
            addCriterion("count_id >", value, "countId");
            return (Criteria) this;
        }

        public Criteria andCountIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("count_id >=", value, "countId");
            return (Criteria) this;
        }

        public Criteria andCountIdLessThan(Integer value) {
            addCriterion("count_id <", value, "countId");
            return (Criteria) this;
        }

        public Criteria andCountIdLessThanOrEqualTo(Integer value) {
            addCriterion("count_id <=", value, "countId");
            return (Criteria) this;
        }

        public Criteria andCountIdIn(List<Integer> values) {
            addCriterion("count_id in", values, "countId");
            return (Criteria) this;
        }

        public Criteria andCountIdNotIn(List<Integer> values) {
            addCriterion("count_id not in", values, "countId");
            return (Criteria) this;
        }

        public Criteria andCountIdBetween(Integer value1, Integer value2) {
            addCriterion("count_id between", value1, value2, "countId");
            return (Criteria) this;
        }

        public Criteria andCountIdNotBetween(Integer value1, Integer value2) {
            addCriterion("count_id not between", value1, value2, "countId");
            return (Criteria) this;
        }

        public Criteria andCountTypeIsNull() {
            addCriterion("count_type is null");
            return (Criteria) this;
        }

        public Criteria andCountTypeIsNotNull() {
            addCriterion("count_type is not null");
            return (Criteria) this;
        }

        public Criteria andCountTypeEqualTo(String value) {
            addCriterion("count_type =", value, "countType");
            return (Criteria) this;
        }

        public Criteria andCountTypeNotEqualTo(String value) {
            addCriterion("count_type <>", value, "countType");
            return (Criteria) this;
        }

        public Criteria andCountTypeGreaterThan(String value) {
            addCriterion("count_type >", value, "countType");
            return (Criteria) this;
        }

        public Criteria andCountTypeGreaterThanOrEqualTo(String value) {
            addCriterion("count_type >=", value, "countType");
            return (Criteria) this;
        }

        public Criteria andCountTypeLessThan(String value) {
            addCriterion("count_type <", value, "countType");
            return (Criteria) this;
        }

        public Criteria andCountTypeLessThanOrEqualTo(String value) {
            addCriterion("count_type <=", value, "countType");
            return (Criteria) this;
        }

        public Criteria andCountTypeLike(String value) {
            addCriterion("count_type like", value, "countType");
            return (Criteria) this;
        }

        public Criteria andCountTypeNotLike(String value) {
            addCriterion("count_type not like", value, "countType");
            return (Criteria) this;
        }

        public Criteria andCountTypeIn(List<String> values) {
            addCriterion("count_type in", values, "countType");
            return (Criteria) this;
        }

        public Criteria andCountTypeNotIn(List<String> values) {
            addCriterion("count_type not in", values, "countType");
            return (Criteria) this;
        }

        public Criteria andCountTypeBetween(String value1, String value2) {
            addCriterion("count_type between", value1, value2, "countType");
            return (Criteria) this;
        }

        public Criteria andCountTypeNotBetween(String value1, String value2) {
            addCriterion("count_type not between", value1, value2, "countType");
            return (Criteria) this;
        }

        public Criteria andCountCidIsNull() {
            addCriterion("count_cid is null");
            return (Criteria) this;
        }

        public Criteria andCountCidIsNotNull() {
            addCriterion("count_cid is not null");
            return (Criteria) this;
        }

        public Criteria andCountCidEqualTo(String value) {
            addCriterion("count_cid =", value, "countCid");
            return (Criteria) this;
        }

        public Criteria andCountCidNotEqualTo(String value) {
            addCriterion("count_cid <>", value, "countCid");
            return (Criteria) this;
        }

        public Criteria andCountCidGreaterThan(String value) {
            addCriterion("count_cid >", value, "countCid");
            return (Criteria) this;
        }

        public Criteria andCountCidGreaterThanOrEqualTo(String value) {
            addCriterion("count_cid >=", value, "countCid");
            return (Criteria) this;
        }

        public Criteria andCountCidLessThan(String value) {
            addCriterion("count_cid <", value, "countCid");
            return (Criteria) this;
        }

        public Criteria andCountCidLessThanOrEqualTo(String value) {
            addCriterion("count_cid <=", value, "countCid");
            return (Criteria) this;
        }

        public Criteria andCountCidLike(String value) {
            addCriterion("count_cid like", value, "countCid");
            return (Criteria) this;
        }

        public Criteria andCountCidNotLike(String value) {
            addCriterion("count_cid not like", value, "countCid");
            return (Criteria) this;
        }

        public Criteria andCountCidIn(List<String> values) {
            addCriterion("count_cid in", values, "countCid");
            return (Criteria) this;
        }

        public Criteria andCountCidNotIn(List<String> values) {
            addCriterion("count_cid not in", values, "countCid");
            return (Criteria) this;
        }

        public Criteria andCountCidBetween(String value1, String value2) {
            addCriterion("count_cid between", value1, value2, "countCid");
            return (Criteria) this;
        }

        public Criteria andCountCidNotBetween(String value1, String value2) {
            addCriterion("count_cid not between", value1, value2, "countCid");
            return (Criteria) this;
        }

        public Criteria andCountPvIsNull() {
            addCriterion("count_pv is null");
            return (Criteria) this;
        }

        public Criteria andCountPvIsNotNull() {
            addCriterion("count_pv is not null");
            return (Criteria) this;
        }

        public Criteria andCountPvEqualTo(Integer value) {
            addCriterion("count_pv =", value, "countPv");
            return (Criteria) this;
        }

        public Criteria andCountPvNotEqualTo(Integer value) {
            addCriterion("count_pv <>", value, "countPv");
            return (Criteria) this;
        }

        public Criteria andCountPvGreaterThan(Integer value) {
            addCriterion("count_pv >", value, "countPv");
            return (Criteria) this;
        }

        public Criteria andCountPvGreaterThanOrEqualTo(Integer value) {
            addCriterion("count_pv >=", value, "countPv");
            return (Criteria) this;
        }

        public Criteria andCountPvLessThan(Integer value) {
            addCriterion("count_pv <", value, "countPv");
            return (Criteria) this;
        }

        public Criteria andCountPvLessThanOrEqualTo(Integer value) {
            addCriterion("count_pv <=", value, "countPv");
            return (Criteria) this;
        }

        public Criteria andCountPvIn(List<Integer> values) {
            addCriterion("count_pv in", values, "countPv");
            return (Criteria) this;
        }

        public Criteria andCountPvNotIn(List<Integer> values) {
            addCriterion("count_pv not in", values, "countPv");
            return (Criteria) this;
        }

        public Criteria andCountPvBetween(Integer value1, Integer value2) {
            addCriterion("count_pv between", value1, value2, "countPv");
            return (Criteria) this;
        }

        public Criteria andCountPvNotBetween(Integer value1, Integer value2) {
            addCriterion("count_pv not between", value1, value2, "countPv");
            return (Criteria) this;
        }

        public Criteria andCountTimeIsNull() {
            addCriterion("count_time is null");
            return (Criteria) this;
        }

        public Criteria andCountTimeIsNotNull() {
            addCriterion("count_time is not null");
            return (Criteria) this;
        }

        public Criteria andCountTimeEqualTo(Long value) {
            addCriterion("count_time =", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeNotEqualTo(Long value) {
            addCriterion("count_time <>", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeGreaterThan(Long value) {
            addCriterion("count_time >", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("count_time >=", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeLessThan(Long value) {
            addCriterion("count_time <", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeLessThanOrEqualTo(Long value) {
            addCriterion("count_time <=", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeIn(List<Long> values) {
            addCriterion("count_time in", values, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeNotIn(List<Long> values) {
            addCriterion("count_time not in", values, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeBetween(Long value1, Long value2) {
            addCriterion("count_time between", value1, value2, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeNotBetween(Long value1, Long value2) {
            addCriterion("count_time not between", value1, value2, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountIntervalIsNull() {
            addCriterion("count_interval is null");
            return (Criteria) this;
        }

        public Criteria andCountIntervalIsNotNull() {
            addCriterion("count_interval is not null");
            return (Criteria) this;
        }

        public Criteria andCountIntervalEqualTo(Integer value) {
            addCriterion("count_interval =", value, "countInterval");
            return (Criteria) this;
        }

        public Criteria andCountIntervalNotEqualTo(Integer value) {
            addCriterion("count_interval <>", value, "countInterval");
            return (Criteria) this;
        }

        public Criteria andCountIntervalGreaterThan(Integer value) {
            addCriterion("count_interval >", value, "countInterval");
            return (Criteria) this;
        }

        public Criteria andCountIntervalGreaterThanOrEqualTo(Integer value) {
            addCriterion("count_interval >=", value, "countInterval");
            return (Criteria) this;
        }

        public Criteria andCountIntervalLessThan(Integer value) {
            addCriterion("count_interval <", value, "countInterval");
            return (Criteria) this;
        }

        public Criteria andCountIntervalLessThanOrEqualTo(Integer value) {
            addCriterion("count_interval <=", value, "countInterval");
            return (Criteria) this;
        }

        public Criteria andCountIntervalIn(List<Integer> values) {
            addCriterion("count_interval in", values, "countInterval");
            return (Criteria) this;
        }

        public Criteria andCountIntervalNotIn(List<Integer> values) {
            addCriterion("count_interval not in", values, "countInterval");
            return (Criteria) this;
        }

        public Criteria andCountIntervalBetween(Integer value1, Integer value2) {
            addCriterion("count_interval between", value1, value2, "countInterval");
            return (Criteria) this;
        }

        public Criteria andCountIntervalNotBetween(Integer value1, Integer value2) {
            addCriterion("count_interval not between", value1, value2, "countInterval");
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