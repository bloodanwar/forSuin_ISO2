package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class PantallaGestionarCursos extends JFrame{
    
    private JButton button;
    private JList list;
    private DefaultListModel model = new DefaultListModel(); 
    
    public PantallaGestionarCursos () {
        // Propiedades basicas
        setLayout(null);
        setBounds(10, 10, 800,800);
        setTitle("Gestion de cursos propuestos");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        
        // Lista de cursos propuestos -- Leer de bd  
        model.add(0, "Curso 1");
        model.add(1, "Curso 2");
        model.add(2, "Curso 3");
        
        list = new JList(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollLista = new JScrollPane();
        scrollLista.setBounds(10, 10, 220, 80);
        scrollLista.setViewportView(list);
        add(scrollLista);
        
        
        // Boton para realizar propuesta de curso
        button = new JButton("Realizar propuesta");
        button.setBounds(10,110,200,30);
        add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaRealizarPropuestaCurso();
                setVisible(false);
            }

        });
        
        // Boton para editar propuesta de curso
        button = new JButton("Editar");
        button.setBounds(250,110,200,30);
        add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                //new
                //setVisible(false);
            }

        });
        
        // Boton para eliminar propuesta de curso
        button = new JButton("Eliminar");
        button.setBounds(490,110,200,30);
        add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	model.remove(list.getSelectedIndex());
            }

        });
        
        
        // Bot�n para ir atras
        button = new JButton("Atras");
        button.setBounds(10,160,200,30);
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
