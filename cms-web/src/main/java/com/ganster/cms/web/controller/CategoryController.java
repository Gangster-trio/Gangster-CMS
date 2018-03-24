package com.ganster.cms.web.controller;

import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.Category;
import com.ganster.cms.web.dto.ModelResult;
import com.ganster.cms.web.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/category/")
public class CategoryController {
    private final WebService webService;

    @Autowired
    public CategoryController(WebService webService) {
        this.webService = webService;
    }

    @RequestMapping("{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        ModelResult result = webService.getCategoryModel(id);

        if (result == null) {
            return "404";
        }

        //Add result to module
        model.addAttribute("result", result);

        Category category = (Category) result.get("category");

        //If skin = null, set default skin
        if (category.getCategorySkin() == null) {
            category.setCategorySkin(CmsConst.DEFAULT_SKIN);
        }

        //Return to the site's skin view, for example : default-category
        return category.getCategorySkin() + CmsConst.CATEGORY_SKIN_SUFFIX;
    }
}
