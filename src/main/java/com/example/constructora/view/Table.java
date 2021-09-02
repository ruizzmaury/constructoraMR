package com.example.constructora.view;

import com.example.constructora.JDBCRepository.*;
import com.example.constructora.Utils;
import com.example.constructora.domain.Obra;
import com.example.constructora.domain.Pago;
import com.example.constructora.domain.Trabajador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.util.List;
import java.util.Objects;

public class Table {
    private final TrabajadorServiceJDBC trabajadorServiceJDBC = new TrabajadorServiceImplJDBC();
    private final ObrasServiceJDBC obrasServiceJDBC = new ObrasServiceImplJDBC();
    private final PagosServiceJDBC pagosServiceJDBC = new PagosServiceImplJDBC();
    Object[][] listaTrabajadores; //lista información TRABAJADORES de la BD
    Object[][] listaObras; //lista información OBRAS de la BD
    Object[][] listaPagos; //lista información PAGOS de la BD




    public void showTable(JTable jTable, int i) {
        jTable.setDefaultRenderer(Object.class, new TableIcon());

        JButton btnUpdate = new JButton();
        ImageIcon updateIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/updateIcon.png")));
        btnUpdate.setIcon(updateIcon);
        btnUpdate.setName("m");
        JButton btnDelete = new JButton();
        ImageIcon deleteIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/deleteIcon.png")));
        btnDelete.setIcon(deleteIcon);
        btnDelete.setName("e");

//         if (o.equals(Trabajador.class))  {
//             System.out.println("ENTRO EN WORKERS");
//             jTable.setModel(loadTrabajadores(btnUpdate, btnDelete));
//        } else if (o.equals(Obra.class)) {
//             System.out.println("ENTRO EN OBRAS");
//             jTable.setModel(loadObras(btnUpdate, btnDelete));
//         } else {
//             System.out.println("ENTRO EN PAYMENTS");
//             jTable.setModel(loadPagos(btnUpdate, btnDelete));
//         }
        switch (i) {
            case 1 -> {
                System.out.println("ENTRO EN WORKERS");
                jTable.setModel(loadTrabajadores(btnUpdate, btnDelete));
            }
            case 2 -> {
                System.out.println("ENTRO EN OBRAS");
                jTable.setModel(loadObras(btnUpdate, btnDelete));
            }
            case 3 -> {
                System.out.println("ENTRO EN PAYMENTS");
                jTable.setModel(loadPagos(btnUpdate, btnDelete));
            }
        }


        jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
    }


    private TableModel loadTrabajadores(JButton btnUpdate, JButton btnDelete) {

        return new DefaultTableModel
                (
                        loadWorkersRows(btnUpdate, btnDelete),
                        Utils.COLUMN_WORKER_NAMES
                )
        {
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
    }

    private TableModel loadObras(JButton btnUpdate, JButton btnDelete) {

        return new DefaultTableModel
                (
                        loadObrasRows(btnUpdate, btnDelete),
                        Utils.COLUMN_OBRAS_NAMES
                )
        {
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
    }

    private TableModel loadPagos(JButton btnUpdate, JButton btnDelete) {

        return new DefaultTableModel
                (
                        loadPaymentsRows(btnUpdate, btnDelete),
                        Utils.COLUMN_PAGOS_NAMES
                )
        {
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
    }



    private Object[][] loadWorkersRows(JButton btnUpdate, JButton btnDelete) {
        List<Trabajador> trabajadoresListDB = trabajadorServiceJDBC.getTrabajadores();

        listaTrabajadores = new Object[trabajadoresListDB.size()][Utils.COLUMN_WORKER_NAMES.length];

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

    private Object[][] loadObrasRows(JButton btnUpdate, JButton btnDelete) {
        List<Obra> obrasListDB = obrasServiceJDBC.getObras();

        listaObras = new Object[obrasListDB.size()][Utils.COLUMN_OBRAS_NAMES.length];

        for (int i = 0; i < obrasListDB.size(); i++) {
            listaObras[i][0] = obrasListDB.get(i).getId();
            listaObras[i][1] = obrasListDB.get(i).getUbicacion();
            listaObras[i][2] = obrasListDB.get(i).getFechaInicio();
            listaObras[i][3] = obrasListDB.get(i).getFechaFin();
            listaObras[i][4] = obrasListDB.get(i).getDescriptor();
            listaObras[i][5] = btnUpdate;
            listaObras[i][6] = btnDelete;
        }
        return listaObras;
    }

    private Object[][] loadPaymentsRows(JButton btnUpdate, JButton btnDelete) {
        List<Pago> pagosListDB = pagosServiceJDBC.getPagos();

        if (pagosListDB.size() > 0) {
            listaPagos = new Object[pagosListDB.size()][Utils.COLUMN_PAGOS_NAMES.length];

            for (int i = 0; i < pagosListDB.size(); i++) {

                Trabajador trabajador = trabajadorServiceJDBC.findByDNI(pagosListDB.get(i).getTrabajadorPago().getTrabajador_dni());

                listaPagos[i][0] = pagosListDB.get(i).getTrabajadorPago().getTrabajador_dni();
                listaPagos[i][1] = trabajador.getNombre();
                listaPagos[i][2] = pagosListDB.get(i).getIdObra();
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
