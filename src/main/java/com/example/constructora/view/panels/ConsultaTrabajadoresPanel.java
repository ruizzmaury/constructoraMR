package com.example.constructora.view.panels;

import com.example.constructora.view.utils.DateFilter;
import com.example.constructora.view.utils.HintTextField;
import com.example.constructora.view.utils.Table;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ConsultaTrabajadoresPanel extends JPanel {
    private JTable tablaTrabajadores;
    private JButton backButton;
    private DateFilter dateFilter = null;
    private String searchFilter = "";
    Table t = new Table();

    public ConsultaTrabajadoresPanel() throws HeadlessException {
        initComponents();
        t.showTable(tablaTrabajadores, 1, searchFilter, dateFilter);
        this.setVisible(true);
    }

    private void initComponents() {
        Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();

        // width will store the width of the screen
        int width = (int)size.getWidth();

        // height will store the height of the screen
        int height = (int)size.getHeight();

        JScrollPane jScrollPane1 = new JScrollPane();
        tablaTrabajadores = new JTable();

        JLabel title = new JLabel("Trabajadores");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setSize(250, 30);
        title.setLocation(130, 15);
        this.add(title);


        JTextField tname = new HintTextField("Buscar trabajador...");
        tname.setFont(new Font("Arial", Font.PLAIN, 13));
        tname.setSize(160, 30);
        tname.setLocation(335, 15);
        this.add(tname);


        JButton btnGetText = new JButton();
        btnGetText.setSize(28, 30);
        btnGetText.setLocation(495, 15);
        btnGetText.addActionListener(e -> {
//            String message = String.format("searchName='%s'",
//                    tname.getText());
//            JOptionPane.showMessageDialog(ConsultaTrabajadoresView.this, message);
            searchFilter = tname.getText();
            t.showTable(tablaTrabajadores, 1, searchFilter, dateFilter);
        });
        ImageIcon searchIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/searchIcon.png")));
        btnGetText.setIcon(searchIcon);
        this.add(btnGetText);



        jScrollPane1.setBorder(BorderFactory.createEmptyBorder(50, 10, 70, 10));


        tablaTrabajadores.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
                }
        ));
        tablaTrabajadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("hola");
            }
        });
        jScrollPane1.setViewportView(tablaTrabajadores);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, width-340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(14, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, height-10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

    }
}
