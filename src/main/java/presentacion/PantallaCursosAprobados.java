package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import negocio.controllers.GestorConsultas;
import negocio.entities.EstadoCurso;
import negocio.entities.ProfesorUCLM;

public class PantallaCursosAprobados extends JFrame{

    private JButton button;
    private JList cursosLista;
    
    public PantallaCursosAprobados (ProfesorUCLM director) {
    	initLayout();
    	botonesLayout(director);

    }

	private void initLayout() {
		// Propiedades basicas
        getContentPane().setLayout(null);
        setBounds(10, 10, 800,600);
        setTitle("Cursos aprobados");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
		
	}

	private void botonesLayout(final ProfesorUCLM director) {
		// ESPERANDO BBDD
		//		GestorConsultas gestor = new GestorConsultas();
		//		List cursos = null;
		//		
		//		try {
		//			cursos = gestor.listarCursosPorEstado(EstadoCurso.VALIDADO);
		//		} catch (ParseException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//
		//		if(cursos != null) {
		//			ListIterator<String> iterator = cursos.listIterator();
		//
		//			while (iterator.hasNext()) {
		//				System.out.println(iterator.next());
		//			}
		//		}
		
		
		// PROVISIONAL --
        String[] cursos = {"Curso 1", "Curso 2", "Curso 3"}; 
        cursosLista = new JList(cursos);
        cursosLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollLista = new JScrollPane(cursosLista);
        scrollLista.setBounds(198, 79, 400, 200);
        getContentPane().add(scrollLista);
        
        // Bot�n para ver datos curso
        button = new JButton("Ver datos");
        button.setBounds(296,290,200,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                //new
                //setVisible(false);
            }

        });
        
        
        // Bot�n para ir atras
        button = new JButton("Atras");
        button.setBounds(10,520,200,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaDireccionCursos(director);
                setVisible(false);
            }

        });
		
	}
}
