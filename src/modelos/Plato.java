package modelos;
import java.util.ArrayList;
import java.util.Scanner;
import utils.Printer;

public class Plato {
	private final String nombre;
	private final int tipo;
    private double precio;

    public Plato(String nombre, int tipo, double precio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
    }

	public String getNombre() {
		return nombre;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public static int tipoPlato() {
		Scanner sc = new Scanner(System.in);

    	boolean salida = false;
		int tipo = 0;

		while(!salida) {
    		try {
				Printer.menuTipo();

				System.out.println("Elije el tipo de plato:");
				tipo = sc.nextInt();

				if (tipo < 1 || tipo > 3) {
					System.err.println("Error. Elije una opcion del 1 al 3.");
				} else {
					salida = true;
				}
			} catch (java.util.InputMismatchException e) {
				sc.nextLine();
				System.err.println("Error. Has introducido un caracter incorrecto.");
			}
		}
		return tipo;
    }

	public String printTipo(int tipo) {
    	String plato;

		if (tipo == 1) {
			 plato = "Entrante";
		} else if (tipo == 2) {
			plato = "Principal";
		} else {
			plato = "Postre";
		}
		return plato;
	}

	public void actualizarPrecioPlato(Plato plato) {
		Scanner sc = new Scanner(System.in);
		boolean salida = false;

		while (!salida) {
			try {
				System.out.println("Introduce el nuevo precio:");
				plato.setPrecio(sc.nextDouble());
				salida = true;

			} catch (java.util.InputMismatchException e) {
				sc.nextLine();
				System.err.println("Error. Introduce un valor correcto.");
			}
		}
	}

	public void borrarPlato(ArrayList<Plato> platos, Plato platoElegido) {
    	platos.remove(platoElegido);
	}

	public void mostrarTipoDePlatos(ArrayList<Plato> platos) {
    	int tipo = tipoPlato();
    	boolean platoExistente = false;

		for (Plato plato : platos) {
			System.out.println("---------"+printTipo(tipo)+"---------");
			if(plato.tipo == tipo) {
				System.out.println("Nombre: " + plato.nombre + " Precio: " + plato.precio);
				platoExistente = true;
			}
		}
		if (!platoExistente) System.out.println("No hay platos de tipo "+ printTipo(tipo));
	}

	@Override
	public String toString() {
		return
				"nombre=" + nombre +
				"; tipo=" + printTipo(tipo) +
				"; precio=" + precio;
	}
}
