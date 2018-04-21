package com.gangster.cms.common.pojo;

import java.util.ArrayList;
import java.util.Date;
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

        public Criteria andFileIdIsNull() {
            addCriterion("file_id is null");
            return (Criteria) this;
        }

        public Criteria andFileIdIsNotNull() {
            addCriterion("file_id is not null");
            return (Criteria) this;
        }

        public Criteria andFileIdEqualTo(Integer value) {
            addCriterion("file_id =", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotEqualTo(Integer value) {
            addCriterion("file_id <>", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThan(Integer value) {
            addCriterion("file_id >", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("file_id >=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThan(Integer value) {
            addCriterion("file_id <", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThanOrEqualTo(Integer value) {
            addCriterion("file_id <=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdIn(List<Integer> values) {
            addCriterion("file_id in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotIn(List<Integer> values) {
            addCriterion("file_id not in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdBetween(Integer value1, Integer value2) {
            addCriterion("file_id between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotBetween(Integer value1, Integer value2) {
            addCriterion("file_id not between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("file_name =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("file_name >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("file_name <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("file_name like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("file_name not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("file_name in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileCreatedIsNull() {
            addCriterion("file_created is null");
            return (Criteria) this;
        }

        public Criteria andFileCreatedIsNotNull() {
            addCriterion("file_created is not null");
            return (Criteria) this;
        }

        public Criteria andFileCreatedEqualTo(Date value) {
            addCriterion("file_created =", value, "fileCreated");
            return (Criteria) this;
        }

        public Criteria andFileCreatedNotEqualTo(Date value) {
            addCriterion("file_created <>", value, "fileCreated");
            return (Criteria) this;
        }

        public Criteria andFileCreatedGreaterThan(Date value) {
            addCriterion("file_created >", value, "fileCreated");
            return (Criteria) this;
        }

        public Criteria andFileCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("file_created >=", value, "fileCreated");
            return (Criteria) this;
        }

        public Criteria andFileCreatedLessThan(Date value) {
            addCriterion("file_created <", value, "fileCreated");
            return (Criteria) this;
        }

        public Criteria andFileCreatedLessThanOrEqualTo(Date value) {
            addCriterion("file_created <=", value, "fileCreated");
            return (Criteria) this;
        }

        public Criteria andFileCreatedIn(List<Date> values) {
            addCriterion("file_created in", values, "fileCreated");
            return (Criteria) this;
        }

        public Criteria andFileCreatedNotIn(List<Date> values) {
            addCriterion("file_created not in", values, "fileCreated");
            return (Criteria) this;
        }

        public Criteria andFileCreatedBetween(Date value1, Date value2) {
            addCriterion("file_created between", value1, value2, "fileCreated");
            return (Criteria) this;
        }

        public Criteria andFileCreatedNotBetween(Date value1, Date value2) {
            addCriterion("file_created not between", value1, value2, "fileCreated");
            return (Criteria) this;
        }

        public Criteria andFileDownCountIsNull() {
            addCriterion("file_down_count is null");
            return (Criteria) this;
        }

        public Criteria andFileDownCountIsNotNull() {
            addCriterion("file_down_count is not null");
            return (Criteria) this;
        }

        public Criteria andFileDownCountEqualTo(Integer value) {
            addCriterion("file_down_count =", value, "fileDownCount");
            return (Criteria) this;
        }

        public Criteria andFileDownCountNotEqualTo(Integer value) {
            addCriterion("file_down_count <>", value, "fileDownCount");
            return (Criteria) this;
        }

        public Criteria andFileDownCountGreaterThan(Integer value) {
            addCriterion("file_down_count >", value, "fileDownCount");
            return (Criteria) this;
        }

        public Criteria andFileDownCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("file_down_count >=", value, "fileDownCount");
            return (Criteria) this;
        }

        public Criteria andFileDownCountLessThan(Integer value) {
            addCriterion("file_down_count <", value, "fileDownCount");
            return (Criteria) this;
        }

        public Criteria andFileDownCountLessThanOrEqualTo(Integer value) {
            addCriterion("file_down_count <=", value, "fileDownCount");
            return (Criteria) this;
        }

        public Criteria andFileDownCountIn(List<Integer> values) {
            addCriterion("file_down_count in", values, "fileDownCount");
            return (Criteria) this;
        }

        public Criteria andFileDownCountNotIn(List<Integer> values) {
            addCriterion("file_down_count not in", values, "fileDownCount");
            return (Criteria) this;
        }

        public Criteria andFileDownCountBetween(Integer value1, Integer value2) {
            addCriterion("file_down_count between", value1, value2, "fileDownCount");
            return (Criteria) this;
        }

        public Criteria andFileDownCountNotBetween(Integer value1, Integer value2) {
            addCriterion("file_down_count not between", value1, value2, "fileDownCount");
            return (Criteria) this;
        }

        public Criteria andFileTypeIsNull() {
            addCriterion("file_type is null");
            return (Criteria) this;
        }

        public Criteria andFileTypeIsNotNull() {
            addCriterion("file_type is not null");
            return (Criteria) this;
        }

        public Criteria andFileTypeEqualTo(String value) {
            addCriterion("file_type =", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotEqualTo(String value) {
            addCriterion("file_type <>", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThan(String value) {
            addCriterion("file_type >", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThanOrEqualTo(String value) {
            addCriterion("file_type >=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThan(String value) {
            addCriterion("file_type <", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThanOrEqualTo(String value) {
            addCriterion("file_type <=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLike(String value) {
            addCriterion("file_type like", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotLike(String value) {
            addCriterion("file_type not like", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeIn(List<String> values) {
            addCriterion("file_type in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotIn(List<String> values) {
            addCriterion("file_type not in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeBetween(String value1, String value2) {
            addCriterion("file_type between", value1, value2, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotBetween(String value1, String value2) {
            addCriterion("file_type not between", value1, value2, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNull() {
            addCriterion("file_size is null");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNotNull() {
            addCriterion("file_size is not null");
            return (Criteria) this;
        }

        public Criteria andFileSizeEqualTo(String value) {
            addCriterion("file_size =", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotEqualTo(String value) {
            addCriterion("file_size <>", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThan(String value) {
            addCriterion("file_size >", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThanOrEqualTo(String value) {
            addCriterion("file_size >=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThan(String value) {
            addCriterion("file_size <", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThanOrEqualTo(String value) {
            addCriterion("file_size <=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLike(String value) {
            addCriterion("file_size like", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotLike(String value) {
            addCriterion("file_size not like", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeIn(List<String> values) {
            addCriterion("file_size in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotIn(List<String> values) {
            addCriterion("file_size not in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeBetween(String value1, String value2) {
            addCriterion("file_size between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotBetween(String value1, String value2) {
            addCriterion("file_size not between", value1, value2, "fileSize");
            return (Criteria) this;
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