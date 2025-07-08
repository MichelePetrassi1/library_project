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
        caretaker.salvaMemento(new LibreriaMemento(libri));
        libri.add(libro);
        salvaSuFile();
    }

    public void rimuoviLibro(Libro libro) {
        caretaker.salvaMemento(new LibreriaMemento(libri));
        libri.remove(libro);
        salvaSuFile();
    }

    public void modificaLibro(Libro originale, Libro nuovo) {
        caretaker.salvaMemento(new LibreriaMemento(libri));
        //trovo l'indice del libro originale
        int indice = libri.indexOf(originale);
        if (indice == -1) {
            throw new IllegalArgumentException("Impossibile modificare: libro non trovato");
        }
        libri.set(indice, nuovo);
        salvaSuFile();
    }

    public void undo() {
        if (!caretaker.haMementoUndo()) return;
        // salviamo il memento corrente per redo
        caretaker.pushRedo(new LibreriaMemento(libri));
        // ripristiniamo l’ultimo memento salvato
        libri = caretaker.popUndo().getStatoSalvato();
        salvaSuFile();
    }

    public void redo() {
        if (!caretaker.haMementoRedo()) return;
        // salviamo il memento corrente per undo
        caretaker.pushUndo(new LibreriaMemento(new ArrayList<>(libri)));
        // ripristiniamo l'ultimo memento prima dell'undo
        libri = caretaker.popRedo().getStatoSalvato();
        salvaSuFile();
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
}
