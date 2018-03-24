package com.ganster.cms.web.directive;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;

import java.util.Map;


public class DirectiveUtil {
    public static String getRetName(String retParam, Map params) {
        return getRetName(retParam, params, retParam);
    }

    public static String getRetName(String retParam, Map params, String defaultName) {
        if (params.containsKey(retParam)) {
            return params.get(retParam).toString();
        }
        return defaultName;
    }

    public static String getString(String key, Map params) throws TemplateModelException {
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

    public static Integer getInteger(String key, Map params) throws TemplateModelException {
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
}
