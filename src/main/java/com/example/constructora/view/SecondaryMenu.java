package com.example.constructora.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class SecondaryMenu extends JFrame
        implements ActionListener {

    private final String typeNameMenu;
    private Container c;
    private JLabel title;
    private JButton consultaPagosButton;
    private JButton nuevoRegistroButton;
    private JButton backButton;

    public SecondaryMenu(String typeMenu) throws HeadlessException {
        typeNameMenu = typeMenu;
        // make the frame half the height and width



        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        Rectangle r = this.getBounds();

        int height = r.height;
        int width = r.width;
        System.out.println(height + " " + width);

        if (typeNameMenu.equals("Trabajador")) {
            setTitle("Menú " + typeNameMenu + "es");
            title = new JLabel("Menú " + typeNameMenu + "es");
        } else {
            setTitle("Menú " + typeNameMenu + "s");
            title = new JLabel("Menú " + typeNameMenu + "s");
        }

        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(300, 30);
        title.setLocation(width/2-150, height/10);
        c.add(title);

        consultaPagosButton = new JButton("CONSULTA");
        consultaPagosButton.setFont(new Font("Arial", Font.BOLD, 30));
        consultaPagosButton.setSize(400, 100);
        consultaPagosButton.setLocation(width/2-200, height / 4);
        consultaPagosButton.addActionListener(this);
        c.add(consultaPagosButton);

        nuevoRegistroButton = new JButton("NUEVO " + typeNameMenu.toUpperCase(Locale.ROOT));
        nuevoRegistroButton.setFont(new Font("Arial", Font.BOLD, 30));
        nuevoRegistroButton.setSize(400, 100);
        nuevoRegistroButton.setLocation(width/2-200, height / 4 + 120);
        nuevoRegistroButton.addActionListener(this);
        c.add(nuevoRegistroButton);


        backButton = new JButton("VOLVER");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setSize(120, 40);
        backButton.setLocation(width/2-60, height / 4 + 250);
        backButton.addActionListener(this);
        c.add(backButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == consultaPagosButton) {
            switch (typeNameMenu) {
                case "Pago":
                    ConsultaPagosView consultaPagosView = new ConsultaPagosView();
                    this.dispose();
                    break;
                case "Trabajador":
                    ConsultaTrabajadoresView consultaTablaTrabajadoresView = new ConsultaTrabajadoresView();
                    this.dispose();
                    break;
                case "Obra":
                    ConsultaObrasView consultaObrasView = new ConsultaObrasView();
                    this.dispose();
                    break;
            }


        } else if (e.getSource() == nuevoRegistroButton) {
            switch (typeNameMenu) {
                case "Pago":
                    RegistroPago registroPago = new RegistroPago();
                    registroPago.setVisible(true);
                    this.dispose();
                    break;
                case "Trabajador":
                    RegistroTrabajador registroTrabajador = new RegistroTrabajador();
                    registroTrabajador.setVisible(true);
                    this.dispose();
                    break;
                case "Obra":
                    RegistroObra registroObra = new RegistroObra();
                    registroObra.setVisible(true);
                    this.dispose();
                    break;
            }

        } else if (e.getSource() == backButton) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
            this.dispose();
        }
    }
}

