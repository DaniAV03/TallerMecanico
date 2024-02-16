package org.iesalandalus.programacion.tallermecanico.modelo.dominio;
import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Revision {
  private static final float PRECIO_HORA = 30;
  private static final float PRECIO_DIA =10;
  private static final double PRECIO_MATERIAL = 1.5;
  public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private LocalDate fechaInicio;
  private LocalDate fechafin;
  private int horas;
  private float precioMaterial;
  private Cliente cliente;
  private Vehiculo vehiculo;
  public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
    setCliente(cliente);
    setVehiculo(vehiculo);
    setFechaInicio(fechaInicio);
  }

  public Revision(Revision revision) {
    if (revision == null) {
      throw new NullPointerException("La revisión no puede ser nula.");
    }
    this.cliente = new Cliente(revision.cliente);
    this.vehiculo = revision.vehiculo;
    this.fechaInicio = revision.fechaInicio;
    this.fechafin = revision.fechafin;
    this.horas = revision.horas;
    this.precioMaterial = revision.precioMaterial;
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
    if (estaCerrada())
      throw new OperationNotSupportedException("No se puede añadir horas, ya que la revisión está cerrada.");
    this.horas += horas;
  }

  public float getPrecioMaterial() {
    return precioMaterial;
  }

  public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
    if (precioMaterial <= 0) {
      throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
    }
    if (fechafin != null) {
      throw new OperationNotSupportedException("No se puede añadir precio del material, ya que la revisión está cerrada.");
    }
    this.precioMaterial += precioMaterial;
  }

  public float getPrecio() {
    float precioTotalHoras = horas * PRECIO_HORA;
    float precioTotalDias = getDias() >= 1 ? (getDias()) * PRECIO_DIA : 0;
    return (float) (precioTotalHoras + precioTotalDias + precioMaterial * PRECIO_MATERIAL);}

  private float getDias() {
    return fechafin != null ? ChronoUnit.DAYS.between(fechaInicio,fechafin)  : 0;
  }
  public void cerrar(LocalDate fechaFin) throws OperationNotSupportedException {
    if (this.fechafin != null) {
      throw new OperationNotSupportedException("La revisión ya está cerrada.");
    }
    setFechafin(fechaFin);
  }
  public boolean estaCerrada() { return fechafin != null; }



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
  @Override
  public String toString() {
    String fechaInicioString = fechaInicio != null ? fechaInicio.format(Revision.FORMATO_FECHA) : "";
    String fechaFinString = fechafin != null ? fechafin.format(Revision.FORMATO_FECHA) : "";
    String horasString = String.valueOf(horas);
    String precioMaterialString = String.format("%.2f", precioMaterial);
    String precioString = getPrecio() != 0.0 ? String.format(", %.2f € total", getPrecio()) : "";
    return cliente + " - " + vehiculo + ": (" + fechaInicioString + " - " + fechaFinString + "), " +
            horasString + " horas, " + precioMaterialString + " € en material" + precioString;
  }



}
