package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
    Clientes clientes;
    Vehiculos vehiculos;
    Revisiones revisiones;
   public void comenzar() {
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        revisiones = new Revisiones();
    }
    public void terminar(){System.out.print("Se terminó, no hace nada más.");}
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        clientes.insertar(new Cliente(cliente));
    }
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        vehiculos.insertar(vehiculo);
    }
    public void insertar(Revision revision) throws OperationNotSupportedException {
        Cliente cliente = clientes.buscar(revision.getCliente());
        Vehiculo vehiculo = vehiculos.buscar(revision.getVehiculo());
        revisiones.insertar(new Revision(cliente,vehiculo,revision.getFechaInicio()));
    }
    public Cliente buscar(Cliente cliente){return clientes.buscar(cliente);
    }
    public Vehiculo buscar(Vehiculo vehiculo){return vehiculos.buscar(vehiculo);
    }public Revision buscar(Revision revision){return revisiones.buscar(revision);
   }
    public boolean modificar(Cliente cliente,String nombre, String telefono) throws OperationNotSupportedException {
        return clientes.modificar(cliente,nombre,telefono);
    }
    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException {
        revisiones.anadirHoras(revision,horas);
    }
    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws OperationNotSupportedException {
        revisiones.anadirPrecioMaterial(revision,precioMaterial);
    }
    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException {
        revisiones.cerrar(revision,fechaFin);
    }
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        List<Revision> listaRevisiones = revisiones.get(cliente);
        while (!listaRevisiones.isEmpty()) {
            Revision revision = listaRevisiones.remove(0);
            revisiones.borrar(revision);
        }
        clientes.borrar(cliente);
    }
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        List<Revision> listaRevisiones = revisiones.get(vehiculo);
        while (!listaRevisiones.isEmpty()) {
            Revision revision = listaRevisiones.remove(0);
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }
    public void borrar(Revision revision) throws OperationNotSupportedException {
        revisiones.borrar(revision);
    }
    public List<Cliente> getClientes() {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            listaClientes.add(new Cliente(cliente));
        }
        return listaClientes;
    }
    public List<Vehiculo> getVehiculos(){
        return new ArrayList<>(vehiculos.get());
    }
    public List<Revision> getRevisiones(){
        ArrayList<Revision> listaRevisones = new ArrayList<>();
        for (Revision revision : revisiones.get()) {
            listaRevisones.add(new Revision (revision));
        }
        return listaRevisones;
    }
    public List<Revision> getRevisiones(Cliente cliente){
        ArrayList<Revision> listaRevisonesCliente = new ArrayList<>();
        for (Revision revision : revisiones.get(cliente)) {
            listaRevisonesCliente.add(new Revision (revision));
        }
        return listaRevisonesCliente;
    }
    public List<Revision> getRevisiones(Vehiculo vehiculo){
        ArrayList<Revision> listaRevisonesVehiculo = new ArrayList<>();
        for (Revision revision : revisiones.get(vehiculo)) {
            listaRevisonesVehiculo.add(new Revision (revision));
        }
        return listaRevisonesVehiculo;
    }
}

