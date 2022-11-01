package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
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
		frame.setBounds(100, 100, 579, 444);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		textField = new JTextField();
		textField.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
			
			}
			public void inputMethodTextChanged(InputMethodEvent event) {
				System.out.println("ola");
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 46, SpringLayout.SOUTH, textField);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("DNI:");
		springLayout.putConstraint(SpringLayout.NORTH, textField, -6, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, textField, 19, SpringLayout.EAST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, textField, 179, SpringLayout.EAST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, -188, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -366, SpringLayout.EAST, frame.getContentPane());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblbienvenido = new JLabel("¡Bienvenido!");
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, lblbienvenido);
		springLayout.putConstraint(SpringLayout.WEST, lblbienvenido, 252, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblbienvenido, -35, SpringLayout.NORTH, textField);
		lblbienvenido.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(lblbienvenido);
		btnNewButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		
		frame.getContentPane().add(btnNewButton);
	}

	public void login() {
		throw new UnsupportedOperationException();
		
		//pedir al usuario el dni
		
		//si no existe dni de error
		
		//si existe que pase a otra ventana
	}

	public void logout() {
		// TODO - implement PantallaLogin.logout
		throw new UnsupportedOperationException();
	}
}
