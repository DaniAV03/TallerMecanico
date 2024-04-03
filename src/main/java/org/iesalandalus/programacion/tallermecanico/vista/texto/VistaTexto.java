package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import java.time.LocalDate;
import java.util.List;

public class VistaTexto implements Vista {

    private final GestorEventos gestorEventos = new GestorEventos(Evento.values());


    public GestorEventos getGestorEventos() {
        return gestorEventos;
    }


    public void comenzar() {
        Evento opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Evento.SALIR);
    }

    private void ejecutar(Evento opcion) {
        Consola.mostrarCabecera(opcion.toString());
        gestorEventos.notificar(opcion);
    }


    public void terminar() {
        System.out.println("¡¡¡Hasta luego Lucasss!!!");
    }


    public Cliente leerCliente() {
        String nombre = Consola.leerCadena("Introduce el nombre: ");
        String dni = Consola.leerCadena("Introduce el DNI: ");
        String telefono = Consola.leerCadena("Introduce el teléfono: ");
        return new Cliente(nombre, dni, telefono);
    }


    public Cliente leerClienteDni() {
        return Cliente.get(Consola.leerCadena("Introduce el DNI: "));
    }


    public String leerNuevoNombre() {
        return Consola.leerCadena("Introduce el nuevo nombre: ");
    }


    public String leerNuevoTelefono() {
        return Consola.leerCadena("Introduce el nuevo teléfono: ");
    }


    public Vehiculo leerVehiculo() {
        String marca = Consola.leerCadena("Introduce la marca: ");
        String modelo = Consola.leerCadena("Introduce el modelo: ");
        String matricula = Consola.leerCadena("Introduce la matrícula: ");
        return new Vehiculo(marca, modelo, matricula);
    }


    public Vehiculo leerVehiculoMatricula() {
        return Vehiculo.get(Consola.leerCadena("Introduce la matrícula: "));
    }

    public Trabajo leerRevision() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = Consola.leerFecha("Introduce la fecha de inicio");
        return new Revision(cliente, vehiculo, fechaInicio);
    }


    public Trabajo leerMecanico() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = Consola.leerFecha("Introduce la fecha de inicio");
        return new Mecanico(cliente, vehiculo, fechaInicio);
    }

    public Trabajo leerTrabajoVehiculo() {
        return Trabajo.get(leerVehiculoMatricula());
    }


    public int leerHoras() {
        return Consola.leerEntero("Introduce las horas a añadir: ");
    }


    public float leerPrecioMaterial() {
        return Consola.leerReal("Introduce el precio del material a añadir: ");
    }


    public LocalDate leerFechaCierre() {
        return Consola.leerFecha("Introduce la fecha de cierre");
    }


    public void notificarResultado(Evento evento, String texto, boolean exito) {
        if (exito) {
            System.out.println(texto);
        } else {
            System.out.printf("ERROR: %s%n", texto);
        }
    }


    public void mostrarCliente(Cliente cliente) {
        System.out.println((cliente != null) ? cliente : "No existe ningún cliente con dicho DNI.");
    }


    public void mostrarVehiculo(Vehiculo vehiculo) {
        System.out.println((vehiculo != null) ? vehiculo : "No existe ningún vehículo con dicha matrícula.");
    }


    public void mostrarTrabajo(Trabajo trabajo) {
        System.out.println((trabajo != null) ? trabajo : "No existe ningún trabajo para ese cliente, vehículo y fecha.");
    }

    public void mostrarClientes(List<Cliente> clientes) {
        if (!clientes.isEmpty()) {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        } else {
            System.out.println("No hay clientes que mostrar.");
        }
    }

    public void mostrarVehiculos(List<Vehiculo> vehiculos) {
        if (!vehiculos.isEmpty()) {
            for (Vehiculo vehiculo : vehiculos) {
                System.out.println(vehiculo);
            }
        } else {
            System.out.println("No hay vehículos que mostrar.");
        }
    }


    public void mostrarTrabajos(List<Trabajo> trabajos) {
        if (!trabajos.isEmpty()) {
            for (Trabajo trabajo : trabajos) {
                System.out.println(trabajo);
            }
        } else {
            System.out.println("No hay trabajos que mostrar.");
        }
    }


    public void mostrarTrabajosCliente(List<Trabajo> trabajosCliente) {
        if (!trabajosCliente.isEmpty()) {
            for (Trabajo trabajo : trabajosCliente) {
                System.out.println(trabajo);
            }
        } else {
            System.out.println("No hay trabajos que mostrar para dicho cliente.");
        }
    }


    public void mostrarTrabajosVehiculo(List<Trabajo> trabajosVehiculo) {
        if (!trabajosVehiculo.isEmpty()) {
            for (Trabajo trabajo : trabajosVehiculo) {
                System.out.println(trabajo);
            }
        } else {
            System.out.println("No hay trabajos que mostrar para dicho vehículo.");
        }
    }

}
