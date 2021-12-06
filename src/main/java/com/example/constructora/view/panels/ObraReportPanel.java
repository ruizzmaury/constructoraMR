package com.example.constructora.view.panels;

import com.example.constructora.JDBCRepository.ObrasServiceImplJDBC;
import com.example.constructora.JDBCRepository.ObrasServiceJDBC;
import com.example.constructora.JDBCRepository.ReportObraServiceImplJDBC;
import com.example.constructora.JDBCRepository.ReportObraServiceJDBC;
import com.example.constructora.JDBCRepository.utilsJDBC.JDBCUtils;
import com.example.constructora.view.utils.StyledButtonUI;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObraReportPanel extends JPanel implements ActionListener {
    private final ObrasServiceJDBC obrasServiceJDBC = new ObrasServiceImplJDBC();
    private final ReportObraServiceJDBC reportObraServiceJDBC = new ReportObraServiceImplJDBC();

    private JComboBox<String> obraDescriptor;
    private JButton createReportButton;
    private JButton deleteObraFromListButton;
    private JButton deleteListButton;
    private JList<Object> obrasDisplayedJList;
    private JLabel mensaje;
    private DefaultListModel<Object> model;
    private JScrollPane scrollLista;
    private final int height;
    private final int width;

    public ObraReportPanel(java.awt.Frame parent, boolean modal, int width, int height) {

        this.width = width;
        this.height = height;
        initComponents();
    }

    private void initComponents() {
        Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();

        // width will store the width of the screen
        int width = (int)size.getWidth();

        // height will store the height of the screen
        int height = (int)size.getHeight();
        setBounds(0, 5, width-310, height-10);
        System.out.println(this.getClass());
        System.out.println(width + " " + height);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(new Color(0xffcc99));

        JLabel title = new JLabel("INFORMES POR OBRA");
        title.setFont(new Font("Calibri", Font.BOLD, 32));
        title.setSize(width, 40);
        title.setLocation(20, 20);
        this.add(title);


        JLabel obra = new JLabel("- Seleccione una obra : ");
        obra.setFont(new Font("Calibri", Font.PLAIN, 26));
        obra.setSize(300, 30);
        obra.setLocation(150, height / 8);
        this.add(obra);


        String[] obrasDescriptors = loadObrasDescriptors();
        obraDescriptor = new JComboBox<>(obrasDescriptors);
        obraDescriptor.setFont(new Font("Calibri", Font.PLAIN, 15));
        obraDescriptor.setSize(190, 28);
        obraDescriptor.setLocation(170, height / 7 + 30);
        this.add(obraDescriptor);

        obraDescriptor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // PARA MOSTRAR LISTA DE OBRAS SELECCIONADAS PARA INFORME
                if (obraDescriptor.getSelectedItem() != null) {
                    if (!model.contains(obraDescriptor.getSelectedItem())) {
                        model.addElement(obraDescriptor.getSelectedItem());
                        obrasDisplayedJList.setModel(model);
                    } else {

                        JOptionPane.showMessageDialog(null, "Obra ya añadida.\n Elige otra obra. "
                                , "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });


        obrasDisplayedJList = new JList<>();
        obrasDisplayedJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        //instanciamos el modelo
        model = new DefaultListModel<>();
        scrollLista = new JScrollPane();
        scrollLista.setBounds(170, height / 7 + 70, 250, 140);
        scrollLista.setViewportView(obrasDisplayedJList);
        this.add(scrollLista);


        deleteObraFromListButton = new JButton("Borrar Obra");
        deleteObraFromListButton.setFont(new Font("Calibri", Font.BOLD, 15));
        deleteObraFromListButton.setBounds(170, height / 7 + 250, 110, 33);
        deleteObraFromListButton.addActionListener(this);
        deleteObraFromListButton.setBackground(new Color(0xbd2b2b));
        deleteObraFromListButton.setForeground(Color.white);
        // customize the button with your own look
        deleteObraFromListButton.setUI(new StyledButtonUI());
        this.add(deleteObraFromListButton);

        deleteListButton = new JButton("Borrar Lista");
        deleteListButton.setFont(new Font("Calibri", Font.BOLD, 15));
        deleteListButton.setBounds(300, height / 7 + 250, 110, 33);
        deleteListButton.addActionListener(this);
        deleteListButton.setBackground(new Color(0xbd2b2b));
        deleteListButton.setForeground(Color.white);
        // customize the button with your own look
        deleteListButton.setUI(new StyledButtonUI());
        this.add(deleteListButton);

        createReportButton = new JButton("CREAR INFORME");
        createReportButton.setFont(new Font("Calibri", Font.BOLD, 18));
        createReportButton.setSize(160, 65);
        createReportButton.setLocation(210, height / 7 + 310);
        createReportButton.addActionListener(this);
        createReportButton.setBackground(new Color(0x0D4931));
        createReportButton.setForeground(Color.white);
        // customize the button with your own look
        createReportButton.setUI(new StyledButtonUI());
        this.add(createReportButton);

        mensaje = new JLabel();
        mensaje.setBounds(200, height / 7 + 220, 280, 23);
        this.add(mensaje);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(width / 2 - 245, width / 2 - 245, width / 2 - 245)
                                .addContainerGap(213, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addContainerGap(275, Short.MAX_VALUE))
        );

    }

    private String[] loadObrasDescriptors() {
        java.util.List<String> obrasList = obrasServiceJDBC.getObrasDescriptor();

        String[] obrasArray = new String[obrasList.size()];
        obrasArray = obrasList.toArray(obrasArray);

        for (String s : obrasArray)
            System.out.println(s);

        return obrasArray;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteObraFromListButton) {
            deleteObraNameFromList(obrasDisplayedJList.getSelectedIndex());
        }
        if (e.getSource() == deleteListButton) {
            borrarLista();
            mensaje.setText("Se borró toda la lista");
        }
        if (e.getSource() == createReportButton) {
            List<String> selectedObrasToReport = new ArrayList<>();

            for (int i = 0; i < obrasDisplayedJList.getModel().getSize(); i++) {

                selectedObrasToReport.add(String.valueOf(obrasDisplayedJList.getModel().getElementAt(i)));
            }

            if (!selectedObrasToReport.isEmpty()) {
                System.out.println("desde el BOTÓN " + selectedObrasToReport);
                reportObraServiceJDBC.createViewTableObras(selectedObrasToReport);

                // 1o - GENERAR EL INFORME JASPER
                generateObrasReport();

                // 2o - BORRAR VIEW
                reportObraServiceJDBC.dropViewTableObras();
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona una obra. "
                        , "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void generateObrasReport() {
        System.out.println("GENEREMOS EL INFORME");
        try {
            JDialog reportObraDialog = new JDialog();
            System.out.println(getClass().getResource("/reports/FINAL_OBRAS_PAGO.jasper"));
            JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/reports/FINAL_OBRAS_PAGO.jrxml"));
            System.out.println(1);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, JDBCUtils.getConnection());
            System.out.println(2);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "obras.pdf");
            System.out.println(3);
            JasperViewer jasperViewer = new JasperViewer(
                    jasperPrint,
                    true
            );
            reportObraDialog.setContentPane(jasperViewer.getContentPane());
            reportObraDialog.setSize(jasperViewer.getSize());
            reportObraDialog.setTitle("PAGO OBRAS");
            reportObraDialog.setVisible(true);
        } catch (JRException | SQLException | FileNotFoundException e) {
            System.out.println(e);
        }
    }


    private void deleteObraNameFromList(int index) {
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

