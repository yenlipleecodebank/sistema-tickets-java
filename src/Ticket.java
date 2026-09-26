import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {

    // Atributos estáticos y de instancia
    private static int cantidad = 1; // Generador del consecutivo
    private int id;
    private String descripcion;
    private String nombreCompleto;
    private String fechaCreacion;
    private String fechaResolucion;
    private int prioridad; // 1: Alta, 2: Media, 3: Baja

    // Constructor
    public Ticket(String descripcion, String nombreCompleto, int prioridad) {
        this.id = cantidad++; // Asignamos el id y luego incrementamos el estático
        this.descripcion = descripcion;
        this.nombreCompleto = nombreCompleto;
        this.prioridad = prioridad;

        // Asignamos la fecha de creación automáticamente
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.fechaCreacion = LocalDateTime.now().format(formatter);
        this.fechaResolucion = null; // Inicia en null por defecto
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public String getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(String fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        String estado = (fechaResolucion == null) ? "Pendiente" : "Resuelto el " + fechaResolucion;
        return "\nTicket ID: " + id +
                "\nUsuario: " + nombreCompleto +
                "\nDescripción: " + descripcion +
                "\nPrioridad: " + prioridad +
                "\nFecha Creación: " + fechaCreacion +
                "\nEstado: " + estado + "\n";
    }
}