package com.gangster.cms.common.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SurveyPageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SurveyPageExample() {
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

        public Criteria andPageIdIsNull() {
            addCriterion("page_id is null");
            return (Criteria) this;
        }

        public Criteria andPageIdIsNotNull() {
            addCriterion("page_id is not null");
            return (Criteria) this;
        }

        public Criteria andPageIdEqualTo(Integer value) {
            addCriterion("page_id =", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdNotEqualTo(Integer value) {
            addCriterion("page_id <>", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdGreaterThan(Integer value) {
            addCriterion("page_id >", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("page_id >=", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdLessThan(Integer value) {
            addCriterion("page_id <", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdLessThanOrEqualTo(Integer value) {
            addCriterion("page_id <=", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdIn(List<Integer> values) {
            addCriterion("page_id in", values, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdNotIn(List<Integer> values) {
            addCriterion("page_id not in", values, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdBetween(Integer value1, Integer value2) {
            addCriterion("page_id between", value1, value2, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("page_id not between", value1, value2, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageTitleIsNull() {
            addCriterion("page_title is null");
            return (Criteria) this;
        }

        public Criteria andPageTitleIsNotNull() {
            addCriterion("page_title is not null");
            return (Criteria) this;
        }

        public Criteria andPageTitleEqualTo(String value) {
            addCriterion("page_title =", value, "pageTitle");
            return (Criteria) this;
        }

        public Criteria andPageTitleNotEqualTo(String value) {
            addCriterion("page_title <>", value, "pageTitle");
            return (Criteria) this;
        }

        public Criteria andPageTitleGreaterThan(String value) {
            addCriterion("page_title >", value, "pageTitle");
            return (Criteria) this;
        }

        public Criteria andPageTitleGreaterThanOrEqualTo(String value) {
            addCriterion("page_title >=", value, "pageTitle");
            return (Criteria) this;
        }

        public Criteria andPageTitleLessThan(String value) {
            addCriterion("page_title <", value, "pageTitle");
            return (Criteria) this;
        }

        public Criteria andPageTitleLessThanOrEqualTo(String value) {
            addCriterion("page_title <=", value, "pageTitle");
            return (Criteria) this;
        }

        public Criteria andPageTitleLike(String value) {
            addCriterion("page_title like", value, "pageTitle");
            return (Criteria) this;
        }

        public Criteria andPageTitleNotLike(String value) {
            addCriterion("page_title not like", value, "pageTitle");
            return (Criteria) this;
        }

        public Criteria andPageTitleIn(List<String> values) {
            addCriterion("page_title in", values, "pageTitle");
            return (Criteria) this;
        }

        public Criteria andPageTitleNotIn(List<String> values) {
            addCriterion("page_title not in", values, "pageTitle");
            return (Criteria) this;
        }

        public Criteria andPageTitleBetween(String value1, String value2) {
            addCriterion("page_title between", value1, value2, "pageTitle");
            return (Criteria) this;
        }

        public Criteria andPageTitleNotBetween(String value1, String value2) {
            addCriterion("page_title not between", value1, value2, "pageTitle");
            return (Criteria) this;
        }

        public Criteria andPageCreateTimeIsNull() {
            addCriterion("page_create_time is null");
            return (Criteria) this;
        }

        public Criteria andPageCreateTimeIsNotNull() {
            addCriterion("page_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andPageCreateTimeEqualTo(Date value) {
            addCriterion("page_create_time =", value, "pageCreateTime");
            return (Criteria) this;
        }

        public Criteria andPageCreateTimeNotEqualTo(Date value) {
            addCriterion("page_create_time <>", value, "pageCreateTime");
            return (Criteria) this;
        }

        public Criteria andPageCreateTimeGreaterThan(Date value) {
            addCriterion("page_create_time >", value, "pageCreateTime");
            return (Criteria) this;
        }

        public Criteria andPageCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("page_create_time >=", value, "pageCreateTime");
            return (Criteria) this;
        }

        public Criteria andPageCreateTimeLessThan(Date value) {
            addCriterion("page_create_time <", value, "pageCreateTime");
            return (Criteria) this;
        }

        public Criteria andPageCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("page_create_time <=", value, "pageCreateTime");
            return (Criteria) this;
        }

        public Criteria andPageCreateTimeIn(List<Date> values) {
            addCriterion("page_create_time in", values, "pageCreateTime");
            return (Criteria) this;
        }

        public Criteria andPageCreateTimeNotIn(List<Date> values) {
            addCriterion("page_create_time not in", values, "pageCreateTime");
            return (Criteria) this;
        }

        public Criteria andPageCreateTimeBetween(Date value1, Date value2) {
            addCriterion("page_create_time between", value1, value2, "pageCreateTime");
            return (Criteria) this;
        }

        public Criteria andPageCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("page_create_time not between", value1, value2, "pageCreateTime");
            return (Criteria) this;
        }

        public Criteria andPageEndTimeIsNull() {
            addCriterion("page_end_time is null");
            return (Criteria) this;
        }

        public Criteria andPageEndTimeIsNotNull() {
            addCriterion("page_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andPageEndTimeEqualTo(Date value) {
            addCriterion("page_end_time =", value, "pageEndTime");
            return (Criteria) this;
        }

        public Criteria andPageEndTimeNotEqualTo(Date value) {
            addCriterion("page_end_time <>", value, "pageEndTime");
            return (Criteria) this;
        }

        public Criteria andPageEndTimeGreaterThan(Date value) {
            addCriterion("page_end_time >", value, "pageEndTime");
            return (Criteria) this;
        }

        public Criteria andPageEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("page_end_time >=", value, "pageEndTime");
            return (Criteria) this;
        }

        public Criteria andPageEndTimeLessThan(Date value) {
            addCriterion("page_end_time <", value, "pageEndTime");
            return (Criteria) this;
        }

        public Criteria andPageEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("page_end_time <=", value, "pageEndTime");
            return (Criteria) this;
        }

        public Criteria andPageEndTimeIn(List<Date> values) {
            addCriterion("page_end_time in", values, "pageEndTime");
            return (Criteria) this;
        }

        public Criteria andPageEndTimeNotIn(List<Date> values) {
            addCriterion("page_end_time not in", values, "pageEndTime");
            return (Criteria) this;
        }

        public Criteria andPageEndTimeBetween(Date value1, Date value2) {
            addCriterion("page_end_time between", value1, value2, "pageEndTime");
            return (Criteria) this;
        }

        public Criteria andPageEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("page_end_time not between", value1, value2, "pageEndTime");
            return (Criteria) this;
        }

        public Criteria andPageSiteIdIsNull() {
            addCriterion("page_site_id is null");
            return (Criteria) this;
        }

        public Criteria andPageSiteIdIsNotNull() {
            addCriterion("page_site_id is not null");
            return (Criteria) this;
        }

        public Criteria andPageSiteIdEqualTo(Integer value) {
            addCriterion("page_site_id =", value, "pageSiteId");
            return (Criteria) this;
        }

        public Criteria andPageSiteIdNotEqualTo(Integer value) {
            addCriterion("page_site_id <>", value, "pageSiteId");
            return (Criteria) this;
        }

        public Criteria andPageSiteIdGreaterThan(Integer value) {
            addCriterion("page_site_id >", value, "pageSiteId");
            return (Criteria) this;
        }

        public Criteria andPageSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("page_site_id >=", value, "pageSiteId");
            return (Criteria) this;
        }

        public Criteria andPageSiteIdLessThan(Integer value) {
            addCriterion("page_site_id <", value, "pageSiteId");
            return (Criteria) this;
        }

        public Criteria andPageSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("page_site_id <=", value, "pageSiteId");
            return (Criteria) this;
        }

        public Criteria andPageSiteIdIn(List<Integer> values) {
            addCriterion("page_site_id in", values, "pageSiteId");
            return (Criteria) this;
        }

        public Criteria andPageSiteIdNotIn(List<Integer> values) {
            addCriterion("page_site_id not in", values, "pageSiteId");
            return (Criteria) this;
        }

        public Criteria andPageSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("page_site_id between", value1, value2, "pageSiteId");
            return (Criteria) this;
        }

        public Criteria andPageSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("page_site_id not between", value1, value2, "pageSiteId");
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