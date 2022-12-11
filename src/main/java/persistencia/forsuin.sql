/*AUTORES: MIRIAM FERNANDEZ OSUNA, SERGIO GARCIA MUÑOZ, RUBEN GOMEZ VILLEGAS Y CARLOS RINCON GONZALEZ*/
DROP TABLE IF EXISTS MATERIA;
DROP TABLE IF EXISTS MATRICULA;
DROP TABLE IF EXISTS CURSOPROPIO;
DROP TABLE IF EXISTS PROFESORUCLM;
DROP TABLE IF EXISTS PROFESOREXTERNO;
DROP TABLE IF EXISTS PROFESOR;
DROP TABLE IF EXISTS ESTUDIANTE;
DROP TABLE IF EXISTS CENTRO;
DROP TABLE IF EXISTS VICERRECTORADO;

/*CREACION DE TABLA VICERRECTORADO*/
CREATE TABLE VICERRECTORADO (
	DNI VARCHAR(45) NOT NULL PRIMARY KEY, 
	NOMBRE VARCHAR(45), 
	APELLIDOS VARCHAR(45), 
	JEFE BOOLEAN, 
	FECHACREACION DATE, 
	FECHAACTUALIZACION DATE
);

/*CREACION DE TABLA CENTRO*/
CREATE TABLE CENTRO (
	NOMBRE VARCHAR(45) NOT NULL PRIMARY KEY,
	LOCALIZACION VARCHAR(45),
	ATRIBUTO INT,
	FECHACREACION DATE,
	FECHAACTUALIZACION DATE
);

/*CREACION DE TABLA ESTUDIANTE*/
CREATE TABLE ESTUDIANTE (
	DNI VARCHAR(45) NOT NULL PRIMARY KEY, 
	NOMBRE VARCHAR(45), 
	APELLIDOS VARCHAR(45), 
	TITULACION VARCHAR(45), 
	CUALIFICACION VARCHAR(45), 
	FECHACREACION DATE, 
	FECHAACTUALIZACION DATE
);

/*CREACION DE TABLA PROFESOR*/
CREATE TABLE PROFESOR (
	DNI VARCHAR(45) NOT NULL PRIMARY KEY, 
	NOMBRE VARCHAR(45), 
	APELLIDOS VARCHAR(45), 
	DOCTOR BOOLEAN, 
	FECHACREACION DATE, 
	FECHAACTUALIZACION DATE
);

/*CREACION DE TABLA PROFESORUCLM*/
CREATE TABLE PROFESORUCLM (
	PROFESOR_DNI VARCHAR(45) NOT NULL PRIMARY KEY,
	FOREIGN KEY (PROFESOR_DNI) REFERENCES PROFESOR(DNI),
	CATEGORIAPROFESOR VARCHAR(45),  
	CENTRO_NOMBRE VARCHAR(45) NOT NULL,
	FOREIGN KEY (CENTRO_NOMBRE) REFERENCES CENTRO(NOMBRE),
	FECHACREACION DATE, 
	FECHAACTUALIZACION DATE
);


/*CREACION DE TABLA PROFESOREXTERNO*/
CREATE TABLE PROFESOREXTERNO (
	PROFESOR_DNI VARCHAR(45) NOT NULL PRIMARY KEY,
	FOREIGN KEY (PROFESOR_DNI) REFERENCES PROFESOR(DNI),
	TITULACION VARCHAR(45),
	FECHACREACION DATE, 
	FECHAACTUALIZACION DATE
); 

/*CREACION DE TABLA CURSOPROPIO*/
CREATE TABLE CURSOPROPIO (
	ID VARCHAR(45) NOT NULL,
	NOMBRE VARCHAR(45),
	ECTS INT,
	FECHAINICIO DATE,
	FECHAFIN DATE,
	TASAMATRICULA DOUBLE,
	EDICION INT NOT NULL,
	ESTADOCURSO VARCHAR(45),
	TIPOCURSO VARCHAR(45),  
	CENTRO_NOMBRE VARCHAR(45) NOT NULL,
	FOREIGN KEY (CENTRO_NOMBRE) REFERENCES CENTRO(NOMBRE),
    SECRETARIO_PROFESOR_DNI VARCHAR(45) NOT NULL,
    FOREIGN KEY (SECRETARIO_PROFESOR_DNI) REFERENCES PROFESORUCLM(PROFESOR_DNI),
    DIRECTOR_PROFESOR_DNI VARCHAR(45) NOT NULL,
    FOREIGN KEY (DIRECTOR_PROFESOR_DNI) REFERENCES PROFESORUCLM(PROFESOR_DNI),
    FECHACREACION DATE,
	FECHAACTUALIZACION DATE,
	PRIMARY KEY(ID, EDICION)
);

