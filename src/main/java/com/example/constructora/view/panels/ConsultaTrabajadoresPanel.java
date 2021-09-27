package com.example.constructora.view.panels;

import com.example.constructora.JDBCRepository.*;
import com.example.constructora.view.utils.DateFilter;
import com.example.constructora.view.utils.HintTextField;
import com.example.constructora.view.utils.Table;
import com.example.constructora.view.utils.ViewUtils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ConsultaTrabajadoresPanel extends JPanel {
    private final TrabajadorServiceJDBC trabajadorServiceJDBC = new TrabajadorServiceImplJDBC();

    private JTable tablaTrabajadores;
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
        int width = (int) size.getWidth();

        // height will store the height of the screen
        int height = (int) size.getHeight();

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


        tablaTrabajadores.getSelectionModel().addListSelectionListener(e -> {

            // TODO : UPDATE ROW IN UI TABLE AND DB

            // DELETE ROW IN UI TABLE AND DB
            if (tablaTrabajadores.getSelectedColumn() == ViewUtils.COLUMN_WORKER_NAMES.length - 1) {
                // TODO : CREATE ALERT DIALOG TO CONFIRM REMOVED

                System.out.println("entro a borral");
                int row = tablaTrabajadores.getSelectedRow();
                if (row != -1) {
                    // remove worker from database
                    trabajadorServiceJDBC.delete(tablaTrabajadores.getValueAt(row, 0).toString());

                    // remove worker from ui table
                    int modelIndex = tablaTrabajadores.convertRowIndexToModel(row); // converts the row index in the view to the appropriate index in the model
                    DefaultTableModel model = (DefaultTableModel) tablaTrabajadores.getModel();
                    model.removeRow(modelIndex);
                }

            }
        });

        jScrollPane1.setViewportView(tablaTrabajadores);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, width - 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(14, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, height - 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

    }
}
