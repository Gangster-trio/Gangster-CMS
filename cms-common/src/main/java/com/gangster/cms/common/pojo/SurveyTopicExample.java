package com.gangster.cms.common.pojo;

import java.util.ArrayList;
import java.util.List;

public class SurveyTopicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SurveyTopicExample() {
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

        public Criteria andTopicIdIsNull() {
            addCriterion("topic_id is null");
            return (Criteria) this;
        }

        public Criteria andTopicIdIsNotNull() {
            addCriterion("topic_id is not null");
            return (Criteria) this;
        }

        public Criteria andTopicIdEqualTo(Integer value) {
            addCriterion("topic_id =", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotEqualTo(Integer value) {
            addCriterion("topic_id <>", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdGreaterThan(Integer value) {
            addCriterion("topic_id >", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("topic_id >=", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdLessThan(Integer value) {
            addCriterion("topic_id <", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdLessThanOrEqualTo(Integer value) {
            addCriterion("topic_id <=", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdIn(List<Integer> values) {
            addCriterion("topic_id in", values, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotIn(List<Integer> values) {
            addCriterion("topic_id not in", values, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdBetween(Integer value1, Integer value2) {
            addCriterion("topic_id between", value1, value2, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotBetween(Integer value1, Integer value2) {
            addCriterion("topic_id not between", value1, value2, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicQuestionIsNull() {
            addCriterion("topic_question is null");
            return (Criteria) this;
        }

        public Criteria andTopicQuestionIsNotNull() {
            addCriterion("topic_question is not null");
            return (Criteria) this;
        }

        public Criteria andTopicQuestionEqualTo(String value) {
            addCriterion("topic_question =", value, "topicQuestion");
            return (Criteria) this;
        }

        public Criteria andTopicQuestionNotEqualTo(String value) {
            addCriterion("topic_question <>", value, "topicQuestion");
            return (Criteria) this;
        }

        public Criteria andTopicQuestionGreaterThan(String value) {
            addCriterion("topic_question >", value, "topicQuestion");
            return (Criteria) this;
        }

        public Criteria andTopicQuestionGreaterThanOrEqualTo(String value) {
            addCriterion("topic_question >=", value, "topicQuestion");
            return (Criteria) this;
        }

        public Criteria andTopicQuestionLessThan(String value) {
            addCriterion("topic_question <", value, "topicQuestion");
            return (Criteria) this;
        }

        public Criteria andTopicQuestionLessThanOrEqualTo(String value) {
            addCriterion("topic_question <=", value, "topicQuestion");
            return (Criteria) this;
        }

        public Criteria andTopicQuestionLike(String value) {
            addCriterion("topic_question like", value, "topicQuestion");
            return (Criteria) this;
        }

        public Criteria andTopicQuestionNotLike(String value) {
            addCriterion("topic_question not like", value, "topicQuestion");
            return (Criteria) this;
        }

        public Criteria andTopicQuestionIn(List<String> values) {
            addCriterion("topic_question in", values, "topicQuestion");
            return (Criteria) this;
        }

        public Criteria andTopicQuestionNotIn(List<String> values) {
            addCriterion("topic_question not in", values, "topicQuestion");
            return (Criteria) this;
        }

        public Criteria andTopicQuestionBetween(String value1, String value2) {
            addCriterion("topic_question between", value1, value2, "topicQuestion");
            return (Criteria) this;
        }

        public Criteria andTopicQuestionNotBetween(String value1, String value2) {
            addCriterion("topic_question not between", value1, value2, "topicQuestion");
            return (Criteria) this;
        }

        public Criteria andTopicTypeIsNull() {
            addCriterion("topic_type is null");
            return (Criteria) this;
        }

        public Criteria andTopicTypeIsNotNull() {
            addCriterion("topic_type is not null");
            return (Criteria) this;
        }

        public Criteria andTopicTypeEqualTo(String value) {
            addCriterion("topic_type =", value, "topicType");
            return (Criteria) this;
        }

        public Criteria andTopicTypeNotEqualTo(String value) {
            addCriterion("topic_type <>", value, "topicType");
            return (Criteria) this;
        }

        public Criteria andTopicTypeGreaterThan(String value) {
            addCriterion("topic_type >", value, "topicType");
            return (Criteria) this;
        }

        public Criteria andTopicTypeGreaterThanOrEqualTo(String value) {
            addCriterion("topic_type >=", value, "topicType");
            return (Criteria) this;
        }

        public Criteria andTopicTypeLessThan(String value) {
            addCriterion("topic_type <", value, "topicType");
            return (Criteria) this;
        }

        public Criteria andTopicTypeLessThanOrEqualTo(String value) {
            addCriterion("topic_type <=", value, "topicType");
            return (Criteria) this;
        }

        public Criteria andTopicTypeLike(String value) {
            addCriterion("topic_type like", value, "topicType");
            return (Criteria) this;
        }

        public Criteria andTopicTypeNotLike(String value) {
            addCriterion("topic_type not like", value, "topicType");
            return (Criteria) this;
        }

        public Criteria andTopicTypeIn(List<String> values) {
            addCriterion("topic_type in", values, "topicType");
            return (Criteria) this;
        }

        public Criteria andTopicTypeNotIn(List<String> values) {
            addCriterion("topic_type not in", values, "topicType");
            return (Criteria) this;
        }

        public Criteria andTopicTypeBetween(String value1, String value2) {
            addCriterion("topic_type between", value1, value2, "topicType");
            return (Criteria) this;
        }

        public Criteria andTopicTypeNotBetween(String value1, String value2) {
            addCriterion("topic_type not between", value1, value2, "topicType");
            return (Criteria) this;
        }

        public Criteria andTopicPageIdIsNull() {
            addCriterion("topic_page_id is null");
            return (Criteria) this;
        }

        public Criteria andTopicPageIdIsNotNull() {
            addCriterion("topic_page_id is not null");
            return (Criteria) this;
        }

        public Criteria andTopicPageIdEqualTo(Integer value) {
            addCriterion("topic_page_id =", value, "topicPageId");
            return (Criteria) this;
        }

        public Criteria andTopicPageIdNotEqualTo(Integer value) {
            addCriterion("topic_page_id <>", value, "topicPageId");
            return (Criteria) this;
        }

        public Criteria andTopicPageIdGreaterThan(Integer value) {
            addCriterion("topic_page_id >", value, "topicPageId");
            return (Criteria) this;
        }

        public Criteria andTopicPageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("topic_page_id >=", value, "topicPageId");
            return (Criteria) this;
        }

        public Criteria andTopicPageIdLessThan(Integer value) {
            addCriterion("topic_page_id <", value, "topicPageId");
            return (Criteria) this;
        }

        public Criteria andTopicPageIdLessThanOrEqualTo(Integer value) {
            addCriterion("topic_page_id <=", value, "topicPageId");
            return (Criteria) this;
        }

        public Criteria andTopicPageIdIn(List<Integer> values) {
            addCriterion("topic_page_id in", values, "topicPageId");
            return (Criteria) this;
        }

        public Criteria andTopicPageIdNotIn(List<Integer> values) {
            addCriterion("topic_page_id not in", values, "topicPageId");
            return (Criteria) this;
        }

        public Criteria andTopicPageIdBetween(Integer value1, Integer value2) {
            addCriterion("topic_page_id between", value1, value2, "topicPageId");
            return (Criteria) this;
        }

        public Criteria andTopicPageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("topic_page_id not between", value1, value2, "topicPageId");
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