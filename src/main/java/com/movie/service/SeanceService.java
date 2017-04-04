package com.movie.service;
/*- просмотреть расписание;

        */

import com.movie.fileLayer.SeanceFileDao;
import com.movie.model.Seance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeanceService implements ISeanceService {

    public static final String SEANCES_FILE_PATH = "D:\\statistic\\data\\seances.txt";

    private SeanceFileDao seanceFileDao = new SeanceFileDao();

    public List<Seance> getSeances() {
        return seanceFileDao.getSeances(SEANCES_FILE_PATH);
    }

    public Seance getSeance(Long id) {
        return seanceFileDao.getSeance(id, SEANCES_FILE_PATH);
    }

    public void saveSeances(List<Seance> seances) {
        seanceFileDao.writeSeances(seances, SEANCES_FILE_PATH);
    }


}
