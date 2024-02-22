package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;

import java.util.List;
import java.util.Objects;

public class Vista {
    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "Controlador nulo , por lo cual esta mal , no puede ser nulo.");
        this.controlador = controlador;
    }

    public void comenzar() throws OperationNotSupportedException {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
        controlador.terminar();
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
            case ANADIR_HORAS_REVISION -> anadirHoras();
            case ANADIR_PRECIO_MATERIAL_REVISON -> anadirPrecioMaterial();
            case CERRAR_REVISION -> cerrarRevision();
            case SALIR -> salir();
        }
    }


    private void insertarCliente() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Insertar Cliente");
        controlador.insertar(Consola.leerCliente());
        System.out.println("Cliente insertado correctamente.");
    }

    private void insertarVehiculo() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Insertar Vehículo");
        controlador.insertar(Consola.leerVehiculo());
        System.out.println("Vehículo insertado correctamente.");
    }

    private void insertarRevision() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Insertar Revisión");
        controlador.insertar(Consola.leerRevision());
        System.out.println("Revisión insertada correctamente.");
    }

    private void buscarCliente() {
        Consola.mostrarCabecera("Buscar cliente");
        Cliente cliente = controlador.buscar(Consola.leerClienteDni());
        System.out.println((cliente != null) ? cliente : "No existe ningún cliente con dicho DNI.");
    }

    private void buscarVehiculo() {
        Consola.mostrarCabecera("Buscar Vehículo");
        Vehiculo vehiculo = controlador.buscar(Consola.leerMatriculaVehiculo());
        System.out.println((vehiculo != null) ? vehiculo : "No existe ningún vehículo con dicha matrícula.");
    }

    private void buscarRevision() {
        Consola.mostrarCabecera("Buscar Revisión");
        Revision revision = controlador.buscar(Consola.leerRevision());
        System.out.println((revision != null) ? revision : "No existe ninguna revisión para ese cliente, vehículo y fecha.");
    }

    private void modificarCliente() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Modificar Cliente");
        boolean modificado = controlador.modificar(Consola.leerClienteDni(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono());
        System.out.println((modificado) ? "El cliente se ha modificado correctamente." : "El cliente no se ha modificado.");
    }

    private void anadirHoras() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Añadir Horas Revisión");
        controlador.anadirHoras(Consola.leerRevision(), Consola.leerHoras());
        System.out.println("Horas añadidas correctamente.");
    }


    private void anadirPrecioMaterial() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Añadir Precio Material Revisión");
        controlador.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerPrecioMaterial());
        System.out.println("Precio material añadido correctamente.");
    }

    private void cerrarRevision() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Cerrar Revisión");
        controlador.cerrar(Consola.leerRevision(), Consola.leerFechaFin());
        System.out.println("Revisión cerrada correctamente.");
    }

    private void borrarCliente() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Borrar Cliente");
        controlador.borrar(Consola.leerClienteDni());
        System.out.println("Cliente borrado correctamente.");
    }

    private void borrarVehiculo() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Borrar Vehículo");
        controlador.borrar(Consola.leerMatriculaVehiculo());
        System.out.println("Vehículo borrado correctamente.");
    }

    private void borrarRevision() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Borrar Revisión");
        controlador.borrar(Consola.leerRevision());
        System.out.println("Revisión borrada correctamente.");
    }

    private void listarClientes() {
        Consola.mostrarCabecera("Listar Clientes");
        List<Cliente> clientes = controlador.getClientes();
        if (!clientes.isEmpty()) {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        } else {
            System.out.println("No hay clientes que mostrar.");
        }
    }

    private void listarVehiculos() {
        Consola.mostrarCabecera("Listar Vehículos");
        List<Vehiculo> vehiculos = controlador.getVehiculos();
        if (!vehiculos.isEmpty()) {
            for (Vehiculo vehiculo : vehiculos) {
                System.out.println(vehiculo);
            }
        } else {
            System.out.println("No hay vehículos que mostrar.");
        }
    }

    private void listarRevisiones() {
        Consola.mostrarCabecera("Listar Revisiones");
        List<Revision> revisiones = controlador.getRevisiones();
        if (!revisiones.isEmpty()) {
            for (Revision revision : revisiones) {
                System.out.println(revision);
            }
        } else {
            System.out.println("No hay revisiones que mostrar.");
        }
    }

    public void listarRevisionesCliente() {
        Consola.mostrarCabecera("Listar Revisiones Cliente");
        List<Revision> revisionesCliente = controlador.getRevisiones(Consola.leerClienteDni());
        if (!revisionesCliente.isEmpty()) {
            for (Revision revision : revisionesCliente) {
                System.out.println(revision);
            }
        } else {
            System.out.println("No hay revisiones que mostrar para dicho cliente.");
        }
    }

    public void listarRevisionesVehiculo() {
        Consola.mostrarCabecera("Listar Revisiones Vehículo");
        List<Revision> revisionesVehiculo = controlador.getRevisiones(Consola.leerMatriculaVehiculo());
        if (!revisionesVehiculo.isEmpty()) {
            for (Revision revision : revisionesVehiculo) {
                System.out.println(revision);
            }
        } else {
            System.out.println("No hay revisiones que mostrar para dicho vehículo.");
        }
    }

    private void salir() {
        controlador.terminar();
    }
}


