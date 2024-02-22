package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;


public class Consola {
    private static final String EXPRESION_REGULAR_FECHA = "^\\d{2}\\d{2}\\d{4}$";

    public Consola() {
    }

    public static void mostrarCabecera(String mensaje) {
        System.out.println(mensaje);
        int longitudMensaje = mensaje.length();
        StringBuilder subrayadoBuilder = new StringBuilder();
        for (int i = 0; i < longitudMensaje; i++) {
            subrayadoBuilder.append("-");
        }
        String subrayado = subrayadoBuilder.toString();
        System.out.println(subrayado);
    }

    public static void mostrarMenu() {
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion() {
        int opcion = leerEntero("Elige una opción.");
        if (!Opcion.esValida(opcion)) {
            throw new IllegalArgumentException("La opción no es válida.");
        }
        return Opcion.get(opcion);
    }

    public static float leerReal(String mensaje) {
        System.out.println(mensaje);
        return Entrada.real();
    }

    public static int leerEntero(String mensaje) {
        System.out.println(mensaje);
        return Entrada.entero();
    }

    public static String leerCadena(String mensaje) {
        System.out.println(mensaje);
        return Entrada.cadena();
    }

    public static LocalDate leerFecha(String mensaje) {
        System.out.println(mensaje);
        String fecha = Entrada.cadena();
        if (!fecha.matches(EXPRESION_REGULAR_FECHA)) {
            throw new IllegalArgumentException("La fecha no tiene el formato esperado.");
        }
        int dia = Integer.parseInt(fecha.substring(0, 2));
        int mes = Integer.parseInt(fecha.substring(2, 4));
        int año = Integer.parseInt(fecha.substring(4, 8));
        return LocalDate.of(año, mes, dia);
    }

    public static Cliente leerCliente() {
        return new Cliente(leerNuevoNombre(), leerCadena("Dime el DNI."), leerNuevoTelefono());
    }

    public static Cliente leerClienteDni() {
        return Cliente.get(leerCadena("Dime el DNI."));
    }

    public static String leerNuevoNombre() {
        return leerCadena("Dime el nombre.");
    }

    public static String leerNuevoTelefono() {
        return leerCadena("Dime el teléfono.");
    }

    public static Vehiculo leerVehiculo() {
        System.out.println("Dime la marca.");
        String marca = Entrada.cadena();
        System.out.println("Dime la matrícula.");
        String matricula = Entrada.cadena();
        System.out.println("Dime el modelo.");
        String modelo = Entrada.cadena();
        return new Vehiculo(marca, modelo, matricula);
    }

    public static Vehiculo leerMatriculaVehiculo() {
        System.out.println("Dime la matrícula.");
        String matricula = Entrada.cadena();
        return Vehiculo.get(matricula);
    }

    public static Revision leerRevision() {
        return new Revision(leerClienteDni(), leerMatriculaVehiculo(), leerFecha("Introduce la fecha de inicio"));
    }

    public static int leerHoras() {
        return leerEntero("Dime las horas.");
    }

    public static float leerPrecioMaterial() {
        return leerReal("Dime lo que cuesta el material.");
    }

    public static LocalDate leerFechaFin() {
        return leerFecha("Dime cuando termina.");
    }
}