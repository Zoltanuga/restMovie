package com.movie.controller;

import com.movie.model.Seance;
import com.movie.service.ISeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SeanceController {
    @Autowired
    private ISeanceService seanceService;

    @RequestMapping(value = "/seances", method = RequestMethod.GET)
    public List<Seance> get() {
        return seanceService.getSeances();
    }
}
