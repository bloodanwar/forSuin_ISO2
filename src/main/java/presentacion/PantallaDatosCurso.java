package presentacion;

import javax.swing.JFrame;

import negocio.entities.ProfesorUCLM;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaDatosCurso extends JFrame {
	public PantallaDatosCurso (int type, ProfesorUCLM director) {
		initLayout(type, director);
	}

	private void initLayout(final int type, final ProfesorUCLM director) {
		// Propiedades basicas
		getContentPane().setLayout(null);
		
		JLabel tasaMatricula = new JLabel("Este curso es.... ");
		tasaMatricula.setBounds(81, 86, 153, 14);
		getContentPane().add(tasaMatricula);
		
		JLabel tituloCurso = new JLabel("Titulo de curso");
		tituloCurso.setBounds(81, 50, 161, 14);
		getContentPane().add(tituloCurso);
		
		JButton btnNewButton = new JButton("Atras");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (type == 0 || type == 1) new PantallaCursosAprobados(type, director);
				if (type == 2) new PantallaEmpleadosVicerrectorado();
				setVisible(false);
			}
		});
		btnNewButton.setBounds(42, 513, 89, 23);
		getContentPane().add(btnNewButton);
		setBounds(10, 10, 800,600);
		setTitle("Gestion de cursos propuestos");
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}
}
