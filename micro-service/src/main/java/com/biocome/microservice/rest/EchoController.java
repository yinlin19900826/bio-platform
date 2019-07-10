package com.biocome.microservice.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class EchoController {
    @RequestMapping(value = "/echo/{word}", method = RequestMethod.GET)
    @ResponseBody
    public String echo(@PathVariable("word") String word){
        return "echo : "+word;
    }
}
