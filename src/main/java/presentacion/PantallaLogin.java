package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import negocio.entities.ProfesorUCLM;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class PantallaLogin {

	private JFrame frame;
	private JTextField textField;
	private final JButton entrar = new JButton("Entrar");
	private JButton cerrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaLogin window = new PantallaLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(10, 10, 800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		textField = new JTextField("23568907X");
		textField.setBounds(326, 90, 160, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("DNI:");
		lblNewLabel.setBounds(292, 93, 22, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblbienvenido = new JLabel("¡Bienvenido!");
		lblbienvenido.setBounds(378, 70, 60, 14);
		lblbienvenido.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(lblbienvenido);
		entrar.setBounds(326, 131, 160, 23);
		
		entrar.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				ProfesorUCLM director = new ProfesorUCLM(textField.getText());
				new PantallaDireccionCursos(director);
				frame.setVisible(false);
			}
		});
		
		frame.getContentPane().add(entrar);
		
		cerrar = new JButton("Cerrar Programa");
		cerrar.setBounds(326, 165, 160, 23);
		frame.getContentPane().add(cerrar);
		
		JButton profesor = new JButton("Profesor");
		profesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfesorUCLM director = new ProfesorUCLM("23568907X");
				new PantallaDireccionCursos(director);
				frame.setVisible(false);
			}
		});
		
		profesor.setBounds(326, 304, 160, 23);
		frame.getContentPane().add(profesor);
		
		JButton pesonal = new JButton("Empleados Vicerrectorado");
		pesonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PantallaEmpleadosVicerrectorado();
				frame.setVisible(false);
			}
		});
		pesonal.setBounds(326, 338, 160, 23);
		frame.getContentPane().add(pesonal);
		
		JLabel lblBotonesPrototipo = new JLabel("Botones Prototipo");
		lblBotonesPrototipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBotonesPrototipo.setBounds(326, 279, 144, 14);
		frame.getContentPane().add(lblBotonesPrototipo);
		
		cerrar.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				 System.exit(0);
			}
		});
	}
}