package com.example.constructora.view.utils;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DateFilter {
    private LocalDate initialDate;
    private LocalDate endDate;

    public DateFilter() {
    }

    public DateFilter(LocalDate initialDate, LocalDate endDate) {
        this.initialDate = initialDate;
        this.endDate = endDate;
    }
}
