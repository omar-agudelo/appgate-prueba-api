# appgate-prueba-api


Para la prueba de appgate, maneje un proyecto con sprint boot - maven y base de datos H2 mediante una aquietectura de 3 capas en la cual 
 hace referencia a las siguientes definiciones. 



(https://es.slideshare.net/Decimo/arquitectura-3-capas)

La programacionn de software por capas es una arquitectura en la que buscamos separar el cdiogo o logica que hace tareas de negocios
 de la logica de presentacion grafica y de datos. Tambien la conocen como modelo MCV, (vista, controlador, modelo).

La ventaja de este estilo es que el desarrollo es la facilidad de reutilizacion y mantenimiento, ya que en caso de algun cambio, 
solo se modifica la capa necesaria sin tener que revisar todo el cdigo. 

Identificando las capas

Capa logica de presentacion

Hace referencia a como se va a presentar la informacion del programa al usuario. El objetivo es separar todo aquellos que se muestra 
al usuario, esta capa no tiene conexion a base de datos, ni realizar operaciones de ningun tipo solo muestra datos en pantalla, 
la capa de presentacion solicita mediante funciones que se ejecutan en la capa de la logica de negocio.

Capa de logica de negocio

Aqun es donde se encuentran las funciones clases y funciones o procedimientos que seran invocados a traves de la interfaz grafica.
 

Recibe peticiones o eventos del usuario, procesa esas peticiones y luego envia la respuesta a la interfaz grafica,
si es necesario esta capa se comunicara con la capa de datos, pero la capa de negocios no se conecta a la base de datos,
solo recibe datos o los procesa. Aqun se ejecutan e invocan reglas o funciones de negocios por ejemplo, facturar,
listar productos, etc.

Capa de datos

Aqui tendremos clases y funciones que se conectan a la base de datos y es donde se realizan transacciones con sql para leer,
 insertar, modificar o eliminar informacion en la base de datos.
 
Aqui ejecutaremos consultas sql de forma que ninguna de las otras capas saben donde esta la base de datos, 
asi la capa de presentacion podria estar en un pc y las otras capas en un servidor como servicio se software Saas.
 
#################################################################
--CONSUMO DE SERVICIOS 
##################################################################
para el consumo de los servicios se realizan por medio de postman con los siguientes endpoints.

	Carga de archivos
Método POST con body FILE  http://localhost:8696/v1/upload

	Consultar una IP 
Método GET con Header IP http://localhost:8696/v1/v1/findip

--Despliegue del proyecto
Una de las gran vetajas de manejar sprint boot es que maneja su propio servidor de aplicaciones tomcat de esta manra para corer el Proyecto de manera local se ejecuta

En el path raíz de donde se descarga o genera el pull del repositorio, ingresar a la carpeta target que es el build del proyecto y ejecutar

Java –jar appgate-prueba-api por lienas de comandos 
##############################################################
--DUDAS DEL PROYECTO
#############################################################
-al iniciar el proyecto no tenia claro el manejo o la funcionalidad al momento de realizar la consulta de una ip con solo IP_from, IP_to
de esta manera maneje un rango entre esas dos IP´s, 

- no me queda muy claro poder especificar como otorgar o que la aplicación valide una ip real como lo dice el documento

#############################################################
FALTANTES
############################################################

- uno de los faltes fue realizar una prueba unitaria de la carga del archivo csv ya que el tamaño es muy grande 

#######################################################
REPOSITORIO
######################################################

- El repositorio como lo conocen es git hub y maneje los commits con emojis en cada push, esto nos pude dar a conocer
  el tipo de commit que se está realizando para una estructuración eficinte en cada comentario e historial de una aplicación empresarial
  

