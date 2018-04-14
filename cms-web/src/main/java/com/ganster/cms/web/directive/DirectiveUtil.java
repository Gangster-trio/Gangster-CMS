package com.ganster.cms.web.directive;

import com.ganster.cms.core.pojo.Site;
import com.ganster.cms.web.dto.ModelResult;
import freemarker.core.Environment;
import freemarker.template.*;

import java.util.Map;


class DirectiveUtil {
    private static DefaultObjectWrapper wrapper = new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

    static String getRetName(String retParam, Map params) {
        return getRetName(retParam, params, retParam);
    }

    static String getRetName(String retParam, Map params, String defaultName) {
        if (params.containsKey(retParam)) {
            return params.get(retParam).toString();
        }
        return defaultName;
    }

    static String getString(String key, Map params) throws TemplateModelException {
        TemplateModel model = (TemplateModel) params.get(key);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateScalarModel) {
            return ((TemplateScalarModel) model).getAsString();
        }
        if (model instanceof TemplateNumberModel) {
            return ((TemplateNumberModel) model).getAsNumber().toString();
        }
        throw new TemplateModelException(key + " must is String or Number");
    }

    static Integer getInteger(String key, Map params) throws TemplateModelException {
        TemplateModel model = (TemplateModel) params.get(key);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateNumberModel) {
            return ((TemplateNumberModel) model).getAsNumber().intValue();
        }
        if (model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel) model).getAsString();
            if (s == null || s.equals("")) {
                return null;
            }
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new TemplateModelException(s + " must is Number");
            }
        }
        throw new TemplateModelException(key + "must is Number");
    }

    static Boolean getBoolean(String key, Map param) throws TemplateModelException {
        TemplateModel model = (TemplateModel) param.get(key);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateBooleanModel) {
            return ((TemplateBooleanModel) model).getAsBoolean();
        }
        if (model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel) model).getAsString();
            if (!s.equals("true")) {
                if (s.equals("false")) {
                    return false;
                } else {
                    throw new TemplateModelException(key + "Must is Boolean");
                }
            } else {
                return true;
            }
        }
        return false;
    }

    static Site getSite(Environment env) throws TemplateModelException {
        return (Site) ((ModelResult) wrapper.unwrap(env.getVariable("result"))).get("site");
    }
}
