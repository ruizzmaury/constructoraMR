package com.example.constructora.view;


import com.example.constructora.JDBCRepository.PagosServiceImplJDBC;
import com.example.constructora.JDBCRepository.PagosServiceJDBC;
import com.example.constructora.JDBCRepository.TrabajadorServiceImplJDBC;
import com.example.constructora.JDBCRepository.TrabajadorServiceJDBC;
import com.example.constructora.domain.Pago;
import com.example.constructora.domain.Trabajador;
import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class ConsultaPagosView implements ActionListener {


    private final PagosServiceJDBC pagosServiceJDBC = new PagosServiceImplJDBC();
    private final TrabajadorServiceJDBC trabajadorServiceJDBC = new TrabajadorServiceImplJDBC();

    private JFrame f;
    private JLabel title;
    private JButton backButton;
    private JButton editButton;
    private JTable tablaTrabajadores;

    private TableCellRenderer tableRenderer;


    String[] columnNames = {
            "TrabajadorDNI",
            "nombreTrabajador",
            "idObra",
            "fechaPago",
            "horas",
            "cantidad",
            "Editar"
    };

    Object[][] listaPagos; //lista información de la BD

    private void loadPagos() {
        List<Pago> pagosListDB = pagosServiceJDBC.getPagos();
        if (pagosListDB.size() > 0) {


            listaPagos = new Object[pagosListDB.size()][columnNames.length];

            for (int i = 0; i < pagosListDB.size(); i++) {

                Trabajador trabajador = trabajadorServiceJDBC.findByDNI(pagosListDB.get(i).getTrabajadorPago().getTrabajador_dni());

                listaPagos[i][0] = pagosListDB.get(i).getTrabajadorPago().getTrabajador_dni();
                listaPagos[i][1] = trabajador.getNombre();
                listaPagos[i][2] = pagosListDB.get(i).getIdObra();
                listaPagos[i][3] = pagosListDB.get(i).getFechaPago();
                listaPagos[i][4] = pagosListDB.get(i).getHoras();
                listaPagos[i][5] = pagosListDB.get(i).getCantidad();
                listaPagos[i][6] = "editButton";

            }

            System.out.println("DESDE ARRAY");
            System.out.println(listaPagos[0][0]);
        } else {
            listaPagos = new Object[0][0];
        }


    }


    public ConsultaPagosView() throws HeadlessException {
        f = new JFrame();
        JPanel listPane = new JPanel();
//        editButton = new JButton("edit");
//        editButton.setFont(new Font("Arial", Font.BOLD, 12));
//        editButton.setSize(20, 20);
//        editButton.addActionListener(this);

        loadPagos();

        f.setSize( 1280, 900);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Rectangle r = f.getBounds();

        int height = r.height;
        int width = r.width;
        System.out.println("klk");
        System.out.println(height + " x " + width);
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

        title = new JLabel("PAGOS");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setSize(300, 30);
        title.setLocation(width/3, 50);
        // f.add(title);
        listPane.add(title);
        f.add(listPane,BorderLayout.PAGE_START);

        tablaTrabajadores = new JTable(listaPagos, columnNames);
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
            SecondaryMenu secondaryMenu = new SecondaryMenu("Pago");
            secondaryMenu.setVisible(true);
            f.dispose();
        }
    }
}
