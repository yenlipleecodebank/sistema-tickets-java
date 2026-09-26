import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ColaPrioridad colaPendientes = new ColaPrioridad();
        ListaEnlazadaSimple listaResueltos = new ListaEnlazadaSimple();

        boolean salir = false;

        System.out.println("BIENVENIDO AL SISTEMA DE TICKETS");

        while (!salir) {
            System.out.println("\nSeleccione el menú al que desea acceder:");
            System.out.println("1. Menú de Usuario");
            System.out.println("2. Menú de Administrador");
            System.out.println("3. Salir");
            System.out.print("Opción: ");

            int opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    menuUsuario(scanner, colaPendientes, listaResueltos);
                    break;
                case 2:
                    menuAdministrador(scanner, colaPendientes, listaResueltos);
                    break;
                case 3:
                    salir = true;
                    System.out.println("¡Gracias por utilizar el sistema de tickets! Hasta luego.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    private static void menuUsuario(Scanner scanner, ColaPrioridad cola, ListaEnlazadaSimple lista) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\nMENÚ DE USUARIO");
            System.out.println("1. Crear un ticket");
            System.out.println("2. Buscar un ticket resuelto");
            System.out.println("3. Volver al menú principal");
            System.out.print("Opción: ");

            int opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese su nombre completo: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese la descripción del problema: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Ingrese la prioridad (1: Alta, 2: Media, 3: Baja): ");
                    int prioridad = leerEntero(scanner);

                    // Creamos el ticket y lo insertamos a la cola de prioridad
                    Ticket nuevoTicket = new Ticket(descripcion, nombre, prioridad);
                    cola.insertar(nuevoTicket);
                    System.out.println("\n¡Ticket creado exitosamente! Su número de ID es: " + nuevoTicket.getId());
                    break;

                case 2:
                    System.out.print("Ingrese el ID del ticket que desea buscar: ");
                    int idBuscar = leerEntero(scanner);
                    Ticket ticketBuscado = lista.buscar(idBuscar);

                    if (ticketBuscado != null) {
                        System.out.println("\nEl ticket ha sido encontrado:");
                        System.out.println(ticketBuscado.toString());
                    } else {
                        System.out.println("\nEl ticket con ID " + idBuscar + " está pendiente de solución o no existe.");
                    }
                    break;

                case 3:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuAdministrador(Scanner scanner, ColaPrioridad cola, ListaEnlazadaSimple lista) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\nMENÚ DE ADMINISTRADOR");
            System.out.println("1. Ver ticket al frente de la cola");
            System.out.println("2. Resolver ticket actual");
            System.out.println("3. Ver todos los tickets resueltos");
            System.out.println("4. Volver al menú principal");
            System.out.print("Opción: ");

            int opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    Ticket alFrente = cola.verFrente();
                    if (alFrente != null) {
                        System.out.println("\nTicket al frente de la cola (Mayor prioridad):");
                        System.out.println(alFrente.toString());
                    }
                    break;

                case 2:
                    Ticket ticketAResolver = cola.eliminar();
                    if (ticketAResolver != null) {
                        // Setteamos la fecha de resolución
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                        ticketAResolver.setFechaResolucion(LocalDateTime.now().format(formatter));

                        // Lo guardamos en la lista de resueltos
                        lista.insertarFin(ticketAResolver);
                        System.out.println("\n¡El ticket ID " + ticketAResolver.getId() + " ha sido resuelto y movido al historial!");
                    }
                    break;

                case 3:
                    lista.imprimirLista();
                    break;

                case 4:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    // Metodo auxiliar para evitar errores del Scanner al leer enteros - Este metodo lo tome de IA,
    // ya que me estaba generando un error al ingresar algunos números y fue
    // la solución que se me sugerió.
    private static int leerEntero(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next(); // Limpia la entrada incorrecta
        }
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del enter
        return numero;
    }
}