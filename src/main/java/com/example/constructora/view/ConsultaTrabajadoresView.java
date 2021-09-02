package com.example.constructora.view;

import com.example.constructora.JDBCRepository.TrabajadorServiceImplJDBC;
import com.example.constructora.JDBCRepository.TrabajadorServiceJDBC;
import com.example.constructora.Utils;


import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultaTrabajadoresView extends JFrame implements ActionListener {

    private final TrabajadorServiceJDBC trabajadorServiceJDBC = new TrabajadorServiceImplJDBC();
    private JTable tablaTrabajadores;
    private JButton backButton;
    Table t = new Table();


    public ConsultaTrabajadoresView() throws HeadlessException {
        initComponents();
        t.showTable(tablaTrabajadores);
        this.setVisible(true);
    }

    private void initComponents() {
        JScrollPane jScrollPane1 = new JScrollPane();
        tablaTrabajadores= new JTable();

        JLabel title = new JLabel("Trabajadores");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setSize(250, 30);
        title.setLocation(130, 15);
        this.add(title);


        JTextField tname = new HintTextField("Buscar trabajador...");
        tname.setFont(new Font("Arial", Font.PLAIN, 13));
        tname.setSize(190, 30);
        tname.setLocation(335, 15);
        this.add(tname);

        JButton btnGetText = new JButton("Get text");
        btnGetText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = String.format("searchName='%s'",
                        tname.getText());
                JOptionPane.showMessageDialog(ConsultaTrabajadoresView.this, message);
            }
        });
        this.add(btnGetText);

        JLabel initialDate = new JLabel("Desde : ");
        initialDate.setFont(new Font("Arial", Font.PLAIN, 20));
        initialDate.setSize(100, 20);
        initialDate.setLocation(570, 24);
        this.add(initialDate);

        JComboBox<String> dateI = new JComboBox<>(Utils.DAYS);
        dateI.setFont(new Font("Arial", Font.PLAIN, 15));
        dateI.setSize(60, 20);
        dateI.setLocation(650, 24);
        this.add(dateI);

        JComboBox<String> monthI = new JComboBox<>(Utils.MONTHS);
        monthI.setFont(new Font("Arial", Font.PLAIN, 15));
        monthI.setSize(60, 20);
        monthI.setLocation(720, 24);
        this.add(monthI);

        JComboBox<String> yearI = new JComboBox<>(Utils.DOBYEARS);
        yearI.setFont(new Font("Arial", Font.PLAIN, 15));
        yearI.setSize(60, 26);
        yearI.setLocation(790, 24);
        this.add(yearI);


        JLabel endDate = new JLabel("Hasta : ");
        endDate.setFont(new Font("Arial", Font.PLAIN, 20));
        endDate.setSize(100, 20);
        endDate.setLocation(920, 24);
        this.add(endDate);

        JComboBox<String> dateE = new JComboBox<>(Utils.DAYS);
        dateE.setFont(new Font("Arial", Font.PLAIN, 15));
        dateE.setSize(60, 20);
        dateE.setLocation(1000, 24);
        this.add(dateE);

        JComboBox<String> monthE = new JComboBox<>(Utils.MONTHS);
        monthE.setFont(new Font("Arial", Font.PLAIN, 15));
        monthE.setSize(60, 20);
        monthE.setLocation(1070, 24);
        this.add(monthE);

        JComboBox<String> yearE = new JComboBox<>(Utils.DOBYEARS);
        yearE.setFont(new Font("Arial", Font.PLAIN, 15));
        yearE.setSize(60, 20);
        yearE.setLocation(1140, 24);
        this.add(yearE);

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
        setTitle("Trabajadores registrados");

        tablaTrabajadores.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaTrabajadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("hola");
            }
        });
        jScrollPane1.setViewportView(tablaTrabajadores);

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


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            SecondaryMenu secondaryMenu = new SecondaryMenu("Trabajador");
            secondaryMenu.setVisible(true);
            this.dispose();
        }
    }

    private String[] loadWorkersNames() {
        List<String> workersList = trabajadorServiceJDBC.getTrabajadoresNames();

        String[] workersArray = new String[workersList.size()];
        workersArray = workersList.toArray(workersArray);

        for(String s : workersArray)
            System.out.println(s);

        return workersArray;
    }
}
