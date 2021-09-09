package com.example.constructora.view;

import com.example.constructora.JDBCRepository.*;
import com.example.constructora.JDBCRepository.utilsJDBC.JDBCUtils;
import com.example.constructora.view.utils.StyledButtonUI;
import com.toedter.calendar.JDateChooser;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class DateRangeReportMenu extends JDialog implements ActionListener {
    private final TrabajadorServiceJDBC trabajadorServiceJDBC = new TrabajadorServiceImplJDBC();
    private final RepWorkerServiceJDBC reportWorkerServiceJDBC = new RepWorkerServiceImplJDBC();

    private JComboBox<String> workerName;
    private JButton createReportButton;
    private JButton deleteWorkerFromListButton;
    private JButton deleteListButton;
    private JList<Object> workersDisplayedJList;
    private JLabel mensaje;
    private DefaultListModel<Object> model;
    private JScrollPane scrollLista;
    JDateChooser initialDateChooser = new JDateChooser();
    JDateChooser endDateChooser = new JDateChooser();
    private final int height;
    private final int width;

    public DateRangeReportMenu(java.awt.Frame parent, boolean modal, int width, int height) {
        super(parent, modal);
        this.width = width;
        this.height = height;
        setLocationRelativeTo(parent);
        initComponents();
    }

    private void initComponents() {

        System.out.println(this.getClass());
        System.out.println(width + " " + height);


        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("INFORMES POR TRABAJADOR Y FECHA");
        title.setFont(new Font("Calibri", Font.BOLD, 32));
        title.setSize(200, 40);
//        title.setLocation(400, height / 10);
        this.add(title);

        JLabel date = new JLabel("Seleccione intervalo de fechas: ");
        date.setFont(new Font("Calibri", Font.PLAIN, 26));
        date.setSize(400, 30);
        date.setLocation(width / 2 - 180, height / 8);
        this.add(date);

        JLabel initialDateText = new JLabel("Desde : ");
        initialDateText.setFont(new Font("Arial", Font.PLAIN, 20));
        initialDateText.setSize(100, 20);
        initialDateText.setLocation(width / 2 - 220, height / 8 + 50);
        this.add(initialDateText);

        initialDateChooser.setBounds(width / 2 - 140, height / 8 + 44, 100, 30);
        this.getContentPane().add(initialDateChooser);
        initialDateChooser.setDateFormatString("yyyy-MM-dd");


        JLabel endDateText = new JLabel("Hasta : ");
        endDateText.setFont(new Font("Arial", Font.PLAIN, 20));
        endDateText.setSize(100, 20);
        endDateText.setLocation(width / 2 + 80, height / 8 + 50);
        this.add(endDateText);

        endDateChooser.setBounds(width / 2 + 160, height / 8 + 44, 100, 30);
        this.getContentPane().add(endDateChooser);
        endDateChooser.setDateFormatString("yyyy-MM-dd");


        JLabel worker = new JLabel("Seleccione una trabajador");
        worker.setFont(new Font("Calibri", Font.PLAIN, 26));
        worker.setSize(300, 30);
        worker.setLocation(width / 2 - 180, height / 8 + 140);
        this.add(worker);


        String[] workerNames = loadWorkerNames();
        workerName = new JComboBox<>(workerNames);
        workerName.setFont(new Font("Calibri", Font.PLAIN, 15));
        workerName.setSize(190, 28);
        workerName.setLocation(width / 2 - 95, height / 7 + 170);
        this.add(workerName);

        workerName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // PARA MOSTRAR LISTA DE TRABAJADORES SELECCIONADOS PARA INFORME
                if (workerName.getSelectedItem() != null) {
                    if (!model.contains(workerName.getSelectedItem())) {
                        model.addElement(workerName.getSelectedItem());
                        workersDisplayedJList.setModel(model);
                    } else {

                        JOptionPane.showMessageDialog(null, "Trabajador ya añadido.\n Elige otro trabajador. "
                                , "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });


        workersDisplayedJList = new JList<>();
        workersDisplayedJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        //instanciamos el modelo
        model = new DefaultListModel<>();
        scrollLista = new JScrollPane();
        scrollLista.setBounds(width / 2 - 115, height / 3 + 85, 250, 140);
        scrollLista.setViewportView(workersDisplayedJList);
        this.add(scrollLista);


        deleteWorkerFromListButton = new JButton("Borrar Trabajador");
        deleteWorkerFromListButton.setFont(new Font("Calibri", Font.BOLD, 15));
        deleteWorkerFromListButton.setBounds(width / 2 - 150, height / 2 + 120, 145, 40);
        deleteWorkerFromListButton.addActionListener(this);
        deleteWorkerFromListButton.setBackground(new Color(0xbd2b2b));
        deleteWorkerFromListButton.setForeground(Color.white);
        // customize the button with your own look
        deleteWorkerFromListButton.setUI(new StyledButtonUI());
        this.add(deleteWorkerFromListButton);

        deleteListButton = new JButton("Borrar Lista");
        deleteListButton.setFont(new Font("Calibri", Font.BOLD, 15));
        deleteListButton.setBounds(width / 2 + 20, height / 2 + 120, 145, 40);
        deleteListButton.addActionListener(this);
        deleteListButton.setBackground(new Color(0xbd2b2b));
        deleteListButton.setForeground(Color.white);
        // customize the button with your own look
        deleteListButton.setUI(new StyledButtonUI());
        this.add(deleteListButton);


        createReportButton = new JButton("CREAR INFORME");
        createReportButton.setFont(new Font("Calibri", Font.BOLD, 18));
        createReportButton.setSize(160, 65);
        createReportButton.setLocation(width / 2 - 75, height / 2 + 220);
        createReportButton.addActionListener(this);
        createReportButton.setBackground(new Color(0x2dce98));
        createReportButton.setForeground(Color.white);
        // customize the button with your own look
        createReportButton.setUI(new StyledButtonUI());
        this.add(createReportButton);

        mensaje = new JLabel();
        mensaje.setBounds(width / 2 - 75, height / 2 + 220, 280, 23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(width / 2 - 245, width / 2 - 145, width / 2 - 145)
                                .addComponent(title)
                                .addContainerGap(213, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(title)
                                .addContainerGap(275, Short.MAX_VALUE))
        );

        pack();
    }

    private String[] loadWorkerNames() {
        java.util.List<String> workerNamesList = trabajadorServiceJDBC.getTrabajadoresNames();

        String[] workersArray = new String[workerNamesList.size()];
        workersArray = workerNamesList.toArray(workersArray);

        for (String s : workersArray)
            System.out.println(s);

        return workersArray;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteWorkerFromListButton) {
            deleteWorkerNameFromList(workersDisplayedJList.getSelectedIndex());
        }
        if (e.getSource() == deleteListButton) {
            borrarLista();
            mensaje.setText("Se borró toda la lista");
        }
        if (e.getSource() == createReportButton) {
            List<String> selectedWorkersToReport = new ArrayList<>();

            for (int i = 0; i < workersDisplayedJList.getModel().getSize(); i++) {

                selectedWorkersToReport.add(String.valueOf(workersDisplayedJList.getModel().getElementAt(i)));
            }

            if (!selectedWorkersToReport.isEmpty()) {
                System.out.println("desde el BOTÓN " + selectedWorkersToReport);
                reportWorkerServiceJDBC.createViewTableWorkerDate(
                        selectedWorkersToReport,
                        initialDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        endDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                        );

                // 1o - GENERAR EL INFORME JASPER
                generateWorkersReport();

                // 2o - BORRAR VIEW
                reportWorkerServiceJDBC.dropViewTableWorkerDate();
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un trabajador. "
                        , "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void generateWorkersReport() {
        System.out.println("GENEREMOS EL INFORME");
        try {
            JDialog reportWorkerDialog = new JDialog(this);
            System.out.println(getClass().getResource("/reports/FECHA_TRAB_OBRAS.jasper"));
            JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/reports/FECHA_TRAB_OBRAS.jrxml"));
            System.out.println(1);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, JDBCUtils.getConnection());
            System.out.println(2);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "trabajador.pdf");
            System.out.println(3);
            JasperViewer jasperViewer = new JasperViewer(
                    jasperPrint,
                    true
            );
            reportWorkerDialog.setContentPane(jasperViewer.getContentPane());
            reportWorkerDialog.setSize(jasperViewer.getSize());
            reportWorkerDialog.setTitle("PAGO TRABAJADOR");
            reportWorkerDialog.setVisible(true);
        } catch (JRException | SQLException | FileNotFoundException e) {
            System.out.println(e);
        }
    }


    private void deleteWorkerNameFromList(int index) {
        if (index >= 0) {
            model.removeElementAt(index);
            mensaje.setText("Se eliminó un elemento en la posición " + index);
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un indice"
                    , "Error", JOptionPane.ERROR_MESSAGE);

            mensaje.setText("NO se seleccionó ningún elemento");
        }

    }

    private void borrarLista() {
        model.clear();
    }

}

