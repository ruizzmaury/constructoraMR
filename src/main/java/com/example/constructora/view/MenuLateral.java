package com.example.constructora.view;

import com.example.constructora.view.utils.StyledButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuLateral extends JFrame implements ActionListener {
    int width;
    int height;
    private JButton registerPagoButton;
    private JButton consultasPagoButton;
    private JButton consultasTrabButton;
    private JButton consultasObrasButton;
    private JButton registerTrabButton;
    private JButton registerObrasButton;
    private JButton dateReportButton;
    private JButton obraReportButton;

    public MenuLateral() throws HeadlessException {
        getContentPane().setLayout(null);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();

        // width will store the width of the screen
        width = (int) size.getWidth();

        // height will store the height of the screen
        height = (int) size.getHeight();

        setTitle("Principal");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new FlowLayout());
        add(loadLeftBar());
        add(loadMainScreen());
        pack();

    }

    private JPanel loadMainScreen() {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(width - 310, height));
        mainPanel.setBackground(new Color(217, 235, 249));
        return mainPanel;
    }

    private JPanel loadLeftBar() {
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(300, height));
//        leftPanel.setLocation(0,0);
        leftPanel.setBackground(new Color(26, 60, 87));

        JLabel titleMenu = new JLabel("             Menú");
        titleMenu.setFont(new Font("Calibri", Font.BOLD, 35));
        titleMenu.setPreferredSize(new Dimension(300, 50));
        titleMenu.setForeground(Color.WHITE);
        titleMenu.setSize(300, 30);
        leftPanel.add(titleMenu);


        JLabel consultTitle = new JLabel(" Consulta");
        consultTitle.setFont(new Font("Calibri", Font.BOLD, 28));
        consultTitle.setPreferredSize(new Dimension(200, 50));
        consultTitle.setBackground(new Color(0x2dce98));
        consultTitle.setForeground(Color.white);
        leftPanel.add(consultTitle);

        consultasPagoButton = new JButton("Pagos");
        consultasPagoButton.setFont(new Font("Calibri", Font.BOLD, 25));
        consultasPagoButton.setPreferredSize(new Dimension(280, 50));
        consultasPagoButton.setBackground(new Color(0x245479));
        consultasPagoButton.setForeground(Color.white);
        consultasPagoButton.addActionListener(this);
        leftPanel.add(consultasPagoButton);

        consultasTrabButton = new JButton("Trabajadores");
        consultasTrabButton.setFont(new Font("Calibri", Font.BOLD, 25));
        consultasTrabButton.setPreferredSize(new Dimension(280, 50));
        consultasTrabButton.setBackground(new Color(0x245479));
        consultasTrabButton.setForeground(Color.white);
        consultasTrabButton.addActionListener(this);
        leftPanel.add(consultasTrabButton);

        consultasObrasButton = new JButton("Obras");
        consultasObrasButton.setFont(new Font("Calibri", Font.BOLD, 25));
        consultasObrasButton.setPreferredSize(new Dimension(280, 50));
        consultasObrasButton.setBackground(new Color(0x245479));
        consultasObrasButton.setForeground(Color.white);
        consultasObrasButton.addActionListener(this);
        leftPanel.add(consultasObrasButton);


        JLabel spacer = new JLabel();
        spacer.setPreferredSize(new Dimension(280, 50));
        leftPanel.add(spacer);

        JLabel registerTitle = new JLabel(" Registro");
        registerTitle.setFont(new Font("Calibri", Font.BOLD, 28));
        registerTitle.setPreferredSize(new Dimension(200, 50));
        registerTitle.setBackground(new Color(0x2dce98));
        registerTitle.setForeground(Color.white);
        leftPanel.add(registerTitle);

        registerPagoButton = new JButton("Pagos");
        registerPagoButton.setFont(new Font("Calibri", Font.BOLD, 25));
        registerPagoButton.setPreferredSize(new Dimension(280, 50));
        registerPagoButton.setBackground(new Color(0x245479));
        registerPagoButton.setForeground(Color.white);
        registerPagoButton.addActionListener(this);
        leftPanel.add(registerPagoButton);

        registerTrabButton = new JButton("Trabajadores");
        registerTrabButton.setFont(new Font("Calibri", Font.BOLD, 25));
        registerTrabButton.setPreferredSize(new Dimension(280, 50));
        registerTrabButton.setBackground(new Color(0x245479));
        registerTrabButton.setForeground(Color.white);
        registerTrabButton.addActionListener(this);
        leftPanel.add(registerTrabButton);

        registerObrasButton = new JButton("Obras");
        registerObrasButton.setFont(new Font("Calibri", Font.BOLD, 25));
        registerObrasButton.setPreferredSize(new Dimension(280, 50));
        registerObrasButton.setBackground(new Color(0x245479));
        registerObrasButton.setForeground(Color.white);
        registerObrasButton.addActionListener(this);
        leftPanel.add(registerObrasButton);


        JLabel spacer2 = new JLabel();
        spacer2.setPreferredSize(new Dimension(280, 50));
        leftPanel.add(spacer2);


        JLabel reportTitle = new JLabel(" Informes");
        reportTitle.setFont(new Font("Calibri", Font.BOLD, 28));
        reportTitle.setPreferredSize(new Dimension(200, 50));
        reportTitle.setBackground(new Color(0x2dce98));
        reportTitle.setForeground(Color.white);
        leftPanel.add(reportTitle);

        dateReportButton = new JButton("Trabajador - Fecha");
        dateReportButton.setFont(new Font("Calibri", Font.BOLD, 25));
        dateReportButton.setPreferredSize(new Dimension(280, 50));
        dateReportButton.setBackground(new Color(0x245479));
        dateReportButton.setForeground(Color.white);
        dateReportButton.addActionListener(this);
        leftPanel.add(dateReportButton);

        obraReportButton = new JButton("Obra/s");
        obraReportButton.setFont(new Font("Calibri", Font.BOLD, 25));
        obraReportButton.setPreferredSize(new Dimension(280, 50));
        obraReportButton.setBackground(new Color(0x245479));
        obraReportButton.setForeground(Color.white);
        obraReportButton.addActionListener(this);
        leftPanel.add(obraReportButton);

        JLabel spacer3 = new JLabel();
        spacer3.setPreferredSize(new Dimension(280, 160));
        leftPanel.add(spacer3);

        JLabel copyright = new JLabel(" © Copyright - Maurici Ruiz Plaza");
        copyright.setFont(new Font("Calibri", Font.PLAIN, 18));
        copyright.setPreferredSize(new Dimension(300, 25));
        copyright.setForeground(Color.white);
        leftPanel.add(copyright);

        JLabel copyright1 = new JLabel(" All Rights Reserved.");
        copyright1.setFont(new Font("Calibri", Font.PLAIN, 18));
        copyright1.setPreferredSize(new Dimension(300, 20));
        copyright1.setForeground(Color.white);
        leftPanel.add(copyright1);

        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        return leftPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == consultasPagoButton){
            ConsultaPagosView consultaPagosView = new ConsultaPagosView();

        }
        if (e.getSource() == consultasTrabButton) {
            System.out.println(2);
        }
        if (e.getSource() == consultasObrasButton) {
            System.out.println(3);
        }
        if (e.getSource() == registerPagoButton) {
            System.out.println(4);
        }
        if (e.getSource() == registerTrabButton) {
            System.out.println(5);
        }
        if (e.getSource() == registerObrasButton) {
            System.out.println(6);
        }
        if (e.getSource() == dateReportButton) {
            System.out.println(7);
        }
        if (e.getSource() == obraReportButton) {
            System.out.println(8);
        }

    }
}
