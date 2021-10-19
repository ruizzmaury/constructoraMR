package com.example.constructora.view.panels;

import com.example.constructora.JDBCRepository.*;
import com.example.constructora.domain.CategoriaLaboral;
import com.example.constructora.domain.Genero;
import com.example.constructora.domain.Trabajador;
import com.example.constructora.view.utils.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class RegistroTrabajadorPanel extends JPanel implements ActionListener {

    private final TrabajadorServiceJDBC trabajadorServiceJDBC = new TrabajadorServiceImplJDBC();
    private final GeneroServiceJDBC generoServiceJDBC = new GeneroServiceImplJDBC();
    private final CatLaboralServiceJDBC catLaboralServiceJDBC = new CatLaboralServiceImplJDBC();
    private final Trabajador workerToUpdate;

    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel name;
    private JTextField tname;
    private JLabel DNI;
    private JTextField tDNI;
    private JLabel phone;
    private JTextField tphone;
    private JLabel gender;
    private JRadioButton male;
    private JRadioButton female;
    private JRadioButton other;
    private JLabel email;
    private JTextField temail;
    private ButtonGroup gengp;
    private JLabel dob;
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;
    private JLabel add;
    private JTextArea tadd;
    private JLabel catLab;
    private JComboBox tiposCatLab;
    private JButton sub;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;
    private boolean badInput = false;

    private String[] loadCatLaboralesNames() {
        List<String> catLabList = catLaboralServiceJDBC.getCatLaboralNames();

        String[] catLabArray = new String[catLabList.size()];
        catLabArray = catLabList.toArray(catLabArray);

        for (String s : catLabArray)
            System.out.println(s);

        return catLabArray;
    }


    // constructor, to initialize the components
    // with default values.
    public RegistroTrabajadorPanel(Trabajador workerToUpdate) throws HeadlessException {
        this.workerToUpdate = workerToUpdate;

        setBounds(300, 90, 1000, 800);

        c = this;
        c.setLayout(null);


        title = new JLabel(Objects.equals(workerToUpdate.getNombre(), null) ? "Registro Trabajador" : "Actualizar Trabajador");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 0);
        c.add(title);

        name = new JLabel("Nombre");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        c.add(name);

        tname = new JTextField(workerToUpdate.getNombre());
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 100);
        c.add(tname);

        DNI = new JLabel("DNI");
        DNI.setFont(new Font("Arial", Font.PLAIN, 20));
        DNI.setSize(100, 20);
        DNI.setLocation(100, 150);
        c.add(DNI);

        tDNI = new JTextField(workerToUpdate.getTrabajador_dni());
        tDNI.setFont(new Font("Arial", Font.PLAIN, 15));
        tDNI.setSize(190, 20);
        tDNI.setLocation(200, 150);
        c.add(tDNI);


        gender = new JLabel("Género");
        gender.setFont(new Font("Arial", Font.PLAIN, 20));
        gender.setSize(100, 20);
        gender.setLocation(100, 200);
        c.add(gender);

        male = new JRadioButton("Hombre");
        male.setFont(new Font("Arial", Font.PLAIN, 15));
//        male.setSelected(false);
        male.setSize(85, 20);
        male.setLocation(200, 200);
        c.add(male);

        female = new JRadioButton("Mujer");
        female.setFont(new Font("Arial", Font.PLAIN, 15));
//        female.setSelected(false);
        female.setSize(80, 20);
        female.setLocation(285, 200);
        c.add(female);

        other = new JRadioButton("Otro");
        other.setFont(new Font("Arial", Font.PLAIN, 15));
