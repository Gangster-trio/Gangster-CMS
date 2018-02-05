package com.ganster.cms.core.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ArticleExample() {
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

        public Criteria andArticleIdIsNull() {
            addCriterion("article_id is null");
            return (Criteria) this;
        }

        public Criteria andArticleIdIsNotNull() {
            addCriterion("article_id is not null");
            return (Criteria) this;
        }

        public Criteria andArticleIdEqualTo(Integer value) {
            addCriterion("article_id =", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotEqualTo(Integer value) {
            addCriterion("article_id <>", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThan(Integer value) {
            addCriterion("article_id >", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("article_id >=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThan(Integer value) {
            addCriterion("article_id <", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThanOrEqualTo(Integer value) {
            addCriterion("article_id <=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdIn(List<Integer> values) {
            addCriterion("article_id in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotIn(List<Integer> values) {
            addCriterion("article_id not in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdBetween(Integer value1, Integer value2) {
            addCriterion("article_id between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("article_id not between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIsNull() {
            addCriterion("article_title is null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIsNotNull() {
            addCriterion("article_title is not null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleEqualTo(String value) {
            addCriterion("article_title =", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotEqualTo(String value) {
            addCriterion("article_title <>", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleGreaterThan(String value) {
            addCriterion("article_title >", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleGreaterThanOrEqualTo(String value) {
            addCriterion("article_title >=", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLessThan(String value) {
            addCriterion("article_title <", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLessThanOrEqualTo(String value) {
            addCriterion("article_title <=", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLike(String value) {
            addCriterion("article_title like", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotLike(String value) {
            addCriterion("article_title not like", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIn(List<String> values) {
            addCriterion("article_title in", values, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotIn(List<String> values) {
            addCriterion("article_title not in", values, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleBetween(String value1, String value2) {
            addCriterion("article_title between", value1, value2, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotBetween(String value1, String value2) {
            addCriterion("article_title not between", value1, value2, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTypeIsNull() {
            addCriterion("article_type is null");
            return (Criteria) this;
        }

        public Criteria andArticleTypeIsNotNull() {
            addCriterion("article_type is not null");
            return (Criteria) this;
        }

        public Criteria andArticleTypeEqualTo(String value) {
            addCriterion("article_type =", value, "articleType");
            return (Criteria) this;
        }

        public Criteria andArticleTypeNotEqualTo(String value) {
            addCriterion("article_type <>", value, "articleType");
            return (Criteria) this;
        }

        public Criteria andArticleTypeGreaterThan(String value) {
            addCriterion("article_type >", value, "articleType");
            return (Criteria) this;
        }

        public Criteria andArticleTypeGreaterThanOrEqualTo(String value) {
            addCriterion("article_type >=", value, "articleType");
            return (Criteria) this;
        }

        public Criteria andArticleTypeLessThan(String value) {
            addCriterion("article_type <", value, "articleType");
            return (Criteria) this;
        }

        public Criteria andArticleTypeLessThanOrEqualTo(String value) {
            addCriterion("article_type <=", value, "articleType");
            return (Criteria) this;
        }

        public Criteria andArticleTypeLike(String value) {
            addCriterion("article_type like", value, "articleType");
            return (Criteria) this;
        }

        public Criteria andArticleTypeNotLike(String value) {
            addCriterion("article_type not like", value, "articleType");
            return (Criteria) this;
        }

        public Criteria andArticleTypeIn(List<String> values) {
            addCriterion("article_type in", values, "articleType");
            return (Criteria) this;
        }

        public Criteria andArticleTypeNotIn(List<String> values) {
            addCriterion("article_type not in", values, "articleType");
            return (Criteria) this;
        }

        public Criteria andArticleTypeBetween(String value1, String value2) {
            addCriterion("article_type between", value1, value2, "articleType");
            return (Criteria) this;
        }

        public Criteria andArticleTypeNotBetween(String value1, String value2) {
            addCriterion("article_type not between", value1, value2, "articleType");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorIsNull() {
            addCriterion("article_author is null");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorIsNotNull() {
            addCriterion("article_author is not null");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorEqualTo(String value) {
            addCriterion("article_author =", value, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorNotEqualTo(String value) {
            addCriterion("article_author <>", value, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorGreaterThan(String value) {
            addCriterion("article_author >", value, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("article_author >=", value, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorLessThan(String value) {
            addCriterion("article_author <", value, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorLessThanOrEqualTo(String value) {
            addCriterion("article_author <=", value, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorLike(String value) {
            addCriterion("article_author like", value, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorNotLike(String value) {
            addCriterion("article_author not like", value, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorIn(List<String> values) {
            addCriterion("article_author in", values, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorNotIn(List<String> values) {
            addCriterion("article_author not in", values, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorBetween(String value1, String value2) {
            addCriterion("article_author between", value1, value2, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorNotBetween(String value1, String value2) {
            addCriterion("article_author not between", value1, value2, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleUrlIsNull() {
            addCriterion("article_url is null");
            return (Criteria) this;
        }

        public Criteria andArticleUrlIsNotNull() {
            addCriterion("article_url is not null");
            return (Criteria) this;
        }

        public Criteria andArticleUrlEqualTo(String value) {
            addCriterion("article_url =", value, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlNotEqualTo(String value) {
            addCriterion("article_url <>", value, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlGreaterThan(String value) {
            addCriterion("article_url >", value, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlGreaterThanOrEqualTo(String value) {
            addCriterion("article_url >=", value, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlLessThan(String value) {
            addCriterion("article_url <", value, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlLessThanOrEqualTo(String value) {
            addCriterion("article_url <=", value, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlLike(String value) {
            addCriterion("article_url like", value, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlNotLike(String value) {
            addCriterion("article_url not like", value, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlIn(List<String> values) {
            addCriterion("article_url in", values, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlNotIn(List<String> values) {
            addCriterion("article_url not in", values, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlBetween(String value1, String value2) {
            addCriterion("article_url between", value1, value2, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlNotBetween(String value1, String value2) {
            addCriterion("article_url not between", value1, value2, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleOrderIsNull() {
            addCriterion("article_order is null");
            return (Criteria) this;
        }

        public Criteria andArticleOrderIsNotNull() {
            addCriterion("article_order is not null");
            return (Criteria) this;
        }

        public Criteria andArticleOrderEqualTo(Integer value) {
            addCriterion("article_order =", value, "articleOrder");
            return (Criteria) this;
        }

        public Criteria andArticleOrderNotEqualTo(Integer value) {
            addCriterion("article_order <>", value, "articleOrder");
            return (Criteria) this;
        }

        public Criteria andArticleOrderGreaterThan(Integer value) {
            addCriterion("article_order >", value, "articleOrder");
            return (Criteria) this;
        }

        public Criteria andArticleOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("article_order >=", value, "articleOrder");
            return (Criteria) this;
        }

        public Criteria andArticleOrderLessThan(Integer value) {
            addCriterion("article_order <", value, "articleOrder");
            return (Criteria) this;
        }

        public Criteria andArticleOrderLessThanOrEqualTo(Integer value) {
            addCriterion("article_order <=", value, "articleOrder");
            return (Criteria) this;
        }

        public Criteria andArticleOrderIn(List<Integer> values) {
            addCriterion("article_order in", values, "articleOrder");
            return (Criteria) this;
        }

        public Criteria andArticleOrderNotIn(List<Integer> values) {
            addCriterion("article_order not in", values, "articleOrder");
            return (Criteria) this;
        }

        public Criteria andArticleOrderBetween(Integer value1, Integer value2) {
            addCriterion("article_order between", value1, value2, "articleOrder");
            return (Criteria) this;
        }

        public Criteria andArticleOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("article_order not between", value1, value2, "articleOrder");
            return (Criteria) this;
        }

        public Criteria andArticleSiteIdIsNull() {
            addCriterion("article_site_id is null");
            return (Criteria) this;
        }

        public Criteria andArticleSiteIdIsNotNull() {
            addCriterion("article_site_id is not null");
            return (Criteria) this;
        }

        public Criteria andArticleSiteIdEqualTo(Integer value) {
            addCriterion("article_site_id =", value, "articleSiteId");
            return (Criteria) this;
        }

        public Criteria andArticleSiteIdNotEqualTo(Integer value) {
            addCriterion("article_site_id <>", value, "articleSiteId");
            return (Criteria) this;
        }

        public Criteria andArticleSiteIdGreaterThan(Integer value) {
            addCriterion("article_site_id >", value, "articleSiteId");
            return (Criteria) this;
        }

        public Criteria andArticleSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("article_site_id >=", value, "articleSiteId");
            return (Criteria) this;
        }

        public Criteria andArticleSiteIdLessThan(Integer value) {
            addCriterion("article_site_id <", value, "articleSiteId");
            return (Criteria) this;
        }

        public Criteria andArticleSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("article_site_id <=", value, "articleSiteId");
            return (Criteria) this;
        }

        public Criteria andArticleSiteIdIn(List<Integer> values) {
            addCriterion("article_site_id in", values, "articleSiteId");
            return (Criteria) this;
        }

        public Criteria andArticleSiteIdNotIn(List<Integer> values) {
            addCriterion("article_site_id not in", values, "articleSiteId");
            return (Criteria) this;
        }

        public Criteria andArticleSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("article_site_id between", value1, value2, "articleSiteId");
            return (Criteria) this;
        }

        public Criteria andArticleSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("article_site_id not between", value1, value2, "articleSiteId");
            return (Criteria) this;
        }

        public Criteria andArticleCategoryIdIsNull() {
            addCriterion("article_category_id is null");
            return (Criteria) this;
        }

        public Criteria andArticleCategoryIdIsNotNull() {
            addCriterion("article_category_id is not null");
            return (Criteria) this;
        }

        public Criteria andArticleCategoryIdEqualTo(Integer value) {
            addCriterion("article_category_id =", value, "articleCategoryId");
            return (Criteria) this;
        }

        public Criteria andArticleCategoryIdNotEqualTo(Integer value) {
            addCriterion("article_category_id <>", value, "articleCategoryId");
            return (Criteria) this;
        }

        public Criteria andArticleCategoryIdGreaterThan(Integer value) {
            addCriterion("article_category_id >", value, "articleCategoryId");
            return (Criteria) this;
        }

        public Criteria andArticleCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("article_category_id >=", value, "articleCategoryId");
            return (Criteria) this;
        }

        public Criteria andArticleCategoryIdLessThan(Integer value) {
            addCriterion("article_category_id <", value, "articleCategoryId");
            return (Criteria) this;
        }

        public Criteria andArticleCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("article_category_id <=", value, "articleCategoryId");
            return (Criteria) this;
        }

        public Criteria andArticleCategoryIdIn(List<Integer> values) {
            addCriterion("article_category_id in", values, "articleCategoryId");
            return (Criteria) this;
        }

        public Criteria andArticleCategoryIdNotIn(List<Integer> values) {
            addCriterion("article_category_id not in", values, "articleCategoryId");
            return (Criteria) this;
        }

        public Criteria andArticleCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("article_category_id between", value1, value2, "articleCategoryId");
            return (Criteria) this;
        }

        public Criteria andArticleCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("article_category_id not between", value1, value2, "articleCategoryId");
            return (Criteria) this;
        }

        public Criteria andArticleCreateTimeIsNull() {
            addCriterion("article_create_time is null");
            return (Criteria) this;
        }

        public Criteria andArticleCreateTimeIsNotNull() {
            addCriterion("article_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andArticleCreateTimeEqualTo(Date value) {
            addCriterion("article_create_time =", value, "articleCreateTime");
            return (Criteria) this;
        }

        public Criteria andArticleCreateTimeNotEqualTo(Date value) {
            addCriterion("article_create_time <>", value, "articleCreateTime");
            return (Criteria) this;
        }

        public Criteria andArticleCreateTimeGreaterThan(Date value) {
            addCriterion("article_create_time >", value, "articleCreateTime");
            return (Criteria) this;
        }

        public Criteria andArticleCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("article_create_time >=", value, "articleCreateTime");
            return (Criteria) this;
        }

        public Criteria andArticleCreateTimeLessThan(Date value) {
            addCriterion("article_create_time <", value, "articleCreateTime");
            return (Criteria) this;
        }

        public Criteria andArticleCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("article_create_time <=", value, "articleCreateTime");
            return (Criteria) this;
        }

        public Criteria andArticleCreateTimeIn(List<Date> values) {
            addCriterion("article_create_time in", values, "articleCreateTime");
            return (Criteria) this;
        }

        public Criteria andArticleCreateTimeNotIn(List<Date> values) {
            addCriterion("article_create_time not in", values, "articleCreateTime");
            return (Criteria) this;
        }

        public Criteria andArticleCreateTimeBetween(Date value1, Date value2) {
            addCriterion("article_create_time between", value1, value2, "articleCreateTime");
            return (Criteria) this;
        }

        public Criteria andArticleCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("article_create_time not between", value1, value2, "articleCreateTime");
            return (Criteria) this;
        }

        public Criteria andArticleUpdateTimeIsNull() {
            addCriterion("article_update_time is null");
            return (Criteria) this;
        }

        public Criteria andArticleUpdateTimeIsNotNull() {
            addCriterion("article_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andArticleUpdateTimeEqualTo(Date value) {
            addCriterion("article_update_time =", value, "articleUpdateTime");
            return (Criteria) this;
        }

        public Criteria andArticleUpdateTimeNotEqualTo(Date value) {
            addCriterion("article_update_time <>", value, "articleUpdateTime");
            return (Criteria) this;
        }

        public Criteria andArticleUpdateTimeGreaterThan(Date value) {
            addCriterion("article_update_time >", value, "articleUpdateTime");
            return (Criteria) this;
        }

        public Criteria andArticleUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("article_update_time >=", value, "articleUpdateTime");
            return (Criteria) this;
        }

        public Criteria andArticleUpdateTimeLessThan(Date value) {
            addCriterion("article_update_time <", value, "articleUpdateTime");
            return (Criteria) this;
        }

        public Criteria andArticleUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("article_update_time <=", value, "articleUpdateTime");
            return (Criteria) this;
        }

        public Criteria andArticleUpdateTimeIn(List<Date> values) {
            addCriterion("article_update_time in", values, "articleUpdateTime");
            return (Criteria) this;
        }

        public Criteria andArticleUpdateTimeNotIn(List<Date> values) {
            addCriterion("article_update_time not in", values, "articleUpdateTime");
            return (Criteria) this;
        }

        public Criteria andArticleUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("article_update_time between", value1, value2, "articleUpdateTime");
            return (Criteria) this;
        }

        public Criteria andArticleUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("article_update_time not between", value1, value2, "articleUpdateTime");
            return (Criteria) this;
        }

        public Criteria andArticleThumbIsNull() {
            addCriterion("article_thumb is null");
            return (Criteria) this;
        }

        public Criteria andArticleThumbIsNotNull() {
            addCriterion("article_thumb is not null");
            return (Criteria) this;
        }

        public Criteria andArticleThumbEqualTo(String value) {
            addCriterion("article_thumb =", value, "articleThumb");
            return (Criteria) this;
        }

        public Criteria andArticleThumbNotEqualTo(String value) {
            addCriterion("article_thumb <>", value, "articleThumb");
            return (Criteria) this;
        }

        public Criteria andArticleThumbGreaterThan(String value) {
            addCriterion("article_thumb >", value, "articleThumb");
            return (Criteria) this;
        }

        public Criteria andArticleThumbGreaterThanOrEqualTo(String value) {
            addCriterion("article_thumb >=", value, "articleThumb");
            return (Criteria) this;
        }

        public Criteria andArticleThumbLessThan(String value) {
            addCriterion("article_thumb <", value, "articleThumb");
            return (Criteria) this;
        }

        public Criteria andArticleThumbLessThanOrEqualTo(String value) {
            addCriterion("article_thumb <=", value, "articleThumb");
            return (Criteria) this;
        }

        public Criteria andArticleThumbLike(String value) {
            addCriterion("article_thumb like", value, "articleThumb");
            return (Criteria) this;
        }

        public Criteria andArticleThumbNotLike(String value) {
            addCriterion("article_thumb not like", value, "articleThumb");
            return (Criteria) this;
        }

        public Criteria andArticleThumbIn(List<String> values) {
            addCriterion("article_thumb in", values, "articleThumb");
            return (Criteria) this;
        }

        public Criteria andArticleThumbNotIn(List<String> values) {
            addCriterion("article_thumb not in", values, "articleThumb");
            return (Criteria) this;
        }

        public Criteria andArticleThumbBetween(String value1, String value2) {
            addCriterion("article_thumb between", value1, value2, "articleThumb");
            return (Criteria) this;
        }

        public Criteria andArticleThumbNotBetween(String value1, String value2) {
            addCriterion("article_thumb not between", value1, value2, "articleThumb");
            return (Criteria) this;
        }

        public Criteria andArticleHitIsNull() {
            addCriterion("article_hit is null");
            return (Criteria) this;
        }

        public Criteria andArticleHitIsNotNull() {
            addCriterion("article_hit is not null");
            return (Criteria) this;
        }

        public Criteria andArticleHitEqualTo(Integer value) {
            addCriterion("article_hit =", value, "articleHit");
            return (Criteria) this;
        }

        public Criteria andArticleHitNotEqualTo(Integer value) {
            addCriterion("article_hit <>", value, "articleHit");
            return (Criteria) this;
        }

        public Criteria andArticleHitGreaterThan(Integer value) {
            addCriterion("article_hit >", value, "articleHit");
            return (Criteria) this;
        }

        public Criteria andArticleHitGreaterThanOrEqualTo(Integer value) {
            addCriterion("article_hit >=", value, "articleHit");
            return (Criteria) this;
        }

        public Criteria andArticleHitLessThan(Integer value) {
            addCriterion("article_hit <", value, "articleHit");
            return (Criteria) this;
        }

        public Criteria andArticleHitLessThanOrEqualTo(Integer value) {
            addCriterion("article_hit <=", value, "articleHit");
            return (Criteria) this;
        }

        public Criteria andArticleHitIn(List<Integer> values) {
            addCriterion("article_hit in", values, "articleHit");
            return (Criteria) this;
        }

        public Criteria andArticleHitNotIn(List<Integer> values) {
            addCriterion("article_hit not in", values, "articleHit");
            return (Criteria) this;
        }

        public Criteria andArticleHitBetween(Integer value1, Integer value2) {
            addCriterion("article_hit between", value1, value2, "articleHit");
            return (Criteria) this;
        }

        public Criteria andArticleHitNotBetween(Integer value1, Integer value2) {
            addCriterion("article_hit not between", value1, value2, "articleHit");
            return (Criteria) this;
        }

        public Criteria andArticleDescIsNull() {
            addCriterion("article_desc is null");
            return (Criteria) this;
        }

        public Criteria andArticleDescIsNotNull() {
            addCriterion("article_desc is not null");
            return (Criteria) this;
        }

        public Criteria andArticleDescEqualTo(String value) {
            addCriterion("article_desc =", value, "articleDesc");
            return (Criteria) this;
        }

        public Criteria andArticleDescNotEqualTo(String value) {
            addCriterion("article_desc <>", value, "articleDesc");
            return (Criteria) this;
        }

        public Criteria andArticleDescGreaterThan(String value) {
            addCriterion("article_desc >", value, "articleDesc");
            return (Criteria) this;
        }

        public Criteria andArticleDescGreaterThanOrEqualTo(String value) {
            addCriterion("article_desc >=", value, "articleDesc");
            return (Criteria) this;
        }

        public Criteria andArticleDescLessThan(String value) {
            addCriterion("article_desc <", value, "articleDesc");
            return (Criteria) this;
        }

        public Criteria andArticleDescLessThanOrEqualTo(String value) {
            addCriterion("article_desc <=", value, "articleDesc");
            return (Criteria) this;
        }

        public Criteria andArticleDescLike(String value) {
            addCriterion("article_desc like", value, "articleDesc");
            return (Criteria) this;
        }

        public Criteria andArticleDescNotLike(String value) {
            addCriterion("article_desc not like", value, "articleDesc");
            return (Criteria) this;
        }

        public Criteria andArticleDescIn(List<String> values) {
            addCriterion("article_desc in", values, "articleDesc");
            return (Criteria) this;
        }

        public Criteria andArticleDescNotIn(List<String> values) {
            addCriterion("article_desc not in", values, "articleDesc");
            return (Criteria) this;
        }

        public Criteria andArticleDescBetween(String value1, String value2) {
            addCriterion("article_desc between", value1, value2, "articleDesc");
            return (Criteria) this;
        }

        public Criteria andArticleDescNotBetween(String value1, String value2) {
            addCriterion("article_desc not between", value1, value2, "articleDesc");
            return (Criteria) this;
        }

        public Criteria andArticleStatusIsNull() {
            addCriterion("article_status is null");
            return (Criteria) this;
        }

        public Criteria andArticleStatusIsNotNull() {
            addCriterion("article_status is not null");
            return (Criteria) this;
        }

        public Criteria andArticleStatusEqualTo(Integer value) {
            addCriterion("article_status =", value, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusNotEqualTo(Integer value) {
            addCriterion("article_status <>", value, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusGreaterThan(Integer value) {
            addCriterion("article_status >", value, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("article_status >=", value, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusLessThan(Integer value) {
            addCriterion("article_status <", value, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusLessThanOrEqualTo(Integer value) {
            addCriterion("article_status <=", value, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusIn(List<Integer> values) {
            addCriterion("article_status in", values, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusNotIn(List<Integer> values) {
            addCriterion("article_status not in", values, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusBetween(Integer value1, Integer value2) {
            addCriterion("article_status between", value1, value2, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("article_status not between", value1, value2, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleSkinIsNull() {
            addCriterion("article_skin is null");
            return (Criteria) this;
        }

        public Criteria andArticleSkinIsNotNull() {
            addCriterion("article_skin is not null");
            return (Criteria) this;
        }

        public Criteria andArticleSkinEqualTo(String value) {
            addCriterion("article_skin =", value, "articleSkin");
            return (Criteria) this;
        }

        public Criteria andArticleSkinNotEqualTo(String value) {
            addCriterion("article_skin <>", value, "articleSkin");
            return (Criteria) this;
        }

        public Criteria andArticleSkinGreaterThan(String value) {
            addCriterion("article_skin >", value, "articleSkin");
            return (Criteria) this;
        }

        public Criteria andArticleSkinGreaterThanOrEqualTo(String value) {
            addCriterion("article_skin >=", value, "articleSkin");
            return (Criteria) this;
        }

        public Criteria andArticleSkinLessThan(String value) {
            addCriterion("article_skin <", value, "articleSkin");
            return (Criteria) this;
        }

        public Criteria andArticleSkinLessThanOrEqualTo(String value) {
            addCriterion("article_skin <=", value, "articleSkin");
            return (Criteria) this;
        }

        public Criteria andArticleSkinLike(String value) {
            addCriterion("article_skin like", value, "articleSkin");
            return (Criteria) this;
        }

        public Criteria andArticleSkinNotLike(String value) {
            addCriterion("article_skin not like", value, "articleSkin");
            return (Criteria) this;
        }

        public Criteria andArticleSkinIn(List<String> values) {
            addCriterion("article_skin in", values, "articleSkin");
            return (Criteria) this;
        }

        public Criteria andArticleSkinNotIn(List<String> values) {
            addCriterion("article_skin not in", values, "articleSkin");
            return (Criteria) this;
        }

        public Criteria andArticleSkinBetween(String value1, String value2) {
            addCriterion("article_skin between", value1, value2, "articleSkin");
            return (Criteria) this;
        }

        public Criteria andArticleSkinNotBetween(String value1, String value2) {
            addCriterion("article_skin not between", value1, value2, "articleSkin");
            return (Criteria) this;
        }

        public Criteria andArticleInHomepageIsNull() {
            addCriterion("article_in_homepage is null");
            return (Criteria) this;
        }

        public Criteria andArticleInHomepageIsNotNull() {
            addCriterion("article_in_homepage is not null");
            return (Criteria) this;
        }

        public Criteria andArticleInHomepageEqualTo(Boolean value) {
            addCriterion("article_in_homepage =", value, "articleInHomepage");
            return (Criteria) this;
        }

        public Criteria andArticleInHomepageNotEqualTo(Boolean value) {
            addCriterion("article_in_homepage <>", value, "articleInHomepage");
            return (Criteria) this;
        }

        public Criteria andArticleInHomepageGreaterThan(Boolean value) {
            addCriterion("article_in_homepage >", value, "articleInHomepage");
            return (Criteria) this;
        }

        public Criteria andArticleInHomepageGreaterThanOrEqualTo(Boolean value) {
            addCriterion("article_in_homepage >=", value, "articleInHomepage");
            return (Criteria) this;
        }

        public Criteria andArticleInHomepageLessThan(Boolean value) {
            addCriterion("article_in_homepage <", value, "articleInHomepage");
            return (Criteria) this;
        }

        public Criteria andArticleInHomepageLessThanOrEqualTo(Boolean value) {
            addCriterion("article_in_homepage <=", value, "articleInHomepage");
            return (Criteria) this;
        }

        public Criteria andArticleInHomepageIn(List<Boolean> values) {
            addCriterion("article_in_homepage in", values, "articleInHomepage");
            return (Criteria) this;
        }

        public Criteria andArticleInHomepageNotIn(List<Boolean> values) {
            addCriterion("article_in_homepage not in", values, "articleInHomepage");
            return (Criteria) this;
        }

        public Criteria andArticleInHomepageBetween(Boolean value1, Boolean value2) {
            addCriterion("article_in_homepage between", value1, value2, "articleInHomepage");
            return (Criteria) this;
        }

        public Criteria andArticleInHomepageNotBetween(Boolean value1, Boolean value2) {
            addCriterion("article_in_homepage not between", value1, value2, "articleInHomepage");
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