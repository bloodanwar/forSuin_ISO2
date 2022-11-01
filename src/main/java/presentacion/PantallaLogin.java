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
	private final JButton btnNewButton = new JButton("Entrar");
	private JButton btnNewButton_1;

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
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		textField = new JTextField("23568907X");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 23, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -270, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, -299, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("DNI:");
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -471, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 12, SpringLayout.EAST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 3, SpringLayout.NORTH, textField);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblbienvenido = new JLabel("¡Bienvenido!");
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, lblbienvenido);
		springLayout.putConstraint(SpringLayout.SOUTH, lblbienvenido, -6, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, lblbienvenido, -347, SpringLayout.EAST, frame.getContentPane());
		lblbienvenido.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(lblbienvenido);
		
		btnNewButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				ProfesorUCLM director = new ProfesorUCLM(textField.getText());
				new PantallaDireccionCursos(director);
				frame.setVisible(false);
			}
		});
		
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Cerrar Programa");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1, 48, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1, -314, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				 System.exit(0);
			}
		});
	}
}