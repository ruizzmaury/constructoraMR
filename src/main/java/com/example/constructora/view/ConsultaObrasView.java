package com.example.constructora.view;

import com.example.constructora.JDBCRepository.ObrasServiceImplJDBC;
import com.example.constructora.JDBCRepository.ObrasServiceJDBC;
import com.example.constructora.domain.Obra;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultaObrasView implements ActionListener {

        final ObrasServiceJDBC obrasServiceJDBC = new ObrasServiceImplJDBC();

        private JFrame f;
        private JLabel title;
        private JButton backButton;
        private JButton editButton;
        private JTable tablaObras;

        private TableCellRenderer tableRenderer;


        String[] columnNames = {
                "id",
                "Ubicación",
                "Fecha Inicio",
                "Fecha Fin",
                "Descripcion",
                "Editar"
        };

        Object[][] listaObras; //lista que simula la información de la BD

        private void loadObras() {
            List<Obra> obrasListDB = obrasServiceJDBC.getObras();
            System.out.println(obrasListDB.get(0));


            listaObras = new Object[obrasListDB.size()][columnNames.length];

            for (int i = 0; i < obrasListDB.size(); i++) {
                listaObras[i][0] = obrasListDB.get(i).getId();
                listaObras[i][1] = obrasListDB.get(i).getUbicacion();
                listaObras[i][2] = obrasListDB.get(i).getFechaInicio();
                listaObras[i][3] = obrasListDB.get(i).getFechaFin();
                listaObras[i][4] = obrasListDB.get(i).getDescriptor();
                listaObras[i][5] = "editButton";

            }

            System.out.println("DESDE ARRAY");
            System.out.println(listaObras[0][0]);

        }


        public ConsultaObrasView() throws HeadlessException {

            f = new JFrame();
            JPanel listPane = new JPanel();
//        editButton = new JButton("edit");
//        editButton.setFont(new Font("Arial", Font.BOLD, 12));
//        editButton.setSize(20, 20);
//        editButton.addActionListener(this);

            loadObras();

            f.setSize( 1280, 900);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Rectangle r = f.getBounds();

            int height = r.height;
            int width = r.width;
            System.out.println("klk");
            System.out.println(height + " x " + width);
            listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

            title = new JLabel("OBRAS");
            title.setFont(new Font("Arial", Font.BOLD, 36));
            title.setSize(300, 30);
            title.setLocation(width/3, 50);
            // f.add(title);
            listPane.add(title);
            f.add(listPane,BorderLayout.PAGE_START);

            tablaObras = new JTable(listaObras, columnNames);
            tablaObras.setBounds(100, 100, 1000, 800);

            JScrollPane sp = new JScrollPane(tablaObras);

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
                SecondaryMenu secondaryMenu = new SecondaryMenu("Obra");
                secondaryMenu.setVisible(true);
                f.dispose();
            }
        }
}
