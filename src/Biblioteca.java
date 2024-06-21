import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Biblioteca {

	public static void main(String[] args) {
		double dineroBiblioteca = solicitarDineroInicialBiblioteca();
		int opcionMenu = -1;

		// ---------------------- LIBROS INFORMACION --------------------------
		ArrayList<String> titulo = new ArrayList<String>();
		ArrayList<String> genero = new ArrayList<String>();
		ArrayList<String> autor = new ArrayList<String>();
		ArrayList<String> ano = new ArrayList<String>();
		ArrayList<Integer> ejemplar = new ArrayList<Integer>();
		ArrayList<Boolean> disponible = new ArrayList<Boolean>();
		ArrayList<Double> valorPrest = new ArrayList<Double>();

		// Agrega 5 Libros predeterminados al ejecutar
		agregarLibrosPredeterminados(titulo, genero, autor, ano, ejemplar, disponible, valorPrest);

		// ------------------ USUARIOS --------------------
		ArrayList<String> nombre = new ArrayList<String>();
		ArrayList<Integer> edad = new ArrayList<Integer>();
		ArrayList<String> fechaNaci = new ArrayList<String>();
		ArrayList<Double> dineroDisp = new ArrayList<Double>();
		ArrayList<Integer> librosPrestados = new ArrayList<Integer>();
		ArrayList<Integer> Libro1 = new ArrayList<Integer>();
		ArrayList<Integer> Libro2 = new ArrayList<Integer>();
		ArrayList<Integer> Libro3 = new ArrayList<Integer>();

		// 2 USUARIOS POR DEFECTO
		agregarUsuariosPredeterminados(nombre, edad, fechaNaci, dineroDisp, librosPrestados, Libro1, Libro2, Libro3,
				librosPrestados, disponible);

		// ------------------------- MENU DE OPCIONES DE LA BIBLIOTECA
		// -----------------------
		while (opcionMenu != 0) {
			String infoLibros = mostrarInfoLibros(titulo, genero, autor, ano, ejemplar, disponible, valorPrest); // informacion
																													// completa
																													// sobre
																													// los
																													// libros
			String infoUsuarios = listarUsuarios(nombre, edad, fechaNaci, dineroDisp, librosPrestados, Libro1, Libro2,
					Libro3, titulo, ejemplar);

			opcionMenu = solicitarInt(
					"---------------- MENÚ DE SISTEMA DE BIBLIOTECA ---------------- \n1. Agregar Libro a Biblioteca\n"
							+ "2. Listar libros y sus características\n3. Prestar libros a usuarios\n4. Modificar Información de libros\n5. Eliminar libro\n6. Agregar Usuario\n"
							+ "7. Listar Usuarios y sus características\n8. Modificar Usuario\n9. Devolver libro\n10. Eliminar Usuarios\n0. Salir\n\n"
							+ infoLibros + "\n" + infoUsuarios + "\n------- DINERO DE LA BIBLIOTECA: $"
							+ dineroBiblioteca + " -------");

			if (opcionMenu == 1) {
				// Solo deja agregar si hay menos de 10 libros, ya que el sistema solo permite
				// 10 libros como maximo
				if (titulo.size() < 10) {
					// Agregar Libro y retorna la cantidad de dinero nueva de la biblioteca
					dineroBiblioteca = agregarLibro(titulo, genero, autor, ano, ejemplar, disponible, valorPrest,
							dineroBiblioteca);
				} else
					JOptionPane.showMessageDialog(null, "¡El sistema solo deja registrar máximo 10 libros!", "",
							JOptionPane.ERROR_MESSAGE);

			} else if (opcionMenu == 2) {
				JOptionPane.showMessageDialog(null, infoLibros); // Muestra los libros
			} else if (opcionMenu == 3) {
				// ------------------------------
				// retorna el dinero de la biblioteca para actualizarlo segun las posibles
				// modificaciones
				dineroBiblioteca = prestarLibro(nombre, dineroDisp, dineroBiblioteca, Libro1, Libro2, Libro3, titulo,
						valorPrest, disponible, ejemplar, librosPrestados);

			} else if (opcionMenu == 4) {
				modificarLibro(titulo, genero, autor, ano, ejemplar, disponible, valorPrest);
			} else if (opcionMenu == 5) {
				eliminarLibro(titulo, genero, autor, ano, ejemplar, disponible, valorPrest);
			} else if (opcionMenu == 6) {
				// Solo deja agregar si hay menos de 5 usuarios, ya que el sistema solo permite
				// 5 usuarios como maximo
				if (nombre.size() < 5) {
					agregarUsuario(nombre, edad, fechaNaci, dineroDisp, librosPrestados, Libro1, Libro2, Libro3);
				} else
					JOptionPane.showMessageDialog(null, "¡El sistema solo deja registrar máximo 5 usuarios!", "",
							JOptionPane.ERROR_MESSAGE);

			} else if (opcionMenu == 7) {
				JOptionPane.showMessageDialog(null, infoUsuarios);

			} else if (opcionMenu == 8) {
				modificarUsuarios(nombre, edad, fechaNaci, dineroDisp, librosPrestados);
			} else if (opcionMenu == 9) {
				// --------------------------
				devolverLibro(nombre, Libro1, Libro2, Libro3, disponible, titulo, ejemplar, librosPrestados);

			} else if (opcionMenu == 10) {
				eliminarUsuarios(nombre, edad, fechaNaci, dineroDisp, librosPrestados, Libro1, Libro2, Libro3);
			}
		}

	}

	public static double agregarLibro(ArrayList<String> titulo, ArrayList<String> genero, ArrayList<String> autor,
			ArrayList<String> ano, ArrayList<Integer> ejemplar, ArrayList<Boolean> disponible,
			ArrayList<Double> valorPrest, double dineroB) {
		String[] opcionesGeneros = { "Terror", "Comedia", "Romance", "Autoayuda" }; // Opciones de generos en libros

		String tit = JOptionPane.showInputDialog("Ingrese el titulo del libro");

		// Dialogo que muestra las opciones de generos y obtiene el indice de la
		// posicion del genero escogido
		int seleccion = JOptionPane.showOptionDialog(null, "Seleccione el genero del libro:", "Genero del libro",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcionesGeneros, opcionesGeneros[0]);

		// Se almacena en variable 'vlrPrestar' el valor de prestamo del libro segun el
		// genero del libro ( en el documento pdf estan los precios )
		double vlrPrestar = seleccion == 0 ? 0.5 : seleccion == 1 ? 1 : seleccion == 2 ? 1.5 : seleccion == 3 ? 2 : 0;

		// Se obtiene el dinero parcial de la biblioteca restando el dinero que se tenga
		// y el valor doble del prestamo del libro
		double dineroBibliotecaParcial = dineroB - (vlrPrestar * 2);

		// Si el dinero parcial queda en negativos entonces no se agrega ningun libro al
		// final porque no tiene suficiente dinero la biblioteca, se muestra mensaje de
		// error
		if (dineroBibliotecaParcial < 0) {
			// Muestra mensaje de error por insuficiencia de dinero.
			JOptionPane.showMessageDialog(null, "¡No se puede agregar el libro de genero '" + opcionesGeneros[seleccion]
					+ "' porque la biblioteca tiene solo $" + dineroB + " y el libro cuesta $" + (vlrPrestar* 2) + "!", "", JOptionPane.ERROR_MESSAGE);
			return dineroB;
		} else {
			// Si si tiene dinero entonces agrega todo lo demas
			titulo.add(tit);

			genero.add(opcionesGeneros[seleccion]);

			String aut = JOptionPane.showInputDialog("Ingrese el nombre del autor del libro");
			autor.add(aut);

			String anio = JOptionPane.showInputDialog("Indique el año y mes de publicacion");
			ano.add(anio);

			int ejem = solicitarInt("Indique el numero de ejemplar");
			ejemplar.add(ejem);

			valorPrest.add(vlrPrestar); // la variable vlrPrestar fue declarada anteriormente antes del condicional del
										// dinero de biblioteca

			disponible.add(true);

			JOptionPane.showMessageDialog(null, "¡El libro '" + tit + "' fue agregado exitosamente!");

			return dineroBibliotecaParcial; // Devuelve la cantidad de dinero nueva de la biblioteca
		}

	};

	public static String mostrarInfoLibros(ArrayList<String> titulos, ArrayList<String> generos,
			ArrayList<String> autores, ArrayList<String> fechasP, ArrayList<Integer> numEjemplares,
			ArrayList<Boolean> dispnbldds, ArrayList<Double> valorPrest) {

		// String que almacena toda la info
		String info = "---------------- LIBROS DE LA BIBLIOTECA ----------------\n";

		// este for almacena los libros de forma ordenada en el string
		for (int i = 0; i < titulos.size(); i++) {

			info += (i + 1) + ". " + titulos.get(i) + "  (" + generos.get(i) + ")  DE: " + autores.get(i) + "  - "
					+ fechasP.get(i) + "  - Ejemplar #" + numEjemplares.get(i) + "  - Préstamo: $" + valorPrest.get(i)
					+ (dispnbldds.get(i) ? ".  Disponible" : ".  No Disponible") + "\n";

		}

		// muestra toda la info del string
		return info;
	}

	public static double prestarLibro(ArrayList<String> usuario, ArrayList<Double> dineroU, double dineroB,
			ArrayList<Integer> libro1, ArrayList<Integer> libro2, ArrayList<Integer> libro3, ArrayList<String> titulos,
			ArrayList<Double> precio, ArrayList<Boolean> dispon, ArrayList<Integer> ejemplar,
			ArrayList<Integer> prestados) {

		// String que almacena los titulos a prestar dados por el for
		String libros = "¿QUE TITULO SE VA A PRESTAR? \n0. Volver\n\nTitulos\n";

		for (int i = 0; i < titulos.size(); i++) {
			String disponible = dispon.get(i) == true ? "Disponible" : "No Disponible";
			libros += (i + 1) + ". " + titulos.get(i) + "   #" + ejemplar.get(i) + "   " + precio.get(i) + "$   "
					+ disponible + "       \n";
		}

		// String que alamcena los usuarios a prestar dados por el for
		String usuarios = "¿A QUE USUARIO SE VA A PRESTAR? \n0. Volver\n\nUsuarios\n";

		for (int i = 0; i < usuario.size(); i++) {
			usuarios += (i + 1) + ". " + usuario.get(i) + " ---> con " + dineroU.get(i) + "$\n";
		}

		// Este ciclo continua la ejecucion considerando las diferentes entradas del
		// usuario
		int libroPres = -2;

		while (libroPres != 0) {

			// Control de posiblidades
			if (titulos.size() == 0) {
				JOptionPane.showMessageDialog(null, "NO HAY LIBROS EXISTENTES");
				break;
			} else if (usuario.size() == 0) {
				JOptionPane.showMessageDialog(null, "NO HAY USUARIOS EXISTENTES");
				break;
			}

			// Conseguir el indice del libro que se desea prestar
			libroPres = solicitarInt(libros) - 1;

			if (libroPres + 1 == 0) {
				break;
			}

			if (libroPres < 0 || libroPres >= titulos.size()) {
				continue;
			}

			// Si el libro esta disponible
			if (dispon.get(libroPres) == true) {

				int usuarioPres = -2;

				// Este ciclo continua la ejecucion considerando los datos ingresados
				while (usuarioPres != 0) {

					// Conseguir el indice del usuario al que se desea prestar
					usuarioPres = solicitarInt(usuarios) - 1;

					if (usuarioPres + 1 == 0) {
						break;
					}
					if (usuarioPres < 0 || usuarioPres >= usuario.size()) {
						continue;
					}

					// Si el usuario tiene capacidad para mas libros
					if (libro1.get(usuarioPres) == null || libro2.get(usuarioPres) == null
							|| libro3.get(usuarioPres) == null) {

						// Si el usuario tiene el dinero suficiente
						if (precio.get(libroPres) <= dineroU.get(usuarioPres)) {

							// Entonces se le restara el precio del prestamos al dinero del usuario
							dineroU.set(usuarioPres, dineroU.get(usuarioPres) - precio.get(libroPres));

							// El dinero de la biblioteca tendra que aumentar en relacion al precio del
							// libro
							dineroB += precio.get(libroPres);

							// El ejemplar deja de estar disponible
							dispon.set(libroPres, false);

							// Se almacena la ubicacion del libro prestado en el espacio libre del usuario
							if (libro1.get(usuarioPres) == null) {
								libro1.set(usuarioPres, libroPres);
							} else if (libro2.get(usuarioPres) == null) {
								libro2.set(usuarioPres, libroPres);
							} else {
								libro3.set(usuarioPres, libroPres);
							}

							// Se actualiza el contador de libros prestados
							prestados.set(usuarioPres, prestados.get(usuarioPres) + 1);
							JOptionPane.showMessageDialog(null, "¡LIBRO PRESTADO EXITOSAMENTE!");
							break;
						} else {
							JOptionPane.showMessageDialog(null, "Este usuario no tiene dinero suficiente");
						}
					} else {
						JOptionPane.showMessageDialog(null, "El usuario tiene demasiados libros prestados");
					}
				}
				break;
			} else {
				JOptionPane.showMessageDialog(null, "Este libro no esta disponible");
			}
		}
		return dineroB;
	}

	public static void modificarLibro(ArrayList<String> titulo, ArrayList<String> genero, ArrayList<String> autor,
			ArrayList<String> ano, ArrayList<Integer> ejemplar, ArrayList<Boolean> disponible,
			ArrayList<Double> valorPrest) {

		String salida = "Seleccione uno de los usuarios mostrados: \n";
		String[] opcionesGeneros = { "Terror", "Comedia", "Romance", "Autoayuda" }; // Opciones de generos en libros

		// Dialogo que muestra las opciones de generos y obtiene el indice de la
		// posicion del genero escogido

		for (int j = 0; j < titulo.size(); j++) {
			salida += (j + 1) + "." + " Titulo: " + titulo.get(j) + " - " + " Genero: " + " - " + genero.get(j) + " - "
					+ " Autor: " + autor.get(j) + " - " + " Año: " + ano.get(j) + " - " + " Ejemplar: "
					+ ejemplar.get(j) + " - " + " Valor del prestamo: " + valorPrest.get(j) + "\n";
		}

		int selec = solicitarInt(salida);

		// Si no se ingresa un libro dentro del rango de la lista de libros entonces
		// muestra una ventana de alerta y lo devuelve al menu principal
		if (selec >= 1 && selec <= titulo.size()) {
			int op = 0;

			while (op != 6) {
				op = solicitarInt(
						"Que desea editar? \n" + "1. Titulo\n2. Genero\n3.Autor\n4. Año\n5. Ejemplar\n6. Salir");

				if (op == 1) {
					String nuevoTitulo = JOptionPane.showInputDialog("Ingresa el nuevo titulo: ");
					titulo.set(selec - 1, nuevoTitulo);
				} else if (op == 2) {

					int nuevoGenero = JOptionPane.showOptionDialog(null, "Seleccione el genero del libro:",
							"Genero del libro", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
							opcionesGeneros, opcionesGeneros[0]);

					genero.set(selec - 1, opcionesGeneros[nuevoGenero]);

					double nuevoVlrPrestamo = nuevoGenero == 0 ? 0.5
							: nuevoGenero == 1 ? 1 : nuevoGenero == 2 ? 1.5 : nuevoGenero == 3 ? 2 : 0;
					valorPrest.set(selec - 1, nuevoVlrPrestamo);

				} else if (op == 3) {
					String nuevoAutor = JOptionPane.showInputDialog("Ingresa el nuevo autor: ");
					autor.set(selec - 1, nuevoAutor);
				} else if (op == 4) {
					String nuevoAño = JOptionPane.showInputDialog("Ingresa el nuevo año: ");
					ano.set(selec - 1, nuevoAño);
				} else if (op == 5) {
					int nuevoEjemplar = solicitarInt("Ingresa el nuevo ejemplar: ");
					ejemplar.set(selec - 1, nuevoEjemplar);
				} else if (op == 6) {
					break;
				} else
					JOptionPane.showMessageDialog(null, "¡El dato ingresado no es valido!", "",
							JOptionPane.ERROR_MESSAGE);

			}
		} else
			JOptionPane.showMessageDialog(null, "¡El libro " + selec + " ingresado no existe!", "",
					JOptionPane.ERROR_MESSAGE);
	}

	public static void eliminarLibro(ArrayList<String> titulo, ArrayList<String> genero, ArrayList<String> autor,
			ArrayList<String> ano, ArrayList<Integer> ejemplar, ArrayList<Boolean> disponible,
			ArrayList<Double> valorPrest) {

		int posicion = solicitarInt("Escoge el libro que deseas eliminar: \n"
				+ mostrarInfoLibros(titulo, genero, autor, ano, ejemplar, disponible, valorPrest));

		// Verifica si la posición ingresada esta dentro del rango de la lista de libros
		if (posicion >= 1 && posicion <= titulo.size()) {
			posicion -= 1;
			String libroAEliminar = titulo.get(posicion);
			// Verifica que el libro no este en prestamo validando si la disponibilidad es
			// true
			if (disponible.get(posicion) == true) {
				titulo.remove(posicion);
				genero.remove(posicion);
				autor.remove(posicion);
				ano.remove(posicion);
				ejemplar.remove(posicion);
				disponible.remove(posicion);
				valorPrest.remove(posicion);
				JOptionPane.showMessageDialog(null, "¡El Libro '" + libroAEliminar + "' fue eliminado exitosamente!");
			} else
				JOptionPane.showMessageDialog(null,
						"¡El Libro '" + libroAEliminar + "' no se puede eliminar porque está en préstamo!", "",
						JOptionPane.ERROR_MESSAGE);

		} else
			JOptionPane.showMessageDialog(null, "¡La posicion " + posicion + " no existe en la lista de libro!", "",
					JOptionPane.ERROR_MESSAGE);
	};

	public static void agregarUsuario(ArrayList<String> nombre, ArrayList<Integer> edad, ArrayList<String> fechaNaci,
			ArrayList<Double> dineroDisp, ArrayList<Integer> librosPrestados, ArrayList<Integer> libro1,
			ArrayList<Integer> libro2, ArrayList<Integer> libro3) {

		String Nombre = JOptionPane.showInputDialog("Por favor escriba el nombre del usuario");
		nombre.add(Nombre);
		int Edad = solicitarInt("Por favor escriba la edad del usuario");
		edad.add(Edad);
		String Fecha = JOptionPane.showInputDialog("Por favor escriba la fecha de nacimiento del usuario");
		fechaNaci.add(Fecha);
		Double Dinero = Double.parseDouble(JOptionPane
				.showInputDialog("Por favor indique el dinero disponible en USD (0 para usar el defecto 10$)"));

		if (Dinero == 0) {
			dineroDisp.add(10.0);
		} else {
			dineroDisp.add(Dinero);
		}

		librosPrestados.add(0);
		libro1.add(null);
		libro2.add(null);
		libro3.add(null);

	};

	public static String listarUsuarios(ArrayList<String> nombre, ArrayList<Integer> edad, ArrayList<String> fechaNaci,
			ArrayList<Double> dineroDisp, ArrayList<Integer> librosPrestados, ArrayList<Integer> libro1,
			ArrayList<Integer> libro2, ArrayList<Integer> libro3, ArrayList<String> titulos,
			ArrayList<Integer> ejemplar) {
		String salida = "---------------- USUARIOS ----------------\n";
		for (int j = 0; j < nombre.size(); j++) {

			// En variable librosU se va agregando los libros prestados de este usuario
			// viendo con condicionales si existe alguno de los 3 libros
			String librosU = "";
			if (libro1.get(j) != null) {
				librosU += "  -  " + titulos.get(libro1.get(j)) + " #" + ejemplar.get(libro1.get(j));
			}

			if (libro2.get(j) != null) {
				librosU += "  -  " + titulos.get(libro2.get(j)) + " #" + ejemplar.get(libro2.get(j));
			}

			if (libro3.get(j) != null) {
				librosU += "  -  " + titulos.get(libro3.get(j)) + " #" + ejemplar.get(libro3.get(j));
			}

			salida += (j + 1) + "." + " Nombre: " + nombre.get(j) + " Edad: " + edad.get(j) + " Fecha de nacimiento: "
					+ fechaNaci.get(j) + " Dinero: " + dineroDisp.get(j) + "$" + " Libros prestados: "
					+ librosPrestados.get(j) + "\n" + librosU
					+ (librosU.equals("") ? "" : nombre.size() == j + 1 ? "\n" : "\n\n"); // Si no hay libros no deja
																							// salto de linea y si es el
																							// ultimo usuario solo hace
																							// 1 salto y si no deja 2
																							// saltos
		}

		return salida;
	};

	public static void modificarUsuarios(ArrayList<String> nombre, ArrayList<Integer> edad, ArrayList<String> fechaNaci,
			ArrayList<Double> dineroDisp, ArrayList<Integer> librosPrestados) {
		String salida = "Seleccione uno de los usuarios mostrados: \n";
		for (int j = 0; j < nombre.size(); j++) {
			salida += (j + 1) + "." + " Nombre: " + nombre.get(j) + " Edad: " + edad.get(j) + " Fecha de nacimiento: "
					+ fechaNaci.get(j) + " Dinero: " + dineroDisp.get(j) + "$" + " Libros prestados: "
					+ librosPrestados.get(j) + "\n";
		}
		int selec = solicitarInt(salida);

		// Si el numero del usuario seleccionado no está dentro del rango de la lista de
		// usuarios manda pantalla de error y devuelve al menu principal
		if (selec >= 1 && selec <= nombre.size()) {
			int j = 0;
			while (j != 6) {
				j = solicitarInt("Que desea editar? \n"
						+ "1. Nombre\n2. Edad\n3.Fecha de nacimiento\n4. Dinero disponible\n\n5. Salir");

				if (j == 1) {
					String nuevoNom = JOptionPane.showInputDialog("Ingrese el nuevo nombre");
					nombre.set(selec - 1, nuevoNom);

				} else if (j == 2) {
					int nuevaEdad = solicitarInt("Ingrese la nueva edad");
					edad.set(selec - 1, nuevaEdad);

				}

				else if (j == 3) {
					String nuevaFecha = JOptionPane.showInputDialog("Ingrese la nueva fecha de nacimiento");
					fechaNaci.set(selec - 1, nuevaFecha);

				} else if (j == 4) {
					Double nuevoDinero = Double
							.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad de dinero en USD"));
					dineroDisp.set(selec - 1, nuevoDinero);

				} else if (j == 5) {
					break;
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese un numero mostrado en pantalla");
				}
			}
		} else
			JOptionPane.showMessageDialog(null, "¡El usuario " + selec + " ingresado no existe!", "",
					JOptionPane.ERROR_MESSAGE);
	};

	public static void devolverLibro(ArrayList<String> usuario, ArrayList<Integer> libro1, ArrayList<Integer> libro2,
			ArrayList<Integer> libro3, ArrayList<Boolean> dispo, ArrayList<String> titulos, ArrayList<Integer> ejemplar,
			ArrayList<Integer> prestados) {

		// En esta variable se muestran los usuarios que el for llenara
		String usuarios = "¿A QUE USUARIO SE VA A RECLAMAR EL LIBRO? \n0. Volver\n\nUsuarios\n";

		for (int i = 0; i < usuario.size(); i++) {
			usuarios += (i + 1) + ". " + usuario.get(i) + "\n";
		}

		// Este ciclo se encarga de seguir funcionando cuando el usuario no ha ingresado
		// datos validos
		int usuarioPres = -2;

		while (usuarioPres != 0) {

			// Si no hay usuarios, no se puede reclamar libros
			if (usuario.size() == 0) {
				JOptionPane.showMessageDialog(null, "NO HAY USUARIOS EXISTENTES");
				break;
			}

			// Se selecciona el usuario al que se le reclamara el libro
			usuarioPres = solicitarInt(usuarios) - 1;

			if (usuarioPres + 1 == 0) {
				break;
			}
			if (usuarioPres < 0 || usuarioPres >= usuario.size()) {
				continue;
			}

			// String que alamcena los datos dados
			String librosU = "LOS LIBROS PRESTADOS A (" + usuario.get(usuarioPres) + ") SON: \n 0. Volver\n\nLibros\n";
			int control = 0;

			// Estos if muestran los libros que tiene el usuario, maximo 3
			if (libro1.get(usuarioPres) != null) {
				librosU += "1. " + titulos.get(libro1.get(usuarioPres)) + "\n";
				control = 1;
			}

			if (libro2.get(usuarioPres) != null) {
				librosU += "2. " + titulos.get(libro2.get(usuarioPres)) + "\n";
				control = 2;
			}

			if (libro3.get(usuarioPres) != null) {
				librosU += "3. " + titulos.get(libro3.get(usuarioPres)) + "\n";
				control = 3;
			}
			if (control == 0) {
				JOptionPane.showMessageDialog(null, "NO HAY LIBROS PRESTADOS A ESTE USUARIO");
				break;
			}

			// Este ciclo se encarga de continuar la ejecucion, con el ingreso de datos
			// invalidos
			int libroS = -2;

			while (libroS != 0) {

				// Se le pregunta al usuario el libro por reclamar
				libroS = solicitarInt(librosU + "\nEscoja el libro que desea reclamar al usuario: \n");

				if (libroS == 0) {
					break;
				} else if (libroS < 0 || libroS > control) {
					continue;
				}

				// Estos if actualizan la disponiblidad del libro a true y eliminan el vinculo
				// del usuario con el libro
				if (libroS == 1) {
					dispo.set(libro1.get(usuarioPres), true);
					libro1.set(usuarioPres, null);
				} else if (libroS == 2) {
					dispo.set(libro2.get(usuarioPres), true);
					libro2.set(usuarioPres, null);
				} else if (libroS == 3) {
					dispo.set(libro3.get(usuarioPres), true);
					libro3.set(usuarioPres, null);
				}

				// Se actualiza el contador de libros prestados
				prestados.set(usuarioPres, prestados.get(usuarioPres) - 1);

				JOptionPane.showMessageDialog(null, "¡LIBRO RECUPERADO EXITOSAMENTE!");
				break;
			}
			break;
		}
	}

	public static void eliminarUsuarios(ArrayList<String> nombre, ArrayList<Integer> edad, ArrayList<String> fechaNaci,
			ArrayList<Double> dineroDisp, ArrayList<Integer> librosPrestados, ArrayList<Integer> libro1,
			ArrayList<Integer> libro2, ArrayList<Integer> libro3) {
		String salida = "Escoge el usuario que deseas eliminar: \n";
		// Se solicita el usuario que se va a eliminar
		for (int j = 0; j < nombre.size(); j++) {
			salida += (j + 1) + "." + " Nombre: " + nombre.get(j) + " Edad: " + edad.get(j) + " Fecha de nacimiento: "
					+ fechaNaci.get(j) + " Dinero: " + dineroDisp.get(j) + "$" + " Libros prestados: "
					+ librosPrestados.get(j) + "\n";
		}
		;

		// Se almacena el indice del usuario elegido
		int posicion = solicitarInt(salida);

		if (posicion >= 1 && posicion <= nombre.size()) {
			posicion -= 1;

			if (librosPrestados.get(posicion) != 0) {
				JOptionPane.showMessageDialog(null, "¡El usuario no puede ser eliminado ya que posee libros!", "",
						JOptionPane.ERROR_MESSAGE);
			} else {
				nombre.remove(posicion);
				edad.remove(posicion);
				fechaNaci.remove(posicion);
				dineroDisp.remove(posicion);
				librosPrestados.remove(posicion);
				libro1.remove(posicion);
				libro2.remove(posicion);
				libro3.remove(posicion);
				JOptionPane.showMessageDialog(null, "¡El usuario fue eliminado exitosamente!");
			}

		} else
			JOptionPane.showMessageDialog(null, "¡La posicion " + posicion + " no existe en la lista de usuarios!", "",
					JOptionPane.ERROR_MESSAGE);
	}

	// -------------------------- METODOS DE INICIALIZACION DE DATA POR DEFECTO
	// ------------------------------------
	public static void agregarLibrosPredeterminados(ArrayList<String> titulo, ArrayList<String> genero,
			ArrayList<String> autor, ArrayList<String> ano, ArrayList<Integer> ejemplar, ArrayList<Boolean> disponible,
			ArrayList<Double> valorPrest) {

		String[] titulos = { "It", "La vida de Brian", "Orgullo y prejuicio", "El poder del ahora", "El exorcista" };
		String[] generos = { "Terror", "Comedia", "Romance", "Autoayuda", "Terror" };
		String[] autores = { "Stephen King", "Varios", "Jane Austen", "Eckhart Tolle", "William Peter Blatty" };
		String[] anos = { "1986/09", "1979/08", "1813/07", "1997/09", "1971/05" };
		int[] ejemplares = { 1, 1, 1, 1, 1 };
		// boolean[] disponibles = {true, true, true, true, true};
		double[] valoresPrestamo = { 0.5, 1, 1.5, 2, 0.5 };

		// Agregar los libros a los ArrayLists
		for (int i = 0; i < titulos.length; i++) {
			titulo.add(titulos[i]);
			genero.add(generos[i]);
			autor.add(autores[i]);
			ano.add(anos[i]);
			ejemplar.add(ejemplares[i]);
			disponible.add(true);
			valorPrest.add(valoresPrestamo[i]);
		}
	}

	public static void agregarUsuariosPredeterminados(ArrayList<String> nombre, ArrayList<Integer> edad,
			ArrayList<String> fechaNaci, ArrayList<Double> dineroDisp, ArrayList<Integer> librosPrestados,
			ArrayList<Integer> libro1, ArrayList<Integer> libro2, ArrayList<Integer> libro3,
			ArrayList<Integer> prestados, ArrayList<Boolean> dispon) {
		nombre.add("Pocholo");
		nombre.add("Millonarios");
		edad.add(17);
		edad.add(18);
		fechaNaci.add("25-01-2007");
		fechaNaci.add("01-04-2006");
		dineroDisp.add(10.0);
		dineroDisp.add(10.0);
		librosPrestados.add(0);
		librosPrestados.add(0);

		for (int i = 0; i < nombre.size(); i++) {

			libro1.add(i, null);
			libro2.add(i, null);
			libro3.add(i, null);

			prestados.add(i, 0);
		}

		// Agrega 2 prestamos por defecto al primer usuario
		dispon.set(2, false);
		dispon.set(3, false);
		libro1.set(0, 2);
		libro2.set(0, 3);
		prestados.set(0, 2);
	}

	// Método que facilita la obtencion de datos tipo int
	public static int solicitarInt(String mensaje) {
		return Integer.parseInt(JOptionPane.showInputDialog(mensaje));
	}

	// Método para solicitar el dinero inicial de la biblioteca y retorna el dinero
	public static double solicitarDineroInicialBiblioteca() {
		double dinero = 0; // valor random para que entre al bucle
		do {
			dinero = Double.parseDouble(JOptionPane.showInputDialog(
					"Ingresa el dinero inicial que tendra la biblioteca a su disposición ( El dinero debe ser mayor a 3 y menor que 20 con fines dinamicos)"));
		} while (!(dinero >= 3 && dinero <= 20));

		return dinero;
	}
}

