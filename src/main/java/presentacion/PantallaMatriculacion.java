package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import negocio.entities.CursoPropio;
import negocio.entities.Estudiante;
import negocio.entities.Matricula;
import negocio.entities.ModoPago;
import negocio.entities.ProfesorUCLM;

@Generated
public class PantallaMatriculacion extends JFrame{

	JRadioButton rdbtnTran;
	JRadioButton rdbtnTarj;

	public PantallaMatriculacion(ProfesorUCLM director, CursoPropio curso) {

		Estudiante estudiante = new Estudiante("12457560J", "Ricardo", "Balas Bodas" , "Enfermeria", "Apto");

		getContentPane().setLayout(null);

		initLayout();
		metodoPago();
		datosCurso(curso);
		buttonLayout(curso, estudiante, director);
	}


	private void initLayout() {
		setBounds(10, 10, 800,600);
		setTitle("Matriculacion");
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void datosCurso(CursoPropio curso) {  // Texto de display de la info del curso
		JLabel tituloCurso = 	new JLabel(curso.getNombre());
		tituloCurso.setHorizontalAlignment(SwingConstants.CENTER);
		tituloCurso.setBounds(250, 50, 300, 14);
		getContentPane().add(tituloCurso);

		JLabel tasaMatricula = new JLabel("Centro: "+curso.centro.getNombre());
		tasaMatricula.setBounds(60, 123, 200, 14);
		getContentPane().add(tasaMatricula);

		JLabel fechaInicio = new JLabel("Fecha Inicio: "+curso.getFechaInicio());
		fechaInicio.setBounds(60, 172, 200, 14);
		getContentPane().add(fechaInicio);

		JLabel edicion = new JLabel("Edicion: "+curso.getEdicion());
		edicion.setBounds(60, 100, 200, 14);
		getContentPane().add(edicion);

		JLabel categoria = new JLabel("Categoria: "+curso.tipo);
		categoria.setBounds(60, 147, 200, 14);
		getContentPane().add(categoria);

		JLabel fechaFin = new JLabel("Fecha Fin: "+curso.getFechaFin());
		fechaFin.setBounds(60, 197, 200, 14);
		getContentPane().add(fechaFin);

		JLabel lblNewLabel = new JLabel("Selecciona tu Metodo de pago");
		lblNewLabel.setBounds(500, 100, 150, 14);
		getContentPane().add(lblNewLabel);

		JLabel totalAPagar = new JLabel("Total a pagar: "+curso.getTasaMatricula());
		totalAPagar.setBounds(500, 196, 200, 14);
		getContentPane().add(totalAPagar);
	}

	private void metodoPago() {
		// Seleccion del metodo de pago
		rdbtnTran = new JRadioButton("Transferencia");
		rdbtnTran.setBounds(526, 121, 150, 15);
		getContentPane().add(rdbtnTran);

		rdbtnTarj = new JRadioButton("Tarjeta");
		rdbtnTarj.setBounds(526, 139, 90, 15);
		getContentPane().add(rdbtnTarj);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnTran);
		group.add(rdbtnTarj);
	}

	private void buttonLayout(final CursoPropio curso, final Estudiante estudiante,final ProfesorUCLM director) {
		// Botones Atras y Pagar
		JButton btnPagar = new JButton("Realizar Pago");
		btnPagar.addActionListener(new ActionListener() {
			@Generated @Override
			public void actionPerformed(ActionEvent e) {
				ModoPago metodoPago = null;
				boolean pagado=false;
				if(rdbtnTran.isSelected()) {
					metodoPago=ModoPago.TRANSFERENCIA;
					pagado=true;
				}
				else if(rdbtnTarj.isSelected()) {
					metodoPago=ModoPago.TARJETA_CREDITO;
					pagado=true;
				};
				Matricula matricula = new Matricula(estudiante,curso);

				matricula.setPagado(pagado);
				matricula.setTipoPago(metodoPago);
			};		
		});
		btnPagar.setBounds(590, 500, 150, 25);
		getContentPane().add(btnPagar);

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			@Generated @Override
			public void actionPerformed(ActionEvent e) {
				new PantallaDatosCurso(0, director, curso);
				setVisible(false);
			}
		});
		btnAtras.setBounds(60, 500, 150, 25);
		getContentPane().add(btnAtras);
	}
}