package presentacion;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class PantallaRealizarPropuestaCurso extends JFrame {

    private JButton button;
    private JLabel label;
    private JTextField tituloText, fechaText, materiaText;
    private JTextArea descripcionText;
    private JScrollPane scrollLista;
    private JList profesores, categorias, materias;
    private String[] profesoresLista = {"Profe 1", "Profe 2", "Profe 3"}; // Provisional

    public PantallaRealizarPropuestaCurso () {
        // Propiedades basicas
        setLayout(null);
        setBounds(10, 10, 800,800);
        setTitle("Realizar propuesta curso");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        // Titulo
        label = new JLabel("Titulo de curso");
        label.setBounds(10,10,200,30);
        add(label);

        tituloText = new JTextField();
        tituloText.setBounds(10,40,200,30);
        add(tituloText);
        
        
        // Descripción
        label = new JLabel("Descripción de curso");
        label.setBounds(10,90,200,30);
        add(label);

        descripcionText = new JTextArea();
        descripcionText.setBounds(10,120,200,200);
        add(descripcionText);

        // Fecha propuesta
        label = new JLabel("Fecha propuesta (DD/MM/AAAA)");
        label.setBounds(10,350,200,30);
        add(label);

        fechaText = new JTextField();
        fechaText.setBounds(10,380,200,30);
        add(fechaText);
        
        // Tipo de enseñanza y subcategoria
        label = new JLabel("Tipo de enseñanza >> Categoria");
        label.setBounds(300,10,200,30);
        add(label);
        
        String[] categoriasLista = {"Categoria 1", "Categoria 2", "Categoria 3"}; // Provisional
        categorias = new JList(categoriasLista);
        scrollLista = new JScrollPane();
        scrollLista.setBounds(300, 40, 220, 80);
        scrollLista.setViewportView(categorias);
        add(scrollLista);

        
        // Profesor Secretario
        label = new JLabel("Profesor secretario");
        label.setBounds(300,130,200,30);
        add(label);

        profesores = new JList(profesoresLista);
        scrollLista = new JScrollPane();
        scrollLista.setBounds(300, 160, 220, 80);
        scrollLista.setViewportView(profesores);
        add(scrollLista);
        
        
        // CREACION DE MATERIAS
        label = new JLabel("Lista de materias creadas");
        label.setBounds(600,10,200,30);
        add(label);
        
        String[] materiasLista = {"Materia Creada 1", "Materia Creada 1", "Materia Creada 1"}; // Provisional
        materias = new JList(materiasLista);
        scrollLista = new JScrollPane();
        scrollLista.setBounds(600, 40, 220, 80);
        scrollLista.setViewportView(materias);
        add(scrollLista);

        // Nombre de materia
        label = new JLabel("Nombre de materia");
        label.setBounds(600,130,200,30);
        add(label);

        materiaText = new JTextField();
        materiaText.setBounds(600,160,200,30);
        add(materiaText);
        
        
        // Profesor responsable de materia
        label = new JLabel("Profesor responsable de materia");
        label.setBounds(600,210,200,30);
        add(label);

        profesores = new JList(profesoresLista);
        scrollLista = new JScrollPane();
        scrollLista.setBounds(600, 240, 220, 80);
        scrollLista.setViewportView(profesores);
        add(scrollLista);
        
        // Profesores que la imparten
        label = new JLabel("Profesores que imparten la materia (uno o más)");
        label.setBounds(900,210,300,30);
        add(label);

        profesores = new JList(profesoresLista);
        profesores.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollLista = new JScrollPane();
        scrollLista.setBounds(900, 240, 220, 80);
        scrollLista.setViewportView(profesores);
        add(scrollLista);
        
        
        // Boton para añadir materia
        button = new JButton("Añadir materia");
        button.setBounds(900,40,200,30);
        add(button);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });
        
        label = new JLabel("(Completar campos de abajo antes)");
        label.setBounds(900,70,300,30);
        add(label);
        
        
        // Boton para enviar propuesta
        button = new JButton("Enviar propuesta");
        button.setBounds(680,430,200,30);
        add(button);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //new PantallaDireccionCursos();
                //setVisible(false);
            }

        });

        // Bot�n para ir atras
        button = new JButton("Atras");
        button.setBounds(900,430,200,30);
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