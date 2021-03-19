# appgate-prueba-api


Para la prueba de appgate, maneje un proyecto con sprint boot - maven y base de datos H2 mediante una aquietectura de 3 capas en la cual 
 hace referencia a las siguientes definiciones. 


###################################################################################################################################
La programaci�n de software por capas es una arquitectura en la que buscamos separar el c�digo o l�gica que hace tareas de negocios
 (facturar, ventas) de la l�gica de presentaci�n gr�fica y de datos. Tambi�n le conocer como modelo MCV, (vista, controlador, modelo).

La ventaja de este estilo es que el desarrollo es la facilidad de reutilizaci�n y mantenimiento, ya que en caso de alg�n cambio, 
solo se modifica la capa necesaria sin tener que revisar todo el c�digo. 

Identificando las capas

Capa l�gica de presentaci�n

Hace referencia a como se va a presentar la informaci�n del programa al usuario. El objetivo es separar todo aquellos que se muestra 
al usuario, esta capa no tiene conexi�n a base de datos, ni realizar operaciones de ning�n tipo solo muestra datos en pantalla, 
la capa de presentaci�n solicita mediante funciones que se ejecutan en la capa de la l�gica de negocio.

Capa de l�gica de negocio

Aqu� es donde se encuentran las funciones clases y funciones o procedimientos que ser�n invocados a trav�s de la interfaz gr�fica.
 

Recibe peticiones o eventos del usuario, procesa esas peticiones y luego env�a la respuesta a la interfaz gr�fica,
si es necesario esta capa se comunicara con la capa de datos, pero la capa de negocios no se conecta a la base de datos,
solo recibe datos o los procesa. Aqu� se ejecutan e invocan reglas o funciones de negocios por ejemplo, facturar,
listar productos, etc.

Capa de datos

Aqu� tendremos clases y funciones que se conectan a la base de datos y es donde se realizan transacciones con sql para leer,
 insertar, modificar o eliminar informaci�n en la base de datos.
 
Aqu� ejecutaremos consultas sql de forma que ninguna de las otras capas saben donde esta la base de datos, 
as� la capa de presentaci�n podr�a estar en un pc y las otras capas en un servidor como servicio se software Saas.
 
###########################################################################################################################
###########################################################################################################################
										microservicio en spring boot 
###########################################################################################################################