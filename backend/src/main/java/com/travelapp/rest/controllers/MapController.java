package com.travelapp.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapController {

    @GetMapping("/")
    public String showMap() {
        return "index";
    }
}
