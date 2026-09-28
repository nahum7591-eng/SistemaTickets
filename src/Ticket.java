import java.time.LocalDateTime;

public class Ticket {
    private static int cantidad = 0;
    private int id;
    private String descripcion;
    private String nombreCompleto;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaResolucion;

    public Ticket(String descripcion, String nombreCompleto) {
        this.id = ++cantidad;
        this.descripcion = descripcion;
        this.nombreCompleto = nombreCompleto;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaResolucion = null;
    }

    // Getters
    public int getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public String getNombreCompleto() { return nombreCompleto; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getFechaResolucion() { return fechaResolucion; }

    // Setter que faltaba
    public void setFechaResolucion(LocalDateTime fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    // Método para resolver directamente
    public void resolver() {
        this.fechaResolucion = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Ticket #" + id + " - " + descripcion +
                " | Usuario: " + nombreCompleto +
                " | Creado: " + fechaCreacion +
                (fechaResolucion == null ? " | Pendiente" : " | Resuelto: " + fechaResolucion);
    }
}
