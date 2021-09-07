package com.example.constructora.JDBCRepository;

import com.example.constructora.domain.ReportByObra;

import java.util.List;

public interface ReportObraServiceJDBC {

    void createViewTableObras(List<String> selectedObras);

    ReportByObra create(ReportByObra reportByObra);

    List<ReportByObra> getSelectedObras();

    ReportByObra getSelectedObra();

    ReportByObra update(ReportByObra reportByObra);

    void delete(String id);

    void deleteViewTableObras();

}
