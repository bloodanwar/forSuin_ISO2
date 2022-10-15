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
    private JList cursosLista;
    private DefaultListModel model = new DefaultListModel(); 
    CountDownLatch latch = new CountDownLatch(2);

    
    public PantallaGestionarCursos () {
    	initLayout();
    	basicLayout();
    	botonesLayout();
    }

	private void initLayout() {
		// Propiedades basicas
        setLayout(null);
        setBounds(10, 10, 800,800);
        setTitle("Gestion de cursos propuestos");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
		
	}

	private void basicLayout() {
		// Lista de cursos propuestos -- Se lee de la base de datos
        model.add(0, "Curso 1");
        model.add(1, "Curso 2");
        model.add(2, "Curso 3");
        
        cursosLista = new JList(model);
        cursosLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollLista = new JScrollPane(cursosLista);
        scrollLista.setBounds(200, 10, 400, 200);
        add(scrollLista);

	}

	private void botonesLayout() {
		// Boton para realizar propuesta de curso
        button = new JButton("Realizar propuesta");
        button.setBounds(10,260,200,30);
        add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaRealizarPropuestaCurso();
                setVisible(false);
            }

        });
        
        // Boton para editar propuesta de curso
        button = new JButton("Editar");
        button.setBounds(250,260,200,30);
        add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(cursosLista.isSelectionEmpty()) return;

            	//pantallaEditarCurso (misma que realizar pero rellenado ??)
                //setVisible(false);
            }

        });
        
        // Boton para eliminar propuesta de curso
        button = new JButton("Eliminar");
        button.setBounds(490,260,200,30);
        add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(cursosLista.isSelectionEmpty()) return;            	
            	model.remove(cursosLista.getSelectedIndex());
            }

        });
        
        
        // Boton para ir atras
        button = new JButton("Atras");
        button.setBounds(10,310,200,30);
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
