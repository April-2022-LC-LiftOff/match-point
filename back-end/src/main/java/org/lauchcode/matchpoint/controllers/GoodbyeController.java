package org.lauchcode.matchpoint.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GoodbyeController {
    @RequestMapping(value="goodbye")
    @ResponseBody
    public String goodbye(){
        return "Goodbye";
    }
}
