package persistenza;

import implementazione.Libro;
import java.util.*;

public class LibreriaMemento {
    private final List<Libro> stato;

    public LibreriaMemento(List<Libro> statoCorrente) {
        this.stato = new ArrayList<>(statoCorrente); // deep copy
    }

    public List<Libro> getStatoSalvato() {
        return new ArrayList<>(stato); // restituisce una copia
    }
}
