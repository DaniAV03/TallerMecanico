package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.time.LocalDate;

public class Revisiones {
    private final List<Revision> listarevisiones;

    public Revisiones() {
        listarevisiones = new ArrayList<>();
    }

    public List<Revision> get() {return new ArrayList<>(listarevisiones);
    }

    public List<Revision> get(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        List<Revision> listaRevisionesClientes = new ArrayList<>();
        for (Revision revision : listarevisiones) {
            if (revision.getCliente().equals(cliente)) {
                listaRevisionesClientes.add(revision);
            }
        }
        return listaRevisionesClientes;
    }

    public List<Revision> get(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        List<Revision> listaReisionesVehículos = new ArrayList<>();
        for (Revision revision : listarevisiones) {
            if (revision.getVehiculo().equals(vehiculo)) {
                listaReisionesVehículos.add(revision);
            }
        }
        return listaReisionesVehículos;
    }

    public void insertar(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No se puede insertar una revisión nula.");
        comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio());
        listarevisiones.add(revision);
    }

    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws OperationNotSupportedException {
        for (Revision revision : listarevisiones) {
            if (revision.getCliente().equals(cliente) && !revision.estaCerrada()) {
                throw new OperationNotSupportedException("El cliente tiene otra revisión en curso.");
            }
            if (revision.getVehiculo().equals(vehiculo) && !revision.estaCerrada()) {
                throw new OperationNotSupportedException("El vehículo está actualmente en revisión.");
            }
            if (revision.estaCerrada() && revision.getCliente().equals(cliente) && !fechaRevision.isAfter(revision.getFechaFin())) {
                throw new OperationNotSupportedException("El cliente tiene una revisión posterior.");
            }
            if (revision.estaCerrada() && revision.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(revision.getFechaFin())) {
                throw new OperationNotSupportedException("El vehículo tiene una revisión posterior.");
            }
        }
    }
    private Revision getRevision(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No se puede buscar una revisión nula.");
        int index = listarevisiones.indexOf(revision);
        if (index != -1) {
            return listarevisiones.get(index);
        } else {
            throw new OperationNotSupportedException("No existe ninguna revisión igual.");
        }
    }

    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
        getRevision(revision).anadirHoras(horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No se puede buscar una revisión nula.");
        getRevision(revision).anadirPrecioMaterial(precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
        getRevision(revision).cerrar(fechaFin);
    }

    public Revision buscar(Revision revision) {
        Objects.requireNonNull(revision, "No se puede buscar una revisión nula.");
        int index = listarevisiones.indexOf(revision);
        if (index != -1) {
            return listarevisiones.get(index);
        } else {
            return null;
        }
    }

    public void borrar(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No se puede borrar una revisión nula.");
        int index = listarevisiones.indexOf(revision);
        if (index != -1) {
            listarevisiones.remove(revision);
        } else {
            throw new OperationNotSupportedException("No existe ninguna revisión igual.");
        }
    }
}