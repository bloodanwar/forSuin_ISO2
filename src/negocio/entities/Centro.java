package negocio.entities;

import java.util.*;
import persistencia.*;

public class Centro {
	Collection<CursoPropio> cursoPropios;
	Collection<ProfesorUCLM> plantilla;
	CentroDAO centroDao;
	private String nombre;
	private String localizacion;
	private int attribute;
}