/*CREACION DE TABLA MATRICULA*/
CREATE TABLE MATRICULA (
	FECHA DATE, 
	PAGADO BOOLEAN, 
	ATRIBUTO INT, 
	MODOPAGO VARCHAR(45), 
	CURSOPROPIO_ID VARCHAR(45) NOT NULL,
	CURSOPROPIO_EDICION INT NOT NULL,
	FOREIGN KEY (CURSOPROPIO_ID, CURSOPROPIO_EDICION) REFERENCES CURSOPROPIO(ID, EDICION), 
	ESTUDIANTE_DNI VARCHAR(45) NOT NULL,
	FOREIGN KEY (ESTUDIANTE_DNI) REFERENCES ESTUDIANTE (DNI), 
	FECHACREACION DATE, 
	FECHAACTUALIZACION DATE,
	PRIMARY KEY(CURSOPROPIO_ID, CURSOPROPIO_EDICION, ESTUDIANTE_DNI)
);

/*CREACION DE TABLA MATERIA*/
CREATE TABLE MATERIA (
	NOMBRE VARCHAR(45) NOT NULL,
	HORAS DOUBLE,
	FECHAINICIO DATE,
	FECHAFIN DATE,
	CURSOPROPIO_ID VARCHAR(45) NOT NULL,
	CURSOPROPIO_EDICION INT NOT NULL,
	FOREIGN KEY (CURSOPROPIO_ID, CURSOPROPIO_EDICION) REFERENCES CURSOPROPIO(ID, EDICION), 
	RESPONSABLE_PROFESOR_DNI VARCHAR(45) NOT NULL,
	FOREIGN KEY (RESPONSABLE_PROFESOR_DNI) REFERENCES PROFESOR(DNI),
	FECHACREACION DATE, 
	FECHAACTUALIZACION DATE,
	PRIMARY KEY(NOMBRE, CURSOPROPIO_ID, CURSOPROPIO_EDICION)
);

/*VALORES PARA VICERRECTORADO*/
INSERT INTO VICERRECTORADO (dni, nombre, apellidos, jefe) VALUES ('20937167H', 'Paco', 'Jerme Duro', TRUE);
INSERT INTO VICERRECTORADO (dni, nombre, apellidos, jefe) VALUES ('36631551A', 'Aitor', 'Tilla Ruiz', FALSE);
INSERT INTO VICERRECTORADO (dni, nombre, apellidos, jefe) VALUES ('86017372Y', 'Dolores', 'Delano Severo', FALSE);
INSERT INTO VICERRECTORADO (dni, nombre, apellidos, jefe) VALUES ('29643769P', 'Elba', 'Surero Lleno', FALSE);
INSERT INTO VICERRECTORADO (dni, nombre, apellidos, jefe) VALUES ('72709753U', 'Armando', 'Bronca Matamoros', FALSE);
INSERT INTO VICERRECTORADO (dni, nombre, apellidos, jefe) VALUES ('14821461T', 'Ema', 'Amado Bustos', FALSE);
INSERT INTO VICERRECTORADO (dni, nombre, apellidos, jefe) VALUES ('80998895I', 'Luz', 'Cuesta Mogollón', FALSE);
INSERT INTO VICERRECTORADO (dni, nombre, apellidos, jefe) VALUES ('76837339T', 'Lola', 'Mento Mucho', FALSE);
INSERT INTO VICERRECTORADO (dni, nombre, apellidos, jefe) VALUES ('63934392A', 'Elena', 'Nito García', FALSE);
INSERT INTO VICERRECTORADO (dni, nombre, apellidos, jefe)  VALUES ('89217655S', 'Rubén', 'Fermizo de Santos', FALSE);

