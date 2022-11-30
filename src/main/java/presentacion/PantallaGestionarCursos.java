package presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CountDownLatch;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import negocio.controllers.GestorConsultas;
import negocio.controllers.GestorPropuestasCursos;
import negocio.entities.Centro;
import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.ProfesorUCLM;

public class PantallaGestionarCursos extends JFrame{

	private List<CursoPropio> cursosDao = null;
	private DefaultTableModel cursosEnviados = new DefaultTableModel(); 
	private JTable cursosTable = new JTable();


	public PantallaGestionarCursos (ProfesorUCLM director) {
		initLayout();
		listLayout(director);
		botonesLayout(director);
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

	private void listLayout(ProfesorUCLM director) {
		GestorConsultas gestor = new GestorConsultas();

		try {
			cursosDao = gestor.listarCursosPorDirector(director);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		cursosEnviados.addColumn("Nombre");
		cursosEnviados.addColumn("Estado");

		if(cursosDao != null) {	
			for(int i = 0; i<cursosDao.size(); i++) {
				CursoPropio cursoTemp = cursosDao.get(i);
				cursosEnviados.insertRow(i, new Object[] { cursoTemp.getNombre(), cursoTemp.estado });
			}
		}

		cursosTable = new JTable(cursosEnviados){
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; //Disallow the editing of any cell
			}
		};

		cursosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollLista = new JScrollPane(cursosTable);
		scrollLista.setBounds(199, 113, 400, 200);
		getContentPane().add(scrollLista);
	}

	private void botonesLayout(final ProfesorUCLM director) {
		// Boton para realizar propuesta de curso
		JButton button = new JButton("Realizar propuesta");
		button.setBounds(195,324,200,30);
		getContentPane().add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CursoPropio curso = new CursoPropio(""); 
				new PantallaGestionarPropuestaCurso(director, curso, 0);
				setVisible(false);
			}

		});

		// Boton para nueva edicion de curso
		button = new JButton("Nueva edición");
		button.setBounds(405,324,200,30);
		getContentPane().add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(cursosTable.getSelectionModel().isSelectionEmpty()) return;
				CursoPropio curso = cursosDao.get(cursosTable.getSelectedRow());
				if(curso.estado == EstadoCurso.TERMINADO) {
					// TODO -- nueva edicion curso 
					curso.setEdicion(curso.getEdicion() + 1);
					new PantallaGestionarPropuestaCurso(director, curso, 3);
					setVisible(false);
				}
				
			}


		});

		// Boton para editar propuesta de curso
		button = new JButton("Editar");
		button.setBounds(195,365,200,30);
		getContentPane().add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(cursosTable.getSelectionModel().isSelectionEmpty()) return;
				CursoPropio curso = cursosDao.get(cursosTable.getSelectedRow());
				
				if(curso.estado == EstadoCurso.PROPUESTO || curso.estado == EstadoCurso.PROPUESTA_RECHAZADA) {
					// TODO -- editar curso
					new PantallaGestionarPropuestaCurso(director, curso, 2);
					setVisible(false);
				}
				
			}

		});

		// Boton para eliminar propuesta de curso
		button = new JButton("Eliminar");
		button.setBounds(405,365,200,30);
		getContentPane().add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(cursosTable.getSelectionModel().isSelectionEmpty()) return;    
				
				int confirm = JOptionPane.showConfirmDialog(null,"¿Eliminar propuesta de curso?","Eliminar propuesta de curso",JOptionPane.YES_NO_OPTION, 1);
				
				if (confirm == 0) {
					GestorPropuestasCursos gestor = new GestorPropuestasCursos();
					gestor.eliminarPropuestaCurso(cursosDao.get(cursosTable.getSelectedRow()));
					cursosEnviados.removeRow(cursosTable.getSelectedRow());
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
				new PantallaDireccionCursos(director);
				setVisible(false);
			}

		});

	}
}
