package com.example.constructora.view;

import com.example.constructora.JDBCRepository.*;
import com.example.constructora.view.utils.StyledButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ObraReportMenu extends JDialog implements ActionListener {
    private final ObrasServiceJDBC obrasServiceJDBC = new ObrasServiceImplJDBC();
    private ReportObraServiceJDBC reportObraServiceJDBC = new ReportObraServiceImplJDBC();

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

    public ObraReportMenu(java.awt.Frame parent, boolean modal, int width, int height) {
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

        JLabel title = new JLabel("INFORMES POR OBRA");
        title.setFont(new Font("Calibri", Font.BOLD, 32));
        title.setSize(200, 40);
//        title.setLocation(400, height / 10);
        this.add(title);


        JLabel obra = new JLabel("Seleccione una obra");
        obra.setFont(new Font("Calibri", Font.PLAIN, 26));
        obra.setSize(300, 30);
        obra.setLocation(width / 2 - 120, height / 8);
        this.add(obra);


        String[] obrasDescriptors = loadObrasDescriptors();
        obraDescriptor = new JComboBox<>(obrasDescriptors);
        obraDescriptor.setFont(new Font("Calibri", Font.PLAIN, 15));
        obraDescriptor.setSize(190, 28);
        obraDescriptor.setLocation(width / 2 - 95, height / 7 + 30);
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
        scrollLista.setBounds(width / 2 - 115, height / 3 - 15, 250, 140);
        scrollLista.setViewportView(obrasDisplayedJList);
        this.add(scrollLista);


        deleteObraFromListButton = new JButton("Borrar Obra");
        deleteObraFromListButton.setFont(new Font("Calibri", Font.BOLD, 15));
        deleteObraFromListButton.setBounds(width / 2 - 120, height / 2 + 60, 110, 33);
        deleteObraFromListButton.addActionListener(this);
        deleteObraFromListButton.setBackground(new Color(0xbd2b2b));
        deleteObraFromListButton.setForeground(Color.white);
        // customize the button with your own look
        deleteObraFromListButton.setUI(new StyledButtonUI());
        this.add(deleteObraFromListButton);

        deleteListButton = new JButton("Borrar Lista");
        deleteListButton.setFont(new Font("Calibri", Font.BOLD, 15));
        deleteListButton.setBounds(width / 2 + 20, height / 2 + 60, 110, 33);
        deleteListButton.addActionListener(this);
        deleteListButton.setBackground(new Color(0xbd2b2b));
        deleteListButton.setForeground(Color.white);
        // customize the button with your own look
        deleteListButton.setUI(new StyledButtonUI());
        this.add(deleteListButton);

        createReportButton = new JButton("CREAR INFORME");
        createReportButton.setFont(new Font("Calibri", Font.BOLD, 18));
        createReportButton.setSize(160, 65);
        createReportButton.setLocation(width / 2 - 75, height / 2 + 120);
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
                                .addGap(width / 2 - 120, width / 2 - 120, width / 2 - 120)
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

    private String[] loadObrasDescriptors() {
        List<String> obrasList = obrasServiceJDBC.getObrasDescriptor();

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
                // TODO
                // 1o - GENERAR EL INFORME JASPER
                // 2o - BORRAR VIEW

            } else {
                JOptionPane.showMessageDialog(null, "Selecciona una obra. "
                        , "Error", JOptionPane.ERROR_MESSAGE);
            }

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