//        other.setSelected(false);
        other.setSize(85, 20);
        other.setLocation(360, 200);
        c.add(other);

        switch (workerToUpdate.getGenero() == null ? "" : workerToUpdate.getGenero().getNombreGenero()) {
            case "Hombre" -> {
                male.setSelected(true);
                female.setSelected(false);
                other.setSelected(false);
            }
            case "Mujer" -> {
                male.setSelected(false);
                female.setSelected(true);
                other.setSelected(false);
            }
            case "Otro" -> {
                male.setSelected(false);
                female.setSelected(false);
                other.setSelected(true);
            }
            default -> {
                male.setSelected(false);
                female.setSelected(false);
                other.setSelected(false);
            }
        }

        gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);
        gengp.add(other);

        phone = new JLabel("Teléfono");
        phone.setFont(new Font("Arial", Font.PLAIN, 20));
        phone.setSize(100, 20);
        phone.setLocation(100, 250);
        c.add(phone);

        tphone = new JTextField(String.valueOf(workerToUpdate.getTelefono()).equals("0") ? null : String.valueOf(workerToUpdate.getTelefono()));
        tphone.setFont(new Font("Arial", Font.PLAIN, 15));
        tphone.setSize(150, 20);
        tphone.setLocation(200, 250);
        c.add(tphone);

        email = new JLabel("Email");
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email.setSize(100, 20);
        email.setLocation(100, 300);
        c.add(email);

        temail = new JTextField(workerToUpdate.getEmail() == null ? null : workerToUpdate.getEmail());
        temail.setFont(new Font("Arial", Font.PLAIN, 15));
        temail.setSize(190, 20);
        temail.setLocation(200, 300);
        c.add(temail);

        dob = new JLabel("F.Nac");
        dob.setFont(new Font("Arial", Font.PLAIN, 20));
        dob.setSize(100, 20);
        dob.setLocation(100, 350);
        c.add(dob);

        date = new JComboBox(ViewUtils.DAYS);
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(50, 20);
        date.setLocation(200, 350);
        if (workerToUpdate.getFechaNacimiento() != null)
            date.setSelectedIndex(workerToUpdate.getFechaNacimiento().getDayOfMonth());
        c.add(date);

        month = new JComboBox(ViewUtils.MONTHS);
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(60, 20);
        month.setLocation(260, 350);
        if (workerToUpdate.getFechaNacimiento() != null)
            month.setSelectedIndex(workerToUpdate.getFechaNacimiento().getMonthValue());
        c.add(month);

        year = new JComboBox(ViewUtils.DOBYEARS);
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(60, 20);
        year.setLocation(330, 350);
        if (workerToUpdate.getFechaNacimiento() != null)
            year.setSelectedIndex(workerToUpdate.getFechaNacimiento().getYear() - 1951);
        c.add(year);

        add = new JLabel("Dirección");
        add.setFont(new Font("Arial", Font.PLAIN, 20));
        add.setSize(100, 20);
        add.setLocation(100, 400);
        c.add(add);

        tadd = new JTextArea(workerToUpdate.getDireccion());
        tadd.setFont(new Font("Arial", Font.PLAIN, 15));
        tadd.setSize(200, 75);
        tadd.setLocation(200, 400);
        tadd.setLineWrap(true);
        c.add(tadd);


        catLab = new JLabel("Cat. Laboral");
        catLab.setFont(new Font("Arial", Font.BOLD, 16));
        catLab.setSize(200, 20);
        catLab.setLocation(100, 500);
        c.add(catLab);

        String[] listadoCatLab = loadCatLaboralesNames();
        tiposCatLab = new JComboBox(listadoCatLab);
        tiposCatLab.setFont(new Font("Arial", Font.PLAIN, 15));
        tiposCatLab.setSize(200, 20);
        tiposCatLab.setLocation(200, 500);
        c.add(tiposCatLab);

        if (
                Objects.equals(tadd.getText(), "") ||
                        Objects.equals(temail.getText(), "") ||
                        Objects.equals(tDNI.getText(), "") ||
                        Objects.equals(tname.getText(), "") ||
                        tiposCatLab.getSelectedItem() == null ||
                        date.getSelectedItem() == null ||
                        month.getSelectedItem() == null ||
                        year.getSelectedItem() == null
        ) badInput = true;

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
        tout.setSize(300, 600);
        tout.setLocation(580, 50);
        tout.setLineWrap(true);
        tout.setEditable(false);
        c.add(tout);


        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(580, 50);
        resadd.setLineWrap(true);
        c.add(resadd);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(500, 600);
        c.add(res);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            String genero;
            String catLaboral;

            String data1;
            String data
                    = "Nombre : "
                    + tname.getText() + "\n";
            String data0 = "DNI : " + tDNI.getText() + "\n";

            if (male.isSelected()) {
                genero = male.getText();
                data1 = "Género : Hombre"
                        + "\n";
            } else if (female.isSelected()) {
                genero = female.getText();
                data1 = "Género : Mujer"
                        + "\n";
            } else {
                genero = other.getText();
                data1 = "Género : Otro"
                        + "\n";
            }
            String data2
                    = "Email : "
                    + temail.getText() + "\n"
                    + "Teléfono : "
                    + tphone.getText() + "\n"
                    + "Fecha Nac. : "
                    + (String) date.getSelectedItem()
                    + "/" + (String) month.getSelectedItem()
                    + "/" + (String) year.getSelectedItem()
                    + "\n";

            String data3 = "";
            String data4 = "";
            try {
                data3 = "Dirección : " + tadd.getText() + "\n";
                data4 = "Categoria Laboral: " + tiposCatLab.getSelectedItem() + "\n";

            }catch (Exception ex) {
                badInput = true;
            }

            // TODO:  IMPLEMENTAR CON JDBC UNIQUE ID
            if (badInput) {
                JOptionPane.showMessageDialog(null, "Entrada incorrecta.\n Introduzca valor válido. "
                        , "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Trabajador nuevo = new Trabajador(
                        tDNI.getText(),
                        tname.getText(),
                        new Genero(genero),
                        Integer.parseInt(tphone.getText()),
                        temail.getText(),
                        LocalDate.of(
                                Integer.parseInt(year.getSelectedItem().toString()),
                                month.getSelectedIndex() + 1,
                                Integer.parseInt(date.getSelectedItem().toString())),
                        tadd.getText(),
                        new CategoriaLaboral(
                                tiposCatLab.getSelectedItem().toString(),
                                catLaboralServiceJDBC.getPrecioHora(tiposCatLab.getSelectedItem().toString())
                        )
                );
                System.out.println(nuevo.getCatLaboral().getClass());
                // TODO : IF workerToUpdate.getNombre() == "" create new worker, otherwise will update the worker
                if (workerToUpdate.getNombre() == null) {
                    trabajadorServiceJDBC.create(nuevo);
                } else {
                    trabajadorServiceJDBC.update(nuevo);
                }
                res.setText("Trabajador registrado correctamente.");

                tout.setText(data + data0 + data1 + data2 + data3 + data4);
                tout.setEditable(false);
            }


        } else if (e.getSource() == reset) {
            String def = "";
            tname.setText(def);
            tadd.setText(def);
            tphone.setText(def);
            res.setText(def);
            tout.setText(def);
            date.setSelectedIndex(0);
            month.setSelectedIndex(0);
            year.setSelectedIndex(0);
            tiposCatLab.setSelectedIndex(0);
            resadd.setText(def);

        }

    }


}

