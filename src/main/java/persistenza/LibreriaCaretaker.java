package persistenza;

import java.util.Stack;

public class LibreriaCaretaker {

    private final Stack<LibreriaMemento> cronologia = new Stack<>();
    private final Stack<LibreriaMemento> cronologiaRedo = new Stack<>();

    public void salvaStato(LibreriaMemento memento) {
        cronologia.push(memento);
        cronologiaRedo.clear(); //a ogni salvataggio dobbiamo svuotare redo

    }

    public boolean haStatiSalvati() {
        return !cronologia.isEmpty();
    }

    public boolean haStatiRedo() {
        return !cronologiaRedo.isEmpty();
    }

    public LibreriaMemento ripristinaStatoRedo() {
        return cronologiaRedo.pop();
    }

    public LibreriaMemento ripristinaUltimoStato() {
        if (cronologia.isEmpty()) return null;
        return cronologia.pop();
    }

}
