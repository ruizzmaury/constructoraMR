package com.example.constructora.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenu extends JFrame
        implements ActionListener {

    private Container c;
    private JLabel title;
    private JButton pagosButton;
    private JButton trabButton;
    private JButton obrasButton;

    public MainMenu() throws HeadlessException {
        // make the frame half the height and width

        setTitle("Menú Principal");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        Rectangle r = this.getBounds();

        int height = r.height;
        int width = r.width;
        System.out.println(height + " " + width);

        title = new JLabel("Menú Principal");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(300, 30);
        title.setLocation(width/2-100, height/10);
        c.add(title);

        pagosButton = new JButton("PAGOS");
        pagosButton.setFont(new Font("Arial", Font.BOLD, 30));
        pagosButton.setSize(400, 100);
        pagosButton.setLocation(width/2-200, height/5);
        pagosButton.addActionListener(this);
        c.add(pagosButton);

        trabButton = new JButton("TRABAJADORES");
        trabButton.setFont(new Font("Arial", Font.BOLD, 30));
        trabButton.setSize(400, 100);
        trabButton.setLocation(width/2-200, height/5 + 120);
        trabButton.addActionListener(this);
        c.add(trabButton);

        obrasButton = new JButton("OBRAS");
        obrasButton.setFont(new Font("Arial", Font.BOLD, 30));
        obrasButton.setSize(400, 100);
        obrasButton.setLocation(width/2-200, height/5 + 240);
        obrasButton.addActionListener(this);
        c.add(obrasButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pagosButton) {
            SecondaryMenu secondaryMenu = new SecondaryMenu("Pago");
            secondaryMenu.setVisible(true);
            this.dispose();


        } else if (e.getSource() == trabButton) {
            SecondaryMenu secondaryMenu = new SecondaryMenu("Trabajador");
            secondaryMenu.setVisible(true);
            this.dispose();

        } else if (e.getSource() == obrasButton) {
            SecondaryMenu secondaryMenu = new SecondaryMenu("Obra");
            secondaryMenu.setVisible(true);
            this.dispose();
        }
    }
}
