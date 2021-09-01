package com.example.constructora.view;

import com.example.constructora.JDBCRepository.TrabajadorServiceImplJDBC;
import com.example.constructora.JDBCRepository.TrabajadorServiceJDBC;
import com.example.constructora.domain.Trabajador;


import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultaTrabajadoresView implements ActionListener {

    private final TrabajadorServiceJDBC trabajadorServiceJDBC = new TrabajadorServiceImplJDBC();

    private JFrame f;
    private JLabel title;
    private JButton backButton;
    private JButton editButton;
    private JTable tablaTrabajadores;

    private TableCellRenderer tableRenderer;


    String[] columnNames = {
            "DNI",
            "Nombre",
            "Genero",
            "telefono",
            "email",
            "FDN",
            "direccion",
            "CatLaboral",
            "Editar"
    };

    Object[][] listaTrabajadores; //lista que simula la información de la BD

    private void loadTrabajadores() {
        List<Trabajador> trabajadoresListDB = trabajadorServiceJDBC.getTrabajadores();
        System.out.println(trabajadoresListDB.get(0));


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
            listaTrabajadores[i][8] = "editButton";

        }

        System.out.println("DESDE ARRAY");
        System.out.println(listaTrabajadores[0][0]);

    }


    public ConsultaTrabajadoresView() throws HeadlessException {
        f = new JFrame();
        JPanel listPane = new JPanel();
//        editButton = new JButton("edit");
//        editButton.setFont(new Font("Arial", Font.BOLD, 12));
//        editButton.setSize(20, 20);
//        editButton.addActionListener(this);

        loadTrabajadores();

        f.setSize( 1280, 900);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Rectangle r = f.getBounds();

        int height = r.height;
        int width = r.width;
        System.out.println("klk");
        System.out.println(height + " x " + width);
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

        title = new JLabel("TRABAJADORES");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setSize(300, 30);
        title.setLocation(width/3, 50);
        // f.add(title);
        listPane.add(title);
        f.add(listPane,BorderLayout.PAGE_START);

        tablaTrabajadores = new JTable(listaTrabajadores, columnNames);
        tablaTrabajadores.setBounds(100, 100, 1000, 800);

        JScrollPane sp = new JScrollPane(tablaTrabajadores);

        f.add(sp,BorderLayout.CENTER);


        backButton = new JButton("VOLVER");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setSize(120, 40);
        backButton.setLocation(width/3, 820);
        backButton.addActionListener(this);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(backButton);

        //f.add(backButton);
        f.add(buttonPane,BorderLayout.PAGE_END);

        f.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            SecondaryMenu secondaryMenu = new SecondaryMenu("Trabajador");
            secondaryMenu.setVisible(true);
            f.dispose();
        }
    }
}
