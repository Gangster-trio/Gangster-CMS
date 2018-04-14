package com.ganster.cms.core.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CategoryExample() {
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

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Integer value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Integer value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Integer value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Integer value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Integer> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Integer> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryTitleIsNull() {
            addCriterion("category_title is null");
            return (Criteria) this;
        }

        public Criteria andCategoryTitleIsNotNull() {
            addCriterion("category_title is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryTitleEqualTo(String value) {
            addCriterion("category_title =", value, "categoryTitle");
            return (Criteria) this;
        }

        public Criteria andCategoryTitleNotEqualTo(String value) {
            addCriterion("category_title <>", value, "categoryTitle");
            return (Criteria) this;
        }

        public Criteria andCategoryTitleGreaterThan(String value) {
            addCriterion("category_title >", value, "categoryTitle");
            return (Criteria) this;
        }

        public Criteria andCategoryTitleGreaterThanOrEqualTo(String value) {
            addCriterion("category_title >=", value, "categoryTitle");
            return (Criteria) this;
        }

        public Criteria andCategoryTitleLessThan(String value) {
            addCriterion("category_title <", value, "categoryTitle");
            return (Criteria) this;
        }

        public Criteria andCategoryTitleLessThanOrEqualTo(String value) {
            addCriterion("category_title <=", value, "categoryTitle");
            return (Criteria) this;
        }

        public Criteria andCategoryTitleLike(String value) {
            addCriterion("category_title like", value, "categoryTitle");
            return (Criteria) this;
        }

        public Criteria andCategoryTitleNotLike(String value) {
            addCriterion("category_title not like", value, "categoryTitle");
            return (Criteria) this;
        }

        public Criteria andCategoryTitleIn(List<String> values) {
            addCriterion("category_title in", values, "categoryTitle");
            return (Criteria) this;
        }

        public Criteria andCategoryTitleNotIn(List<String> values) {
            addCriterion("category_title not in", values, "categoryTitle");
            return (Criteria) this;
        }

        public Criteria andCategoryTitleBetween(String value1, String value2) {
            addCriterion("category_title between", value1, value2, "categoryTitle");
            return (Criteria) this;
        }

        public Criteria andCategoryTitleNotBetween(String value1, String value2) {
            addCriterion("category_title not between", value1, value2, "categoryTitle");
            return (Criteria) this;
        }

        public Criteria andCategoryCreateTimeIsNull() {
            addCriterion("category_create_time is null");
            return (Criteria) this;
        }

        public Criteria andCategoryCreateTimeIsNotNull() {
            addCriterion("category_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryCreateTimeEqualTo(Date value) {
            addCriterion("category_create_time =", value, "categoryCreateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryCreateTimeNotEqualTo(Date value) {
            addCriterion("category_create_time <>", value, "categoryCreateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryCreateTimeGreaterThan(Date value) {
            addCriterion("category_create_time >", value, "categoryCreateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("category_create_time >=", value, "categoryCreateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryCreateTimeLessThan(Date value) {
            addCriterion("category_create_time <", value, "categoryCreateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("category_create_time <=", value, "categoryCreateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryCreateTimeIn(List<Date> values) {
            addCriterion("category_create_time in", values, "categoryCreateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryCreateTimeNotIn(List<Date> values) {
            addCriterion("category_create_time not in", values, "categoryCreateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryCreateTimeBetween(Date value1, Date value2) {
            addCriterion("category_create_time between", value1, value2, "categoryCreateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("category_create_time not between", value1, value2, "categoryCreateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryUpdateTimeIsNull() {
            addCriterion("category_update_time is null");
            return (Criteria) this;
        }

        public Criteria andCategoryUpdateTimeIsNotNull() {
            addCriterion("category_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryUpdateTimeEqualTo(Date value) {
            addCriterion("category_update_time =", value, "categoryUpdateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryUpdateTimeNotEqualTo(Date value) {
            addCriterion("category_update_time <>", value, "categoryUpdateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryUpdateTimeGreaterThan(Date value) {
            addCriterion("category_update_time >", value, "categoryUpdateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("category_update_time >=", value, "categoryUpdateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryUpdateTimeLessThan(Date value) {
            addCriterion("category_update_time <", value, "categoryUpdateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("category_update_time <=", value, "categoryUpdateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryUpdateTimeIn(List<Date> values) {
            addCriterion("category_update_time in", values, "categoryUpdateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryUpdateTimeNotIn(List<Date> values) {
            addCriterion("category_update_time not in", values, "categoryUpdateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("category_update_time between", value1, value2, "categoryUpdateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("category_update_time not between", value1, value2, "categoryUpdateTime");
            return (Criteria) this;
        }

        public Criteria andCategoryParentIdIsNull() {
            addCriterion("category_parent_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryParentIdIsNotNull() {
            addCriterion("category_parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryParentIdEqualTo(Integer value) {
            addCriterion("category_parent_id =", value, "categoryParentId");
            return (Criteria) this;
        }

        public Criteria andCategoryParentIdNotEqualTo(Integer value) {
            addCriterion("category_parent_id <>", value, "categoryParentId");
            return (Criteria) this;
        }

        public Criteria andCategoryParentIdGreaterThan(Integer value) {
            addCriterion("category_parent_id >", value, "categoryParentId");
            return (Criteria) this;
        }

        public Criteria andCategoryParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_parent_id >=", value, "categoryParentId");
            return (Criteria) this;
        }

        public Criteria andCategoryParentIdLessThan(Integer value) {
            addCriterion("category_parent_id <", value, "categoryParentId");
            return (Criteria) this;
        }

        public Criteria andCategoryParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("category_parent_id <=", value, "categoryParentId");
            return (Criteria) this;
        }

        public Criteria andCategoryParentIdIn(List<Integer> values) {
            addCriterion("category_parent_id in", values, "categoryParentId");
            return (Criteria) this;
        }

        public Criteria andCategoryParentIdNotIn(List<Integer> values) {
            addCriterion("category_parent_id not in", values, "categoryParentId");
            return (Criteria) this;
        }

        public Criteria andCategoryParentIdBetween(Integer value1, Integer value2) {
            addCriterion("category_parent_id between", value1, value2, "categoryParentId");
            return (Criteria) this;
        }

        public Criteria andCategoryParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("category_parent_id not between", value1, value2, "categoryParentId");
            return (Criteria) this;
        }

        public Criteria andCategoryLevelIsNull() {
            addCriterion("category_level is null");
            return (Criteria) this;
        }

        public Criteria andCategoryLevelIsNotNull() {
            addCriterion("category_level is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryLevelEqualTo(Integer value) {
            addCriterion("category_level =", value, "categoryLevel");
            return (Criteria) this;
        }

        public Criteria andCategoryLevelNotEqualTo(Integer value) {
            addCriterion("category_level <>", value, "categoryLevel");
            return (Criteria) this;
        }

        public Criteria andCategoryLevelGreaterThan(Integer value) {
            addCriterion("category_level >", value, "categoryLevel");
            return (Criteria) this;
        }

        public Criteria andCategoryLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_level >=", value, "categoryLevel");
            return (Criteria) this;
        }

        public Criteria andCategoryLevelLessThan(Integer value) {
            addCriterion("category_level <", value, "categoryLevel");
            return (Criteria) this;
        }

        public Criteria andCategoryLevelLessThanOrEqualTo(Integer value) {
            addCriterion("category_level <=", value, "categoryLevel");
            return (Criteria) this;
        }

        public Criteria andCategoryLevelIn(List<Integer> values) {
            addCriterion("category_level in", values, "categoryLevel");
            return (Criteria) this;
        }

        public Criteria andCategoryLevelNotIn(List<Integer> values) {
            addCriterion("category_level not in", values, "categoryLevel");
            return (Criteria) this;
        }

        public Criteria andCategoryLevelBetween(Integer value1, Integer value2) {
            addCriterion("category_level between", value1, value2, "categoryLevel");
            return (Criteria) this;
        }

        public Criteria andCategoryLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("category_level not between", value1, value2, "categoryLevel");
            return (Criteria) this;
        }

        public Criteria andCategorySiteIdIsNull() {
            addCriterion("category_site_id is null");
            return (Criteria) this;
        }

        public Criteria andCategorySiteIdIsNotNull() {
            addCriterion("category_site_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategorySiteIdEqualTo(Integer value) {
            addCriterion("category_site_id =", value, "categorySiteId");
            return (Criteria) this;
        }

        public Criteria andCategorySiteIdNotEqualTo(Integer value) {
            addCriterion("category_site_id <>", value, "categorySiteId");
            return (Criteria) this;
        }

        public Criteria andCategorySiteIdGreaterThan(Integer value) {
            addCriterion("category_site_id >", value, "categorySiteId");
            return (Criteria) this;
        }

        public Criteria andCategorySiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_site_id >=", value, "categorySiteId");
            return (Criteria) this;
        }

        public Criteria andCategorySiteIdLessThan(Integer value) {
            addCriterion("category_site_id <", value, "categorySiteId");
            return (Criteria) this;
        }

        public Criteria andCategorySiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("category_site_id <=", value, "categorySiteId");
            return (Criteria) this;
        }

        public Criteria andCategorySiteIdIn(List<Integer> values) {
            addCriterion("category_site_id in", values, "categorySiteId");
            return (Criteria) this;
        }

        public Criteria andCategorySiteIdNotIn(List<Integer> values) {
            addCriterion("category_site_id not in", values, "categorySiteId");
            return (Criteria) this;
        }

        public Criteria andCategorySiteIdBetween(Integer value1, Integer value2) {
            addCriterion("category_site_id between", value1, value2, "categorySiteId");
            return (Criteria) this;
        }

        public Criteria andCategorySiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("category_site_id not between", value1, value2, "categorySiteId");
            return (Criteria) this;
        }

        public Criteria andCategoryStatusIsNull() {
            addCriterion("category_status is null");
            return (Criteria) this;
        }

        public Criteria andCategoryStatusIsNotNull() {
            addCriterion("category_status is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryStatusEqualTo(Integer value) {
            addCriterion("category_status =", value, "categoryStatus");
            return (Criteria) this;
        }

        public Criteria andCategoryStatusNotEqualTo(Integer value) {
            addCriterion("category_status <>", value, "categoryStatus");
            return (Criteria) this;
        }

        public Criteria andCategoryStatusGreaterThan(Integer value) {
            addCriterion("category_status >", value, "categoryStatus");
            return (Criteria) this;
        }

        public Criteria andCategoryStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_status >=", value, "categoryStatus");
            return (Criteria) this;
        }

        public Criteria andCategoryStatusLessThan(Integer value) {
            addCriterion("category_status <", value, "categoryStatus");
            return (Criteria) this;
        }

        public Criteria andCategoryStatusLessThanOrEqualTo(Integer value) {
            addCriterion("category_status <=", value, "categoryStatus");
            return (Criteria) this;
        }

        public Criteria andCategoryStatusIn(List<Integer> values) {
            addCriterion("category_status in", values, "categoryStatus");
            return (Criteria) this;
        }

        public Criteria andCategoryStatusNotIn(List<Integer> values) {
            addCriterion("category_status not in", values, "categoryStatus");
            return (Criteria) this;
        }

        public Criteria andCategoryStatusBetween(Integer value1, Integer value2) {
            addCriterion("category_status between", value1, value2, "categoryStatus");
            return (Criteria) this;
        }

        public Criteria andCategoryStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("category_status not between", value1, value2, "categoryStatus");
            return (Criteria) this;
        }

        public Criteria andCategoryDescIsNull() {
            addCriterion("category_desc is null");
            return (Criteria) this;
        }

        public Criteria andCategoryDescIsNotNull() {
            addCriterion("category_desc is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryDescEqualTo(String value) {
            addCriterion("category_desc =", value, "categoryDesc");
            return (Criteria) this;
        }

        public Criteria andCategoryDescNotEqualTo(String value) {
            addCriterion("category_desc <>", value, "categoryDesc");
            return (Criteria) this;
        }

        public Criteria andCategoryDescGreaterThan(String value) {
            addCriterion("category_desc >", value, "categoryDesc");
            return (Criteria) this;
        }

        public Criteria andCategoryDescGreaterThanOrEqualTo(String value) {
            addCriterion("category_desc >=", value, "categoryDesc");
            return (Criteria) this;
        }

        public Criteria andCategoryDescLessThan(String value) {
            addCriterion("category_desc <", value, "categoryDesc");
            return (Criteria) this;
        }

        public Criteria andCategoryDescLessThanOrEqualTo(String value) {
            addCriterion("category_desc <=", value, "categoryDesc");
            return (Criteria) this;
        }

        public Criteria andCategoryDescLike(String value) {
            addCriterion("category_desc like", value, "categoryDesc");
            return (Criteria) this;
        }

        public Criteria andCategoryDescNotLike(String value) {
            addCriterion("category_desc not like", value, "categoryDesc");
            return (Criteria) this;
        }

        public Criteria andCategoryDescIn(List<String> values) {
            addCriterion("category_desc in", values, "categoryDesc");
            return (Criteria) this;
        }

        public Criteria andCategoryDescNotIn(List<String> values) {
            addCriterion("category_desc not in", values, "categoryDesc");
            return (Criteria) this;
        }

        public Criteria andCategoryDescBetween(String value1, String value2) {
            addCriterion("category_desc between", value1, value2, "categoryDesc");
            return (Criteria) this;
        }

        public Criteria andCategoryDescNotBetween(String value1, String value2) {
            addCriterion("category_desc not between", value1, value2, "categoryDesc");
            return (Criteria) this;
        }

        public Criteria andCategoryOrderIsNull() {
            addCriterion("category_order is null");
            return (Criteria) this;
        }

        public Criteria andCategoryOrderIsNotNull() {
            addCriterion("category_order is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryOrderEqualTo(Integer value) {
            addCriterion("category_order =", value, "categoryOrder");
            return (Criteria) this;
        }

        public Criteria andCategoryOrderNotEqualTo(Integer value) {
            addCriterion("category_order <>", value, "categoryOrder");
            return (Criteria) this;
        }

        public Criteria andCategoryOrderGreaterThan(Integer value) {
            addCriterion("category_order >", value, "categoryOrder");
            return (Criteria) this;
        }

        public Criteria andCategoryOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_order >=", value, "categoryOrder");
            return (Criteria) this;
        }

        public Criteria andCategoryOrderLessThan(Integer value) {
            addCriterion("category_order <", value, "categoryOrder");
            return (Criteria) this;
        }

        public Criteria andCategoryOrderLessThanOrEqualTo(Integer value) {
            addCriterion("category_order <=", value, "categoryOrder");
            return (Criteria) this;
        }

        public Criteria andCategoryOrderIn(List<Integer> values) {
            addCriterion("category_order in", values, "categoryOrder");
            return (Criteria) this;
        }

        public Criteria andCategoryOrderNotIn(List<Integer> values) {
            addCriterion("category_order not in", values, "categoryOrder");
            return (Criteria) this;
        }

        public Criteria andCategoryOrderBetween(Integer value1, Integer value2) {
            addCriterion("category_order between", value1, value2, "categoryOrder");
            return (Criteria) this;
        }

        public Criteria andCategoryOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("category_order not between", value1, value2, "categoryOrder");
            return (Criteria) this;
        }

        public Criteria andCategorySkinIsNull() {
            addCriterion("category_skin is null");
            return (Criteria) this;
        }

        public Criteria andCategorySkinIsNotNull() {
            addCriterion("category_skin is not null");
            return (Criteria) this;
        }

        public Criteria andCategorySkinEqualTo(String value) {
            addCriterion("category_skin =", value, "categorySkin");
            return (Criteria) this;
        }

        public Criteria andCategorySkinNotEqualTo(String value) {
            addCriterion("category_skin <>", value, "categorySkin");
            return (Criteria) this;
        }

        public Criteria andCategorySkinGreaterThan(String value) {
            addCriterion("category_skin >", value, "categorySkin");
            return (Criteria) this;
        }

        public Criteria andCategorySkinGreaterThanOrEqualTo(String value) {
            addCriterion("category_skin >=", value, "categorySkin");
            return (Criteria) this;
        }

        public Criteria andCategorySkinLessThan(String value) {
            addCriterion("category_skin <", value, "categorySkin");
            return (Criteria) this;
        }

        public Criteria andCategorySkinLessThanOrEqualTo(String value) {
            addCriterion("category_skin <=", value, "categorySkin");
            return (Criteria) this;
        }

        public Criteria andCategorySkinLike(String value) {
            addCriterion("category_skin like", value, "categorySkin");
            return (Criteria) this;
        }

        public Criteria andCategorySkinNotLike(String value) {
            addCriterion("category_skin not like", value, "categorySkin");
            return (Criteria) this;
        }

        public Criteria andCategorySkinIn(List<String> values) {
            addCriterion("category_skin in", values, "categorySkin");
            return (Criteria) this;
        }

        public Criteria andCategorySkinNotIn(List<String> values) {
            addCriterion("category_skin not in", values, "categorySkin");
            return (Criteria) this;
        }

        public Criteria andCategorySkinBetween(String value1, String value2) {
            addCriterion("category_skin between", value1, value2, "categorySkin");
            return (Criteria) this;
        }

        public Criteria andCategorySkinNotBetween(String value1, String value2) {
            addCriterion("category_skin not between", value1, value2, "categorySkin");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeIsNull() {
            addCriterion("category_type is null");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeIsNotNull() {
            addCriterion("category_type is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeEqualTo(String value) {
            addCriterion("category_type =", value, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeNotEqualTo(String value) {
            addCriterion("category_type <>", value, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeGreaterThan(String value) {
            addCriterion("category_type >", value, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeGreaterThanOrEqualTo(String value) {
            addCriterion("category_type >=", value, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeLessThan(String value) {
            addCriterion("category_type <", value, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeLessThanOrEqualTo(String value) {
            addCriterion("category_type <=", value, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeLike(String value) {
            addCriterion("category_type like", value, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeNotLike(String value) {
            addCriterion("category_type not like", value, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeIn(List<String> values) {
            addCriterion("category_type in", values, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeNotIn(List<String> values) {
            addCriterion("category_type not in", values, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeBetween(String value1, String value2) {
            addCriterion("category_type between", value1, value2, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeNotBetween(String value1, String value2) {
            addCriterion("category_type not between", value1, value2, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryInHomepageIsNull() {
            addCriterion("category_in_homepage is null");
            return (Criteria) this;
        }

        public Criteria andCategoryInHomepageIsNotNull() {
            addCriterion("category_in_homepage is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryInHomepageEqualTo(Boolean value) {
            addCriterion("category_in_homepage =", value, "categoryInHomepage");
            return (Criteria) this;
        }

        public Criteria andCategoryInHomepageNotEqualTo(Boolean value) {
            addCriterion("category_in_homepage <>", value, "categoryInHomepage");
            return (Criteria) this;
        }

        public Criteria andCategoryInHomepageGreaterThan(Boolean value) {
            addCriterion("category_in_homepage >", value, "categoryInHomepage");
            return (Criteria) this;
        }

        public Criteria andCategoryInHomepageGreaterThanOrEqualTo(Boolean value) {
            addCriterion("category_in_homepage >=", value, "categoryInHomepage");
            return (Criteria) this;
        }

        public Criteria andCategoryInHomepageLessThan(Boolean value) {
            addCriterion("category_in_homepage <", value, "categoryInHomepage");
            return (Criteria) this;
        }

        public Criteria andCategoryInHomepageLessThanOrEqualTo(Boolean value) {
            addCriterion("category_in_homepage <=", value, "categoryInHomepage");
            return (Criteria) this;
        }

        public Criteria andCategoryInHomepageIn(List<Boolean> values) {
            addCriterion("category_in_homepage in", values, "categoryInHomepage");
            return (Criteria) this;
        }

        public Criteria andCategoryInHomepageNotIn(List<Boolean> values) {
            addCriterion("category_in_homepage not in", values, "categoryInHomepage");
            return (Criteria) this;
        }

        public Criteria andCategoryInHomepageBetween(Boolean value1, Boolean value2) {
            addCriterion("category_in_homepage between", value1, value2, "categoryInHomepage");
            return (Criteria) this;
        }

        public Criteria andCategoryInHomepageNotBetween(Boolean value1, Boolean value2) {
            addCriterion("category_in_homepage not between", value1, value2, "categoryInHomepage");
            return (Criteria) this;
        }

        public Criteria andCategoryHitIsNull() {
            addCriterion("category_hit is null");
            return (Criteria) this;
        }

        public Criteria andCategoryHitIsNotNull() {
            addCriterion("category_hit is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryHitEqualTo(Integer value) {
            addCriterion("category_hit =", value, "categoryHit");
            return (Criteria) this;
        }

        public Criteria andCategoryHitNotEqualTo(Integer value) {
            addCriterion("category_hit <>", value, "categoryHit");
            return (Criteria) this;
        }

        public Criteria andCategoryHitGreaterThan(Integer value) {
            addCriterion("category_hit >", value, "categoryHit");
            return (Criteria) this;
        }

        public Criteria andCategoryHitGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_hit >=", value, "categoryHit");
            return (Criteria) this;
        }

        public Criteria andCategoryHitLessThan(Integer value) {
            addCriterion("category_hit <", value, "categoryHit");
            return (Criteria) this;
        }

        public Criteria andCategoryHitLessThanOrEqualTo(Integer value) {
            addCriterion("category_hit <=", value, "categoryHit");
            return (Criteria) this;
        }

        public Criteria andCategoryHitIn(List<Integer> values) {
            addCriterion("category_hit in", values, "categoryHit");
            return (Criteria) this;
        }

        public Criteria andCategoryHitNotIn(List<Integer> values) {
            addCriterion("category_hit not in", values, "categoryHit");
            return (Criteria) this;
        }

        public Criteria andCategoryHitBetween(Integer value1, Integer value2) {
            addCriterion("category_hit between", value1, value2, "categoryHit");
            return (Criteria) this;
        }

        public Criteria andCategoryHitNotBetween(Integer value1, Integer value2) {
            addCriterion("category_hit not between", value1, value2, "categoryHit");
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