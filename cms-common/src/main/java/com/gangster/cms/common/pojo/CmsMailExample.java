package com.gangster.cms.common.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CmsMailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CmsMailExample() {
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

        public Criteria andMailIdIsNull() {
            addCriterion("mail_id is null");
            return (Criteria) this;
        }

        public Criteria andMailIdIsNotNull() {
            addCriterion("mail_id is not null");
            return (Criteria) this;
        }

        public Criteria andMailIdEqualTo(Integer value) {
            addCriterion("mail_id =", value, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdNotEqualTo(Integer value) {
            addCriterion("mail_id <>", value, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdGreaterThan(Integer value) {
            addCriterion("mail_id >", value, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mail_id >=", value, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdLessThan(Integer value) {
            addCriterion("mail_id <", value, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdLessThanOrEqualTo(Integer value) {
            addCriterion("mail_id <=", value, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdIn(List<Integer> values) {
            addCriterion("mail_id in", values, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdNotIn(List<Integer> values) {
            addCriterion("mail_id not in", values, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdBetween(Integer value1, Integer value2) {
            addCriterion("mail_id between", value1, value2, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mail_id not between", value1, value2, "mailId");
            return (Criteria) this;
        }

        public Criteria andMailInMailIsNull() {
            addCriterion("mail_in_mail is null");
            return (Criteria) this;
        }

        public Criteria andMailInMailIsNotNull() {
            addCriterion("mail_in_mail is not null");
            return (Criteria) this;
        }

        public Criteria andMailInMailEqualTo(String value) {
            addCriterion("mail_in_mail =", value, "mailInMail");
            return (Criteria) this;
        }

        public Criteria andMailInMailNotEqualTo(String value) {
            addCriterion("mail_in_mail <>", value, "mailInMail");
            return (Criteria) this;
        }

        public Criteria andMailInMailGreaterThan(String value) {
            addCriterion("mail_in_mail >", value, "mailInMail");
            return (Criteria) this;
        }

        public Criteria andMailInMailGreaterThanOrEqualTo(String value) {
            addCriterion("mail_in_mail >=", value, "mailInMail");
            return (Criteria) this;
        }

        public Criteria andMailInMailLessThan(String value) {
            addCriterion("mail_in_mail <", value, "mailInMail");
            return (Criteria) this;
        }

        public Criteria andMailInMailLessThanOrEqualTo(String value) {
            addCriterion("mail_in_mail <=", value, "mailInMail");
            return (Criteria) this;
        }

        public Criteria andMailInMailLike(String value) {
            addCriterion("mail_in_mail like", value, "mailInMail");
            return (Criteria) this;
        }

        public Criteria andMailInMailNotLike(String value) {
            addCriterion("mail_in_mail not like", value, "mailInMail");
            return (Criteria) this;
        }

        public Criteria andMailInMailIn(List<String> values) {
            addCriterion("mail_in_mail in", values, "mailInMail");
            return (Criteria) this;
        }

        public Criteria andMailInMailNotIn(List<String> values) {
            addCriterion("mail_in_mail not in", values, "mailInMail");
            return (Criteria) this;
        }

        public Criteria andMailInMailBetween(String value1, String value2) {
            addCriterion("mail_in_mail between", value1, value2, "mailInMail");
            return (Criteria) this;
        }

        public Criteria andMailInMailNotBetween(String value1, String value2) {
            addCriterion("mail_in_mail not between", value1, value2, "mailInMail");
            return (Criteria) this;
        }

        public Criteria andMailToMailIsNull() {
            addCriterion("mail_to_mail is null");
            return (Criteria) this;
        }

        public Criteria andMailToMailIsNotNull() {
            addCriterion("mail_to_mail is not null");
            return (Criteria) this;
        }

        public Criteria andMailToMailEqualTo(String value) {
            addCriterion("mail_to_mail =", value, "mailToMail");
            return (Criteria) this;
        }

        public Criteria andMailToMailNotEqualTo(String value) {
            addCriterion("mail_to_mail <>", value, "mailToMail");
            return (Criteria) this;
        }

        public Criteria andMailToMailGreaterThan(String value) {
            addCriterion("mail_to_mail >", value, "mailToMail");
            return (Criteria) this;
        }

        public Criteria andMailToMailGreaterThanOrEqualTo(String value) {
            addCriterion("mail_to_mail >=", value, "mailToMail");
            return (Criteria) this;
        }

        public Criteria andMailToMailLessThan(String value) {
            addCriterion("mail_to_mail <", value, "mailToMail");
            return (Criteria) this;
        }

        public Criteria andMailToMailLessThanOrEqualTo(String value) {
            addCriterion("mail_to_mail <=", value, "mailToMail");
            return (Criteria) this;
        }

        public Criteria andMailToMailLike(String value) {
            addCriterion("mail_to_mail like", value, "mailToMail");
            return (Criteria) this;
        }

        public Criteria andMailToMailNotLike(String value) {
            addCriterion("mail_to_mail not like", value, "mailToMail");
            return (Criteria) this;
        }

        public Criteria andMailToMailIn(List<String> values) {
            addCriterion("mail_to_mail in", values, "mailToMail");
            return (Criteria) this;
        }

        public Criteria andMailToMailNotIn(List<String> values) {
            addCriterion("mail_to_mail not in", values, "mailToMail");
            return (Criteria) this;
        }

        public Criteria andMailToMailBetween(String value1, String value2) {
            addCriterion("mail_to_mail between", value1, value2, "mailToMail");
            return (Criteria) this;
        }

        public Criteria andMailToMailNotBetween(String value1, String value2) {
            addCriterion("mail_to_mail not between", value1, value2, "mailToMail");
            return (Criteria) this;
        }

        public Criteria andMailSenddateIsNull() {
            addCriterion("mail_sendDate is null");
            return (Criteria) this;
        }

        public Criteria andMailSenddateIsNotNull() {
            addCriterion("mail_sendDate is not null");
            return (Criteria) this;
        }

        public Criteria andMailSenddateEqualTo(Date value) {
            addCriterion("mail_sendDate =", value, "mailSenddate");
            return (Criteria) this;
        }

        public Criteria andMailSenddateNotEqualTo(Date value) {
            addCriterion("mail_sendDate <>", value, "mailSenddate");
            return (Criteria) this;
        }

        public Criteria andMailSenddateGreaterThan(Date value) {
            addCriterion("mail_sendDate >", value, "mailSenddate");
            return (Criteria) this;
        }

        public Criteria andMailSenddateGreaterThanOrEqualTo(Date value) {
            addCriterion("mail_sendDate >=", value, "mailSenddate");
            return (Criteria) this;
        }

        public Criteria andMailSenddateLessThan(Date value) {
            addCriterion("mail_sendDate <", value, "mailSenddate");
            return (Criteria) this;
        }

        public Criteria andMailSenddateLessThanOrEqualTo(Date value) {
            addCriterion("mail_sendDate <=", value, "mailSenddate");
            return (Criteria) this;
        }

        public Criteria andMailSenddateIn(List<Date> values) {
            addCriterion("mail_sendDate in", values, "mailSenddate");
            return (Criteria) this;
        }

        public Criteria andMailSenddateNotIn(List<Date> values) {
            addCriterion("mail_sendDate not in", values, "mailSenddate");
            return (Criteria) this;
        }

        public Criteria andMailSenddateBetween(Date value1, Date value2) {
            addCriterion("mail_sendDate between", value1, value2, "mailSenddate");
            return (Criteria) this;
        }

        public Criteria andMailSenddateNotBetween(Date value1, Date value2) {
            addCriterion("mail_sendDate not between", value1, value2, "mailSenddate");
            return (Criteria) this;
        }

        public Criteria andMailReceivedateIsNull() {
            addCriterion("mail_receiveDate is null");
            return (Criteria) this;
        }

        public Criteria andMailReceivedateIsNotNull() {
            addCriterion("mail_receiveDate is not null");
            return (Criteria) this;
        }

        public Criteria andMailReceivedateEqualTo(Date value) {
            addCriterion("mail_receiveDate =", value, "mailReceivedate");
            return (Criteria) this;
        }

        public Criteria andMailReceivedateNotEqualTo(Date value) {
            addCriterion("mail_receiveDate <>", value, "mailReceivedate");
            return (Criteria) this;
        }

        public Criteria andMailReceivedateGreaterThan(Date value) {
            addCriterion("mail_receiveDate >", value, "mailReceivedate");
            return (Criteria) this;
        }

        public Criteria andMailReceivedateGreaterThanOrEqualTo(Date value) {
            addCriterion("mail_receiveDate >=", value, "mailReceivedate");
            return (Criteria) this;
        }

        public Criteria andMailReceivedateLessThan(Date value) {
            addCriterion("mail_receiveDate <", value, "mailReceivedate");
            return (Criteria) this;
        }

        public Criteria andMailReceivedateLessThanOrEqualTo(Date value) {
            addCriterion("mail_receiveDate <=", value, "mailReceivedate");
            return (Criteria) this;
        }

        public Criteria andMailReceivedateIn(List<Date> values) {
            addCriterion("mail_receiveDate in", values, "mailReceivedate");
            return (Criteria) this;
        }

        public Criteria andMailReceivedateNotIn(List<Date> values) {
            addCriterion("mail_receiveDate not in", values, "mailReceivedate");
            return (Criteria) this;
        }

        public Criteria andMailReceivedateBetween(Date value1, Date value2) {
            addCriterion("mail_receiveDate between", value1, value2, "mailReceivedate");
            return (Criteria) this;
        }

        public Criteria andMailReceivedateNotBetween(Date value1, Date value2) {
            addCriterion("mail_receiveDate not between", value1, value2, "mailReceivedate");
            return (Criteria) this;
        }

        public Criteria andMailContentIsNull() {
            addCriterion("mail_content is null");
            return (Criteria) this;
        }

        public Criteria andMailContentIsNotNull() {
            addCriterion("mail_content is not null");
            return (Criteria) this;
        }

        public Criteria andMailContentEqualTo(String value) {
            addCriterion("mail_content =", value, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentNotEqualTo(String value) {
            addCriterion("mail_content <>", value, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentGreaterThan(String value) {
            addCriterion("mail_content >", value, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentGreaterThanOrEqualTo(String value) {
            addCriterion("mail_content >=", value, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentLessThan(String value) {
            addCriterion("mail_content <", value, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentLessThanOrEqualTo(String value) {
            addCriterion("mail_content <=", value, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentLike(String value) {
            addCriterion("mail_content like", value, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentNotLike(String value) {
            addCriterion("mail_content not like", value, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentIn(List<String> values) {
            addCriterion("mail_content in", values, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentNotIn(List<String> values) {
            addCriterion("mail_content not in", values, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentBetween(String value1, String value2) {
            addCriterion("mail_content between", value1, value2, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentNotBetween(String value1, String value2) {
            addCriterion("mail_content not between", value1, value2, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailReadIsNull() {
            addCriterion("mail_read is null");
            return (Criteria) this;
        }

        public Criteria andMailReadIsNotNull() {
            addCriterion("mail_read is not null");
            return (Criteria) this;
        }

        public Criteria andMailReadEqualTo(Integer value) {
            addCriterion("mail_read =", value, "mailRead");
            return (Criteria) this;
        }

        public Criteria andMailReadNotEqualTo(Integer value) {
            addCriterion("mail_read <>", value, "mailRead");
            return (Criteria) this;
        }

        public Criteria andMailReadGreaterThan(Integer value) {
            addCriterion("mail_read >", value, "mailRead");
            return (Criteria) this;
        }

        public Criteria andMailReadGreaterThanOrEqualTo(Integer value) {
            addCriterion("mail_read >=", value, "mailRead");
            return (Criteria) this;
        }

        public Criteria andMailReadLessThan(Integer value) {
            addCriterion("mail_read <", value, "mailRead");
            return (Criteria) this;
        }

        public Criteria andMailReadLessThanOrEqualTo(Integer value) {
            addCriterion("mail_read <=", value, "mailRead");
            return (Criteria) this;
        }

        public Criteria andMailReadIn(List<Integer> values) {
            addCriterion("mail_read in", values, "mailRead");
            return (Criteria) this;
        }

        public Criteria andMailReadNotIn(List<Integer> values) {
            addCriterion("mail_read not in", values, "mailRead");
            return (Criteria) this;
        }

        public Criteria andMailReadBetween(Integer value1, Integer value2) {
            addCriterion("mail_read between", value1, value2, "mailRead");
            return (Criteria) this;
        }

        public Criteria andMailReadNotBetween(Integer value1, Integer value2) {
            addCriterion("mail_read not between", value1, value2, "mailRead");
            return (Criteria) this;
        }

        public Criteria andMailFlagStatusIsNull() {
            addCriterion("mail_flag_status is null");
            return (Criteria) this;
        }

        public Criteria andMailFlagStatusIsNotNull() {
            addCriterion("mail_flag_status is not null");
            return (Criteria) this;
        }

        public Criteria andMailFlagStatusEqualTo(Integer value) {
            addCriterion("mail_flag_status =", value, "mailFlagStatus");
            return (Criteria) this;
        }

        public Criteria andMailFlagStatusNotEqualTo(Integer value) {
            addCriterion("mail_flag_status <>", value, "mailFlagStatus");
            return (Criteria) this;
        }

        public Criteria andMailFlagStatusGreaterThan(Integer value) {
            addCriterion("mail_flag_status >", value, "mailFlagStatus");
            return (Criteria) this;
        }

        public Criteria andMailFlagStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("mail_flag_status >=", value, "mailFlagStatus");
            return (Criteria) this;
        }

        public Criteria andMailFlagStatusLessThan(Integer value) {
            addCriterion("mail_flag_status <", value, "mailFlagStatus");
            return (Criteria) this;
        }

        public Criteria andMailFlagStatusLessThanOrEqualTo(Integer value) {
            addCriterion("mail_flag_status <=", value, "mailFlagStatus");
            return (Criteria) this;
        }

        public Criteria andMailFlagStatusIn(List<Integer> values) {
            addCriterion("mail_flag_status in", values, "mailFlagStatus");
            return (Criteria) this;
        }

        public Criteria andMailFlagStatusNotIn(List<Integer> values) {
            addCriterion("mail_flag_status not in", values, "mailFlagStatus");
            return (Criteria) this;
        }

        public Criteria andMailFlagStatusBetween(Integer value1, Integer value2) {
            addCriterion("mail_flag_status between", value1, value2, "mailFlagStatus");
            return (Criteria) this;
        }

        public Criteria andMailFlagStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("mail_flag_status not between", value1, value2, "mailFlagStatus");
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