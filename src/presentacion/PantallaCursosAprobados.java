package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class PantallaCursosAprobados extends JFrame{

    private JButton button;
    private JList list;
    
    public PantallaCursosAprobados () {
        // Propiedades basicas
        setLayout(null);
        setBounds(10, 10, 800,800);
        setTitle("Cursos aprobados");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    
        // Lista de cursos
        String[] cursos = {"Curso 1", "Curso 2", "Curso 3"}; // Provisional
        list = new JList(cursos);
        
        JScrollPane scrollLista = new JScrollPane();
        scrollLista.setBounds(10, 10, 220, 80);
        scrollLista.setViewportView(list);
        add(scrollLista);
        
        // Botón para ver datos curso
        button = new JButton("Ver datos");
        button.setBounds(10,110,200,30);
        add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                //new
                //setVisible(false);
            }

        });
        
        
        // Botón para ir atras
        button = new JButton("Atras");
        button.setBounds(250,110,200,30);
        add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaDireccionCursos();
                setVisible(false);
            }

        });
    }
}
