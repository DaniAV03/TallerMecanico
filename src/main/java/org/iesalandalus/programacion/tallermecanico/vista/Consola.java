package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Consola {
    private static final String EXPRESION_REGULAR_FECHA = "dd/MM/yyyy";

    public Consola() {
    }

    public static void mostrarCabecera(String mensaje) {
        System.out.printf("%n%s%n", mensaje);
        String formatoStr = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(formatoStr, 0).replace("0", "-"));
    }

    public static void mostrarMenu() {
        mostrarCabecera("Taller mecánico.");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion() {
        Opcion opcion = null;
        do {
            try {
                opcion = Opcion.get(leerEntero("\nElige un opción: "));
            } catch (IllegalArgumentException e) {
                System.out.printf("ERROR: %s%n", e.getMessage());
            }
        } while (opcion == null);
        return opcion;
    }

    public static float leerReal(String mensaje) {
        System.out.print(mensaje);
        return Entrada.real();
    }

    public static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Entrada.entero();
    }

    public static String leerCadena(String mensaje) {
        System.out.println(mensaje);
        return Entrada.cadena();
    }

    private static LocalDate leerFecha(String mensaje) {
        LocalDate fecha;
        DateTimeFormatter fechaFormato = DateTimeFormatter.ofPattern(EXPRESION_REGULAR_FECHA);
        mensaje = String.format("%s (%s): ", mensaje, EXPRESION_REGULAR_FECHA);
        try {
            fecha = LocalDate.parse(leerCadena(mensaje), fechaFormato);
        } catch (DateTimeException e) {
            fecha = null;
        }
        return fecha;
    }


    public static Cliente leerCliente() {

        String nombre = leerCadena("Introduce el nombre: ");
        String dni = leerCadena("Introduce el DNI: ");
        String telefono = leerCadena("Introduce el teléfono: ");
        return new Cliente(nombre, dni, telefono);
    }

    public static Cliente leerClienteDni() {
        return Cliente.get(leerCadena("Introduce el DNI: "));

    }

    public static String leerNuevoNombre() {
        return leerCadena("Introduce el nuevo nombre: ");
    }

    public static String leerNuevoTelefono() {
        return leerCadena("Introduce el nuevo teléfono: ");
    }

    public static Vehiculo leerVehiculo() {
        System.out.println("Dime la marca.");
        String marca = Entrada.cadena();
        System.out.println("Dime el modelo.");
        String modelo = Entrada.cadena();
        System.out.println("Dime la matrícula.");
        String matricula = Entrada.cadena();
        return new Vehiculo(marca, modelo, matricula);
    }

    public static Vehiculo leerMatriculaVehiculo() {
        return Vehiculo.get(leerCadena("Introduce la matrícula: "));
    }

    public static Revision leerRevision() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerMatriculaVehiculo();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio");
        return new Revision(cliente, vehiculo, fechaInicio);
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