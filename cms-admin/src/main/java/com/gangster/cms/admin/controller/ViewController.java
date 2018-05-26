package com.gangster.cms.admin.controller;

import com.gangster.cms.common.pojo.Article;
import com.gangster.cms.common.pojo.WebFile;
import com.gangster.cms.common.pojo.WebFileExample;
import com.gangster.cms.dao.mapper.ArticleMapper;
import com.gangster.cms.dao.mapper.WebFileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/view/test")
public class ViewController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewController.class);
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private WebFileMapper webFileMapper;

    @GetMapping("/article/{id}")
    public String showOneArticle(@PathVariable("id") Integer id, Model model) {

        Article article = articleMapper.selectByPrimaryKey(id);
        // TODO: 视频播放目前只支持mp3,mp4格式
        List<String> fileType = Arrays.asList("mp3", "mp4");
        WebFileExample webFileExample = new WebFileExample();
        webFileExample.or().andFileArticleIdEqualTo(article.getArticleId()).andFileTypeIn(fileType);
        List<WebFile> webFiles = webFileMapper.selectByExample(webFileExample);
        if (webFiles != null) {
            webFiles = webFiles.stream()
                    .peek(webFile -> webFile.setFileName("http://localhost:8080" + webFile.getFileName()))
                    .collect(Collectors.toList());
        }
        model.addAttribute("files", webFiles);
        model.addAttribute("article", article);
        return "video";
    }
}
