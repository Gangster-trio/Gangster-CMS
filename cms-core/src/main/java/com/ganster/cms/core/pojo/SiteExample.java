package com.ganster.cms.core.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SiteExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SiteExample() {
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

        public Criteria andSiteIdIsNull() {
            addCriterion("site_id is null");
            return (Criteria) this;
        }

        public Criteria andSiteIdIsNotNull() {
            addCriterion("site_id is not null");
            return (Criteria) this;
        }

        public Criteria andSiteIdEqualTo(Integer value) {
            addCriterion("site_id =", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotEqualTo(Integer value) {
            addCriterion("site_id <>", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThan(Integer value) {
            addCriterion("site_id >", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("site_id >=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThan(Integer value) {
            addCriterion("site_id <", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("site_id <=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdIn(List<Integer> values) {
            addCriterion("site_id in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotIn(List<Integer> values) {
            addCriterion("site_id not in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("site_id between", value1, value2, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("site_id not between", value1, value2, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteNameIsNull() {
            addCriterion("site_name is null");
            return (Criteria) this;
        }

        public Criteria andSiteNameIsNotNull() {
            addCriterion("site_name is not null");
            return (Criteria) this;
        }

        public Criteria andSiteNameEqualTo(String value) {
            addCriterion("site_name =", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotEqualTo(String value) {
            addCriterion("site_name <>", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameGreaterThan(String value) {
            addCriterion("site_name >", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameGreaterThanOrEqualTo(String value) {
            addCriterion("site_name >=", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameLessThan(String value) {
            addCriterion("site_name <", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameLessThanOrEqualTo(String value) {
            addCriterion("site_name <=", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameLike(String value) {
            addCriterion("site_name like", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotLike(String value) {
            addCriterion("site_name not like", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameIn(List<String> values) {
            addCriterion("site_name in", values, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotIn(List<String> values) {
            addCriterion("site_name not in", values, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameBetween(String value1, String value2) {
            addCriterion("site_name between", value1, value2, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotBetween(String value1, String value2) {
            addCriterion("site_name not between", value1, value2, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteUrlIsNull() {
            addCriterion("site_url is null");
            return (Criteria) this;
        }

        public Criteria andSiteUrlIsNotNull() {
            addCriterion("site_url is not null");
            return (Criteria) this;
        }

        public Criteria andSiteUrlEqualTo(String value) {
            addCriterion("site_url =", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlNotEqualTo(String value) {
            addCriterion("site_url <>", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlGreaterThan(String value) {
            addCriterion("site_url >", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlGreaterThanOrEqualTo(String value) {
            addCriterion("site_url >=", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlLessThan(String value) {
            addCriterion("site_url <", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlLessThanOrEqualTo(String value) {
            addCriterion("site_url <=", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlLike(String value) {
            addCriterion("site_url like", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlNotLike(String value) {
            addCriterion("site_url not like", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlIn(List<String> values) {
            addCriterion("site_url in", values, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlNotIn(List<String> values) {
            addCriterion("site_url not in", values, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlBetween(String value1, String value2) {
            addCriterion("site_url between", value1, value2, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlNotBetween(String value1, String value2) {
            addCriterion("site_url not between", value1, value2, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteDescIsNull() {
            addCriterion("site_desc is null");
            return (Criteria) this;
        }

        public Criteria andSiteDescIsNotNull() {
            addCriterion("site_desc is not null");
            return (Criteria) this;
        }

        public Criteria andSiteDescEqualTo(String value) {
            addCriterion("site_desc =", value, "siteDesc");
            return (Criteria) this;
        }

        public Criteria andSiteDescNotEqualTo(String value) {
            addCriterion("site_desc <>", value, "siteDesc");
            return (Criteria) this;
        }

        public Criteria andSiteDescGreaterThan(String value) {
            addCriterion("site_desc >", value, "siteDesc");
            return (Criteria) this;
        }

        public Criteria andSiteDescGreaterThanOrEqualTo(String value) {
            addCriterion("site_desc >=", value, "siteDesc");
            return (Criteria) this;
        }

        public Criteria andSiteDescLessThan(String value) {
            addCriterion("site_desc <", value, "siteDesc");
            return (Criteria) this;
        }

        public Criteria andSiteDescLessThanOrEqualTo(String value) {
            addCriterion("site_desc <=", value, "siteDesc");
            return (Criteria) this;
        }

        public Criteria andSiteDescLike(String value) {
            addCriterion("site_desc like", value, "siteDesc");
            return (Criteria) this;
        }

        public Criteria andSiteDescNotLike(String value) {
            addCriterion("site_desc not like", value, "siteDesc");
            return (Criteria) this;
        }

        public Criteria andSiteDescIn(List<String> values) {
            addCriterion("site_desc in", values, "siteDesc");
            return (Criteria) this;
        }

        public Criteria andSiteDescNotIn(List<String> values) {
            addCriterion("site_desc not in", values, "siteDesc");
            return (Criteria) this;
        }

        public Criteria andSiteDescBetween(String value1, String value2) {
            addCriterion("site_desc between", value1, value2, "siteDesc");
            return (Criteria) this;
        }

        public Criteria andSiteDescNotBetween(String value1, String value2) {
            addCriterion("site_desc not between", value1, value2, "siteDesc");
            return (Criteria) this;
        }

        public Criteria andSiteCopyrightIsNull() {
            addCriterion("site_copyright is null");
            return (Criteria) this;
        }

        public Criteria andSiteCopyrightIsNotNull() {
            addCriterion("site_copyright is not null");
            return (Criteria) this;
        }

        public Criteria andSiteCopyrightEqualTo(String value) {
            addCriterion("site_copyright =", value, "siteCopyright");
            return (Criteria) this;
        }

        public Criteria andSiteCopyrightNotEqualTo(String value) {
            addCriterion("site_copyright <>", value, "siteCopyright");
            return (Criteria) this;
        }

        public Criteria andSiteCopyrightGreaterThan(String value) {
            addCriterion("site_copyright >", value, "siteCopyright");
            return (Criteria) this;
        }

        public Criteria andSiteCopyrightGreaterThanOrEqualTo(String value) {
            addCriterion("site_copyright >=", value, "siteCopyright");
            return (Criteria) this;
        }

        public Criteria andSiteCopyrightLessThan(String value) {
            addCriterion("site_copyright <", value, "siteCopyright");
            return (Criteria) this;
        }

        public Criteria andSiteCopyrightLessThanOrEqualTo(String value) {
            addCriterion("site_copyright <=", value, "siteCopyright");
            return (Criteria) this;
        }

        public Criteria andSiteCopyrightLike(String value) {
            addCriterion("site_copyright like", value, "siteCopyright");
            return (Criteria) this;
        }

        public Criteria andSiteCopyrightNotLike(String value) {
            addCriterion("site_copyright not like", value, "siteCopyright");
            return (Criteria) this;
        }

        public Criteria andSiteCopyrightIn(List<String> values) {
            addCriterion("site_copyright in", values, "siteCopyright");
            return (Criteria) this;
        }

        public Criteria andSiteCopyrightNotIn(List<String> values) {
            addCriterion("site_copyright not in", values, "siteCopyright");
            return (Criteria) this;
        }

        public Criteria andSiteCopyrightBetween(String value1, String value2) {
            addCriterion("site_copyright between", value1, value2, "siteCopyright");
            return (Criteria) this;
        }

        public Criteria andSiteCopyrightNotBetween(String value1, String value2) {
            addCriterion("site_copyright not between", value1, value2, "siteCopyright");
            return (Criteria) this;
        }

        public Criteria andSiteSkinIsNull() {
            addCriterion("site_skin is null");
            return (Criteria) this;
        }

        public Criteria andSiteSkinIsNotNull() {
            addCriterion("site_skin is not null");
            return (Criteria) this;
        }

        public Criteria andSiteSkinEqualTo(Integer value) {
            addCriterion("site_skin =", value, "siteSkin");
            return (Criteria) this;
        }

        public Criteria andSiteSkinNotEqualTo(Integer value) {
            addCriterion("site_skin <>", value, "siteSkin");
            return (Criteria) this;
        }

        public Criteria andSiteSkinGreaterThan(Integer value) {
            addCriterion("site_skin >", value, "siteSkin");
            return (Criteria) this;
        }

        public Criteria andSiteSkinGreaterThanOrEqualTo(Integer value) {
            addCriterion("site_skin >=", value, "siteSkin");
            return (Criteria) this;
        }

        public Criteria andSiteSkinLessThan(Integer value) {
            addCriterion("site_skin <", value, "siteSkin");
            return (Criteria) this;
        }

        public Criteria andSiteSkinLessThanOrEqualTo(Integer value) {
            addCriterion("site_skin <=", value, "siteSkin");
            return (Criteria) this;
        }

        public Criteria andSiteSkinIn(List<Integer> values) {
            addCriterion("site_skin in", values, "siteSkin");
            return (Criteria) this;
        }

        public Criteria andSiteSkinNotIn(List<Integer> values) {
            addCriterion("site_skin not in", values, "siteSkin");
            return (Criteria) this;
        }

        public Criteria andSiteSkinBetween(Integer value1, Integer value2) {
            addCriterion("site_skin between", value1, value2, "siteSkin");
            return (Criteria) this;
        }

        public Criteria andSiteSkinNotBetween(Integer value1, Integer value2) {
            addCriterion("site_skin not between", value1, value2, "siteSkin");
            return (Criteria) this;
        }

        public Criteria andSiteCreateTimeIsNull() {
            addCriterion("site_create_time is null");
            return (Criteria) this;
        }

        public Criteria andSiteCreateTimeIsNotNull() {
            addCriterion("site_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andSiteCreateTimeEqualTo(Date value) {
            addCriterion("site_create_time =", value, "siteCreateTime");
            return (Criteria) this;
        }

        public Criteria andSiteCreateTimeNotEqualTo(Date value) {
            addCriterion("site_create_time <>", value, "siteCreateTime");
            return (Criteria) this;
        }

        public Criteria andSiteCreateTimeGreaterThan(Date value) {
            addCriterion("site_create_time >", value, "siteCreateTime");
            return (Criteria) this;
        }

        public Criteria andSiteCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("site_create_time >=", value, "siteCreateTime");
            return (Criteria) this;
        }

        public Criteria andSiteCreateTimeLessThan(Date value) {
            addCriterion("site_create_time <", value, "siteCreateTime");
            return (Criteria) this;
        }

        public Criteria andSiteCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("site_create_time <=", value, "siteCreateTime");
            return (Criteria) this;
        }

        public Criteria andSiteCreateTimeIn(List<Date> values) {
            addCriterion("site_create_time in", values, "siteCreateTime");
            return (Criteria) this;
        }

        public Criteria andSiteCreateTimeNotIn(List<Date> values) {
            addCriterion("site_create_time not in", values, "siteCreateTime");
            return (Criteria) this;
        }

        public Criteria andSiteCreateTimeBetween(Date value1, Date value2) {
            addCriterion("site_create_time between", value1, value2, "siteCreateTime");
            return (Criteria) this;
        }

        public Criteria andSiteCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("site_create_time not between", value1, value2, "siteCreateTime");
            return (Criteria) this;
        }

        public Criteria andSiteStatusIsNull() {
            addCriterion("site_status is null");
            return (Criteria) this;
        }

        public Criteria andSiteStatusIsNotNull() {
            addCriterion("site_status is not null");
            return (Criteria) this;
        }

        public Criteria andSiteStatusEqualTo(Integer value) {
            addCriterion("site_status =", value, "siteStatus");
            return (Criteria) this;
        }

        public Criteria andSiteStatusNotEqualTo(Integer value) {
            addCriterion("site_status <>", value, "siteStatus");
            return (Criteria) this;
        }

        public Criteria andSiteStatusGreaterThan(Integer value) {
            addCriterion("site_status >", value, "siteStatus");
            return (Criteria) this;
        }

        public Criteria andSiteStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("site_status >=", value, "siteStatus");
            return (Criteria) this;
        }

        public Criteria andSiteStatusLessThan(Integer value) {
            addCriterion("site_status <", value, "siteStatus");
            return (Criteria) this;
        }

        public Criteria andSiteStatusLessThanOrEqualTo(Integer value) {
            addCriterion("site_status <=", value, "siteStatus");
            return (Criteria) this;
        }

        public Criteria andSiteStatusIn(List<Integer> values) {
            addCriterion("site_status in", values, "siteStatus");
            return (Criteria) this;
        }

        public Criteria andSiteStatusNotIn(List<Integer> values) {
            addCriterion("site_status not in", values, "siteStatus");
            return (Criteria) this;
        }

        public Criteria andSiteStatusBetween(Integer value1, Integer value2) {
            addCriterion("site_status between", value1, value2, "siteStatus");
            return (Criteria) this;
        }

        public Criteria andSiteStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("site_status not between", value1, value2, "siteStatus");
            return (Criteria) this;
        }

        public Criteria andSitePicIsNull() {
            addCriterion("site_pic is null");
            return (Criteria) this;
        }

        public Criteria andSitePicIsNotNull() {
            addCriterion("site_pic is not null");
            return (Criteria) this;
        }

        public Criteria andSitePicEqualTo(String value) {
            addCriterion("site_pic =", value, "sitePic");
            return (Criteria) this;
        }

        public Criteria andSitePicNotEqualTo(String value) {
            addCriterion("site_pic <>", value, "sitePic");
            return (Criteria) this;
        }

        public Criteria andSitePicGreaterThan(String value) {
            addCriterion("site_pic >", value, "sitePic");
            return (Criteria) this;
        }

        public Criteria andSitePicGreaterThanOrEqualTo(String value) {
            addCriterion("site_pic >=", value, "sitePic");
            return (Criteria) this;
        }

        public Criteria andSitePicLessThan(String value) {
            addCriterion("site_pic <", value, "sitePic");
            return (Criteria) this;
        }

        public Criteria andSitePicLessThanOrEqualTo(String value) {
            addCriterion("site_pic <=", value, "sitePic");
            return (Criteria) this;
        }

        public Criteria andSitePicLike(String value) {
            addCriterion("site_pic like", value, "sitePic");
            return (Criteria) this;
        }

        public Criteria andSitePicNotLike(String value) {
            addCriterion("site_pic not like", value, "sitePic");
            return (Criteria) this;
        }

        public Criteria andSitePicIn(List<String> values) {
            addCriterion("site_pic in", values, "sitePic");
            return (Criteria) this;
        }

        public Criteria andSitePicNotIn(List<String> values) {
            addCriterion("site_pic not in", values, "sitePic");
            return (Criteria) this;
        }

        public Criteria andSitePicBetween(String value1, String value2) {
            addCriterion("site_pic between", value1, value2, "sitePic");
            return (Criteria) this;
        }

        public Criteria andSitePicNotBetween(String value1, String value2) {
            addCriterion("site_pic not between", value1, value2, "sitePic");
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