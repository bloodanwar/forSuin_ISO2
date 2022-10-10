package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PantallaDireccionCursos extends JFrame{

    private JButton button;
    
    public PantallaDireccionCursos () {
        // Propiedades basicas
        setLayout(null);
        setBounds(10, 10, 500,500);
        setTitle("Dirección de cursos");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        
        // Boton para visualizar cursos aprobados
        button = new JButton("Consultar cursos aprobados");
        button.setBounds(10,10,200,30);
        add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaCursosAprobados();
                setVisible(false);
            }

        });
        
        
        // Boton para gestionar cursos propuestos
        button = new JButton("Gestionar cursos propuestos");
        button.setBounds(10,60,200,30);
        add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaGestionarCursos();
                setVisible(false);
            }

        });
        
        // Botón para cerrar sesion
        button = new JButton("Cerrar sesión");
        button.setBounds(10,110,200,30);
        add(button);
    
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