/*VALORES PARA CENTRO*/
INSERT INTO CENTRO (nombre, localizacion, atributo) VALUES ('UCLM CUE', 'Cuenca', 6);
INSERT INTO CENTRO (nombre, localizacion, atributo) VALUES ('UCLM TAL', 'Talavera de la Reina', 34);
INSERT INTO CENTRO (nombre, localizacion, atributo) VALUES ('UCLM ALM', 'Almaden', 67);
INSERT INTO CENTRO (nombre, localizacion, atributo) VALUES ('UCLM CIU', 'Ciudad real', 12);
INSERT INTO CENTRO (nombre, localizacion, atributo) VALUES ('UCLM ALB', 'Albacete', 0);
INSERT INTO CENTRO (nombre, localizacion, atributo) VALUES ('UCLM GUA', 'Guadalajara', 3);
INSERT INTO CENTRO (nombre, localizacion, atributo) VALUES ('UCLM TOL', 'Toledo', 65);

/*VALORES PARA ESTUDIANTE*/
INSERT INTO ESTUDIANTE (dni, nombre, apellidos, titulacion, cualificacion) VALUES ('12457560J', 'Ricardo', 'Balas Bodas' , 'Enfermeria', 'Apto');
INSERT INTO ESTUDIANTE (dni, nombre, apellidos, titulacion, cualificacion) VALUES ('09764672L', 'Leandro', 'Ñuñez Rincon', 'Podologia', 'No apto');
INSERT INTO ESTUDIANTE (dni, nombre, apellidos, titulacion, cualificacion) VALUES ('23568237K', 'Roberto', 'Sanchez Iglesias', 'Ingenieria agronoma', 'Apto');
INSERT INTO ESTUDIANTE (dni, nombre, apellidos, titulacion, cualificacion) VALUES ('98653134I', 'Carlos', 'Jimenez de la Cruz', 'Ingenieria aeroespacial', 'No apto');
INSERT INTO ESTUDIANTE (dni, nombre, apellidos, titulacion, cualificacion) VALUES ('14709343B', 'Lucia', 'Alfaro Pulido', 'Trabajo social', 'Apto');
INSERT INTO ESTUDIANTE (dni, nombre, apellidos, titulacion, cualificacion) VALUES ('07412568V', 'Andrea', 'Rubio Sanguino', 'Educacion social', 'Apto');
INSERT INTO ESTUDIANTE (dni, nombre, apellidos, titulacion, cualificacion) VALUES ('25885896X', 'Sara', 'Montalban de la Iglesia', 'Arquitectura', 'No apto');
INSERT INTO ESTUDIANTE (dni, nombre, apellidos, titulacion, cualificacion) VALUES ('12563009A', 'Miriam', 'Villegas Gonzalez', 'Ingenieria informatica', 'Apto');
INSERT INTO ESTUDIANTE (dni, nombre, apellidos, titulacion, cualificacion) VALUES ('12457330W', 'Sergio', 'Garcia Osuna', 'Medicina', 'Apto');
INSERT INTO ESTUDIANTE (dni, nombre, apellidos, titulacion, cualificacion) VALUES ('12457220T', 'Alfredo', 'Lorente Taboas', 'Podologia', 'No apto');

/*VALORES PARA PROFESOR*/
INSERT INTO PROFESOR (dni, nombre, apellidos, doctor) VALUES ('12457890Y', 'Jose', 'Perez Esteban', TRUE);
INSERT INTO PROFESOR (dni, nombre, apellidos, doctor) VALUES ('09764312U', 'Antonio', 'Santos Ruiz', FALSE);
INSERT INTO PROFESOR (dni, nombre, apellidos, doctor) VALUES ('23568907X', 'Marcos', 'Fernandez Toledano', FALSE);
INSERT INTO PROFESOR (dni, nombre, apellidos, doctor) VALUES ('98653214Z', 'Miguel', 'Romeo Orozco', TRUE);
INSERT INTO PROFESOR (dni, nombre, apellidos, doctor) VALUES ('14709633I', 'Laura', 'Mendo Palomeque', TRUE);
INSERT INTO PROFESOR (dni, nombre, apellidos, doctor) VALUES ('07412588O', 'Ana', 'Gomez Muñoz', FALSE);
INSERT INTO PROFESOR (dni, nombre, apellidos, doctor) VALUES ('25885236P', 'Elena', 'Parro Lopez', FALSE);
INSERT INTO PROFESOR (dni, nombre, apellidos, doctor) VALUES ('45738298T', 'Alba', 'Vicario Corrochano', TRUE);
INSERT INTO PROFESOR (dni, nombre, apellidos, doctor) VALUES ('60863916B', 'David', 'Marquez Alvarez', TRUE);
INSERT INTO PROFESOR (dni, nombre, apellidos, doctor)  VALUES ('92659487C', 'Luis', 'Garatea Hidalgo', FALSE);

