package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaJefeGabineteVicerrectorado extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaJefeGabineteVicerrectorado frame = new PantallaJefeGabineteVicerrectorado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaJefeGabineteVicerrectorado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAprobar_cursos = new JButton("Aprobar Cursos");
		btnAprobar_cursos.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
            	new PantallaLogin();
                setVisible(false);
            }
		});
		
		btnAprobar_cursos.setBounds(76, 223, 174, 19);
		contentPane.add(btnAprobar_cursos);
		
		JButton btnRealizar_consulta_cursos = new JButton("Realizar consulta cursos");
		btnRealizar_consulta_cursos.setBounds(404, 223, 199, 19);
		contentPane.add(btnRealizar_consulta_cursos);
		
		JButton btnCerrar_sesion = new JButton("Cerrar sesión");
		btnCerrar_sesion.setBounds(297, 395, 87, 19);
		contentPane.add(btnCerrar_sesion);
	}
}
