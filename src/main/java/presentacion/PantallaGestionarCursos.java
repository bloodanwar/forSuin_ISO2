package presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PantallaGestionarCursos extends JFrame{
    
    private JButton button;
    private JLabel estado;
    private JList cursosLista;
    private DefaultListModel cursosEnviados = new DefaultListModel(); 
 
    
    public PantallaGestionarCursos () {
    	initLayout();
    	basicLayout();
    	botonesLayout();
    }

	private void initLayout() {
		// Propiedades basicas
        getContentPane().setLayout(null);
        setBounds(10, 10, 800,600);
        setTitle("Gestion de cursos propuestos");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
		
	}

	private void basicLayout() {
		// Lista de cursos propuestos -- Se lee de la base de datos
		cursosEnviados.add(0, "Curso 1");
		cursosEnviados.add(1, "Curso 2");
		cursosEnviados.add(2, "Curso 3");
        
        cursosLista = new JList(cursosEnviados);
        cursosLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollLista = new JScrollPane(cursosLista);
        scrollLista.setBounds(199, 113, 400, 200);
        getContentPane().add(scrollLista);
        
        estado = new JLabel("");
        estado.setBackground(new Color(128, 128, 128));
        estado.setBounds(609,150,129,81);
        getContentPane().add(estado);
        
        cursosLista.addListSelectionListener(new ListSelectionListener() { // Da error porque "colisiona" con eliminar
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//String curso = (String) cursosEnviados.getElementAt(cursosLista.getSelectedIndex());
				//estado.setText("Estado de " + curso + ": ACEPTADO");
			}
		});

	}

	private void botonesLayout() {
		// Boton para realizar propuesta de curso
        button = new JButton("Realizar propuesta");
        button.setBounds(94,322,200,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaRealizarPropuestaCurso();
                setVisible(false);
            }

        });
        
        // Boton para editar propuesta de curso
        button = new JButton("Editar");
        button.setBounds(304,324,200,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(cursosLista.isSelectionEmpty()) return;

            	//pantallaEditarCurso --> Re-utilizar pantalla de realizar 
            	//setVisible(false);
            }

        });
        
        // Boton para eliminar propuesta de curso
        button = new JButton("Eliminar");
        button.setBounds(514,324,200,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(cursosLista.isSelectionEmpty()) return;         
            	cursosEnviados.remove(cursosLista.getSelectedIndex());
            }

        });
        
        
        // Boton para ir atras
        button = new JButton("Atras");
        button.setBounds(10,520,200,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaDireccionCursos();
                setVisible(false);
            }

        });
		
	}
}
