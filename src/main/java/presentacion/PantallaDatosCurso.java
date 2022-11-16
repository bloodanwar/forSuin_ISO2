package presentacion;

import javax.swing.JFrame;

import negocio.entities.CursoPropio;
import negocio.entities.ProfesorUCLM;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaDatosCurso extends JFrame {
	private DefaultTableModel materiasCurso = new DefaultTableModel(); 
	
	public PantallaDatosCurso (int type, ProfesorUCLM director, CursoPropio curso) {
		initLayout();
		basicLayout(type, director, curso);
		tablaMaterias(curso);
		botonesLayout(type, director, curso);
	}

	private void initLayout() {
		setBounds(10, 10, 800,600);
		setTitle("Gestion de cursos propuestos");
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void basicLayout(final int type, final ProfesorUCLM director, final CursoPropio curso) {
		// Propiedades basicas
		getContentPane().setLayout(null);
		
		JLabel tasaMatricula = new JLabel("Centro: " + curso.centro.getNombre());
		tasaMatricula.setBounds(42, 143, 200, 14);
		getContentPane().add(tasaMatricula);
		
		JLabel tituloCurso = new JLabel("Titulo: " + curso.getNombre());
		tituloCurso.setBounds(42, 95, 250, 14);
		getContentPane().add(tituloCurso);
		
		JLabel precio = new JLabel("Precio: " + curso.getTasaMatricula());
		precio.setBounds(42, 243, 200, 14);
		getContentPane().add(precio);
		
		JLabel creditos = new JLabel("Creditos: " + curso.getECTS());
		creditos.setBounds(42, 193, 200, 14);
		getContentPane().add(creditos);
		
		JLabel directorLabel = new JLabel("Profesor Director: " + curso.director.getNombre());
		directorLabel.setBounds(42, 168, 200, 14);
		getContentPane().add(directorLabel);
		
		JLabel fechaInicio = new JLabel("Fecha Inicio: " + curso.getFechaInicio());
		fechaInicio.setBounds(42, 268, 200, 14);
		getContentPane().add(fechaInicio);
		
		JLabel edicion = new JLabel("Edicion: " + curso.getEdicion());
		edicion.setBounds(42, 120, 200, 14);
		getContentPane().add(edicion);
		
		JLabel categoria = new JLabel("Categoria: " + curso.tipo);
		categoria.setBounds(42, 218, 200, 14);
		getContentPane().add(categoria);
		
		JLabel fechaFin = new JLabel("Fecha Fin: " + curso.getFechaFin());
		fechaFin.setBounds(42, 293, 200, 14);
		getContentPane().add(fechaFin);

		JLabel materiasLabel = new JLabel("Materias");
		materiasLabel.setBounds(472, 95, 53, 14);
		getContentPane().add(materiasLabel);
	}
	
	private void tablaMaterias(CursoPropio curso) {
		materiasCurso.addColumn("Nombre");
		materiasCurso.addColumn("Responsable");
		materiasCurso.addColumn("Horas");
		materiasCurso.addColumn("Fecha Inicio");
		materiasCurso.addColumn("Fecha Fin");
		
		JTable materiasTable = new JTable(materiasCurso){
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; //Disallow the editing of any cell
			}
		};

		materiasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollLista = new JScrollPane(materiasTable);
		scrollLista.setBounds(305, 119, 400, 213);
		getContentPane().add(scrollLista);
	}
	
	private void botonesLayout(final int type, final ProfesorUCLM director, CursoPropio curso) {
		JButton btnNewButton = new JButton("Atras");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (type == 0 || type == 1) new PantallaCursosAprobados(type, director);
				if (type == 2) new PantallaEmpleadosVicerrectorado();
				setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 513, 200,30);
		getContentPane().add(btnNewButton);
		
		JButton btnMatricular = new JButton("Matricular");
		btnMatricular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// type 0 --> Matricular como estudiante
				// type 2 --> Evaluar como empleado
			}
		});
		btnMatricular.setBounds(574, 513, 200,30);
		getContentPane().add(btnMatricular);
		
		// CAMBIOS DE BOTONES
		if (type == 1) btnMatricular.setVisible(false);
		else if (type == 2) btnMatricular.setText("Evaluar");		
	}
}
