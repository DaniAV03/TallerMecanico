package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public abstract class Trabajo {
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final float PRECIO_DIA = 10;
    protected LocalDate fechaInicio;
    protected LocalDate fechafin;
    protected int horas;
    protected Cliente cliente;
    protected Vehiculo vehiculo;
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




    public static Trabajo copiar (Trabajo trabajo){
        Objects.requireNonNull(trabajo,"El trabajo no puede ser nulo.");
        if(trabajo instanceof Revision){
            return new Revision((Revision) trabajo);
        }else if (trabajo instanceof  Mecanico){
            return  new Mecanico((Mecanico) trabajo);
        }else throw new IllegalArgumentException("Error al copiar el trabajo.");
    }
    public static Trabajo get (Vehiculo vehiculo){

    }
    public Cliente getCliente() {
        return cliente;
    }

    protected void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    protected void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    protected void setFechaInicio(LocalDate fechaInicio) {
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
        if (estaCerrada())
            throw new OperationNotSupportedException("No se puede añadir horas, ya que la revisión está cerrada.");
        this.horas += horas;
    }

    public float getPrecio() {
        float precioTotalHoras = horas * Revision.PRECIO_HORA;
        float precioTotalDias = getDias() >= 1 ? (getDias()) * PRECIO_DIA : 0;
        return (float) (precioTotalHoras + precioTotalDias + precioMaterial * Revision.PRECIO_MATERIAL);
    }

    private float getDias() {
        return fechafin != null ? ChronoUnit.DAYS.between(fechaInicio, fechafin) : 0;
    }

    public void cerrar(LocalDate fechaFin) throws OperationNotSupportedException {
        if (this.fechafin != null) {
            throw new OperationNotSupportedException("La revisión ya está cerrada.");
        }
        setFechafin(fechaFin);
    }

    public boolean estaCerrada() {
        return fechafin != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return Objects.equals(fechaInicio, revision.fechaInicio) && Objects.equals(cliente, revision.cliente) && Objects.equals(vehiculo, revision.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, cliente, vehiculo);
    }
}
