package com.ganster.cms.web.controller;

import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.Site;
import com.ganster.cms.web.dto.ModelResult;
import com.ganster.cms.web.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {

    private final WebService webService;

    @Autowired
    public SiteController(WebService webService) {
        this.webService = webService;
    }


    @RequestMapping("/{siteUrl}")
    public String show(@PathVariable("siteUrl") String siteUrl, Model model) {

        ModelResult result = webService.getSiteModel(siteUrl);

        //Add result to module
        model.addAttribute("result", result);

        Site site = (Site) result.get("site");

        //If skin = null, set default skin
        if (site.getSiteSkin() == null) {
            site.setSiteSkin(CmsConst.DEFAULT_SKIN);
        }

        //Return to the site's skin view, for example : default-site
        return site.getSiteSkin() + CmsConst.SITE_SKIN_SUFFIX;
    }

    /**
     * map "/" to "/index"
     *
     * @return redirect
     */

    @RequestMapping("/")
    public String index() {
        return "redirect:/index";
    }
}
