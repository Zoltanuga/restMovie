package com.movie.service;

import com.movie.model.Seance;

import java.util.List;


public interface ISeanceService {

    List<Seance> getSeances();

    Seance getSeance(Long id);

    void saveSeances(List<Seance> seances);
}
