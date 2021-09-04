package com.example.constructora.view;

import com.example.constructora.view.utils.DateFilter;
import com.example.constructora.view.utils.HintTextField;
import com.example.constructora.view.utils.Table;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.Objects;

public class ConsultaObrasView extends JFrame implements ActionListener {

    private JTable tablaObras;
    private JButton backButton;
    private DateFilter dateFilter = null;
    private String searchFilter = "";
    Table t = new Table();


    public ConsultaObrasView() throws HeadlessException {
        initComponents();
        t.showTable(tablaObras, 2, searchFilter, dateFilter);
        this.setVisible(true);
    }

    private void initComponents() {
        JScrollPane jScrollPane1 = new JScrollPane();
        tablaObras = new JTable();

        JLabel title = new JLabel("Obras");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setSize(250, 30);
        title.setLocation(130, 15);
        this.add(title);


        JTextField tname = new HintTextField("OBRA DESCRIPTOR...");
        tname.setFont(new Font("Arial", Font.PLAIN, 13));
        tname.setSize(160, 30);
        tname.setLocation(335, 15);
        this.add(tname);


        JButton btnGetText = new JButton();
        btnGetText.setSize(28, 30);
        btnGetText.setLocation(495, 15);
        btnGetText.addActionListener(e -> {
            searchFilter = tname.getText();
            dateFilter = new DateFilter();
            System.out.println(dateFilter);
            t.showTable(tablaObras, 2, searchFilter, dateFilter);
        });
        ImageIcon searchIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/searchIcon.png")));
        btnGetText.setIcon(searchIcon);
        this.add(btnGetText);

        JLabel initialDateText = new JLabel("Desde : ");
        initialDateText.setFont(new Font("Arial", Font.PLAIN, 20));
        initialDateText.setSize(100, 20);
        initialDateText.setLocation(600, 21);
        this.add(initialDateText);

        JDateChooser initialDateChooser = new JDateChooser();
        initialDateChooser.setBounds(680, 15,100,30);
        this.getContentPane().add(initialDateChooser);
        initialDateChooser.setDateFormatString("yyyy-MM-dd");


        JLabel endDateText = new JLabel("Hasta : ");
        endDateText.setFont(new Font("Arial", Font.PLAIN, 20));
        endDateText.setSize(100, 20);
        endDateText.setLocation(840, 21);
        this.add(endDateText);

        JDateChooser endDateChooser = new JDateChooser();
        endDateChooser.setBounds(920, 15,100,30);
        this.getContentPane().add(endDateChooser);
        endDateChooser.setDateFormatString("yyyy-MM-dd");

        initialDateChooser.getDateEditor().addPropertyChangeListener(
                e -> {
                    if ("date".equals(e.getPropertyName())) {
                        if (endDateChooser.getDate() == null) { // si no hay fecha final,
                            // únicamente filtramos fecha inicial hacia delante indefinidamente
                            dateFilter = new DateFilter(
                                    initialDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                                    null
                            );
                        } else {
                            loadFullDateFilter(initialDateChooser, endDateChooser);
                        }
                        t.showTable(tablaObras, 2, searchFilter, dateFilter);
                    }

                }
        );


        endDateChooser.getDateEditor().addPropertyChangeListener(
                e -> {
                    if ("date".equals(e.getPropertyName())) {
                        if (initialDateChooser.getDate() == null) {// si no hay fecha inicial,
                            // únicamente filtramos fecha final hacia atrás indefinidamente
                            dateFilter = new DateFilter(
                                    null,
                                    endDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                            );
                        } else {
                            loadFullDateFilter(initialDateChooser, endDateChooser);
                        }
                        t.showTable(tablaObras, 2, searchFilter, dateFilter);
                    }

                }
        );



        backButton = new JButton("VOLVER");
        backButton.setFont(new Font("Arial", Font.BOLD, 12));
        backButton.setSize(85, 38);
        backButton.setLocation(8, 14);
        backButton.addActionListener(this);
        this.add(backButton);

        // TODO FOR CONSULTAPAGOSVIEW
//        String[] workersNamesList = loadWorkersNames();
//        JComboBox<String> trabajadorName = new JComboBox<>(workersNamesList);
//        trabajadorName.setFont(new Font("Arial", Font.PLAIN, 15));
//        trabajadorName.setSize(190, 20);
//        trabajadorName.setLocation(400, 15);
//        this.add(trabajadorName);

        jScrollPane1.setBorder(BorderFactory.createEmptyBorder(50,10,70,10));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Obras registradas");

        tablaObras.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null}
                },
                new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
                }
        ));
        tablaObras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("hola");
            }
        });
        jScrollPane1.setViewportView(tablaObras);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(14, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);

    }

    private void loadFullDateFilter(JDateChooser initialDateChooser, JDateChooser endDateChooser) {
        dateFilter = new DateFilter(
                initialDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                endDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            SecondaryMenu secondaryMenu = new SecondaryMenu("Obra");
            secondaryMenu.setVisible(true);
            this.dispose();
        }
    }

}
