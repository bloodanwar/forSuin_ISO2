package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import negocio.controllers.GestorConsultas;
import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;

import javax.swing.JButton;
import javax.swing.JList;

public class PantallaJefeVicerrectoradoAprobar extends JFrame {

	private List<CursoPropio> cursosDao = null;
    private List<String> cursosNombre = new ArrayList<>();
    private JList<String> propuestasLista = new JList<>();
    private JButton btnAtras;
	
    public PantallaJefeVicerrectoradoAprobar () {
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
		GestorConsultas gestor = new GestorConsultas();

		try {
			cursosDao = gestor.listarCursosPorEstado(EstadoCurso.PROPUESTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		if(cursosDao != null) {
			for(int i = 0; i<cursosDao.size(); i++) {
				CursoPropio cursoTemp = cursosDao.get(i);
				cursosNombre.add(cursoTemp.getNombre());
			}
		}

        propuestasLista = new JList(cursosNombre.toArray());
        propuestasLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollLista = new JScrollPane(propuestasLista);
        scrollLista.setBounds(200, 69, 400, 200);
        getContentPane().add(scrollLista);
	}

	private void botonesLayout() {
		 JButton button = new JButton("Ver datos");
	     button.setBounds(300,280,200,30);
	     getContentPane().add(button);

		 button.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	if(propuestasLista.isSelectionEmpty()) return;
	            	new PantallaDatosCurso(2, null, (CursoPropio) cursosDao.get(propuestasLista.getSelectedIndex()));
	                setVisible(false);
	            }

	     });
		 
		 btnAtras = new JButton("Atras");
	     btnAtras.setBounds(300,321,200,30);
		 getContentPane().add(btnAtras);

		 btnAtras.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	new PantallaJefeGabineteVicerrectorado();
	                setVisible(false);
	            }
	        });
	}
	
	public void evaluarCurso() {
		throw new UnsupportedOperationException();
	}
}