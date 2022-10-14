# HISTORIAS DE USUARIO [REVISAR‼]

## COSAS PRINCIPALES
- [ ] Menu principal -> Inicio de sesión
- [ ] Base de datos
- [ ] USO DE LOGS❕(usando la dependencia de maven Log4j 2)

## DIRECTOR DE CURSO [Es un profesor]
- [ ] Visualizar cursos aprobados -> Cada curso tiene una “página web” con toda la información (Se puede usar la misma interfaz que alumno!?)

- [ ] Visualizar propuesta curso

- [ ] Realizar propuesta curso
  -	Nombre de curso
  -	Descripción
  - Creditos
  - Centro
  -	Edición
  - Matricula
  -	Fecha inicio y fin
  -	Tipo de enseñanza
  - Requisitos necesarios (En caso de master y experto)
  -	Profesor como secretario
  -	Materias -> Nombre + Número de horas + Profesor responsable + Profesores que imparten
  -	Profesores -> Categoría + Si son o no doctores
  -	Fecha cuando se propone el curso
  -	Estado (al realizar poner en pendiente)

- [ ] Editar propuesta curso

- [ ] Cerrar sesión

## PERSONAL VICERRECTORADO
- [ ] Evaluar propuesta de curso -> Aprobar o Rechazar
  - Si se aprueba -> Se podrá empezar a impartir curso en fecha propuesta
  - Si se rechaza -> Completar informe de subsanaciones y recomendaciones de mejora -> Se manda por correo y se podrá volver a proponer

- [ ] Cerrar sesión

## ESTUDIANTE
- [ ] Visualizar cursos aprobados -> Cada curso tiene una “página web” con toda la información (Se puede usar la misma interfaz que profesor!?)

- [ ] Realizar matricula -> Abonar matricula -> Por transferencia o por tarjeta de crédito [usar sistema bancario]
*No puede impartir un mismo curso dos veces

- [ ] Cerrar sesión

## JEFE GABINETE VICERRECTORADO
- [ ] Conocer ingresos globales en cada uno de los tres cursos

- [ ] Realizar consulta de listado de cursos -> Se puede usar filtro

- [ ] Cerrar sesión
