package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Generated
public class PantallaJefeGabineteVicerrectorado extends JFrame {

	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Generated
			public void run() {
				try {
					PantallaJefeGabineteVicerrectorado window = new PantallaJefeGabineteVicerrectorado();

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
		frame = new JFrame();
		frame.setBounds(10, 10, 800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JButton btnAprobar_cursos = new JButton("Aprobar Cursos");
		btnAprobar_cursos.addActionListener(new ActionListener() {
			@Generated @Override
            public void actionPerformed(ActionEvent e) {
            	new PantallaJefeVicerrectoradoAprobar();
                setVisible(false);
            }
		});
		
		btnAprobar_cursos.setBounds(76, 223, 174, 19);
		frame.getContentPane().add(btnAprobar_cursos);
		
		JButton btnRealizar_consulta_cursos = new JButton("Realizar consulta cursos");
		btnRealizar_consulta_cursos.setBounds(404, 223, 199, 19);
		frame.getContentPane().add(btnRealizar_consulta_cursos);
		
		JButton btnCerrar_sesion = new JButton("Cerrar sesión");
		btnCerrar_sesion.setBounds(297, 395, 87, 19);
		frame.getContentPane().add(btnCerrar_sesion);
		
		btnCerrar_sesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PantallaLogin();
				frame.setVisible(false);
			}
		});
	}
}
