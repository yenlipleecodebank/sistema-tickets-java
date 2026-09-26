public class ListaEnlazadaSimple {

    // Atributos
    private NodoLista primero;

    // Constructor
    public ListaEnlazadaSimple() {
        primero = null;
    }

    // Getter & Setter
    private NodoLista getPrimero() {
        return primero;
    }

    private void setPrimero(NodoLista primero) {
        this.primero = primero;
    }

    // Operaciones
    public boolean estaVacia() {
        return primero == null;
    }

    // Metodo para insertar al final
    public void insertarFin(Ticket ticket) {
        NodoLista nodo = new NodoLista(ticket);

        if (estaVacia()) {
            setPrimero(nodo);
            return;
        }

        NodoLista temp = primero;
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }
        temp.setSiguiente(nodo);
    }

    // Metodo buscar  para buscar por ID
    public Ticket buscar(int id) {
        if (estaVacia()) {
            return null;
        }
        NodoLista temp = primero;
        while (temp != null) {
            if (temp.getTicket().getId() == id) {
                return temp.getTicket();
            }
            temp = temp.getSiguiente();
        }
        return null;
    }

    public void imprimirLista() {
        if (estaVacia()) {
            System.out.println("No hay tickets resueltos actualmente.");
            return;
        }

        System.out.println("\nLISTA DE TICKETS RESUELTOS");
        NodoLista temp = primero;

        while (temp != null) {
            System.out.println(temp.getTicket().toString());
            temp = temp.getSiguiente();
        }
    }

    // Clase interna NodoLista
    private class NodoLista {

        private Ticket ticket;
        private NodoLista siguiente;

        // Constructor
        public NodoLista(Ticket ticket) {
            this.ticket = ticket;
            this.siguiente = null;
        }

        // Getters & Setters
        public Ticket getTicket() {
            return ticket;
        }

        public void setTicket(Ticket ticket) {
            this.ticket = ticket;
        }

        public NodoLista getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(NodoLista siguiente) {
            this.siguiente = siguiente;
        }
    }
}