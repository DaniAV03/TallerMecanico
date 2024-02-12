package dominio;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
    private static final String ER_NOMBRE = "([A-ZÁÉÍÓÚ][a-záéíóú]+)((\\s[A-ZÁÉÍÓÚ][a-záéíóú]+)?)+";
    private static final String ER_DNI = "^[0-9]{8}[A-Za-z]$";
    private static final String ER_TELEFONO = "^[0-9]{9}$";

    private String nombre;
    private String dni;
    private String telefono;

    public Cliente(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("No es posible copiar un cliente nulo.");
        }
        this.nombre = cliente.nombre;
        this.dni = cliente.dni;
        this.telefono = cliente.telefono;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo.");
        Pattern patron = Pattern.compile(ER_NOMBRE);
        Matcher matcher = patron.matcher(nombre);
        if (!Pattern.matches(ER_NOMBRE, nombre)) {
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        }
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        Objects.requireNonNull(dni, "El DNI no puede ser nulo.");
        if (!Pattern.matches(ER_DNI, dni)) {
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }

        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("La letra del DNI no es correcta.");
        }
        this.dni = dni;
    }

    private static boolean comprobarLetraDni(String dni) {
        String letra = dni.substring(8);
        int numero = Integer.parseInt(dni.substring(0, 8));//parseINT = cambia la cadena de texto por un número entero.
        int resto = numero % 23;
        String[] letras = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        return letra.equalsIgnoreCase(letras[resto]);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono, "El teléfono no puede ser nulo.");
        if (!Pattern.matches(ER_TELEFONO, telefono)) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    public static final Cliente get(String dni) {
        if (dni == null) {
            throw new NullPointerException("El DNI no puede ser nulo.");
        }
        if (!Pattern.matches(ER_DNI, dni)) {
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("La letra del DNI no es correcta.");
        }

        return new Cliente("Patricio Estrella", dni ,"950111111");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nombre, cliente.nombre) && Objects.equals(dni, cliente.dni) && Objects.equals(telefono, cliente.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, dni, telefono);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", this.nombre, this.dni, this.telefono);
    }
}
