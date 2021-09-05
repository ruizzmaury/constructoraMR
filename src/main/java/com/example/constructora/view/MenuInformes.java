package com.example.constructora.view;

import com.example.constructora.view.*;
import com.example.constructora.view.utils.StyledButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInformes extends JFrame implements ActionListener {

    private Container c;
    private JLabel title;
    private JButton byDate;
    private JButton byObra;
//    private JButton backButton;

    public MenuInformes() throws HeadlessException {
        // make the frame half the height and width

        setTitle("Menú Informes");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        Rectangle r = this.getBounds();

        int height = r.height;
        int width = r.width;
        System.out.println(height + " " + width);

        title = new JLabel("Menú Informes");
        title.setFont(new Font("Calibri", Font.BOLD, 32));
        title.setSize(300, 40);
        title.setLocation(width / 2 - 100, height / 10);
        c.add(title);

        byDate = new JButton("Por Fecha");
        byDate.setFont(new Font("Calibri", Font.BOLD, 35));
        byDate.setSize(400, 100);
        byDate.setLocation(width / 2 - 200, height / 5);
        byDate.addActionListener(this);
        byDate.setBackground(new Color(0x2dce98));
        byDate.setForeground(Color.white);
        // customize the button with your own look
        byDate.setUI(new StyledButtonUI());
        c.add(byDate);

        byObra = new JButton("Por Obra");
        byObra.setFont(new Font("Calibri", Font.BOLD, 35));
        byObra.setSize(400, 100);
        byObra.setLocation(width / 2 - 200, height / 5 + 120);
        byObra.addActionListener(this);
        byObra.setBackground(new Color(0x2dce98));
        byObra.setForeground(Color.white);
        // customize the button with your own look
        byObra.setUI(new StyledButtonUI());
        c.add(byObra);

//        backButton = new JButton("VOLVER");
//        backButton.setFont(new Font("Calibri", Font.BOLD, 25));
//        backButton.setSize(160, 60);
//        backButton.setLocation(width / 2 - 80, height / 5 + 240);
//        backButton.addActionListener(this);
//        backButton.setBackground(new Color(0x2dce98));
//        backButton.setForeground(Color.white);
//        // customize the button with your own look
//        backButton.setUI(new StyledButtonUI());
//        c.add(backButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == byDate) {
            System.out.println("INFORMES POR FECHA (INTERVALO/MES)");
            DateRangeReportMenu dateRangeReportMenu = new DateRangeReportMenu(this, rootPaneCheckingEnabled);
            dateRangeReportMenu.setVisible(true);

        } else if (e.getSource() == byObra) {
            System.out.println("INFORMES POR OBRA");
            int windowWidth = 800;
            int windowHeight = 600;

            ObraReportMenu obraReportMenu = new ObraReportMenu(this, rootPaneCheckingEnabled, windowWidth, windowHeight);
            obraReportMenu.setSize(new Dimension(windowWidth, windowHeight));
            obraReportMenu.setVisible(true);
        }
//        else if (e.getSource() == backButton) {
//            MainMenu mainMenu = new MainMenu();
//            mainMenu.setVisible(true);
//            this.dispose();
//        }

    }
}
