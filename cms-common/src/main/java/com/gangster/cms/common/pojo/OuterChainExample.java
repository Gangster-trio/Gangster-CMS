package com.gangster.cms.common.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OuterChainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OuterChainExample() {
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

        public Criteria andOuterchainIdIsNull() {
            addCriterion("outerchain_id is null");
            return (Criteria) this;
        }

        public Criteria andOuterchainIdIsNotNull() {
            addCriterion("outerchain_id is not null");
            return (Criteria) this;
        }

        public Criteria andOuterchainIdEqualTo(Integer value) {
            addCriterion("outerchain_id =", value, "outerchainId");
            return (Criteria) this;
        }

        public Criteria andOuterchainIdNotEqualTo(Integer value) {
            addCriterion("outerchain_id <>", value, "outerchainId");
            return (Criteria) this;
        }

        public Criteria andOuterchainIdGreaterThan(Integer value) {
            addCriterion("outerchain_id >", value, "outerchainId");
            return (Criteria) this;
        }

        public Criteria andOuterchainIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("outerchain_id >=", value, "outerchainId");
            return (Criteria) this;
        }

        public Criteria andOuterchainIdLessThan(Integer value) {
            addCriterion("outerchain_id <", value, "outerchainId");
            return (Criteria) this;
        }

        public Criteria andOuterchainIdLessThanOrEqualTo(Integer value) {
            addCriterion("outerchain_id <=", value, "outerchainId");
            return (Criteria) this;
        }

        public Criteria andOuterchainIdIn(List<Integer> values) {
            addCriterion("outerchain_id in", values, "outerchainId");
            return (Criteria) this;
        }

        public Criteria andOuterchainIdNotIn(List<Integer> values) {
            addCriterion("outerchain_id not in", values, "outerchainId");
            return (Criteria) this;
        }

        public Criteria andOuterchainIdBetween(Integer value1, Integer value2) {
            addCriterion("outerchain_id between", value1, value2, "outerchainId");
            return (Criteria) this;
        }

        public Criteria andOuterchainIdNotBetween(Integer value1, Integer value2) {
            addCriterion("outerchain_id not between", value1, value2, "outerchainId");
            return (Criteria) this;
        }

        public Criteria andOuterchainUrlIsNull() {
            addCriterion("outerchain_url is null");
            return (Criteria) this;
        }

        public Criteria andOuterchainUrlIsNotNull() {
            addCriterion("outerchain_url is not null");
            return (Criteria) this;
        }

        public Criteria andOuterchainUrlEqualTo(String value) {
            addCriterion("outerchain_url =", value, "outerchainUrl");
            return (Criteria) this;
        }

        public Criteria andOuterchainUrlNotEqualTo(String value) {
            addCriterion("outerchain_url <>", value, "outerchainUrl");
            return (Criteria) this;
        }

        public Criteria andOuterchainUrlGreaterThan(String value) {
            addCriterion("outerchain_url >", value, "outerchainUrl");
            return (Criteria) this;
        }

        public Criteria andOuterchainUrlGreaterThanOrEqualTo(String value) {
            addCriterion("outerchain_url >=", value, "outerchainUrl");
            return (Criteria) this;
        }

        public Criteria andOuterchainUrlLessThan(String value) {
            addCriterion("outerchain_url <", value, "outerchainUrl");
            return (Criteria) this;
        }

        public Criteria andOuterchainUrlLessThanOrEqualTo(String value) {
            addCriterion("outerchain_url <=", value, "outerchainUrl");
            return (Criteria) this;
        }

        public Criteria andOuterchainUrlLike(String value) {
            addCriterion("outerchain_url like", value, "outerchainUrl");
            return (Criteria) this;
        }

        public Criteria andOuterchainUrlNotLike(String value) {
            addCriterion("outerchain_url not like", value, "outerchainUrl");
            return (Criteria) this;
        }

        public Criteria andOuterchainUrlIn(List<String> values) {
            addCriterion("outerchain_url in", values, "outerchainUrl");
            return (Criteria) this;
        }

        public Criteria andOuterchainUrlNotIn(List<String> values) {
            addCriterion("outerchain_url not in", values, "outerchainUrl");
            return (Criteria) this;
        }

        public Criteria andOuterchainUrlBetween(String value1, String value2) {
            addCriterion("outerchain_url between", value1, value2, "outerchainUrl");
            return (Criteria) this;
        }

        public Criteria andOuterchainUrlNotBetween(String value1, String value2) {
            addCriterion("outerchain_url not between", value1, value2, "outerchainUrl");
            return (Criteria) this;
        }

        public Criteria andOuterchainCreateTimeIsNull() {
            addCriterion("outerchain_create_time is null");
            return (Criteria) this;
        }

        public Criteria andOuterchainCreateTimeIsNotNull() {
            addCriterion("outerchain_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andOuterchainCreateTimeEqualTo(Date value) {
            addCriterion("outerchain_create_time =", value, "outerchainCreateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainCreateTimeNotEqualTo(Date value) {
            addCriterion("outerchain_create_time <>", value, "outerchainCreateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainCreateTimeGreaterThan(Date value) {
            addCriterion("outerchain_create_time >", value, "outerchainCreateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("outerchain_create_time >=", value, "outerchainCreateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainCreateTimeLessThan(Date value) {
            addCriterion("outerchain_create_time <", value, "outerchainCreateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("outerchain_create_time <=", value, "outerchainCreateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainCreateTimeIn(List<Date> values) {
            addCriterion("outerchain_create_time in", values, "outerchainCreateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainCreateTimeNotIn(List<Date> values) {
            addCriterion("outerchain_create_time not in", values, "outerchainCreateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainCreateTimeBetween(Date value1, Date value2) {
            addCriterion("outerchain_create_time between", value1, value2, "outerchainCreateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("outerchain_create_time not between", value1, value2, "outerchainCreateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainDescIsNull() {
            addCriterion("outerchain_desc is null");
            return (Criteria) this;
        }

        public Criteria andOuterchainDescIsNotNull() {
            addCriterion("outerchain_desc is not null");
            return (Criteria) this;
        }

        public Criteria andOuterchainDescEqualTo(String value) {
            addCriterion("outerchain_desc =", value, "outerchainDesc");
            return (Criteria) this;
        }

        public Criteria andOuterchainDescNotEqualTo(String value) {
            addCriterion("outerchain_desc <>", value, "outerchainDesc");
            return (Criteria) this;
        }

        public Criteria andOuterchainDescGreaterThan(String value) {
            addCriterion("outerchain_desc >", value, "outerchainDesc");
            return (Criteria) this;
        }

        public Criteria andOuterchainDescGreaterThanOrEqualTo(String value) {
            addCriterion("outerchain_desc >=", value, "outerchainDesc");
            return (Criteria) this;
        }

        public Criteria andOuterchainDescLessThan(String value) {
            addCriterion("outerchain_desc <", value, "outerchainDesc");
            return (Criteria) this;
        }

        public Criteria andOuterchainDescLessThanOrEqualTo(String value) {
            addCriterion("outerchain_desc <=", value, "outerchainDesc");
            return (Criteria) this;
        }

        public Criteria andOuterchainDescLike(String value) {
            addCriterion("outerchain_desc like", value, "outerchainDesc");
            return (Criteria) this;
        }

        public Criteria andOuterchainDescNotLike(String value) {
            addCriterion("outerchain_desc not like", value, "outerchainDesc");
            return (Criteria) this;
        }

        public Criteria andOuterchainDescIn(List<String> values) {
            addCriterion("outerchain_desc in", values, "outerchainDesc");
            return (Criteria) this;
        }

        public Criteria andOuterchainDescNotIn(List<String> values) {
            addCriterion("outerchain_desc not in", values, "outerchainDesc");
            return (Criteria) this;
        }

        public Criteria andOuterchainDescBetween(String value1, String value2) {
            addCriterion("outerchain_desc between", value1, value2, "outerchainDesc");
            return (Criteria) this;
        }

        public Criteria andOuterchainDescNotBetween(String value1, String value2) {
            addCriterion("outerchain_desc not between", value1, value2, "outerchainDesc");
            return (Criteria) this;
        }

        public Criteria andOuterchainSiteIdIsNull() {
            addCriterion("outerchain_site_id is null");
            return (Criteria) this;
        }

        public Criteria andOuterchainSiteIdIsNotNull() {
            addCriterion("outerchain_site_id is not null");
            return (Criteria) this;
        }

        public Criteria andOuterchainSiteIdEqualTo(Integer value) {
            addCriterion("outerchain_site_id =", value, "outerchainSiteId");
            return (Criteria) this;
        }

        public Criteria andOuterchainSiteIdNotEqualTo(Integer value) {
            addCriterion("outerchain_site_id <>", value, "outerchainSiteId");
            return (Criteria) this;
        }

        public Criteria andOuterchainSiteIdGreaterThan(Integer value) {
            addCriterion("outerchain_site_id >", value, "outerchainSiteId");
            return (Criteria) this;
        }

        public Criteria andOuterchainSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("outerchain_site_id >=", value, "outerchainSiteId");
            return (Criteria) this;
        }

        public Criteria andOuterchainSiteIdLessThan(Integer value) {
            addCriterion("outerchain_site_id <", value, "outerchainSiteId");
            return (Criteria) this;
        }

        public Criteria andOuterchainSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("outerchain_site_id <=", value, "outerchainSiteId");
            return (Criteria) this;
        }

        public Criteria andOuterchainSiteIdIn(List<Integer> values) {
            addCriterion("outerchain_site_id in", values, "outerchainSiteId");
            return (Criteria) this;
        }

        public Criteria andOuterchainSiteIdNotIn(List<Integer> values) {
            addCriterion("outerchain_site_id not in", values, "outerchainSiteId");
            return (Criteria) this;
        }

        public Criteria andOuterchainSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("outerchain_site_id between", value1, value2, "outerchainSiteId");
            return (Criteria) this;
        }

        public Criteria andOuterchainSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("outerchain_site_id not between", value1, value2, "outerchainSiteId");
            return (Criteria) this;
        }

        public Criteria andOuterchainNameIsNull() {
            addCriterion("outerchain_name is null");
            return (Criteria) this;
        }

        public Criteria andOuterchainNameIsNotNull() {
            addCriterion("outerchain_name is not null");
            return (Criteria) this;
        }

        public Criteria andOuterchainNameEqualTo(String value) {
            addCriterion("outerchain_name =", value, "outerchainName");
            return (Criteria) this;
        }

        public Criteria andOuterchainNameNotEqualTo(String value) {
            addCriterion("outerchain_name <>", value, "outerchainName");
            return (Criteria) this;
        }

        public Criteria andOuterchainNameGreaterThan(String value) {
            addCriterion("outerchain_name >", value, "outerchainName");
            return (Criteria) this;
        }

        public Criteria andOuterchainNameGreaterThanOrEqualTo(String value) {
            addCriterion("outerchain_name >=", value, "outerchainName");
            return (Criteria) this;
        }

        public Criteria andOuterchainNameLessThan(String value) {
            addCriterion("outerchain_name <", value, "outerchainName");
            return (Criteria) this;
        }

        public Criteria andOuterchainNameLessThanOrEqualTo(String value) {
            addCriterion("outerchain_name <=", value, "outerchainName");
            return (Criteria) this;
        }

        public Criteria andOuterchainNameLike(String value) {
            addCriterion("outerchain_name like", value, "outerchainName");
            return (Criteria) this;
        }

        public Criteria andOuterchainNameNotLike(String value) {
            addCriterion("outerchain_name not like", value, "outerchainName");
            return (Criteria) this;
        }

        public Criteria andOuterchainNameIn(List<String> values) {
            addCriterion("outerchain_name in", values, "outerchainName");
            return (Criteria) this;
        }

        public Criteria andOuterchainNameNotIn(List<String> values) {
            addCriterion("outerchain_name not in", values, "outerchainName");
            return (Criteria) this;
        }

        public Criteria andOuterchainNameBetween(String value1, String value2) {
            addCriterion("outerchain_name between", value1, value2, "outerchainName");
            return (Criteria) this;
        }

        public Criteria andOuterchainNameNotBetween(String value1, String value2) {
            addCriterion("outerchain_name not between", value1, value2, "outerchainName");
            return (Criteria) this;
        }

        public Criteria andOuterchainUpdateTimeIsNull() {
            addCriterion("outerchain_update_time is null");
            return (Criteria) this;
        }

        public Criteria andOuterchainUpdateTimeIsNotNull() {
            addCriterion("outerchain_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andOuterchainUpdateTimeEqualTo(Date value) {
            addCriterion("outerchain_update_time =", value, "outerchainUpdateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainUpdateTimeNotEqualTo(Date value) {
            addCriterion("outerchain_update_time <>", value, "outerchainUpdateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainUpdateTimeGreaterThan(Date value) {
            addCriterion("outerchain_update_time >", value, "outerchainUpdateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("outerchain_update_time >=", value, "outerchainUpdateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainUpdateTimeLessThan(Date value) {
            addCriterion("outerchain_update_time <", value, "outerchainUpdateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("outerchain_update_time <=", value, "outerchainUpdateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainUpdateTimeIn(List<Date> values) {
            addCriterion("outerchain_update_time in", values, "outerchainUpdateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainUpdateTimeNotIn(List<Date> values) {
            addCriterion("outerchain_update_time not in", values, "outerchainUpdateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("outerchain_update_time between", value1, value2, "outerchainUpdateTime");
            return (Criteria) this;
        }

        public Criteria andOuterchainUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("outerchain_update_time not between", value1, value2, "outerchainUpdateTime");
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