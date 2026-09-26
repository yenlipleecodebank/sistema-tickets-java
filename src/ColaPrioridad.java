import java.util.ArrayList;

public class ColaPrioridad {

    // Atributos
    private ArrayList<Ticket> cola;

    // Constructor
    public ColaPrioridad() {
        cola = new ArrayList<>();
    }

    // Operaciones
    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public void insertar(Ticket ticket) {
        // Si está vacía, simplemente agregamos el ticket
        if (estaVacia()) {
            cola.add(ticket);
            return;
        }

        // Si no está vacía, buscamos la posición correcta según prioridad (1 es mayor prioridad)
        // Manteniendo el orden FIFO para prioridades iguales.
        boolean insertado = false;
        for (int i = 0; i < cola.size(); i++) {
            if (ticket.getPrioridad() < cola.get(i).getPrioridad()) {
                cola.add(i, ticket);
                insertado = true;
                break;
            }
        }

        // Si su prioridad es la más baja, va al final de la cola
        if (!insertado) {
            cola.add(ticket);
        }
    }

    public Ticket eliminar() {
        if (estaVacia()) {
            System.out.println("La cola de tickets pendientes está vacía.\n");
            return null;
        }
        return cola.removeFirst(); // Removemos y devolvemos el primero (el de mayor prioridad)
    }

    public Ticket verFrente() {
        if (estaVacia()) {
            System.out.println("La cola de tickets pendientes está vacía.\n");
            return null;
        }
        return cola.getFirst();
    }
}