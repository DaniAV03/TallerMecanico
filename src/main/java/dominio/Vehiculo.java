package dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vehiculo {
    private static final String ER_MARCA = "[A-Z]+[a-z]*([ -]?[A-Z][a-z]+)*";
    private static final String ER_MATRICULA = "\\d{4}[QWRTYPSDFGHJKLZCVBNM]{3}";
    private String marca;
    private String modelo;
    private String matricula;
    public Vehiculo (String marca , String modelo , String matricula){
      validarMarca(marca);
      validarModelo(modelo);
      validar(matricula);
    }
    public String marca() {return marca;}

    public String matricula() {return matricula;}

    public String modelo() {return modelo;}
    private void validarMarca(String marca) {
        Objects.requireNonNull(marca,"La marca no puede ser nula.");
        Pattern patron = Pattern.compile(ER_MARCA);
        Matcher matcher = patron.matcher(marca);
        if (!Pattern.matches(ER_MARCA, marca)) {
            throw new IllegalArgumentException("La marca no tiene un formato válido.");
        }
        this.marca = marca;
    }
    private void validarModelo(String modelo) {
        Objects.requireNonNull(modelo , "El modelo no puede ser nulo.");
        if (modelo.isBlank()){
            throw new IllegalArgumentException("El modelo no puede estar en blanco.");
        }
        this.modelo = modelo;
    }
    private void validar(String matricula) {
        Objects.requireNonNull(matricula,"La matrícula no puede ser nula.");
        Pattern patron = Pattern.compile(ER_MATRICULA);
        Matcher matcher = patron.matcher(matricula);
        if (!Pattern.matches(ER_MATRICULA , matricula)) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
        this.matricula=matricula;
    }
    public static final Vehiculo get(String matricula) {
        if (matricula == null) {
            throw new NullPointerException("La matrícula no puede ser nula.");
        }
        if (!Pattern.matches(ER_MATRICULA, matricula)) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
        return new Vehiculo("Renault Magane","Civic",matricula);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(matricula, vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
    @Override
    public String toString() {
        return String.format("%s %s - %s", this.marca, this.modelo, this.matricula);
    }



}