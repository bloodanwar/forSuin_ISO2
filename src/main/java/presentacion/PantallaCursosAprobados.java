package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import negocio.controllers.GestorConsultas;

public class PantallaCursosAprobados extends JFrame{

    private JButton button;
    private JList cursosLista;
    
    public PantallaCursosAprobados () {
    	initLayout();
    	botonesLayout();

    }

	private void initLayout() {
		// Propiedades basicas
        setLayout(null);
        setBounds(10, 10, 800,800);
        setTitle("Cursos aprobados");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
	}

	private void botonesLayout() {
		// Lista de cursos
        String[] cursos = {"Curso 1", "Curso 2", "Curso 3"}; // Provisional -- Leer de la base de datos
        cursosLista = new JList(cursos);
        cursosLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollLista = new JScrollPane(cursosLista);
        scrollLista.setBounds(10, 10, 220, 80);
        add(scrollLista);
        
        // Bot�n para ver datos curso
        button = new JButton("Ver datos");
        button.setBounds(10,110,200,30);
        add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                //new
                //setVisible(false);
            }

        });
        
        
        // Bot�n para ir atras
        button = new JButton("Atras");
        button.setBounds(10,160,200,30);
        add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaDireccionCursos();
                setVisible(false);
            }

        });
		
	}
}
