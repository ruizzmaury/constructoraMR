package com.example.constructora.view.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WelcomePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private JLabel imageIconFull;
    private JLabel labelIconPayment;
    private JLabel labelPaymentService;
    private JLabel labelIconPatients;
    private JLabel labelPatients;
    private JLabel labelWelcome;

    private JPanel panelMain;
    private JPanel panelWelcome;



//    private Image imgIcon = new ImageIcon(HomePage.class.getResource("/Images/cabinet.jpg")).getImage()
//            .getScaledInstance(918, 510, Image.SCALE_SMOOTH);

    public WelcomePanel() {
        guiHomePage();
    }

    public void guiHomePage() {
        Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();
        // width will store the width of the screen
        int width = (int) size.getWidth();

        // height will store the height of the screen
        int height = (int) size.getHeight();


        this.setBackground(new Color(255, 255, 255));
        this.setLayout(null);

        panelMain = new JPanel();
        panelMain.setBackground(new Color(255, 255, 255));
        panelMain.setBounds(0, 0, width-300, height-10);
        this.add(panelMain);
        panelMain.setLayout(null);

        imageIconFull = new JLabel();
        imageIconFull.setBounds(0, 0, 918, 510);




        labelPaymentService = new JLabel("          LOGO DE EMPRESA");
        labelPaymentService.setBounds(0, 137, 800, 29);
        labelPaymentService.setHorizontalAlignment(SwingConstants.CENTER);
        labelPaymentService.setFont(new Font("Tahoma", Font.BOLD, 20));
        panelMain.add(labelPaymentService);

        panelWelcome = new JPanel();
        panelWelcome.setBackground(new Color(32, 178, 170));
        panelWelcome.setBounds(0, 29, width-300, 69);
        panelMain.add(panelWelcome);
        panelWelcome.setLayout(null);

        labelWelcome = new JLabel("          BIENVENIDO DE NUEVO");
        labelWelcome.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        labelWelcome.setBounds(12, 13, 800, 43);
        panelWelcome.add(labelWelcome);



//        imageIconFull.setIcon(new ImageIcon(imgIcon));
//        panelMain.add(imageIconFull);

    }

    public void labelIconPaymentMouseClicked(MouseEvent evt) {
//

    }

    public void labelIconUserMouseClicked(MouseEvent evt) {
//        PatientDisplay patient = new PatientDisplay();
//        patient.setVisible(true);
//        dispose();
    }

}
