package org.lauchcode.matchpoint.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class HelloController {

    @RequestMapping(value="")
    @ResponseBody
    public String index(){
        return "Hello World";
    }
}