/*VALORES PARA PROFESORUCLM*/
INSERT INTO PROFESORUCLM (profesor_DNI, categoriaProfesor, centro_nombre) VALUES ('12457890Y', 'TITULAR_UNIVERSIDAD', 'UCLM TAL');
INSERT INTO PROFESORUCLM (profesor_DNI, categoriaProfesor, centro_nombre) VALUES ('09764312U', 'ASOCIADO', 'UCLM ALB');
INSERT INTO PROFESORUCLM (profesor_DNI, categoriaProfesor, centro_nombre) VALUES ('23568907X', 'AYUDANTE_DOCTOR', 'UCLM CIU');
INSERT INTO PROFESORUCLM (profesor_DNI, categoriaProfesor, centro_nombre) VALUES ('98653214Z', 'CONTRATADO_DOCTOR', 'UCLM ALM');
INSERT INTO PROFESORUCLM (profesor_DNI, categoriaProfesor, centro_nombre) VALUES ('14709633I', 'CATEDRATICO', 'UCLM GUA');

/*VALORES PARA PROFESOREXTERNO*/
INSERT INTO PROFESOREXTERNO (profesor_Dni, titulacion) VALUES ('07412588O', 'Medicina');
INSERT INTO PROFESOREXTERNO (profesor_Dni, titulacion) VALUES ('25885236P', 'Enfermeria');
INSERT INTO PROFESOREXTERNO (profesor_Dni, titulacion) VALUES ('45738298T', 'Podologia');
INSERT INTO PROFESOREXTERNO (profesor_Dni, titulacion) VALUES ('60863916B', 'Medicina');
INSERT INTO PROFESOREXTERNO (profesor_Dni, titulacion) VALUES ('92659487C', 'Ingenieria agronoma');

