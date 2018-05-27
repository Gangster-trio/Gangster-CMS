package com.gangster.cms.admin.util;

import com.alibaba.fastjson.JSONObject;
import com.gangster.cms.admin.controller.ArticleController;
import com.gangster.cms.admin.dto.ArticleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class TransformPOJOUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransformPOJOUtil.class);

    public static Object transformPOJO(HttpServletRequest request) {
        BufferedReader br = null;
        Object o = null;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            o = JSONObject.parseObject(sb.toString(), Objects.requireNonNull(o).getClass());
        } catch (IOException e) {
            LOGGER.error("转换出错,错误信息：{}", e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            ArticleController.closeResource(br, LOGGER);
        }
        return o;
    }
}
