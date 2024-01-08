package utils;

public class Printer {
    public static void bienvenido() {
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        System.out.println("----\uD83E\uDD64️BIENVENIDO A STUBURGER\uD83C\uDF7D️----");
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
    }

    public static void mostrarMenu() {
        System.out.println();
        System.out.println("Elije la opción que desees: ");
        System.out.println();
        System.out.println(" -------\uD83D\uDCCB️MENÚ PRINCIPAL\uD83D\uDCCB️---------");
        System.out.println("| 1) Dar de alta un plato.        |");
        System.out.println("| 2) Dar de alta una valoracion.  |");
        System.out.println("| 3) Consultar listado de platos. |");
        System.out.println("| 4) Dar de baja un plato.        |");
        System.out.println("| 5) Modificar un plato.          |");
        System.out.println("| 6) Mostrar todos los datos.     |");
        System.out.println("| 7) Mostrar platos según el tipo.|");
        System.out.println("| 0) Salir                        |");
        System.out.println(" ---------------------------------");
    }

    public static void menuTipo() {
        System.out.println();
        System.out.println("TIPO PLATO");
        System.out.println("1) Entrante.");
        System.out.println("2) Principal");
        System.out.println("3) Postre.");
        System.out.println();
    }

    public static void salida() {
        System.out.println("----------------------------------");
        System.out.println("------\uD83E\uDD64\uD83C\uDF7D️HASTA PRONTO!\uD83C\uDF7D️\uD83E\uDD64------");
        System.out.println("----------------------------------");
    }


 }
