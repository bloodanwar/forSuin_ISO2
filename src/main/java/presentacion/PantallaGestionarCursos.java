package presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PantallaGestionarCursos extends JFrame{
    
    private JButton button;
    private JTable cursosTable;
    private DefaultTableModel cursosEnviados = new DefaultTableModel(); 
 
    
    public PantallaGestionarCursos () {
    	initLayout();
    	basicLayout();
    	botonesLayout();
    }

	private void initLayout() {
		// Propiedades basicas
        getContentPane().setLayout(null);
        setBounds(10, 10, 800,600);
        setTitle("Gestion de cursos propuestos");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
		
	}

	private void basicLayout() {
		// Lista de cursos propuestos -- Se lee de la base de datos -- Provisional
		cursosEnviados.addColumn("Nombre");
		cursosEnviados.addColumn("Estado");
		
		cursosEnviados.insertRow(0, new Object[] { "Curso 1", "Pendiente" });
		cursosEnviados.insertRow(1, new Object[] { "Curso 2", "Pendiente"});
		cursosEnviados.insertRow(2, new Object[] { "Curso 3", "Rechazado" });

		cursosTable = new JTable(cursosEnviados){
			  public boolean isCellEditable(int rowIndex, int colIndex) {
				  return false; //Disallow the editing of any cell
			  }
		};
			  
		cursosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        		
        JScrollPane scrollLista = new JScrollPane(cursosTable);
        scrollLista.setBounds(199, 113, 400, 200);
        getContentPane().add(scrollLista);
   
     
	}

	private void botonesLayout() {
		// Boton para realizar propuesta de curso
        button = new JButton("Realizar propuesta");
        button.setBounds(195,324,200,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaRealizarPropuestaCurso();
                setVisible(false);
            }

        });
        
        // Boton para nueva edicion de curso
        button = new JButton("Nueva edición");
        button.setBounds(405,324,200,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(cursosTable.getSelectionModel().isSelectionEmpty()) return;

                //pantallaNuevaEdicion --> Re-utilizar pantalla de realizar 
                //setVisible(false);
            }

        });
        
        // Boton para editar propuesta de curso
        button = new JButton("Editar");
        button.setBounds(195,365,200,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(cursosTable.getSelectionModel().isSelectionEmpty()) return;

            	//pantallaEditarCurso --> Re-utilizar pantalla de realizar 
            	//setVisible(false);
            }

        });
        
        // Boton para eliminar propuesta de curso
        button = new JButton("Eliminar");
        button.setBounds(405,365,200,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(cursosTable.getSelectionModel().isSelectionEmpty()) return;         
            	cursosEnviados.removeRow(cursosTable.getSelectedRow());
            }

        });
        
        
        // Boton para ir atras
        button = new JButton("Atras");
        button.setBounds(10,520,200,30);
        getContentPane().add(button);
    
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaDireccionCursos();
                setVisible(false);
            }

        });
		
	}
}
