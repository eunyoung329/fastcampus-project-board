package com.fastcampus.projectboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/articles")
@Controller
public class ArticleController {

@GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId,ModelMap map) {
    map.addAttribute("article","article");//TODO:데이터 구현할때 여기에 실제 데이터를 넣어줘야함
    map.addAttribute("articleComments",List.of());
    return "articles/detail";
}


}
