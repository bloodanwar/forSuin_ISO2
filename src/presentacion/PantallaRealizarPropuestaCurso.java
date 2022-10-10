package presentacion;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PantallaRealizarPropuestaCurso extends JFrame {

    private JButton button;
    private JLabel label;
    private JTextField text;
    private JTextField[] textArray = new JTextField[5];


    public PantallaRealizarPropuestaCurso () {
        // Propiedades basicas
        setLayout(null);
        setBounds(10, 10, 800,800);
        setTitle("Realizar propuesta curso");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        // Campos para rellenar
        int y = 10;
        for (int i = 0; i<textArray.length; i++) {
            label = new JLabel("Nombre de campo");
            label.setBounds(10,y,200,30);
            add(label);
            y+=25;

            text = new JTextField();
            text.setBounds(10,y,200,30);
            add(text);
            textArray[i] = text;
            y+=50;
        }


        // Botón para enviar propuesta
        button = new JButton("Enviar propuesta");
        button.setBounds(10,y,200,30);
        add(button);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //new PantallaDireccionCursos();
                //setVisible(false);
            }

        });

        // Botón para ir atras
        button = new JButton("Atras");
        button.setBounds(250,y,200,30);
        add(button);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaGestionarCursos();
                setVisible(false);
            }

        });
    }
   
    }