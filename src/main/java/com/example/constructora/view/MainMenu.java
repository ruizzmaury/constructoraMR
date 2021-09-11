package com.example.constructora.view;

import com.example.constructora.view.utils.StyledButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame
        implements ActionListener {

    private Container c;
    private JLabel title;
    private JButton pagosButton;
    private JButton trabButton;
    private JButton obrasButton;
    private JButton reportsButton;

    public MainMenu() throws HeadlessException {
        // make the frame half the height and width

         setTitle("Menú Principal");
         setBounds(300, 90, 900, 600);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         setResizable(false);

         c = this.getContentPane();

        c.setLayout(null);

        Rectangle r = this.getBounds();

        int height = r.height;
        int width = r.width;
        System.out.println(height + " " + width);

        title = new JLabel("Menú Principal");
        title.setFont(new Font("Calibri", Font.BOLD, 32));
        title.setSize(300, 30);
        title.setLocation(width / 2 - 100, height / 10);
        c.add(title);

        pagosButton = new JButton("PAGOS");
        pagosButton.setFont(new Font("Calibri", Font.BOLD, 35));
        pagosButton.setSize(400, 100);
        pagosButton.setLocation(width / 2 - 200, height / 5);
        pagosButton.addActionListener(this);
        pagosButton.setBackground(new Color(0x2dce98));
        pagosButton.setForeground(Color.white);
        // customize the button with your own look
        pagosButton.setUI(new StyledButtonUI());
        c.add(pagosButton);

        reportsButton = new JButton("INFORMES");
        reportsButton.setFont(new Font("Calibri", Font.BOLD, 35));
        reportsButton.setSize(400, 100);
        reportsButton.setLocation(width / 2 - 200, height / 5 + 120);
        reportsButton.addActionListener(this);
        reportsButton.setBackground(new Color(0x2dce98));
        reportsButton.setForeground(Color.white);
        // customize the button with your own look
        reportsButton.setUI(new StyledButtonUI());
        c.add(reportsButton);

        obrasButton = new JButton("OBRAS");
        obrasButton.setFont(new Font("Calibri", Font.BOLD, 30));
        obrasButton.setSize(190, 80);
        obrasButton.setLocation(width / 2 - 200, height / 5 + 240);
        obrasButton.addActionListener(this);
        obrasButton.setBackground(new Color(0x2dce98));
        obrasButton.setForeground(Color.white);
        // customize the button with your own look
        obrasButton.setUI(new StyledButtonUI());
        c.add(obrasButton);

        trabButton = new JButton("TRABAJADORES");
        trabButton.setFont(new Font("Calibri", Font.BOLD, 24));
        trabButton.setSize(190, 80);
        trabButton.setLocation(width / 2 + 10, height / 5 + 240);
        trabButton.addActionListener(this);
        trabButton.setBackground(new Color(0x2dce98));
        trabButton.setForeground(Color.white);
        // customize the button with your own look
        trabButton.setUI(new StyledButtonUI());
        c.add(trabButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pagosButton) {
            SecondaryMenu secondaryMenu = new SecondaryMenu("Pago");
            secondaryMenu.setVisible(true);

        } else if (e.getSource() == trabButton) {
            SecondaryMenu secondaryMenu = new SecondaryMenu("Trabajador");
            secondaryMenu.setVisible(true);

        } else if (e.getSource() == obrasButton) {
            SecondaryMenu secondaryMenu = new SecondaryMenu("Obra");
            secondaryMenu.setVisible(true);

        } else if (e.getSource() == reportsButton) {
            MenuInformes menuInformes = new MenuInformes();
            menuInformes.setVisible(true);

        }
    }
}
