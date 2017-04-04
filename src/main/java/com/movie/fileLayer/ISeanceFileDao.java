package com.movie.fileLayer;


import com.movie.model.Seance;

import java.util.List;

public interface ISeanceFileDao {
    void writeSeances(List<Seance> seances, String path);

    List<Seance> getSeances(String path);

    Seance getSeance(Long id, String path);

}
