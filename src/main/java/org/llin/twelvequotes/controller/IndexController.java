package org.llin.twelvequotes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class IndexController extends Base {

    @GetMapping("/")
    public RedirectView redirect() {
        return new RedirectView("home/instruction"); 
    }
    
}