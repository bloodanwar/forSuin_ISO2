package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PantallaLogin extends JFrame {

    private JButton button;
    
    public PantallaLogin () {
        // Propiedades basicas
        setLayout(null);
        setBounds(10, 10, 500,500);
        setTitle("Login [PROTOTIPO]");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        
        // Bot�n para iniciar como profesor (prototipo)
        button = new JButton("Profesor");
        button.setBounds(10,10,100,30);
        add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaDireccionCursos();
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