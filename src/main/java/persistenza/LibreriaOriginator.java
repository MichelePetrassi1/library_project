package persistenza;

import implementazione.Libro;

import java.io.IOException;
import java.util.*;

public class LibreriaOriginator {
    private List<Libro> libri;
    private final LibreriaCaretaker caretaker;

    public LibreriaOriginator() {
        try {
            libri = LibreriaJSON.carica();
        } catch (IOException e) {
            libri = new ArrayList<>();
            System.out.println("Nessun file trovato. Libreria vuota.");
        }
        caretaker = new LibreriaCaretaker();
    }

    public void aggiungiLibro(Libro libro) {
        salvaStatoCorrente(); // salva stato prima della modifica
        libri.add(libro);
    }

    public void rimuoviLibro(Libro libro) {
        salvaStatoCorrente();
        libri.remove(libro);
    }

    public List<Libro> getLibri() {
        return new ArrayList<>(libri);
    }

    public void salvaSuFile() {
        try {
            LibreriaJSON.salva(libri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void undo() {
        if (caretaker.haStatiSalvati()) {
            libri = caretaker.ripristinaUltimoStato().getStatoSalvato();
        } else {
            System.out.println("Nessuno stato precedente disponibile.");
        }
    }

    private void salvaStatoCorrente() {
        caretaker.salvaStato(new LibreriaMemento(libri));
    }
}
