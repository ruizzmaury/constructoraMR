package com.example.constructora.view;

import com.example.constructora.JDBCRepository.ObrasServiceImplJDBC;
import com.example.constructora.JDBCRepository.ObrasServiceJDBC;
import com.example.constructora.domain.Obra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class RegistroObra extends JFrame
        implements ActionListener {

    private final ObrasServiceJDBC obrasServiceJDBC = new ObrasServiceImplJDBC();

    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel beginningDate;
    private JComboBox begDay;
    private JComboBox begMonth;
    private JComboBox begYear;
    private JLabel endDate;
    private JComboBox endDay;
    private JComboBox endMonth;
    private JComboBox endYear;
    private JLabel add;
    private JTextArea tadd;
    private JLabel description;
    private JTextArea tdescription;
    private JButton registroButton;
    private JButton reset;
    private JButton backButton;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;

    private String days[]
            = {"1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31"};
    private String months[]
            = {"Jan", "feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sup", "Oct", "Nov", "Dec"};
    private String years[]
            = {"1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019", "2020", "2021"};

    private String listadoCatLab[]
            = {"Peón", "Albañil 1", "Albañil 2", "Maestro Obra"};

    // constructor, to initialize the components
    // with default values.
    public RegistroObra() throws HeadlessException {

        setTitle("Registro Obra");
        setBounds(300, 90, 1000, 800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

//        backButton = new JButton("VOLVER");
//        backButton.setFont(new Font("Arial", Font.BOLD, 14));
//        backButton.setSize(120, 40);
//        backButton.setLocation(10, 10);
//        backButton.addActionListener(this);
//        c.add(backButton);

        title = new JLabel("Registro Obra");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);

        beginningDate = new JLabel("Fecha Inicio");
        beginningDate.setFont(new Font("Arial", Font.PLAIN, 16));
        beginningDate.setSize(100, 20);
        beginningDate.setLocation(100, 100);
        c.add(beginningDate);

        begDay = new JComboBox(days);
        begDay.setFont(new Font("Arial", Font.PLAIN, 15));
        begDay.setSize(50, 20);
        begDay.setLocation(200, 100);
        c.add(begDay);

        begMonth = new JComboBox(months);
        begMonth.setFont(new Font("Arial", Font.PLAIN, 15));
        begMonth.setSize(60, 20);
        begMonth.setLocation(260, 100);
        c.add(begMonth);

        begYear = new JComboBox(years);
        begYear.setFont(new Font("Arial", Font.PLAIN, 15));
        begYear.setSize(60, 20);
        begYear.setLocation(330, 100);
        c.add(begYear);


        endDate = new JLabel("Fecha Fin");
        endDate.setFont(new Font("Arial", Font.PLAIN, 16));
        endDate.setSize(100, 20);
        endDate.setLocation(100, 150);
        c.add(endDate);

        endDay = new JComboBox(days);
        endDay.setFont(new Font("Arial", Font.PLAIN, 15));
        endDay.setSize(50, 20);
        endDay.setLocation(200, 150);
        c.add(endDay);

        endMonth = new JComboBox(months);
        endMonth.setFont(new Font("Arial", Font.PLAIN, 15));
        endMonth.setSize(60, 20);
        endMonth.setLocation(260, 150);
        c.add(endMonth);

        endYear = new JComboBox(years);
        endYear.setFont(new Font("Arial", Font.PLAIN, 15));
        endYear.setSize(60, 20);
        endYear.setLocation(330, 150);
        c.add(endYear);


        add = new JLabel("Ubicación");
        add.setFont(new Font("Arial", Font.PLAIN, 16));
        add.setSize(100, 20);
        add.setLocation(100, 200);
        c.add(add);

        tadd = new JTextArea();
        tadd.setFont(new Font("Arial", Font.PLAIN, 15));
        tadd.setSize(200, 75);
        tadd.setLocation(200, 200);
        tadd.setLineWrap(true);
        c.add(tadd);


        description = new JLabel("Descriptor");
        description.setFont(new Font("Arial", Font.PLAIN, 16));
        description.setSize(100, 20);
        description.setLocation(100, 300);
        c.add(description);

        tdescription = new JTextArea();
        tdescription.setFont(new Font("Arial", Font.PLAIN, 15));
        tdescription.setSize(200, 100);
        tdescription.setLocation(200, 300);
        tdescription.setLineWrap(true);
        c.add(tdescription);


        registroButton = new JButton("Registrar");
        registroButton.setFont(new Font("Arial", Font.PLAIN, 15));
        registroButton.setSize(100, 20);
        registroButton.setLocation(150, 475);
        registroButton.addActionListener(this);
        c.add(registroButton);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 475);
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
        if (e.getSource() == registroButton) {

            String data
                    = "Fecha Inicio : "
                    + (String) begDay.getSelectedItem()
                    + "/" + (String) begMonth.getSelectedItem()
                    + "/" + (String) begYear.getSelectedItem()
                    + "\n";

            String data1
                    = "Fecha Fin : "
                    + (String) endDay.getSelectedItem()
                    + "/" + (String) endMonth.getSelectedItem()
                    + "/" + (String) endYear.getSelectedItem()
                    + "\n";

            String data2 = "Ubicación : " + tadd.getText() + "\n";

            String data3 = "Descripción : " + tdescription.getText() + "\n";

            tout.setText(data + data2 + data3);
            tout.setEditable(false);
            res.setText("Obra registrada correctamente.");

            Obra nueva = new Obra (
                    tadd.getText(),
                    tdescription.getText(),
                    LocalDate.of(
                            Integer.parseInt(begYear.getSelectedItem().toString()),
                            begMonth.getSelectedIndex() + 1,
                            Integer.parseInt(begDay.getSelectedItem().toString())
                    ),
                    LocalDate.of(
                            Integer.parseInt(endYear.getSelectedItem().toString()),
                            endMonth.getSelectedIndex() + 1,
                            Integer.parseInt(endDay.getSelectedItem().toString())
                    )
            );

            obrasServiceJDBC.create(nueva);

        } else if (e.getSource() == reset) {
            String def = "";
            tadd.setText(def);
            tdescription.setText(def);
            res.setText(def);
            tout.setText(def);
            begDay.setSelectedIndex(0);
            begMonth.setSelectedIndex(0);
            begYear.setSelectedIndex(0);
            resadd.setText(def);

        } else if (e.getSource() == backButton) {
//            SecondaryMenu secondaryMenu = new SecondaryMenu("Obra");
//            secondaryMenu.setVisible(true);
//            this.dispose();
        }
    }
}
