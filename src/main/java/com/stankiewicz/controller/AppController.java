package com.stankiewicz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    @RequestMapping("/")
    String home(ModelMap model){
        model.addAttribute("title","Simple TO-DO list application");
        return "index";
    }

    @RequestMapping("/template/{page}")
    String templateHandler(@PathVariable("page") final String page){
        return page;
    }
}