/*VALORES PARA CURSOPROPIO*/
INSERT INTO CURSOPROPIO (id, nombre, ECTS, fechaInicio, fechaFin, tasaMatricula, edicion, estadoCurso, tipoCurso, centro_nombre, secretario_Profesor_DNI, director_Profesor_DNI) VALUES ('01', 'Cocina', 1, '2020-09-10 00:00:00', '2020-09-20 00:00:00', 45.5, 1, 'PROPUESTO', 'MASTER', 'UCLM ALB', '12457890Y', '23568907X');
INSERT INTO CURSOPROPIO (id, nombre, ECTS, fechaInicio, fechaFin, tasaMatricula, edicion, estadoCurso, tipoCurso, centro_nombre, secretario_Profesor_DNI, director_Profesor_DNI) VALUES ('02', 'Robotica', 3, '2020-09-25 00:00:00', '2020-09-30 00:00:00', 6.66, 7, 'VALIDADO', 'EXPERTO', 'UCLM CUE', '23568907X', '09764312U');
INSERT INTO CURSOPROPIO (id, nombre, ECTS, fechaInicio, fechaFin, tasaMatricula, edicion, estadoCurso, tipoCurso, centro_nombre, secretario_Profesor_DNI, director_Profesor_DNI) VALUES ('03', 'Gestion de datos', 4, '2020-11-15 00:00:00', '2020-11-20 00:00:00', 66.6, 12, 'TERMINADO', 'CORTA_DURACION', 'UCLM TAL', '12457890Y', '09764312U');
INSERT INTO CURSOPROPIO (id, nombre, ECTS, fechaInicio, fechaFin, tasaMatricula, edicion, estadoCurso, tipoCurso, centro_nombre, secretario_Profesor_DNI, director_Profesor_DNI) VALUES ('04', 'Administracion de empresas', 6, '2021-10-05 00:00:00', '2021-10-10 00:00:00', 2.22, 98, 'TERMINADO', 'FORMACION_CONTINUA', 'UCLM GUA', '12457890Y', '14709633I');
INSERT INTO CURSOPROPIO (id, nombre, ECTS, fechaInicio, fechaFin, tasaMatricula, edicion, estadoCurso, tipoCurso, centro_nombre, secretario_Profesor_DNI, director_Profesor_DNI) VALUES ('05', 'Gestion de sistemas de informacion', 1, '2021-09-01 00:00:00', '2021-09-15 00:00:00', 999.99, 56, 'VALIDADO', 'MICROCREDENCIALES', 'UCLM CIU', '14709633I', '12457890Y');
INSERT INTO CURSOPROPIO (id, nombre, ECTS, fechaInicio, fechaFin, tasaMatricula, edicion, estadoCurso, tipoCurso, centro_nombre, secretario_Profesor_DNI, director_Profesor_DNI) VALUES ('06', 'Hackathon', 5, '2021-12-07 00:00:00', '2021-12-14 00:00:00', 1, 23, 'PROPUESTO', 'CORTA_DURACION', 'UCLM ALM', '12457890Y', '98653214Z');
INSERT INTO CURSOPROPIO (id, nombre, ECTS, fechaInicio, fechaFin, tasaMatricula, edicion, estadoCurso, tipoCurso, centro_nombre, secretario_Profesor_DNI, director_Profesor_DNI) VALUES ('07', 'Educacion social', 2, '2021-09-01 00:00:00', '2021-09-30 00:00:00', 67.5, 5, 'PROPUESTO', 'ESPECIALISTA', 'UCLM TOL', '98653214Z', '12457890Y');
INSERT INTO CURSOPROPIO (id, nombre, ECTS, fechaInicio, fechaFin, tasaMatricula, edicion, estadoCurso, tipoCurso, centro_nombre, secretario_Profesor_DNI, director_Profesor_DNI) VALUES ('08', 'Inclusion adultos en nuevas tecnologias', 3, '2022-10-06 00:00:00', '2022-10-12 00:00:00', 220.6, 73, 'TERMINADO', 'FORMACION_AVANZADA', 'UCLM ALB', '98653214Z', '23568907X');
INSERT INTO CURSOPROPIO (id, nombre, ECTS, fechaInicio, fechaFin, tasaMatricula, edicion, estadoCurso, tipoCurso, centro_nombre, secretario_Profesor_DNI, director_Profesor_DNI) VALUES ('09', 'Instrumentos', 4, '2022-12-12 00:00:00', '2022-12-14 00:00:00', 45.5, 21, 'TERMINADO', 'EXPERTO', 'UCLM ALM', '98653214Z', '14709633I');
INSERT INTO CURSOPROPIO (id, nombre, ECTS, fechaInicio, fechaFin, tasaMatricula, edicion, estadoCurso, tipoCurso, centro_nombre, secretario_Profesor_DNI, director_Profesor_DNI) VALUES ('10', 'Pedagogia infantil', 6, '2022-11-15 00:00:00', '2022-11-18 00:00:00', 19.95, 134, 'VALIDADO', 'CORTA_DURACION', 'UCLM GUA', '14709633I', '23568907X');

/*VALORES PARA MATRICULA*/
INSERT INTO MATRICULA (fecha, pagado, atributo, modoPago, cursoPropio_id, cursoPropio_edicion, estudiante_dni) VALUES ('2020-07-20 00:00:00', TRUE, 4, 'TARJETA_CREDITO', '07', 5, '12457330W');
INSERT INTO MATRICULA (fecha, pagado, atributo, modoPago, cursoPropio_id, cursoPropio_edicion, estudiante_dni) VALUES ('2022-07-22 00:00:00', FALSE, 6, 'TRANSFERENCIA', '01', 1, '09764672L');
INSERT INTO MATRICULA (fecha, pagado, atributo, modoPago, cursoPropio_id, cursoPropio_edicion, estudiante_dni) VALUES ('2022-07-25 00:00:00', TRUE, 3, 'TARJETA_CREDITO', '02', 7, '23568237K');
INSERT INTO MATRICULA (fecha, pagado, atributo, modoPago, cursoPropio_id, cursoPropio_edicion, estudiante_dni) VALUES ('2021-07-29 00:00:00', TRUE, 8, 'TRANSFERENCIA', '10', 134, '98653134I');
INSERT INTO MATRICULA (fecha, pagado, atributo, modoPago, cursoPropio_id, cursoPropio_edicion, estudiante_dni) VALUES ('2021-08-04 00:00:00', FALSE, 9, 'TARJETA_CREDITO', '09', 21, '14709343B');
INSERT INTO MATRICULA (fecha, pagado, atributo, modoPago, cursoPropio_id, cursoPropio_edicion, estudiante_dni) VALUES ('2021-08-10 00:00:00', TRUE, 34, 'TRANSFERENCIA', '08', 73, '07412568V');
INSERT INTO MATRICULA (fecha, pagado, atributo, modoPago, cursoPropio_id, cursoPropio_edicion, estudiante_dni) VALUES ('2021-08-17 00:00:00', FALSE, 2, 'TARJETA_CREDITO', '04', 98, '25885896X');
INSERT INTO MATRICULA (fecha, pagado, atributo, modoPago, cursoPropio_id, cursoPropio_edicion, estudiante_dni) VALUES ('2022-08-25 00:00:00', TRUE, 24, 'TARJETA_CREDITO', '03', 12, '12563009A');
INSERT INTO MATRICULA (fecha, pagado, atributo, modoPago, cursoPropio_id, cursoPropio_edicion, estudiante_dni) VALUES ('2022-09-04 00:00:00', FALSE, 1, 'TARJETA_CREDITO', '06', 23, '12457330W');
INSERT INTO MATRICULA (fecha, pagado, atributo, modoPago, cursoPropio_id, cursoPropio_edicion, estudiante_dni) VALUES ('2022-09-14 00:00:00', TRUE, 90, 'TRANSFERENCIA', '05', 56, '12457220T');

