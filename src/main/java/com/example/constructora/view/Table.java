package com.example.constructora.view;

import com.example.constructora.JDBCRepository.TrabajadorServiceImplJDBC;
import com.example.constructora.JDBCRepository.TrabajadorServiceJDBC;
import com.example.constructora.domain.Trabajador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.util.List;
import java.util.Objects;

public class Table {
    private final TrabajadorServiceJDBC trabajadorServiceJDBC = new TrabajadorServiceImplJDBC();
    Object[][] listaTrabajadores; //lista que simula la información de la BD
    Object[] columnNames = {
            "DNI",
            "Nombre",
            "Genero",
            "telefono",
            "email",
            "FDN",
            "direccion",
            "CatLaboral",
            "Actualizar",
            "Eliminar"
    };

    public void showTable(JTable tablaTrabajadores) {
        tablaTrabajadores.setDefaultRenderer(Object.class, new TableIcon());

        JButton btnUpdate = new JButton();
        ImageIcon updateIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/updateIcon.png")));
        btnUpdate.setIcon(updateIcon);
        btnUpdate.setName("m");
        JButton btnDelete = new JButton();
        ImageIcon deleteIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/deleteIcon.png")));
        btnDelete.setIcon(deleteIcon);
        btnDelete.setName("e");


        tablaTrabajadores.setModel(loadTrabajadores(btnUpdate, btnDelete));

        tablaTrabajadores.setPreferredScrollableViewportSize(tablaTrabajadores.getPreferredSize());
    }


    private TableModel loadTrabajadores(JButton btnUpdate, JButton btnDelete) {

        DefaultTableModel d = new DefaultTableModel
                (
                        loadWorkersRows(btnUpdate, btnDelete),
                        columnNames
                )
        {
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        return d;
    }

    private Object[][] loadWorkersRows(JButton btnUpdate, JButton btnDelete) {
        List<Trabajador> trabajadoresListDB = trabajadorServiceJDBC.getTrabajadores();

        listaTrabajadores = new Object[trabajadoresListDB.size()][columnNames.length];

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
}
