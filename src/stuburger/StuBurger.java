package stuburger;

import fichero.Fichero;
import modelos.Plato;
import modelos.Valoracion;
import utils.Printer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StuBurger {
	private static Scanner sc;
	public static ArrayList<Plato> platos;
	public static ArrayList<Valoracion> valoraciones;

    public static void main(String[] args) throws IOException {
    	Printer.bienvenido();
		sc = new Scanner(System.in);
		platos = new ArrayList<>();
		valoraciones = new ArrayList<>();
		Fichero.crearCarpeta();
    	run();
    }
    
    private static void run() {
    	boolean salida = false;

		while(!salida) {
			try {
				Printer.mostrarMenu();

				int option = sc.nextInt();

				switch (option) {
					case 1:
						darAltaPlato();
						break;
					case 2:
						darAltaValoracion();
						break;
					case 3:
						consultarListadoPlatos();
						break;
					case 4:
						bajaPlato();
						break;
					case 5:
						modificarPlato();
						break;
					case 6:
						mostrarTodosPlatos();
						break;
					case 7:
						mostrarPlatosSegunTipo();
						break;
					case 0:
						Printer.salida();
						salida = true;
						break;
					default:
						System.err.println("Error. Elije una opción correcta");
				}
			} catch (InputMismatchException | IOException e) {
				sc.nextLine();
				System.err.println("Error. Has introducido un caracter incorrecto.");
			}
		}
    }

	private static void darAltaPlato()  {
		try {
			boolean nameExists = false;
			System.out.println("\uD83C\uDF74 Creando un nuevo plato \uD83C\uDF74 ...");
			System.out.println("Escribe el nombre del plato:");
			String nombre = sc.next();

			if (platos.isEmpty()) { //Si la array esta vacía directamente añadimos un plato
				pedirDatosPlato(nombre);
			} else { //En caso de no estarlo comprobamos si existe un nombre igual al que acabamos de introducir

				for (Plato plato : platos) {
					if (plato.getNombre().equalsIgnoreCase(nombre)) {
						System.err.println("Error! El nombre ya existe. Vuelve a intentarlo.");
						nameExists = true;
					}
				}
				if(!nameExists) pedirDatosPlato(nombre); //Si no existe un nombre igual podemos pedir los datos.
			}
		} catch (InputMismatchException | IOException e) {
			sc.nextLine();
			System.err.println("Error. Has introducido un carácter incorrecto. Vuelve a intentarlo.");
		}
	}

    private static void darAltaValoracion() {
		if (!platos.isEmpty()) {
			boolean salida = false;

			while(!salida) {
				Plato platoElegido = eligirPlato(platos);

				System.out.println("Escribe el comentario sobre el plato:");
				String comentario = sc.next();

				try {
					System.out.println("Introduce una valoración del 0 al 10:");
					int valoracion = sc.nextInt();

					if (valoracion < 0 || valoracion > 10) {
						System.err.println("Error. Valoración incorrecta. Debes introducir un numero del 0 al 10.");
					} else {
						valoraciones.add(new Valoracion(platoElegido, valoracion, comentario));
						Fichero.escribirValoraciones(valoraciones);
						salida = true;
					}
				} catch (InputMismatchException | IOException e) {
					sc.nextLine();
					System.err.println("Error. Has introducido un caracter incorrecto.");
				}
			}
		} else {
			System.err.println("No hay platos para realizar una valoracón. Crea un plato primero.");
		}
    }

	private static void consultarListadoPlatos() {
		if (!platos.isEmpty()) listadoPlatos(platos, valoraciones);
		else System.err.println("No hay platos todavía. Crea un plato.");
	}

    private static void bajaPlato() throws IOException {
    	if (!platos.isEmpty()) {
			Plato platoElegido = eligirPlato(platos);

			if (!valoraciones.isEmpty()) {
				valoraciones.get(0).borrarValoraciones(platoElegido, valoraciones);
				Fichero.escribirValoraciones(valoraciones);
			}

			platoElegido.borrarPlato(platos, platoElegido);
			Fichero.escribirPlatos(platos);
		} else {
			System.err.println("No hay platos todavía. Crea un plato.");
		}
	}

	private static void modificarPlato() throws IOException {
		if (!platos.isEmpty()) {
			Plato platoElegido = eligirPlato(platos);
			platoElegido.actualizarPrecioPlato(platoElegido);
			Fichero.escribirPlatos(platos);
		} else {
			System.err.println("No hay platos todavía. Crea un plato.");
		}
	}

	private static void mostrarTodosPlatos() {
		if (!platos.isEmpty()) mostrarPlatosYValoraciones(platos, valoraciones);
		else System.err.println("No hay platos todavía. Crea un plato.");
	}

	private static void mostrarPlatosSegunTipo() {
		if (!platos.isEmpty()) platos.get(0).mostrarTipoDePlatos(platos);
		else System.err.println("No hay platos todavía. Crea un plato.");
	}

	private static void pedirDatosPlato(String nombre) throws IOException {
		int tipo = Plato.tipoPlato();
		System.out.println("Escribe el precio:");
		platos.add(new Plato(nombre, tipo, sc.nextDouble()));
		Fichero.escribirPlatos(platos);
	}

	private static Plato eligirPlato(ArrayList<Plato> platos) {
		boolean salida = false;
		int indicePlato = -1;

		while(!salida) {
			try {
				int indice = 0;
				for (Plato plato : platos) {
					System.out.println(indice++ + ") " + plato.toString());
				}

				System.out.println("Elije un plato:");
				indicePlato = sc.nextInt();

				if(indicePlato < 0 || indicePlato > platos.size() - 1) {
					System.err.println("Error. El plato que has escogido no existe.");
				} else {
					salida = true;
				}
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.err.println("Error. Has introducido un caracter incorrecto.");
			}
		}
		return platos.get(indicePlato);
	}

	private static void listadoPlatos (ArrayList<Plato> platos, ArrayList<Valoracion> valoraciones) {
		for (int i = 0; i <= platos.size() - 1; i++) {
			double valoracionMedia = 0;
			int valoracionesTotalesPlato = 0;

			System.out.println(platos.get(i).toString());

			for (int j = 0; j <= valoraciones.size() - 1; j++) {
				if (valoraciones.get(j).getPlato() == platos.get(i)) {
					valoracionesTotalesPlato++;
					valoracionMedia = valoracionMedia + valoraciones.get(j).getNota();
				}
			}
			if (valoracionesTotalesPlato != 0) {
				System.out.println("Valoración media del plato: " + String.format("%.2f", valoracionMedia/valoracionesTotalesPlato));
			} else {
				System.out.println("No hay valoraciones de este plato.");
			}
		}
	}

	private static void mostrarPlatosYValoraciones (ArrayList<Plato> platos, ArrayList<Valoracion> valoraciones) {
		boolean valoracionVacia = true;

		for (int i = 0; i <= platos.size() - 1; i++) {
			System.out.println(platos.get(i).toString());
			System.out.println("Valoraciones: ");

			for (int j = 0; j <= valoraciones.size() - 1; j++) {
				if (valoraciones.get(j).getPlato() == platos.get(i)) {
					System.out.println(valoraciones.get(j).toString());
					valoracionVacia = false;
				}
			}
			if (valoracionVacia) System.out.println("En este momento no hay valoraciones.");
		}
	}
}







