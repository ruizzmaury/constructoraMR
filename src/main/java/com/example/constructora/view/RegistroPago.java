package com.example.constructora.view;

import com.example.constructora.JDBCRepository.*;
import com.example.constructora.view.utils.ViewUtils;
import com.example.constructora.domain.Pago;
import com.example.constructora.domain.Trabajador;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class RegistroPago extends JFrame
        implements ActionListener {

    private final PagosServiceJDBC pagosServiceJDBC = new PagosServiceImplJDBC();
    private final TrabajadorServiceJDBC trabajadorServiceJDBC = new TrabajadorServiceImplJDBC();
    private final ObrasServiceJDBC obrasServiceJDBC = new ObrasServiceImplJDBC();
    private final CatLaboralServiceJDBC catLaboralServiceJDBC = new CatLaboralServiceImplJDBC();




    private final JComboBox<String> date;
    private final JComboBox<String> month;
    private final JComboBox<String> year;
    private final JComboBox<String> trabajadorName;
    private JTextArea tDNI;
    private JTextArea tCatLaboral;
    private JComboBox<Long> obraId;
    private JLabel add;
    private JTextArea tadd;
    private final JTextArea thoras;
    private final JTextArea tcantidad;
    private final JTextArea tdescription;
    private final JButton sub;
    private final JButton reset;
    private final JButton backButton;
    private final JTextArea tout;
    private final JLabel res;
    private final JTextArea resadd;



    private Long[] loadObrasIDs() {
        List<Long> obrasList = obrasServiceJDBC.getObrasIDs();

        Long[] obrasArray = new Long[obrasList.size()];
        obrasArray = obrasList.toArray(obrasArray);

        for(Long s : obrasArray)
            System.out.println(s);

        return obrasArray;
    }

    private String[] loadWorkersNames() {
        List<String> workersList = trabajadorServiceJDBC.getTrabajadoresNames();

        String[] workersArray = new String[workersList.size()];
        workersArray = workersList.toArray(workersArray);

        for(String s : workersArray)
            System.out.println(s);

        return workersArray;
    }


    // constructor, to initialize the components
    // with default values.
    public RegistroPago() throws HeadlessException {
        // seleccionar DNI de trabajador
        // pillar su nombre y categoria laboral por un query mediante el DNI
        // las obras pillar Descriptor

        setTitle("Registro Pago");
        setBounds(300, 90, 1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Components of the Form
        Container c = getContentPane();
        c.setLayout(null);

        backButton = new JButton("VOLVER");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setSize(120, 40);
        backButton.setLocation(10, 10);
        backButton.addActionListener(this);
        c.add(backButton);

        JLabel title = new JLabel("Registro Pago a trabajador");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);

        JLabel beginningDate = new JLabel("Fecha");
        beginningDate.setFont(new Font("Arial", Font.PLAIN, 16));
        beginningDate.setSize(100, 20);
        beginningDate.setLocation(100, 100);
        c.add(beginningDate);

        date = new JComboBox<>(ViewUtils.DAYS);
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(50, 20);
        date.setLocation(200, 100);
        c.add(date);

        month = new JComboBox<>(ViewUtils.MONTHS);
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(60, 20);
        month.setLocation(260, 100);
        c.add(month);

        year = new JComboBox<>(ViewUtils.COMINGYEARS);
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(60, 20);
        year.setLocation(330, 100);
        c.add(year);

        JLabel trabajador = new JLabel("Trabajador");
        trabajador.setFont(new Font("Arial", Font.PLAIN, 16));
        trabajador.setSize(100, 20);
        trabajador.setLocation(100, 150);
        c.add(trabajador);

        String[] workersNamesList = loadWorkersNames();
        trabajadorName = new JComboBox<>(workersNamesList);
        trabajadorName.setFont(new Font("Arial", Font.PLAIN, 15));
        trabajadorName.setSize(190, 20);
        trabajadorName.setLocation(200, 150);
        c.add(trabajadorName);


        tDNI = new JTextArea();
        tDNI.setFont(new Font("Arial", Font.PLAIN, 15));
        tDNI.setSize(120, 20);
        tDNI.setLocation(200, 200);
        tDNI.setLineWrap(true);
        c.add(tDNI);

        tCatLaboral = new JTextArea();
        tCatLaboral.setFont(new Font("Arial", Font.PLAIN, 15));
        tCatLaboral.setSize(120, 20);
        tCatLaboral.setLocation(340, 200);
        tCatLaboral.setLineWrap(true);
        c.add(tCatLaboral);

        tDNI.setEditable(false);
        tCatLaboral.setEditable(false);

        trabajadorName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // PARA MOSTRAR DNI Y CAT. LABORAL DEL TRABAJADOR SELECCIONADO POR NOMBRE
                if(trabajadorName.getSelectedItem() != null) {
                    List<Trabajador> workersPerNameList = trabajadorServiceJDBC.findByNombreStartingWith((String) trabajadorName.getSelectedItem());
                    System.out.println("desde registro :" + workersPerNameList.get(0).getTrabajador_dni());
                    System.out.println("desde registro :" + workersPerNameList.get(0).getCatLaboral());
                    tDNI.setText(workersPerNameList.get(0).getTrabajador_dni());
                    tCatLaboral.setText(workersPerNameList.get(0).getCatLaboral().getNombreCategoria());

                    if(!Objects.equals(thoras.getText(), "")) {
                        float toPay = Float.parseFloat(thoras.getText()) * catLaboralServiceJDBC.getPrecioHora(trabajadorServiceJDBC.getCategoriaLaboral(tDNI.getText()));
                        System.out.println("DESDE action " + toPay);
                        tcantidad.setText(String.valueOf(toPay));
                    }

                }

            }
        });


        JLabel obra = new JLabel("Obra");
        obra.setFont(new Font("Arial", Font.PLAIN, 16));
        obra.setSize(100, 20);
        obra.setLocation(100, 250);
        c.add(obra);

        Long[] obrasIdList = loadObrasIDs();
        obraId = new JComboBox<>(obrasIdList);
        obraId.setFont(new Font("Arial", Font.PLAIN, 15));
        obraId.setSize(190, 20);
        obraId.setLocation(200, 250);
        c.add(obraId);

        JLabel horas = new JLabel("Horas :");
        horas.setFont(new Font("Arial", Font.PLAIN, 16));
        horas.setSize(100, 20);
        horas.setLocation(100, 300);
        c.add(horas);

        thoras = new JTextArea();
        thoras.setFont(new Font("Arial", Font.PLAIN, 15));
        thoras.setSize(200, 20);
        thoras.setLocation(200, 300);
        thoras.setLineWrap(true);
        c.add(thoras);

        JLabel cantidad = new JLabel("A pagar :");
        cantidad.setFont(new Font("Arial", Font.PLAIN, 16));
        cantidad.setSize(100, 20);
        cantidad.setLocation(100, 350);
        c.add(cantidad);

        tcantidad = new JTextArea();
        tcantidad.setFont(new Font("Arial", Font.PLAIN, 15));
        tcantidad.setSize(200, 20);
        tcantidad.setLocation(200, 350);
        tcantidad.setLineWrap(true);
        c.add(tcantidad);


        thoras.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                float toPay = Float.parseFloat(thoras.getText()) * catLaboralServiceJDBC.getPrecioHora(trabajadorServiceJDBC.getCategoriaLaboral(tDNI.getText()));
                System.out.println("kfueeeeeeee " + toPay);
                tcantidad.setText(String.valueOf(toPay));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                float toPay = Float.parseFloat(thoras.getText()) * catLaboralServiceJDBC.getPrecioHora(trabajadorServiceJDBC.getCategoriaLaboral(tDNI.getText()));
                System.out.println("DESDE TEXFIELD " + toPay);
                tcantidad.setText(String.valueOf(toPay));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                float toPay = Float.parseFloat(thoras.getText()) * catLaboralServiceJDBC.getPrecioHora(trabajadorServiceJDBC.getCategoriaLaboral(tDNI.getText()));
                System.out.println("DESDE TEXFIELD " + toPay);
                tcantidad.setText(String.valueOf(toPay));
            }
        });



        JLabel description = new JLabel("Información");
        description.setFont(new Font("Arial", Font.PLAIN, 16));
        description.setSize(100, 20);
        description.setLocation(100, 400);
        c.add(description);

        tdescription = new JTextArea();
        tdescription.setFont(new Font("Arial", Font.PLAIN, 15));
        tdescription.setSize(200, 100);
        tdescription.setLocation(200, 400);
        tdescription.setLineWrap(true);
        c.add(tdescription);


        sub = new JButton("Registrar");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 550);
        sub.addActionListener(this);
        c.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 550);
        reset.addActionListener(this);
        c.add(reset);

        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 15));
        tout.setSize(300, 400);
        tout.setLocation(500, 100);
        tout.setLineWrap(true);
        tout.setEditable(false);
        c.add(tout);

        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(580, 100);
        resadd.setLineWrap(true);
        c.add(resadd);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(500, 525);
        c.add(res);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {


//        // PARA MOSTRAR DNI Y CAT. LABORAL DEL TRABAJADOR SELECCIONADO POR NOMBRE
//        if(trabajadorName.getSelectedItem() != null) {
//            List<Trabajador> workersPerNameList = trabajadorServiceJDBC.findByNombreStartingWith((String) trabajadorName.getSelectedItem());
//            System.out.println("desde registro :" + workersPerNameList.get(0).getTrabajador_dni());
//            System.out.println("desde registro :" + CategoriaLaboral.values()[workersPerNameList.get(0).getCatLaboral().ordinal()]);
//            tDNI.setText(workersPerNameList.get(0).getTrabajador_dni());
//            tCatLaboral.setText(String.valueOf(CategoriaLaboral.values()[workersPerNameList.get(0).getCatLaboral().ordinal()]));
//        }


        if (e.getSource() == sub) {

            String data
                    = "Fecha : "
                    + (String) date.getSelectedItem()
                    + "/" + (String) month.getSelectedItem()
                    + "/" + (String) year.getSelectedItem()
                    + "\n";
            String data1 = "";
            int horas = 0;
            float cantidad = 0;
            try {
                horas = Integer.parseInt(thoras.getText());
                cantidad = Float.parseFloat(tcantidad.getText());
                data1 = "Horas : " + horas + " \n A pagar : " + cantidad + " € \n";
            } catch (Exception exception) {
                System.out.println("Cantidad incorrecta");
                System.out.println("Error : " + exception);
            }


            String data2 = "Trabajador : " + trabajadorName.getSelectedItem() + "\n";

            String data3 = "Obra : " + obraId.getSelectedItem() + "\n";

            tout.setText(data + data1 + data2 + data3);
            tout.setEditable(false);
            res.setText("Pago registrado correctamente.");



            Pago pago = new Pago(
                    horas,
                    (Long) obraId.getSelectedItem(),
                    LocalDate.of(
                            Integer.parseInt(year.getSelectedItem().toString()),
                            month.getSelectedIndex() + 1,
                            Integer.parseInt(date.getSelectedItem().toString())
                    ),
                    cantidad
            );

            pagosServiceJDBC.create(
                pago, tDNI.getText()
            );

        } else if (e.getSource() == reset) {
            String def = "";
            tadd.setText(def);
            tdescription.setText(def);
            res.setText(def);
            tout.setText(def);
            date.setSelectedIndex(0);
            month.setSelectedIndex(0);
            year.setSelectedIndex(0);
            resadd.setText(def);

        }else if (e.getSource() == backButton){
            SecondaryMenu secondaryMenu = new SecondaryMenu("Pago");
            secondaryMenu.setVisible(true);
            this.dispose();
        }
    }
}