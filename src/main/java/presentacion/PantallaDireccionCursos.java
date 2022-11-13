package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import negocio.entities.ProfesorUCLM;

public class PantallaDireccionCursos extends JFrame{

    private JButton button;
    
    public PantallaDireccionCursos (ProfesorUCLM director) {
    	initLayout();
    	botonesLayout(director);        
    }
    
	private void initLayout() {
		// Propiedades basicas
        getContentPane().setLayout(null);
        setBounds(10, 10, 800,600);
        setTitle("Direccion de cursos");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
	private void botonesLayout(final ProfesorUCLM director) {
		// Boton para visualizar cursos aprobados
        button = new JButton("Consultar cursos aprobados");
        button.setBounds(300,163,200,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaCursosAprobados(1, director);
                setVisible(false);
            }
        });
        
        // Boton para gestionar cursos propuestos
        button = new JButton("Gestionar cursos propuestos");
        button.setBounds(300,213,200,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaGestionarCursos(director);
                setVisible(false);
            }
        });
        
        // Boton para cerrar sesion
        button = new JButton("Cerrar sesion");
        button.setBounds(300,263,200,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaLogin();
                setVisible(false);
            }
        });
	}

	public void altaCurso() {
		// TODO - implement PantallaDireccionCursos.altaCurso
		throw new UnsupportedOperationException();
	}

	public void edicionCurso() {
		// TODO - implement PantallaDireccionCursos.edicionCurso
		throw new UnsupportedOperationException();
	}
}