
package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes {
    private final List<Cliente> listaClientes;

    public Clientes() {
        listaClientes = new ArrayList<>();
    }

    public List<Cliente> get() {
        return new ArrayList<>(listaClientes);
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente,"No se puede insertar un cliente nulo.");
        if (listaClientes.contains(cliente)) {
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        listaClientes.add(cliente);
    }

    public boolean modificar(Cliente cliente, String nuevoNombre, String nuevoTelefono) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente,"No se puede modificar un cliente nulo.");
        if ( !listaClientes.contains(cliente)) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        int index = listaClientes.indexOf(cliente);
        if (nuevoNombre != null) {
            cliente.setNombre(nuevoNombre);
        }
        if (nuevoTelefono != null) {
            cliente.setTelefono(nuevoTelefono);
        }
        return listaClientes.set(index, cliente) != null;
    }
    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        int index = listaClientes.indexOf(cliente);
        if (index != -1) {
            return listaClientes.get(index);
        }

       else return null;
    }
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente,"No se puede borrar un cliente nulo.");
        if (!listaClientes.contains(cliente)) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        listaClientes.remove(cliente);
    }
}


