package presentacion;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import negocio.entities.CursoPropio;
import negocio.entities.Materia;

public class PantallaRealizarPropuestaCurso extends JFrame {

    private JButton button;
    private JLabel label;
    private JTextField tituloText, fechaInicioText, fechaFinalText, materiaText, horasText, ectsText, requisitoText;
    private JTextArea descripcionText;
    private JScrollPane scrollPanel;
    private JList secretariosLista, categoriasLista, materiasLista, responsablesLista, profesoresMateriaLista;
    
    
    private String[] categorias = {"Másteres de Formación Permanente", "Especialistas", "Expertos", 
    		"Cursos Universitarios de Formación Avnazada", "Cursos de Formación Continua", 
    		"Microcredenciales", "Actividades formativas de corta duración", "Cursos de Verano y Extensión Universitaria", "Formación de Mayores" }; 

    private String[] profesores= {"Profe 1", "Profe 2", "Profe 3"}; // Provisional -- Se lee de la base de datos
    private DefaultListModel materias = new DefaultListModel(); 
    private int idMateria = 0;
    
    private Materia materia;
    private CursoPropio curso;

    public PantallaRealizarPropuestaCurso () {
        // Propiedades basicas
        setLayout(null);
        setBounds(10, 10, 800,800);
        setTitle("Realizar propuesta curso");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        
        // ----- PARTE BASICA ----- //
        
        // Titulo
        label = new JLabel("Titulo de curso");
        label.setBounds(10,10,400,30);
        add(label);

        tituloText = new JTextField();
        tituloText.setBounds(10,40,400,30);
        add(tituloText);
        
        // Descripción
        label = new JLabel("Descripción de curso");
        label.setBounds(10,90,400,30);
        add(label);

        descripcionText = new JTextArea();
        descripcionText.setLineWrap(true);
        scrollPanel = new JScrollPane(descripcionText);
        scrollPanel.setBounds(10,120,400,200);
        add(scrollPanel);

        // Profesor Secretario
        label = new JLabel("Profesor secretario");
        label.setBounds(10,330,400,30);
        add(label);

        secretariosLista = new JList(profesores);
        secretariosLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanel = new JScrollPane(secretariosLista);
        scrollPanel.setBounds(10, 360, 400, 100);
        add(scrollPanel);
        
        // Fecha Inicio
        label = new JLabel("Fecha inicio (DD / MM / AAAA)");
        label.setBounds(10,480,200,30);
        add(label);

        fechaInicioText = new JTextField();
        fechaInicioText.setBounds(10,510,180,30);
        add(fechaInicioText);
        
        // Fecha Final
        label = new JLabel("Fecha final (DD / MM / AAAA)");
        label.setBounds(200,480,200,30);
        add(label);

        fechaFinalText = new JTextField();
        fechaFinalText.setBounds(200,510,180,30);
        add(fechaFinalText);
        
        
        // ----- PARTE DE ENSEÑANZAS ----- //

        // Categoria 
        label = new JLabel("Categoria");
        label.setBounds(500,10,400,30);
        add(label);
        
        categoriasLista = new JList(categorias);
        categoriasLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanel = new JScrollPane(categoriasLista);
        scrollPanel.setBounds(500, 40, 400, 200);
        add(scrollPanel);
        
        
        // Requistio (Depende de categoria)
        label = new JLabel("Requsito");
        label.setBounds(500,260,200,30);
        add(label);

        requisitoText = new JTextField();
        requisitoText.setBounds(500,290,200,30);
        add(requisitoText);
        
        
        // ECTS (Depende de categoria)
        label = new JLabel("ECTS");
        label.setBounds(500,340,200,30);
        add(label);

        ectsText = new JTextField();
        ectsText.setBounds(500,370,200,30);
        add(ectsText);
        
        
        
        // ----- PARTE DE MATERIAS ----- //

        
        // CREACION DE MATERIAS
        label = new JLabel("Lista de materias creadas");
        label.setBounds(1000,10,200,30);
        add(label);
        
        materiasLista = new JList(materias);
        materiasLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanel = new JScrollPane(materiasLista);
        scrollPanel.setBounds(1000, 40, 220, 80);
        add(scrollPanel);

        // Nombre de materia
        label = new JLabel("Nombre de materia");
        label.setBounds(1000,130,200,30);
        add(label);

        materiaText = new JTextField();
        materiaText.setBounds(1000,160,200,30);
        add(materiaText);
        
        
        // Horas de materia
        label = new JLabel("Horas de materia");
        label.setBounds(1300,130,200,30);
        add(label);

        horasText = new JTextField();
        horasText.setBounds(1300,160,200,30);
        add(horasText);
        
        
        // Profesor responsable de materia
        label = new JLabel("Profesor responsable de materia");
        label.setBounds(1000,210,200,30);
        add(label);

        responsablesLista = new JList(profesores);
        responsablesLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanel = new JScrollPane(responsablesLista);
        scrollPanel.setBounds(1000, 240, 220, 80);
        add(scrollPanel);
        
        // Profesores que la imparten
        label = new JLabel("Profesores que imparten la materia (uno o más)");
        label.setBounds(1300,210,300,30);
        add(label);

        profesoresMateriaLista = new JList(profesores);
        profesoresMateriaLista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollPanel = new JScrollPane(profesoresMateriaLista);
        scrollPanel.setBounds(1300, 240, 220, 80);
        add(scrollPanel);
        
        
        // Boton para añadir materia
        button = new JButton("Añadir materia");
        button.setBounds(1300,40,200,30);
        add(button);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) { // Provisional -- Se debe crear materia
            	if (materiaText.getText() == "") return;
            	materias.add(idMateria, materiaText.getText()); 
            	idMateria++;
            	
            }

        });
        
        label = new JLabel("(Completar campos de abajo antes)");
        label.setBounds(1300,70,300,30);
        add(label);
        
        
        // ----- BOTONES ----- //

        
        // Boton para ir atras
        button = new JButton("Atras");
        button.setBounds(1070,430,200,30);
        add(button);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaGestionarCursos();
                setVisible(false);
            }

        });
        
        // Boton para enviar propuesta
        button = new JButton("Enviar propuesta");
        button.setBounds(1300,430,200,30);
        add(button);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	int confirm = JOptionPane.showConfirmDialog(null,"Confirmar enviar propuesta","Confirmación",JOptionPane.YES_NO_OPTION, 1);
            	    if(confirm == 0)  {
            	    	new PantallaGestionarCursos();
                        setVisible(false);
                        // Crear curso y añadir a lista de cursos propuestos
            	    }
                
            }

        });
    }
   
    }