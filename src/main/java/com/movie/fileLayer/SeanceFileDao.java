package com.movie.fileLayer;

import com.movie.model.Seance;
import com.movie.model.SeanceName;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SeanceFileDao implements ISeanceFileDao {

    public void writeSeances(List<Seance> seances, String path) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, false))) {
            for (Seance currSeance : seances) {
                bufferedWriter.write(buildString(currSeance));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Seance> getSeances(String path) {
        List<Seance> seances = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] sData = currentLine.split(" ");
                Seance seance = new Seance();
                initSeance(seance, sData, Long.parseLong(sData[0]));
                seances.add(seance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return seances;
    }

    public Seance getSeance(Long id, String path) {
        Seance seance = new Seance();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] sData = currentLine.split(" ");
                long currSeanceId = Long.parseLong(sData[0]);
                if (id == currSeanceId) {
                    initSeance(seance, sData, currSeanceId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return seance;
    }

    private void initSeance(Seance seance, String[] sData, long currSeanceId) {
        seance.setSeanceId(currSeanceId);
        seance.setName(SeanceName.valueOf(sData[1]));
        seance.setDate(sData[2]);
        seance.setPrice(Double.parseDouble(sData[3]));
        Map<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < Integer.parseInt(sData[4]); i++) {
            map.put(i, false);
        }
        for (int i = 5; i < sData.length; i++) {
            map.put(Integer.parseInt(sData[i]), true);
        }
        seance.setSeats(map);
    }

    private String buildString(Seance seance) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(seance.getSeanceId()).append(" ")
                .append(seance.getName()).append(" ")
                .append(seance.getDate()).append(" ")
                .append(seance.getPrice()).append(" ")
                .append(seance.getFullSeatsNumber()).append(" ");
        for (Map.Entry<Integer, Boolean> entry : seance.getSeats().entrySet()) {
            if (entry.getValue()) {
                stringBuilder.append(entry.getKey()).append(" ");
            }
        }
        return stringBuilder.toString().trim();
    }

}
