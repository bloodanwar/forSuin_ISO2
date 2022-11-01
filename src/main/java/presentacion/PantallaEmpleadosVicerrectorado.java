package presentacion;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

public class PantallaEmpleadosVicerrectorado extends JFrame {

	private JButton button;
    private JList propuestasLista;
    private DefaultListModel model = new DefaultListModel(); 
	
    public PantallaEmpleadosVicerrectorado () {
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
		// ESPERANDO BBDD
		//		GestorConsultas gestor = new GestorConsultas();
		//		List cursos = null;
		//		
		//		try {
		//			cursos = gestor.listarCursosPorEstado(EstadoCurso.PROPUESTO);
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
        model.add(0, "Propuesta 1");
        model.add(1, "Propuesta 2");
        model.add(2, "Propuesta 3");
        
        propuestasLista = new JList(model);
        propuestasLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollLista = new JScrollPane(propuestasLista);
        scrollLista.setBounds(200, 69, 400, 200);
        getContentPane().add(scrollLista);
	}

	private void botonesLayout() {
		 button = new JButton("Evaluar Propuesta");
	     button.setBounds(300,280,200,30);
	     getContentPane().add(button);

		 button.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	if(propuestasLista.isSelectionEmpty()) return;

	            	//pantallaEvaluarCurso --> Re-utilizar pantalla de realizar --> Añadir cuadro de opinion al rechazar
	                //setVisible(false);
	            }

	     });
		 
		 button = new JButton("Cerrar Sesión");
	     button.setBounds(10,520,200,30);
		 getContentPane().add(button);

		 button.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	new PantallaLogin();
	                setVisible(false);
	            }

	        });
	}

	
	public void evaluarCurso() {
		throw new UnsupportedOperationException();
	}
}