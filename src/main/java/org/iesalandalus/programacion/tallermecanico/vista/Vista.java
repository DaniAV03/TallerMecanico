package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Objects;

public class Vista {
    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "Controlador nulo , por lo cual esta mal , no puede ser nulo.");
        this.controlador = controlador;
    }

    public void comenzar() throws OperationNotSupportedException {
        Opcion opcion = null;
        while (opcion != Opcion.SALIR) {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        }
    }

    public void terminar() {
        System.out.println("Fin");
    }

    private void ejecutar(Opcion opcion) throws OperationNotSupportedException {
        switch (opcion) {
            case INSERTAR_CLIENTE -> insertarCliente();
            case BUSCAR_CLIENTE -> buscarCliente();
            case BORRAR_CLIENTE -> borrarCliente();
            case LISTAR_CLIENTES -> listarClientes();
            case MODIFICAR_CLIENTE -> modificarCliente();
            case INSERTAR_VEHICULO -> insertarVehiculo();
            case BUSCAR_VEHICULO -> buscarVehiculo();
            case BORRAR_VEHICULO -> borrarVehiculo();
            case LISTAR_VEHICULOS -> listarVehiculos();
            case INSERTAR_REVISION -> insertarRevision();
            case BUSCAR_REVISION -> buscarRevision();
            case BORRAR_REVISION -> borrarRevision();
            case LISTAR_REVISIONES -> listarRevisiones();
            case LISTAR_REVISIONES_CLIENTES -> listarRevisionesCliente();
            case LISTAR_REVISIONES_VEHICULOS -> listarRevisionesVehiculo();
            case ANADIR_HORAS_REVISION -> anadirHorasRevision();
            case ANADIR_PRECIO_MATERIAL_REVISON -> anadirPrecioMaterial();
            case CERRAR_REVISION -> cerrarRevision();
            case SALIR -> salir();
        }
    }

    private void insertarCliente() {
        controlador.insertar(Consola.leerCliente());
    }

    private void insertarVehiculo() {
        controlador.insertar(Consola.leerVehiculo());
    }

    private void insertarRevision() {
        controlador.insertar(Consola.leerRevision());
    }

    private void buscarCliente() {
        controlador.buscar(Consola.leerCliente());
    }

    private void buscarVehiculo() {
        controlador.buscar(Consola.leerVehiculo());
    }

    private void buscarRevision() {
        controlador.buscar(Consola.leerRevision());
    }

    private void modificarCliente() {
        try {
            Cliente dni = Consola.leerClienteDni();
            String nuevoNombre = Consola.leerNuevoNombre();
            String nuevoTelefono = Consola.leerNuevoTelefono();
            controlador.modificar(dni, nuevoNombre, nuevoTelefono);
        } catch (OperationNotSupportedException ex) {
            System.out.println("Error al intentar modificar el cliente: " + ex.getMessage());
        }
    }

    private void anadirHorasRevision() {
        controlador.anadirHoras(Consola.leerRevision(), Consola.leerHoras());
    }

    private void anadirPrecioMaterial() {
        try {
            Revision revision = Consola.leerRevision();
            float precioMaterial = Consola.leerPrecioMaterial();
            controlador.anadirPrecioMaterial(revision, precioMaterial);
        } catch (OperationNotSupportedException ex) {
            System.out.println("Error al intentar añadir el precio del material a la revisión: " + ex.getMessage());
        }
    }

    private void cerrarRevision() {
        Revision revision = Consola.leerRevision();
        LocalDate fechaFin = Consola.leerFechaFin();
        controlador.cerrar(revision, fechaFin);
    }

    private void borrarCliente() throws OperationNotSupportedException {
        controlador.borrar(Consola.leerCliente());
    }

    private void borrarVehiculo() throws OperationNotSupportedException {
        controlador.borrar(Consola.leerVehiculo());
    }

    private void borrarRevision() throws OperationNotSupportedException {
        controlador.borrar(Consola.leerRevision());
    }

    private void listarClientes() {
        controlador.getClientes();
    }

    private void listarVehiculos() {
        controlador.getVehiculos();
    }

    private void listarRevisiones() {
        controlador.getRevisiones();
    }

    public void listarRevisionesCliente() {
        controlador.getRevisiones(Consola.leerCliente());
    }

    public void listarRevisionesVehiculo() {
        controlador.getRevisiones(Consola.leerVehiculo());
    }

    private void salir() {
        controlador.terminar();
    }
}


