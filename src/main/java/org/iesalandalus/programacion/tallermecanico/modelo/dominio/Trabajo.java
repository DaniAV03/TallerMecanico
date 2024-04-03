package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public abstract class Trabajo {
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final float FACTOR_DIA = 10;
    protected static LocalDate fechaInicio;
    LocalDate fechafin;
    protected int horas;
    protected static Cliente cliente;
    protected static Vehiculo vehiculo;
    protected Trabajo(Cliente cliente,Vehiculo vehiculo, LocalDate fechaInicio){
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
    }
    protected Trabajo(Trabajo trabajo){
        Objects.requireNonNull(trabajo,"El trabajo no puede ser nulo.");
        this.cliente = new Cliente(trabajo.cliente);
        this.vehiculo = trabajo.vehiculo;
        this.fechaInicio = trabajo.fechaInicio;
        this.fechafin = trabajo.fechafin;
        this.horas = trabajo.horas;
    }
    public static Trabajo copiar(Trabajo trabajo) {
        if (trabajo instanceof Revision) {
            return new Revision((Revision) trabajo);
        } else if (trabajo instanceof Mecanico) {
            return new Mecanico((Mecanico) trabajo);
        } else {
            throw new NullPointerException("El trabajo no puede ser nulo.");
        }
    }
    public static Trabajo get(Vehiculo vehiculo) {
      return new Revision(new Cliente("Dani","07643836F","651179397"),vehiculo,LocalDate.now());
    }
    public Cliente getCliente() {
        return cliente;
    }

    private void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    private void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechafin;
    }

    private void setFechafin(LocalDate fechafin) {
        if (fechafin == null)
            throw new NullPointerException("La fecha de fin no puede ser nula.");
        if (fechafin.isBefore(fechaInicio))
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        if (fechafin.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        this.fechafin = fechafin;
    }

    public int getHoras() {
        return horas;
    }

    public void anadirHoras(int horas) throws OperationNotSupportedException {
        if (horas <= 0)
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        if (estaCerrado())
            throw new OperationNotSupportedException("No se puede añadir horas, ya que el trabajo está cerrado.");
        this.horas += horas;
    }
    public boolean estaCerrado() {
        return fechafin != null;
    }

    public void cerrar(LocalDate fechaFin) throws OperationNotSupportedException {
        if (this.fechafin != null) {
            throw new OperationNotSupportedException("El trabajo ya está cerrado.");
        }
        setFechafin(fechaFin);
    }
    public float getPrecio() {
        return getPrecioEspecifico()+getPrecioFijo();

    }
    public float getPrecioFijo() {
        return getDias()*FACTOR_DIA;
    }
    private float getDias() {
        return fechafin != null ? ChronoUnit.DAYS.between(fechaInicio, fechafin) : 0;
    }

    public abstract float getPrecioEspecifico();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trabajo trabajo = (Trabajo) o;
        return horas == trabajo.horas && Objects.equals(cliente, trabajo.cliente) && Objects.equals(vehiculo,trabajo.vehiculo) && Objects.equals(fechaInicio, trabajo.fechaInicio);
    }
    @Override
    public int hashCode() {
        return Objects.hash( cliente,vehiculo,fechaInicio);
    }
}
