package com.example.constructora.view;

import com.example.constructora.domain.*;
import com.example.constructora.view.panels.*;
import com.example.constructora.view.utils.StyledButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuLateral extends JFrame implements ActionListener {
    static int width;
    static int height;
    private JButton registerPagoButton;
    private JButton consultasPagoButton;
    private JButton consultasTrabButton;
    private JButton consultasObrasButton;
    private JButton registerTrabButton;
    private JButton registerObrasButton;
    private JButton dateReportButton;
    private JButton obraReportButton;
    private static JPanel defaultPanel;



    public MenuLateral() throws HeadlessException, IOException {
        getContentPane().setLayout(null);
        initComponents();
        setVisible(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
    }

    private void initComponents() throws IOException {
        Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();

        // width will store the width of the screen
        width = (int) size.getWidth();

        // height will store the height of the screen
        height = (int) size.getHeight();

        setTitle("Principal");
//        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new FlowLayout());

        JScrollPane scroll = new JScrollPane(loadLeftBar());

        add(loadLeftBar(), BorderLayout.LINE_START);

        defaultPanel = new WelcomePanel(); // TODO : ESPECIFICAR SETBOUNDS PANELES
        defaultPanel.setPreferredSize(new Dimension(width - 310, height));
        defaultPanel.setBackground(new Color(217, 235, 249));
        add(defaultPanel);

        pack();

    }

    public static void loadMainScreen(JPanel currentPanel) {
        defaultPanel.removeAll();

        currentPanel.setPreferredSize(new Dimension(width - 310, height));
        currentPanel.setBackground(new Color(217, 235, 249));
        defaultPanel.add(currentPanel);
        defaultPanel.repaint();
        defaultPanel.validate();
    }

    private JPanel loadLeftBar() {
        Box box = Box.createVerticalBox();
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(300, height));
//        leftPanel.setLocation(0,0);
        leftPanel.setBackground(new Color(26, 60, 87));

        JLabel titleMenu = new JLabel("             Menú");
        titleMenu.setFont(new Font("Calibri", Font.BOLD, 35));
        titleMenu.setPreferredSize(new Dimension(300, 50));
        titleMenu.setForeground(Color.WHITE);
        titleMenu.setSize(300, 30);
        box.add(titleMenu);


        JLabel consultTitle = new JLabel(" Consulta");
        consultTitle.setFont(new Font("Calibri", Font.BOLD, 28));
        consultTitle.setPreferredSize(new Dimension(200, 50));
        consultTitle.setBackground(new Color(0x2dce98));
        consultTitle.setForeground(Color.white);
        box.add(consultTitle);

        consultasPagoButton = new JButton("Pagos");
        consultasPagoButton.setFont(new Font("Calibri", Font.BOLD, 25));
        consultasPagoButton.setPreferredSize(new Dimension(280, 50));
        consultasPagoButton.setBackground(new Color(0x245479));
        consultasPagoButton.setForeground(Color.white);
        consultasPagoButton.addActionListener(this);
        box.add(consultasPagoButton);

        consultasTrabButton = new JButton("Trabajadores");
        consultasTrabButton.setFont(new Font("Calibri", Font.BOLD, 25));
        consultasTrabButton.setPreferredSize(new Dimension(280, 50));
        consultasTrabButton.setBackground(new Color(0x245479));
        consultasTrabButton.setForeground(Color.white);
        consultasTrabButton.addActionListener(this);
        box.add(consultasTrabButton);

        consultasObrasButton = new JButton("Obras");
        consultasObrasButton.setFont(new Font("Calibri", Font.BOLD, 25));
        consultasObrasButton.setPreferredSize(new Dimension(280, 50));
        consultasObrasButton.setBackground(new Color(0x245479));
        consultasObrasButton.setForeground(Color.white);
        consultasObrasButton.addActionListener(this);
        box.add(consultasObrasButton);


//        JLabel spacer = new JLabel();
//        spacer.setPreferredSize(new Dimension(280, 50));
//        box.add(spacer);

        box.add(Box.createRigidArea(new Dimension(0, 40)));

        JLabel registerTitle = new JLabel(" Registro");
        registerTitle.setFont(new Font("Calibri", Font.BOLD, 28));
        registerTitle.setPreferredSize(new Dimension(200, 50));
        registerTitle.setBackground(new Color(0x2dce98));
        registerTitle.setForeground(Color.white);
        box.add(registerTitle);

        registerPagoButton = new JButton("Pagos");
        registerPagoButton.setFont(new Font("Calibri", Font.BOLD, 25));
        registerPagoButton.setPreferredSize(new Dimension(280, 50));
        registerPagoButton.setBackground(new Color(0x245479));
        registerPagoButton.setForeground(Color.white);
        registerPagoButton.addActionListener(this);
        box.add(registerPagoButton);

        registerTrabButton = new JButton("Trabajadores");
        registerTrabButton.setFont(new Font("Calibri", Font.BOLD, 25));
        registerTrabButton.setPreferredSize(new Dimension(280, 50));
        registerTrabButton.setBackground(new Color(0x245479));
        registerTrabButton.setForeground(Color.white);
        registerTrabButton.addActionListener(this);
        box.add(registerTrabButton);

        registerObrasButton = new JButton("Obras");
        registerObrasButton.setFont(new Font("Calibri", Font.BOLD, 25));
        registerObrasButton.setPreferredSize(new Dimension(280, 50));
        registerObrasButton.setBackground(new Color(0x245479));
        registerObrasButton.setForeground(Color.white);
        registerObrasButton.addActionListener(this);
        box.add(registerObrasButton);


//        JLabel spacer2 = new JLabel();
//        spacer2.setPreferredSize(new Dimension(280, 50));
//        box.add(spacer2);
        box.add(Box.createRigidArea(new Dimension(0, 40)));

        JLabel reportTitle = new JLabel(" Informes");
        reportTitle.setFont(new Font("Calibri", Font.BOLD, 28));
        reportTitle.setPreferredSize(new Dimension(200, 50));
        reportTitle.setBackground(new Color(0x2dce98));
        reportTitle.setForeground(Color.white);
        box.add(reportTitle);

        dateReportButton = new JButton("Trabajador - Fecha");
        dateReportButton.setFont(new Font("Calibri", Font.BOLD, 25));
        //dateReportButton.setPreferredSize(new Dimension(280, 50));
        dateReportButton.setBackground(new Color(0x245479));
        dateReportButton.setForeground(Color.white);
        dateReportButton.addActionListener(this);
        box.add(dateReportButton);

        obraReportButton = new JButton("Obra/s");
        obraReportButton.setFont(new Font("Calibri", Font.BOLD, 25));
        //obraReportButton.setPreferredSize(new Dimension(280, 50));
        obraReportButton.setBackground(new Color(0x245479));
        obraReportButton.setForeground(Color.white);
        obraReportButton.addActionListener(this);
        box.add(obraReportButton);

//        JLabel spacer3 = new JLabel();
//        spacer3.setPreferredSize(new Dimension(280, 160));
//        box.add(spacer3);

        box.add(Box.createRigidArea(new Dimension(0, 40)));

        JLabel copyright = new JLabel(" © Copyright 2021 - Maurici Ruiz Plaza");
        copyright.setFont(new Font("Calibri", Font.PLAIN, 18));
        //copyright.setPreferredSize(new Dimension(300, 25));
        copyright.setForeground(Color.white);
        box.add(copyright);

        JLabel copyright1 = new JLabel("  All Rights Reserved.");
        copyright1.setFont(new Font("Calibri", Font.PLAIN, 18));
        //copyright1.setPreferredSize(new Dimension(300, 20));
        copyright1.setForeground(Color.white);
        box.add(copyright1);


        leftPanel.add(box);
        // leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // leftPanel.setLayout(new BorderLayout());

        return leftPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == consultasPagoButton){
            ConsultaPagosPanel consultaPagosPanel = new ConsultaPagosPanel();
            loadMainScreen(consultaPagosPanel);

        }
        if (e.getSource() == consultasTrabButton) {
            ConsultaTrabajadoresPanel consultaTrabajadoresPanel = new ConsultaTrabajadoresPanel();
            loadMainScreen(consultaTrabajadoresPanel);
        }
        if (e.getSource() == consultasObrasButton) {
            ConsultaObrasPanel consultaObrasPanel = new ConsultaObrasPanel();
            loadMainScreen(consultaObrasPanel);
        }
        if (e.getSource() == registerPagoButton) {
            RegistroPagoPanel registroPagoPanel = new RegistroPagoPanel(
                    new Pago()
            );
            loadMainScreen(registroPagoPanel);
        }
        if (e.getSource() == registerTrabButton) {

            RegistroTrabajadorPanel registroTrabajadorPanel = new RegistroTrabajadorPanel(
                    new Trabajador()
            );
            loadMainScreen(registroTrabajadorPanel);
        }
        if (e.getSource() == registerObrasButton) {
            RegistroObraPanel registroObraPanel = new RegistroObraPanel(
                    new Obra()
            );
            loadMainScreen(registroObraPanel);
        }
        if (e.getSource() == dateReportButton) {
            DateRangeReportPanel dateRangeReportPanel = new DateRangeReportPanel(this, rootPaneCheckingEnabled, 1000, 800);
            loadMainScreen(dateRangeReportPanel);
        }
        if (e.getSource() == obraReportButton) {
            ObraReportPanel obraReportPanel = new ObraReportPanel(this, rootPaneCheckingEnabled, 1000, 800);
            loadMainScreen(obraReportPanel);
        }

    }
}
