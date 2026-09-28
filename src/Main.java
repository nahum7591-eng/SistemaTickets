import java.time.LocalDateTime;
import java.util.*;

public class Main {
    // Cola de prioridad para tickets pendientes
    private static PriorityQueue<Ticket> pendientes = new PriorityQueue<>(Comparator.comparing(Ticket::getId));
    // Lista enlazada para tickets resueltos
    private static LinkedList<Ticket> resueltos = new LinkedList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- Sistema de Tickets ---");
            System.out.println("1. Usuario");
            System.out.println("2. Administrador");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch(opcion) {
                case 1 -> menuUsuario();
                case 2 -> menuAdmin();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("⚠️ Opción inválida.");
            }
        } while(opcion != 0);
    }

    // Menú para el usuario
    private static void menuUsuario() {
        System.out.println("\n--- Menú Usuario ---");
        System.out.println("1. Crear ticket");
        System.out.println("2. Buscar ticket resuelto");
        System.out.print("Seleccione una opción: ");
        int op = sc.nextInt(); sc.nextLine();

        if(op == 1) {
            System.out.print("Descripción: ");
            String desc = sc.nextLine();
            System.out.print("Nombre completo: ");
            String nombre = sc.nextLine();
            Ticket t = new Ticket(desc, nombre);
            pendientes.add(t);
            System.out.println("✅ Ticket creado: " + t);
        } else if(op == 2) {
            System.out.print("Ingrese ID del ticket: ");
            int id = sc.nextInt();
            Ticket encontrado = resueltos.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
            if(encontrado != null) {
                System.out.println("🔎 Ticket encontrado: " + encontrado);
            } else {
                System.out.println("⚠️ El ticket está pendiente.");
            }
        } else {
            System.out.println("⚠️ Opción inválida.");
        }
    }

    // Menú para el administrador
    private static void menuAdmin() {
        System.out.println("\n--- Menú Administrador ---");
        System.out.println("1. Ver ticket al frente");
        System.out.println("2. Resolver ticket al frente");
        System.out.print("Seleccione una opción: ");
        int op = sc.nextInt(); sc.nextLine();

        if(op == 1) {
            Ticket frente = pendientes.peek();
            if(frente != null) {
                System.out.println("📌 Ticket al frente: " + frente);
            } else {
                System.out.println("No hay tickets pendientes.");
            }
        } else if(op == 2) {
            Ticket t = pendientes.poll();
            if(t != null) {
                t.setFechaResolucion(LocalDateTime.now());
                resueltos.add(t);
                System.out.println("✅ Ticket resuelto: " + t);
            } else {
                System.out.println("No hay tickets pendientes.");
            }
        } else {
            System.out.println("⚠️ Opción inválida.");
        }
    }
}
