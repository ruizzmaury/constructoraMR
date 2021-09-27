package com.example.constructora.view.utils;

import com.example.constructora.JDBCRepository.*;
import com.example.constructora.domain.Obra;
import com.example.constructora.domain.Pago;
import com.example.constructora.domain.Trabajador;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class Table {
    private final TrabajadorServiceJDBC trabajadorServiceJDBC = new TrabajadorServiceImplJDBC();
    private final ObrasServiceJDBC obrasServiceJDBC = new ObrasServiceImplJDBC();
    private final PagosServiceJDBC pagosServiceJDBC = new PagosServiceImplJDBC();
    Object[][] listaTrabajadores; //lista información TRABAJADORES de la BD
    Object[][] listaObras; //lista información OBRAS de la BD
    Object[][] listaPagos; //lista información PAGOS de la BD


    public void showTable(JTable jTable, int selectedView, String searchFilter, DateFilter dateFilter) {
        jTable.setDefaultRenderer(Object.class, new TableIcon());

        JButton btnUpdate = new JButton();
        ImageIcon updateIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/updateIcon.png")));
        btnUpdate.setIcon(updateIcon);
        btnUpdate.setName("m");

        JButton btnDelete = new JButton();
        ImageIcon deleteIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/deleteIcon.png")));
        btnDelete.setIcon(deleteIcon);
        btnDelete.setName("e");


        switch (selectedView) {
            case 1 -> {
                System.out.println("ENTRO EN WORKERS");
                jTable.setModel(loadTrabajadores(btnUpdate, btnDelete, searchFilter, dateFilter));
            }
            case 2 -> {
                System.out.println("ENTRO EN OBRAS");
                jTable.setModel(loadObras(btnUpdate, btnDelete, searchFilter, dateFilter));
            }
            case 3 -> {
                System.out.println("ENTRO EN PAYMENTS");
                jTable.setModel(loadPagos(btnUpdate, btnDelete, searchFilter, dateFilter));
            }
        }


        jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
    }



    private TableModel loadTrabajadores(JButton btnUpdate, JButton btnDelete, String searchFilter, DateFilter dateFilter) {

        return new DefaultTableModel
                (
                        loadWorkersRows(btnUpdate, btnDelete, searchFilter, dateFilter),
                        ViewUtils.COLUMN_WORKER_NAMES
                ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private TableModel loadObras(JButton btnUpdate, JButton btnDelete, String searchFilter, DateFilter dateFilter) {

        return new DefaultTableModel
                (
                        loadObrasRows(btnUpdate, btnDelete, searchFilter, dateFilter),
                        ViewUtils.COLUMN_OBRAS_NAMES
                ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private TableModel loadPagos(JButton btnUpdate, JButton btnDelete, String searchFilter, DateFilter dateFilter) {

        return new DefaultTableModel
                (
                        loadPaymentsRows(btnUpdate, btnDelete, searchFilter, dateFilter),
                        ViewUtils.COLUMN_PAGOS_NAMES
                ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }


    private Object[][] loadWorkersRows(JButton btnUpdate, JButton btnDelete, String searchFilter, DateFilter dateFilter) {
        List<Trabajador> trabajadoresListDB;

        if (searchFilter.isEmpty()) {
            trabajadoresListDB = trabajadorServiceJDBC.getTrabajadores();
        } else {
            trabajadoresListDB = trabajadorServiceJDBC.findByNombreStartingWith(searchFilter);
        }

        listaTrabajadores = new Object[trabajadoresListDB.size()][ViewUtils.COLUMN_WORKER_NAMES.length];

        for (int i = 0; i < trabajadoresListDB.size(); i++) {
            listaTrabajadores[i][0] = trabajadoresListDB.get(i).getTrabajador_dni();
            listaTrabajadores[i][1] = trabajadoresListDB.get(i).getNombre();
            listaTrabajadores[i][2] = trabajadoresListDB.get(i).getGenero().getNombreGenero();
            listaTrabajadores[i][3] = trabajadoresListDB.get(i).getTelefono();
            listaTrabajadores[i][4] = trabajadoresListDB.get(i).getEmail();
            listaTrabajadores[i][5] = trabajadoresListDB.get(i).getFechaNacimiento();
            listaTrabajadores[i][6] = trabajadoresListDB.get(i).getDireccion();
            listaTrabajadores[i][7] = trabajadoresListDB.get(i).getCatLaboral().getNombreCategoria();
            listaTrabajadores[i][8] = btnUpdate;
            listaTrabajadores[i][9] = btnDelete;

        }

        return listaTrabajadores;
    }

    private Object[][] loadObrasRows(JButton btnUpdate, JButton btnDelete, String searchFilter, DateFilter dateFilter) {
        List<Obra> obrasListDB;

        if (searchFilter.isEmpty()) {
            if (dateFilter == null) {
                obrasListDB = obrasServiceJDBC.getObras();
            } else {
                if (dateFilter.getInitialDate() == null && dateFilter.getEndDate() == null) { // no busca ni por nombre ni por fechas
                    System.out.println("ni nombre ni fechas");
                    obrasListDB = obrasServiceJDBC.getObras();
                } else { // no busca por nombre pero sí por fechas
                    if (dateFilter.getInitialDate() != null && dateFilter.getEndDate() != null) { // intervalo de fechas
                        obrasListDB = obrasServiceJDBC.findBetweenDates(dateFilter.getInitialDate(), dateFilter.getEndDate());
                    } else if (dateFilter.getInitialDate() != null && dateFilter.getEndDate() == null) { // inicial hacia delante
                        obrasListDB = obrasServiceJDBC.findByDateForward(dateFilter.getInitialDate());
                    } else { // final hacia atrás
                        obrasListDB = obrasServiceJDBC.findByDateBackward(dateFilter.getEndDate());
                    }
                }
            }

        } else {
            System.out.println("hola");
            obrasListDB = obrasServiceJDBC.findByNameAndDate(searchFilter, dateFilter.getInitialDate(), dateFilter.getEndDate());
        }

        if (obrasListDB.size() > 0) {
            listaObras = new Object[obrasListDB.size()][ViewUtils.COLUMN_OBRAS_NAMES.length];

            for (int i = 0; i < obrasListDB.size(); i++) {
                listaObras[i][0] = obrasListDB.get(i).getDescriptor();
                listaObras[i][1] = obrasListDB.get(i).getUbicacion();
                listaObras[i][2] = obrasListDB.get(i).getFechaInicio();
                listaObras[i][3] = obrasListDB.get(i).getFechaFin();
                listaObras[i][4] = btnUpdate;
                listaObras[i][5] = btnDelete;
            }
        } else {
            listaObras = new Object[0][0];
        }
        return listaObras;
    }

    private Object[][] loadPaymentsRows(JButton btnUpdate, JButton btnDelete, String searchFilter, DateFilter dateFilter) {
        List<Pago> pagosListDB;

        if (searchFilter.isEmpty()) {
            if (dateFilter == null) {
                pagosListDB = pagosServiceJDBC.getPagos();
            } else {
                if (dateFilter.getInitialDate() == null && dateFilter.getEndDate() == null) { // no busca ni por nombre ni por fechas
                    System.out.println("ni nombre ni fechas");
                    pagosListDB = pagosServiceJDBC.getPagos();
                } else { // no busca por nombre pero sí por fechas
                    if (dateFilter.getInitialDate() != null && dateFilter.getEndDate() != null) { // intervalo de fechas
                        pagosListDB = pagosServiceJDBC.findBetweenDates(dateFilter.getInitialDate(), dateFilter.getEndDate());
                    } else if (dateFilter.getInitialDate() != null && dateFilter.getEndDate() == null) { // inicial hacia delante
                        pagosListDB = pagosServiceJDBC.findByDateForward(dateFilter.getInitialDate());
                    } else { // final hacia atrás
                        pagosListDB = pagosServiceJDBC.findByDateBackward(dateFilter.getEndDate());
                    }
                }
            }

        } else {
            pagosListDB = pagosServiceJDBC.findByNameAndDate(searchFilter, dateFilter.getInitialDate(), dateFilter.getEndDate());
        }

        if (pagosListDB.size() > 0) {
            listaPagos = new Object[pagosListDB.size()][ViewUtils.COLUMN_PAGOS_NAMES.length];

            for (int i = 0; i < pagosListDB.size(); i++) {

                Trabajador trabajador = trabajadorServiceJDBC.findByDNI(pagosListDB.get(i).getTrabajadorPago().getTrabajador_dni());

                listaPagos[i][0] = pagosListDB.get(i).getTrabajadorPago().getTrabajador_dni();
                listaPagos[i][1] = trabajador.getNombre();
                listaPagos[i][2] = pagosListDB.get(i).getObraDescriptor();
                listaPagos[i][3] = pagosListDB.get(i).getFechaPago();
                listaPagos[i][4] = pagosListDB.get(i).getHoras();
                listaPagos[i][5] = pagosListDB.get(i).getCantidad();
                listaPagos[i][6] = btnUpdate;
                listaPagos[i][7] = btnDelete;

            }
        } else {
            listaPagos = new Object[0][0];
        }

        return listaPagos;
    }


}
