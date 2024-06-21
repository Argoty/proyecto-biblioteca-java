# Sistema de Biblioteca en Java
A continuación; se detallada el proyecto final del espacio académico de Fundamentos de
Programación, el cual usted deberá presentar finalizando el Corte 3 del semestre 2024-1.

Por favor lea atentamente cada una de los detalles y proceda construir el mismo según lo
visto y comprendido a lo largo del semestre.

Objetivo: Desarrollar un sistema básico para la gestión de una
biblioteca en lenguaje de programación JAVA que permita a usuarios
agregar, prestar libros a usuarios y eliminar libros, así como también
la gestión de usuarios (agregar, modificar y eliminar) en sí misma.


# Instrucciones
Características de los libros: |
» Título, genero, autor, año y mes de publicación, + ejemplar, disponibilidad, valor préstamo.

Características de los usuarios:

* Nombre, edad, fecha de nacimiento, dinero disponible [en USD), primer libro prestado,
segundo libro prestado, tercer libro prestado.

Funcionalidades que deberá tener el sistema: 1. Agregar libros a la biblioteca.

Listar todos los libros y sus características.
Prestar libros a usuarios.

Modificar información de libros.

Eliminar libros.

Agregar Usuarios.

Listar usuarios y sus caracteristicas.
Modificar información de usuarios.
Devolver libros.

10. Eliminar usuarios.



# Conceptos a Utilizar en el desarrollo del programa:
* Tipos de Datos: Uso de String, int, boolean y/o double para características de libros y usuarios.

» Estructuras de Control: if, if-else, if-else-if y/o switch para manejar decisiones en la lógica.

+= Estructuras lterativas: for, while y/o do-while para navegar a través de los arreglos de
caracteristicas de los libros, de los usuarios y para la realización de tareas repetitivas.

= Métodos: Crear métodos para cada una de las funcionalidades (Y subprogramas necesarios)
para organizar el código y hacerlo más reusable.
Arreglos: Utilizar arreglos para almacenar información de los libros y de los usuarios.
Solicitud y proyección de información: Hacer uso del componente JOptionPane.

# insumos a entregar por equipo de proyecto:

1 sólo archivo comprimido (por equipo) que contenga los siguientes 3 archivos:

Y 1 documento (en pdf) donde se evidencie la realización de las fases de la resolución de
problemas de software (Análisis del Problema, Diseño del Algoritmo, Desarrollo, Compilación
y Ejecución del Programa, Verificación, Depuración, Mantenimiento, Documentación).

Y 1 carpeta (export) con el código fuente del programa construido en JAVA (etapa de desarrollo).

Y 1 video de máximo 5 minutos donde se evidencie a groso modo el funcionamiento del
programa construido, haciendo uso de la mayoría de funcionalidades solicitadas (Cargar a
Youtube y adjuntar archivo txt con link al video).


# Descripción de las Funcionalidades:

1.

Agregar libros a la biblioteca:

= Solicitar al usuario que ingrese datos del libro: Título, genero, autor, año y mes de
publicación, 4 ejemplar (Almacenar estos datos en arreglos manejando la misma
posición en todos los arreglos por cada uno de los libros.

Listar información de los Libros:

+ Mostrar los libros existentes en la biblioteca, sus características y su disponibilidad.

Prestar libro a Usuario:

» Prestar un libro seleccionado a un usuario (siempre y cuando haya disponibilidad del
mismo).

Modificar información de libros:

* Modificar información (susceptibles a modificación) de libros según libro seleccionado.

Eliminar Libros:

+» Eliminar libros (Siempre y cuando NO se encuentren ejemplares en préstamo).

Agregar Usuarios:

- Agregar usuarios (junto a sus características: Nombre, edad, fecha de nacimiento, dinero

disponible [en USD]).

Listar información de los Usuarios:

= Mostrar todos los usuarios, sus características y libros prestados.

Modificar información de los usuarios:

= Modificar información (características posibles de modificación) de los usuarios según
usuario seleccionado.

Devolver Libros:

+ Permitir a los usuarios devolver libros (Actualizar el estado del libro: disponible -> True).

10. Eliminar Usuarios:

+ Eliminar usuarios (Siempre y cuando NO se encuentren con ejemplares en préstamo).

# Condiciones para el funcionamiento del programa:



Cada una de las 10 funcionalidades deberán ser presentadas al usuario a través de un menú
para pueda seleccionar la opción deseada (1, 2, 3, ... 10, y opción 0 [cero] para salir).

El sistema junto al menú, en su parte inferior deberá mostrar la información de los libros,
disponibilidades, usuarios, prestamos, dinero recaudado en tiempo real.

Cada usuario podrá tener entre 0,0 USD y 10 USD de dinero máximo.

Las categorías de para los libros sólo podrán ser: Terror, Comedia, Romance y Autoayuda. El
valor en USD por cada préstamo respectivamente será: 0.5, 1.0, 1.5 y 2.0

Al agregar un libro nuevo, se le deberá descontar del dinero de la biblioteca el valor del libro
ingresado (el cual corresponde al doble de lo que cuesta un préstamo de dicha categoría).
El sistema deberá considerar la mayor cantidad de validaciones posibles (Un libro con
disponibilidad: False no se podrá prestar, un usuario sin el monto de dinero suficiente no
podrá prestar un libro según categoría, un usuario no podrá prestar más de 3 libros, etc.).

Precondiciones existentes al momento de ejecutar el programa por primera vez:

El sistema deberá permitir 5 usuarios máximo en el sistema.

El sistema deberá iniciar con 2 usuarios predeterminadamente creados al iniciar su ejecución.
El sistema deberá permitir 10 libros máximo en el sistema.

El sistema deberá iniciar con 5 libros predeterminadamente creados al iniciar su ejecución.
El sistema deberá iniciar con 2 préstamos realizados a usuarios al iniciar su ejecución.
