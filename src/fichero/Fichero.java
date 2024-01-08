package fichero;

import  modelos.Plato;
import modelos.Valoracion;

import java.io.*;
import java.util.ArrayList;

public class Fichero {

    private static FileWriter Fw = null;
    private static PrintWriter Pw = null;
    private static File file;
    private static String rutaAbsoluta;
    private static final String datos = "datos";
    private static final String valoraciones = "valoraciones.txt";
    private static final String platos = "platos.txt";
    private static final String slashRoute = "//";


    public static void crearCarpeta() throws IOException {
        rutaAbsoluta = new File(datos).getAbsolutePath();
        file = new File(rutaAbsoluta);
        boolean value = file.mkdir();

        if(value) System.out.println("\uD83D\uDCC2 El nuevo directorio se ha creado correctamente.");

        crearArchivos(platos);
        crearArchivos(valoraciones);
    }

    private static void crearArchivos(String file) throws IOException {
        Fichero.file = new File(rutaAbsoluta + slashRoute + file);
        boolean value = Fichero.file.createNewFile();

        if (value) System.out.println("\uD83D\uDCDD El archivo "+file+" se ha creado correctamente.");
    }

    public static void escribirPlatos(ArrayList<Plato> plato) throws IOException {
        Fw = new FileWriter(rutaAbsoluta + slashRoute + platos, true);
        Pw = new PrintWriter(Fw);
        file = new File(rutaAbsoluta + slashRoute + platos);

        vaciarContenido(file);
        String finDeLinea = System.lineSeparator();

        for (Plato Plato: plato ) {
            Pw.write("Plato: " + Plato.toString() + finDeLinea);
        }

        Pw.close();
        Fw.close();
        System.out.println("\uD83D\uDD04 Proceso de platos completado \uD83D\uDD04");
    }

    public static void escribirValoraciones(ArrayList<Valoracion> valoracions) throws IOException {
        Fw = new FileWriter(rutaAbsoluta + slashRoute + valoraciones, true);
        Pw = new PrintWriter(Fw);
        file = new File(rutaAbsoluta + slashRoute + valoraciones);

        vaciarContenido(file);
        String finDeLinea = System.lineSeparator();

        for (Valoracion valoracion: valoracions ) {
            Pw.write("Valoracion: " +valoracion.toString() + finDeLinea);
        }

        Pw.close();
        Fw.close();
        System.out.println("\uD83D\uDD04 Proceso de valoraciones completado \uD83D\uDD04");
    }

    private static void vaciarContenido(File sFichero) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
        bw.write("");
        bw.close();
    }
}