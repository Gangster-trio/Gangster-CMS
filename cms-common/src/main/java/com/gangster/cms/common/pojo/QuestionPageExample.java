package com.gangster.cms.common.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionPageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public QuestionPageExample() {
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

        public Criteria andQuestionPageIdIsNull() {
            addCriterion("question_page_id is null");
            return (Criteria) this;
        }

        public Criteria andQuestionPageIdIsNotNull() {
            addCriterion("question_page_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionPageIdEqualTo(Integer value) {
            addCriterion("question_page_id =", value, "questionPageId");
            return (Criteria) this;
        }

        public Criteria andQuestionPageIdNotEqualTo(Integer value) {
            addCriterion("question_page_id <>", value, "questionPageId");
            return (Criteria) this;
        }

        public Criteria andQuestionPageIdGreaterThan(Integer value) {
            addCriterion("question_page_id >", value, "questionPageId");
            return (Criteria) this;
        }

        public Criteria andQuestionPageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("question_page_id >=", value, "questionPageId");
            return (Criteria) this;
        }

        public Criteria andQuestionPageIdLessThan(Integer value) {
            addCriterion("question_page_id <", value, "questionPageId");
            return (Criteria) this;
        }

        public Criteria andQuestionPageIdLessThanOrEqualTo(Integer value) {
            addCriterion("question_page_id <=", value, "questionPageId");
            return (Criteria) this;
        }

        public Criteria andQuestionPageIdIn(List<Integer> values) {
            addCriterion("question_page_id in", values, "questionPageId");
            return (Criteria) this;
        }

        public Criteria andQuestionPageIdNotIn(List<Integer> values) {
            addCriterion("question_page_id not in", values, "questionPageId");
            return (Criteria) this;
        }

        public Criteria andQuestionPageIdBetween(Integer value1, Integer value2) {
            addCriterion("question_page_id between", value1, value2, "questionPageId");
            return (Criteria) this;
        }

        public Criteria andQuestionPageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("question_page_id not between", value1, value2, "questionPageId");
            return (Criteria) this;
        }

        public Criteria andQuestionPageTitleIsNull() {
            addCriterion("question_page_title is null");
            return (Criteria) this;
        }

        public Criteria andQuestionPageTitleIsNotNull() {
            addCriterion("question_page_title is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionPageTitleEqualTo(String value) {
            addCriterion("question_page_title =", value, "questionPageTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionPageTitleNotEqualTo(String value) {
            addCriterion("question_page_title <>", value, "questionPageTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionPageTitleGreaterThan(String value) {
            addCriterion("question_page_title >", value, "questionPageTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionPageTitleGreaterThanOrEqualTo(String value) {
            addCriterion("question_page_title >=", value, "questionPageTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionPageTitleLessThan(String value) {
            addCriterion("question_page_title <", value, "questionPageTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionPageTitleLessThanOrEqualTo(String value) {
            addCriterion("question_page_title <=", value, "questionPageTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionPageTitleLike(String value) {
            addCriterion("question_page_title like", value, "questionPageTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionPageTitleNotLike(String value) {
            addCriterion("question_page_title not like", value, "questionPageTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionPageTitleIn(List<String> values) {
            addCriterion("question_page_title in", values, "questionPageTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionPageTitleNotIn(List<String> values) {
            addCriterion("question_page_title not in", values, "questionPageTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionPageTitleBetween(String value1, String value2) {
            addCriterion("question_page_title between", value1, value2, "questionPageTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionPageTitleNotBetween(String value1, String value2) {
            addCriterion("question_page_title not between", value1, value2, "questionPageTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionPageCreateTimeIsNull() {
            addCriterion("question_page_create_time is null");
            return (Criteria) this;
        }

        public Criteria andQuestionPageCreateTimeIsNotNull() {
            addCriterion("question_page_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionPageCreateTimeEqualTo(Date value) {
            addCriterion("question_page_create_time =", value, "questionPageCreateTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageCreateTimeNotEqualTo(Date value) {
            addCriterion("question_page_create_time <>", value, "questionPageCreateTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageCreateTimeGreaterThan(Date value) {
            addCriterion("question_page_create_time >", value, "questionPageCreateTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("question_page_create_time >=", value, "questionPageCreateTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageCreateTimeLessThan(Date value) {
            addCriterion("question_page_create_time <", value, "questionPageCreateTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("question_page_create_time <=", value, "questionPageCreateTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageCreateTimeIn(List<Date> values) {
            addCriterion("question_page_create_time in", values, "questionPageCreateTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageCreateTimeNotIn(List<Date> values) {
            addCriterion("question_page_create_time not in", values, "questionPageCreateTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageCreateTimeBetween(Date value1, Date value2) {
            addCriterion("question_page_create_time between", value1, value2, "questionPageCreateTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("question_page_create_time not between", value1, value2, "questionPageCreateTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageEndTimeIsNull() {
            addCriterion("question_page_end_time is null");
            return (Criteria) this;
        }

        public Criteria andQuestionPageEndTimeIsNotNull() {
            addCriterion("question_page_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionPageEndTimeEqualTo(Date value) {
            addCriterion("question_page_end_time =", value, "questionPageEndTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageEndTimeNotEqualTo(Date value) {
            addCriterion("question_page_end_time <>", value, "questionPageEndTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageEndTimeGreaterThan(Date value) {
            addCriterion("question_page_end_time >", value, "questionPageEndTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("question_page_end_time >=", value, "questionPageEndTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageEndTimeLessThan(Date value) {
            addCriterion("question_page_end_time <", value, "questionPageEndTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("question_page_end_time <=", value, "questionPageEndTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageEndTimeIn(List<Date> values) {
            addCriterion("question_page_end_time in", values, "questionPageEndTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageEndTimeNotIn(List<Date> values) {
            addCriterion("question_page_end_time not in", values, "questionPageEndTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageEndTimeBetween(Date value1, Date value2) {
            addCriterion("question_page_end_time between", value1, value2, "questionPageEndTime");
            return (Criteria) this;
        }

        public Criteria andQuestionPageEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("question_page_end_time not between", value1, value2, "questionPageEndTime");
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