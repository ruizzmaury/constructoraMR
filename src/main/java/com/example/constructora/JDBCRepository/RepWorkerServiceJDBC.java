package com.example.constructora.JDBCRepository;

import com.example.constructora.domain.RepWorkerDate;

import java.time.LocalDate;
import java.util.List;

public interface RepWorkerServiceJDBC {

    void createViewTableWorkerDate(List<String> selectedWorkers, LocalDate init, LocalDate end);

    RepWorkerDate create(RepWorkerDate repWorkerDate);

    List<RepWorkerDate> getReports();

    RepWorkerDate getReport();

    RepWorkerDate update(RepWorkerDate repWorkerDate);

    void delete(String id);

    void dropViewTableWorkerDate();
}
