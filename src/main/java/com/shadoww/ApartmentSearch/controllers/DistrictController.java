package com.shadoww.ApartmentSearch.controllers;

import com.shadoww.ApartmentSearch.services.DistrictsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/districts")
public class DistrictController {

    private DistrictsService districtsService;

    @Autowired
    public DistrictController(DistrictsService districtsService) {
        this.districtsService = districtsService;
    }

    @GetMapping("/{id}")
    public String showCity(@PathVariable int id, Model model) {

        model.addAttribute("district", districtsService.findOne(id).get());
        return "districts/district";
    }
}
