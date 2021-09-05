package com.example.constructora.view;

import com.example.constructora.JDBCRepository.ObrasServiceImplJDBC;
import com.example.constructora.JDBCRepository.ObrasServiceJDBC;
import com.example.constructora.view.utils.StyledButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ObraReportMenu extends JDialog implements ActionListener {
    private final ObrasServiceJDBC obrasServiceJDBC = new ObrasServiceImplJDBC();

    private List<String> obrasAddedForReport;
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

        JLabel title = new JLabel("Por Obra");
        title.setFont(new Font("Calibri", Font.BOLD, 32));
        title.setSize(200, 40);
//        title.setLocation(400, height / 10);
        this.add(title);


        JLabel obra = new JLabel("Seleccione una obra");
        obra.setFont(new Font("Calibri", Font.PLAIN, 26));
        obra.setSize(300, 30);
        obra.setLocation(width/2 - 120, height/8);
        this.add(obra);


        String[] obrasDescriptors = loadObrasDescriptors();
        obraDescriptor = new JComboBox<>(obrasDescriptors);
        obraDescriptor.setFont(new Font("Calibri", Font.PLAIN, 15));
        obraDescriptor.setSize(190, 30);
        obraDescriptor.setLocation(width/2 - 95, height/7 + 30);
        this.add(obraDescriptor);

        obraDescriptor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // PARA MOSTRAR LISTA DE OBRAS SELECCIONADAS PARA INFORME
                if(obraDescriptor.getSelectedItem() != null) {
                    model.addElement(obraDescriptor.getSelectedItem());
                    obrasDisplayedJList.setModel(model);
                }

            }
        });


        obrasDisplayedJList = new JList<>();
        obrasDisplayedJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );

        //instanciamos el modelo
        model = new DefaultListModel<>();
        scrollLista = new JScrollPane();
        scrollLista.setBounds(20, 120,220, 80);
        scrollLista.setViewportView(obrasDisplayedJList);


        deleteObraFromListButton= new JButton();
        deleteObraFromListButton.setText("Delete");
        deleteObraFromListButton.setBounds(20, 210, 80, 23);
        deleteObraFromListButton.addActionListener(this);
        this.add(deleteObraFromListButton);

        deleteListButton= new JButton();
        deleteListButton.setText("Borrar Lista");
        deleteListButton.setBounds(120, 210, 120, 23);
        deleteListButton.addActionListener(this);
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



        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(160, 160, 160)
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

        for(String s : obrasArray)
            System.out.println(s);

        return obrasArray;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==deleteObraFromListButton)
        {
            deleteObraNameFromList(obraDescriptor.getSelectedIndex() );
        }
        if (e.getSource()==deleteListButton)
        {
            borrarLista();
            mensaje.setText("Se borró toda la lista");
        }
    }


    private void deleteObraNameFromList(int index) {
        if (index>=0) {
            model.removeElementAt(index);
            mensaje.setText("Se eliminó un elemento en la posición "+index);
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un indice"
                    ,"Error", JOptionPane.ERROR_MESSAGE);

            mensaje.setText("NO se seleccionó ningún elemento");
        }

    }

    private void borrarLista() {
        model.clear();
    }

}

