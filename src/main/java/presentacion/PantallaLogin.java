package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PantallaLogin extends JFrame {

    private JButton button;
    
    public PantallaLogin () {
        // Propiedades basicas
        getContentPane().setLayout(null);
        setBounds(10, 10, 800,600);
        setTitle("Login [PROTOTIPO]");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        

        
        // Bot�n para iniciar como profesor (prototipo)
        button = new JButton("Profesor");
        button.setBounds(275,157,196,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaDireccionCursos();
                setVisible(false);
            }

        });
        
        // Bot�n para iniciar como personal vicerrectorado (prototipo)
        button = new JButton("Personal Vicerrectorado");
        button.setBounds(275,198,196,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaEmpleadosVicerrectorado();
                setVisible(false);
            }

        });
    }
    
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new PantallaLogin();
    }
    
	public void login() {
		// TODO - implement PantallaLogin.login
		throw new UnsupportedOperationException();
	}

	public void logout() {
		// TODO - implement PantallaLogin.logout
		throw new UnsupportedOperationException();
	}

}