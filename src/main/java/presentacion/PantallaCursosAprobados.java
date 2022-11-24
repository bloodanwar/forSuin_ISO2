package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import negocio.controllers.GestorConsultas;
import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.ProfesorUCLM;

public class PantallaCursosAprobados extends JFrame{

	private List<CursoPropio> cursosDao = null;
    private List<String> cursosNombre = new ArrayList<>();
    private JList<String> cursosLista = new JList<>();

    
    public PantallaCursosAprobados (int type, ProfesorUCLM director) {
    	initLayout();
    	listLayout();
    	botonesLayout(type, director);
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
	
	private void listLayout() {
		GestorConsultas gestor = new GestorConsultas();
		
		try {
			cursosDao = gestor.listarCursosPorEstado(EstadoCurso.VALIDADO);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		
		if(cursosDao != null) {
			for(int i = 0; i<cursosDao.size(); i++) {
				CursoPropio cursoTemp = cursosDao.get(i);
				cursosNombre.add(cursoTemp.getNombre());
			}
		}
		
		
        cursosLista = new JList(cursosNombre.toArray());
        cursosLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollLista = new JScrollPane(cursosLista);
        scrollLista.setBounds(198, 79, 400, 200);
        getContentPane().add(scrollLista);
	}

	private void botonesLayout(final int type, final ProfesorUCLM director) {
        // Boton para ver datos curso
        JButton button = new JButton("Ver datos");
        button.setBounds(296,290,200,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!cursosLista.isSelectionEmpty()) {
            		 new PantallaDatosCurso(type, director, cursosDao.get(cursosLista.getSelectedIndex()));
                     setVisible(false);
            	}  
            }
        });
        
        
        // Boton para ir atras
        button = new JButton("Atras");
        button.setBounds(10,520,200,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (type == 0) new PantallaAlumno();
            	else if (type == 1) new PantallaDireccionCursos(director);
                setVisible(false);
            }

        });
		
	}
}
