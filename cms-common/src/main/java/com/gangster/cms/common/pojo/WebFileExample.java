package com.gangster.cms.common.pojo;

import java.util.ArrayList;
import java.util.List;

public class WebFileExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WebFileExample() {
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

        public Criteria andFileArticleIdIsNull() {
            addCriterion("file_article_id is null");
            return (Criteria) this;
        }

        public Criteria andFileArticleIdIsNotNull() {
            addCriterion("file_article_id is not null");
            return (Criteria) this;
        }

        public Criteria andFileArticleIdEqualTo(Integer value) {
            addCriterion("file_article_id =", value, "fileArticleId");
            return (Criteria) this;
        }

        public Criteria andFileArticleIdNotEqualTo(Integer value) {
            addCriterion("file_article_id <>", value, "fileArticleId");
            return (Criteria) this;
        }

        public Criteria andFileArticleIdGreaterThan(Integer value) {
            addCriterion("file_article_id >", value, "fileArticleId");
            return (Criteria) this;
        }

        public Criteria andFileArticleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("file_article_id >=", value, "fileArticleId");
            return (Criteria) this;
        }

        public Criteria andFileArticleIdLessThan(Integer value) {
            addCriterion("file_article_id <", value, "fileArticleId");
            return (Criteria) this;
        }

        public Criteria andFileArticleIdLessThanOrEqualTo(Integer value) {
            addCriterion("file_article_id <=", value, "fileArticleId");
            return (Criteria) this;
        }

        public Criteria andFileArticleIdIn(List<Integer> values) {
            addCriterion("file_article_id in", values, "fileArticleId");
            return (Criteria) this;
        }

        public Criteria andFileArticleIdNotIn(List<Integer> values) {
            addCriterion("file_article_id not in", values, "fileArticleId");
            return (Criteria) this;
        }

        public Criteria andFileArticleIdBetween(Integer value1, Integer value2) {
            addCriterion("file_article_id between", value1, value2, "fileArticleId");
            return (Criteria) this;
        }

        public Criteria andFileArticleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("file_article_id not between", value1, value2, "fileArticleId");
            return (Criteria) this;
        }

        public Criteria andFileKeyIsNull() {
            addCriterion("file_key is null");
            return (Criteria) this;
        }

        public Criteria andFileKeyIsNotNull() {
            addCriterion("file_key is not null");
            return (Criteria) this;
        }

        public Criteria andFileKeyEqualTo(String value) {
            addCriterion("file_key =", value, "fileKey");
            return (Criteria) this;
        }

        public Criteria andFileKeyNotEqualTo(String value) {
            addCriterion("file_key <>", value, "fileKey");
            return (Criteria) this;
        }

        public Criteria andFileKeyGreaterThan(String value) {
            addCriterion("file_key >", value, "fileKey");
            return (Criteria) this;
        }

        public Criteria andFileKeyGreaterThanOrEqualTo(String value) {
            addCriterion("file_key >=", value, "fileKey");
            return (Criteria) this;
        }

        public Criteria andFileKeyLessThan(String value) {
            addCriterion("file_key <", value, "fileKey");
            return (Criteria) this;
        }

        public Criteria andFileKeyLessThanOrEqualTo(String value) {
            addCriterion("file_key <=", value, "fileKey");
            return (Criteria) this;
        }

        public Criteria andFileKeyLike(String value) {
            addCriterion("file_key like", value, "fileKey");
            return (Criteria) this;
        }

        public Criteria andFileKeyNotLike(String value) {
            addCriterion("file_key not like", value, "fileKey");
            return (Criteria) this;
        }

        public Criteria andFileKeyIn(List<String> values) {
            addCriterion("file_key in", values, "fileKey");
            return (Criteria) this;
        }

        public Criteria andFileKeyNotIn(List<String> values) {
            addCriterion("file_key not in", values, "fileKey");
            return (Criteria) this;
        }

        public Criteria andFileKeyBetween(String value1, String value2) {
            addCriterion("file_key between", value1, value2, "fileKey");
            return (Criteria) this;
        }

        public Criteria andFileKeyNotBetween(String value1, String value2) {
            addCriterion("file_key not between", value1, value2, "fileKey");
            return (Criteria) this;
        }

        public Criteria andFileSiteIdIsNull() {
            addCriterion("file_site_id is null");
            return (Criteria) this;
        }

        public Criteria andFileSiteIdIsNotNull() {
            addCriterion("file_site_id is not null");
            return (Criteria) this;
        }

        public Criteria andFileSiteIdEqualTo(Integer value) {
            addCriterion("file_site_id =", value, "fileSiteId");
            return (Criteria) this;
        }

        public Criteria andFileSiteIdNotEqualTo(Integer value) {
            addCriterion("file_site_id <>", value, "fileSiteId");
            return (Criteria) this;
        }

        public Criteria andFileSiteIdGreaterThan(Integer value) {
            addCriterion("file_site_id >", value, "fileSiteId");
            return (Criteria) this;
        }

        public Criteria andFileSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("file_site_id >=", value, "fileSiteId");
            return (Criteria) this;
        }

        public Criteria andFileSiteIdLessThan(Integer value) {
            addCriterion("file_site_id <", value, "fileSiteId");
            return (Criteria) this;
        }

        public Criteria andFileSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("file_site_id <=", value, "fileSiteId");
            return (Criteria) this;
        }

        public Criteria andFileSiteIdIn(List<Integer> values) {
            addCriterion("file_site_id in", values, "fileSiteId");
            return (Criteria) this;
        }

        public Criteria andFileSiteIdNotIn(List<Integer> values) {
            addCriterion("file_site_id not in", values, "fileSiteId");
            return (Criteria) this;
        }

        public Criteria andFileSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("file_site_id between", value1, value2, "fileSiteId");
            return (Criteria) this;
        }

        public Criteria andFileSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("file_site_id not between", value1, value2, "fileSiteId");
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