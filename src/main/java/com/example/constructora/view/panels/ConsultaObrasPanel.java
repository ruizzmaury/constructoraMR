package com.example.constructora.view.panels;

import com.example.constructora.JDBCRepository.ObrasServiceImplJDBC;
import com.example.constructora.JDBCRepository.ObrasServiceJDBC;
import com.example.constructora.JDBCRepository.TrabajadorServiceImplJDBC;
import com.example.constructora.JDBCRepository.TrabajadorServiceJDBC;
import com.example.constructora.domain.CategoriaLaboral;
import com.example.constructora.domain.Genero;
import com.example.constructora.domain.Obra;
import com.example.constructora.domain.Trabajador;
import com.example.constructora.view.MenuLateral;
import com.example.constructora.view.utils.DateFilter;
import com.example.constructora.view.utils.HintTextField;
import com.example.constructora.view.utils.Table;
import com.example.constructora.view.utils.ViewUtils;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

public class ConsultaObrasPanel extends JPanel {

    private final ObrasServiceJDBC obrasServiceJDBC = new ObrasServiceImplJDBC();

    private JTable tablaObras;
    private JButton backButton;
    private DateFilter dateFilter = null;
    private String searchFilter = "";
    Table t = new Table();


    public ConsultaObrasPanel() throws HeadlessException {
        initComponents();
        t.showTable(tablaObras, 2, searchFilter, dateFilter);
        this.setVisible(true);
    }

    private void initComponents() {

        Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();

        // width will store the width of the screen
        int width = (int) size.getWidth();

        // height will store the height of the screen
        int height = (int) size.getHeight();
        setBounds(0, 5, width-300, height-10);

        JScrollPane jScrollPane1 = new JScrollPane();
        tablaObras = new JTable();

        JLabel title = new JLabel("Obras");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setSize(200, 30);
        title.setLocation(130, 15);
        this.add(title);


        JTextField tname = new HintTextField("OBRA DESCRIPTOR...");
        tname.setFont(new Font("Arial", Font.PLAIN, 13));
        tname.setSize(160, 30);
        tname.setLocation(255, 15);
        this.add(tname);


        JButton btnGetText = new JButton();
        btnGetText.setSize(28, 30);
        btnGetText.setLocation(415, 15);
        btnGetText.addActionListener(e -> {
            searchFilter = tname.getText();
            dateFilter = new DateFilter();
            System.out.println(dateFilter);
            t.showTable(tablaObras, 2, searchFilter, dateFilter);
        });
        System.out.println("DESDE CONSULTAAAAAAAAAAAAAAAAAAAAAAAAS");
        System.out.println(getClass().getResource("/images/searchIcon.png"));
        ImageIcon searchIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/searchIcon.png")));
        btnGetText.setIcon(searchIcon);
        this.add(btnGetText);

        JLabel initialDateText = new JLabel("Desde : ");
        initialDateText.setFont(new Font("Arial", Font.PLAIN, 20));
        initialDateText.setSize(100, 20);
        initialDateText.setLocation(500, 21);
        this.add(initialDateText);

        JDateChooser initialDateChooser = new JDateChooser();
        initialDateChooser.setBounds(580, 15, 100, 30);
        this.add(initialDateChooser);
        initialDateChooser.setDateFormatString("yyyy-MM-dd");


        JLabel endDateText = new JLabel("Hasta : ");
        endDateText.setFont(new Font("Arial", Font.PLAIN, 20));
        endDateText.setSize(100, 20);
        endDateText.setLocation(740, 21);
        this.add(endDateText);

        JDateChooser endDateChooser = new JDateChooser();
        endDateChooser.setBounds(820, 15, 100, 30);
        this.add(endDateChooser);
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


        jScrollPane1.setBorder(BorderFactory.createEmptyBorder(50, 10, 70, 10));


        tablaObras.setModel(new javax.swing.table.DefaultTableModel(
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


        tablaObras.getSelectionModel().addListSelectionListener(e -> {

            // TODO : UPDATE ROW IN UI TABLE AND DB
            if (tablaObras.getSelectedColumn() == ViewUtils.COLUMN_OBRAS_NAMES.length - 2) {
                // TODO : CREATE ALERT DIALOG TO CONFIRM UPDATE

                System.out.println("entro a actualizal obras");

                int row = tablaObras.getSelectedRow();
                if (row != -1) {
                    LocalDate fechaInicio = (LocalDate) tablaObras.getValueAt(row, 2);
                    LocalDate fechaFin = (LocalDate) tablaObras.getValueAt(row, 3);
                    // TODO : THIS UPDATE IN DB WILL BE DONE IN THE OBRAS REGISTER VIEW
                    // update worker from ui table
                    RegistroObraPanel registroObraPanel = new RegistroObraPanel(
                            new Obra(
                                    tablaObras.getValueAt(row, 1).toString(),
                                    tablaObras.getValueAt(row, 0).toString(),
                                    fechaInicio,
                                    fechaFin
                            )
                    );

                    MenuLateral.loadMainScreen(registroObraPanel);
                }

            }

            // DELETE ROW IN UI TABLE AND DB
            if (tablaObras.getSelectedColumn() == ViewUtils.COLUMN_OBRAS_NAMES.length - 1) {
                // TODO : CREATE ALERT DIALOG TO CONFIRM REMOVED

                System.out.println("entro a borral");
                int row = tablaObras.getSelectedRow();

                if (row != -1) {
                    int input = JOptionPane.showConfirmDialog(null, "Quieres eliminar " + tablaObras.getValueAt(row, 0).toString() + "?",
                            "Eliminar Obra",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (input == 0) {
                        // remove worker from database
                        obrasServiceJDBC.delete(tablaObras.getValueAt(row, 0).toString());

                        // remove worker from ui table
                        int modelIndex = tablaObras.convertRowIndexToModel(row); // converts the row index in the view to the appropriate index in the model
                        DefaultTableModel model = (DefaultTableModel) tablaObras.getModel();
                        model.removeRow(modelIndex);
                    }
                }


            }
        });
        jScrollPane1.setViewportView(tablaObras);

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

    private void loadFullDateFilter(JDateChooser initialDateChooser, JDateChooser endDateChooser) {
        dateFilter = new DateFilter(
                initialDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                endDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );
    }
}
