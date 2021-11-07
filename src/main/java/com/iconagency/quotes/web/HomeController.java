package com.iconagency.quotes.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class HomeController {
    /*@GetMapping(value="/")
    public String index() {
      return "index";
    }*/

    @GetMapping("/parts")
    public String indexParts() {
        return "index";
    }
}
