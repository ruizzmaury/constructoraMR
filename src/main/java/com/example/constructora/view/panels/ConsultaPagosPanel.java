package com.example.constructora.view.panels;

import com.example.constructora.JDBCRepository.PagosServiceImplJDBC;
import com.example.constructora.JDBCRepository.PagosServiceJDBC;
import com.example.constructora.JDBCRepository.TrabajadorServiceImplJDBC;
import com.example.constructora.JDBCRepository.TrabajadorServiceJDBC;
import com.example.constructora.domain.Pago;
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

public class ConsultaPagosPanel extends JPanel {

    private final PagosServiceJDBC pagosServiceJDBC = new PagosServiceImplJDBC();
    private final TrabajadorServiceJDBC trabajadorServiceJDBC = new TrabajadorServiceImplJDBC();

    private JTable tablaPagos;
    private DateFilter dateFilter = null;
    private String searchFilter = "";
    JDateChooser initialDateChooser = new JDateChooser();
    JDateChooser endDateChooser = new JDateChooser();
    Table t = new Table();


    public ConsultaPagosPanel() throws HeadlessException {

        initComponents();
        t.showTable(tablaPagos, 3, searchFilter, dateFilter);
        this.setVisible(true);
    }

    private void initComponents() {

        setBounds(0, 5, 1600, 1000);
        Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();

        // width will store the width of the screen
        int width = (int)size.getWidth();

        // height will store the height of the screen
        int height = (int)size.getHeight();

        JScrollPane jScrollPane1 = new JScrollPane();
        tablaPagos = new JTable();

        JLabel title = new JLabel("Pagos");
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
            searchFilter = tname.getText();
            dateFilter = new DateFilter();
            System.out.println(dateFilter);
            t.showTable(tablaPagos, 3, searchFilter, dateFilter);
        });
        ImageIcon searchIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/searchIcon.png")));
        btnGetText.setIcon(searchIcon);
        this.add(btnGetText);


        JLabel initialDateText = new JLabel("Desde : ");
        initialDateText.setFont(new Font("Arial", Font.PLAIN, 20));
        initialDateText.setSize(100, 20);
        initialDateText.setLocation(600, 21);
        this.add(initialDateText);

        initialDateChooser.setBounds(680, 15, 100, 30);
        this.add(initialDateChooser);
        initialDateChooser.setDateFormatString("yyyy-MM-dd");


        JLabel endDateText = new JLabel("Hasta : ");
        endDateText.setFont(new Font("Arial", Font.PLAIN, 20));
        endDateText.setSize(100, 20);
        endDateText.setLocation(840, 21);
        this.add(endDateText);

        endDateChooser.setBounds(920, 15, 100, 30);
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
                        t.showTable(tablaPagos, 3, searchFilter, dateFilter);
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
                        t.showTable(tablaPagos, 3, searchFilter, dateFilter);
                    }

                }
        );


//        backButton = new JButton("VOLVER");
//        backButton.setFont(new Font("Arial", Font.BOLD, 12));
//        backButton.setSize(85, 38);
//        backButton.setLocation(8, 14);
//        backButton.addActionListener(this);
//        this.add(backButton);


        jScrollPane1.setBorder(BorderFactory.createEmptyBorder(50, 10, 70, 10));


        tablaPagos.setModel(new DefaultTableModel(
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


        tablaPagos.getSelectionModel().addListSelectionListener(e -> {

            // TODO : UPDATE ROW IN UI TABLE AND DB
            if (tablaPagos.getSelectedColumn() == ViewUtils.COLUMN_PAGOS_NAMES.length - 2) {
                // TODO : CREATE ALERT DIALOG TO CONFIRM UPDATE

                System.out.println("entro a actualizal pago");

                int row = tablaPagos.getSelectedRow();
                if (row != -1) {
                    LocalDate fechaPago = (LocalDate) tablaPagos.getValueAt(row, 4);
                    Trabajador rowWorker = trabajadorServiceJDBC.getTrabajador(tablaPagos.getValueAt(row, 1).toString());
                    // TODO : THIS UPDATE IN DB WILL BE DONE IN THE OBRAS REGISTER VIEW
                    // update worker from ui table
                    RegistroPagoPanel registroPagoPanel = new RegistroPagoPanel(
                            new Pago(
                                    (Long) tablaPagos.getValueAt(row, 0),
                                    (int) tablaPagos.getValueAt(row, 5),
                                    rowWorker,
                                    tablaPagos.getValueAt(row, 3).toString(),
                                    fechaPago,
                                    (float) tablaPagos.getValueAt(row, 6)
                            )
                    );

                    MenuLateral.loadMainScreen(registroPagoPanel);
                }

            }

            // DELETE ROW IN UI TABLE AND DB
            if (tablaPagos.getSelectedColumn() == ViewUtils.COLUMN_PAGOS_NAMES.length - 1) {
                // TODO : CREATE ALERT DIALOG TO CONFIRM REMOVED

                System.out.println("entro a borral");
                int row = tablaPagos.getSelectedRow();
                if (row != -1) {
                    int input = JOptionPane.showConfirmDialog(null, "Quieres eliminar el pago a " + tablaPagos.getValueAt(row, 2).toString()
                                    + " realizado el día " + tablaPagos.getValueAt(row, 4).toString()+ "?",
                            "Eliminar Pago",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                    if(input == 0) {
                        // remove payment from database
                        pagosServiceJDBC.delete((Long) tablaPagos.getValueAt(row, 0));

                        // remove payment from ui table
                        int modelIndex = tablaPagos.convertRowIndexToModel(row); // converts the row index in the view to the appropriate index in the model
                        DefaultTableModel model = (DefaultTableModel) tablaPagos.getModel();
                        model.removeRow(modelIndex);
                    }

                }

            }
        });

        jScrollPane1.setViewportView(tablaPagos);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, width-340, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(14, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, height-10, GroupLayout.PREFERRED_SIZE)
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