/*VALORES PARA MATERIA*/
INSERT INTO MATERIA (nombre, horas, fechaInicio, fechaFin, cursoPropio_id, cursoPropio_edicion, responsable_Profesor_DNI) VALUES ('Sistemas Distribuidos', 1, '2022-09-12 00:00:00', '2022-12-22 00:00:00', '03', 12, '12457890Y');
INSERT INTO MATERIA (nombre, horas, fechaInicio, fechaFin, cursoPropio_id, cursoPropio_edicion, responsable_Profesor_DNI) VALUES ('Algebra y matematica discreta', 762, '2021-02-20 00:00:00', '2021-07-08 00:00:00', '07', 5, '23568907X');
INSERT INTO MATERIA (nombre, horas, fechaInicio, fechaFin, cursoPropio_id, cursoPropio_edicion, responsable_Profesor_DNI) VALUES ('Estructura de computadores', 875, '2020-02-01 00:00:00', '2021-05-30 00:00:00', '04', 98, '14709633I');
INSERT INTO MATERIA (nombre, horas, fechaInicio, fechaFin, cursoPropio_id, cursoPropio_edicion, responsable_Profesor_DNI) VALUES ('Organizacion de computadores', 234, '2022-09-21 00:00:00', '2022-12-22 00:00:00', '01', 1, '98653214Z');
INSERT INTO MATERIA (nombre, horas, fechaInicio, fechaFin, cursoPropio_id, cursoPropio_edicion, responsable_Profesor_DNI) VALUES ('Tecnologia de computadores', 123, '2021-09-12 00:00:00', '2022-07-09 00:00:00', '02', 7, '09764312U');
INSERT INTO MATERIA (nombre, horas, fechaInicio, fechaFin, cursoPropio_id, cursoPropio_edicion, responsable_Profesor_DNI) VALUES ('Estadistica', 198, '2021-02-01 00:00:00', '2021-06-06 00:00:00', '05', 56, '98653214Z');
INSERT INTO MATERIA (nombre, horas, fechaInicio, fechaFin, cursoPropio_id, cursoPropio_edicion, responsable_Profesor_DNI) VALUES ('Ingenieria del software', 100, '2021-09-12 00:00:00', '2022-12-22 00:00:00', '10', 134, '14709633I');
INSERT INTO MATERIA (nombre, horas, fechaInicio, fechaFin, cursoPropio_id, cursoPropio_edicion, responsable_Profesor_DNI) VALUES ('Logica', 999, '2021-09-12 00:00:00', '2021-12-21 00:00:00', '09', 21, '12457890Y');
INSERT INTO MATERIA (nombre, horas, fechaInicio, fechaFin, cursoPropio_id, cursoPropio_edicion, responsable_Profesor_DNI) VALUES ('Bases de datos', 200, '2021-02-01 00:00:00', '2021-07-09 00:00:00', '06', 23, '12457890Y');
INSERT INTO MATERIA (nombre, horas, fechaInicio, fechaFin, cursoPropio_id, cursoPropio_edicion, responsable_Profesor_DNI) VALUES ('Arquitectura de computadores', 0, '2023-09-12 00:00:00', '2023-12-22 00:00:00', '08', 73, '14709633I');
