package persistenza;

import java.util.Stack;

public class LibreriaCaretaker {

    private final Stack<LibreriaMemento> cronologia = new Stack<>();

    public void salvaStato(LibreriaMemento memento) {
        cronologia.push(memento);
    }

    public boolean haStatiSalvati() {
        return !cronologia.isEmpty();
    }

    public LibreriaMemento ripristinaUltimoStato() {
        if (cronologia.isEmpty()) return null;
        return cronologia.pop();
    }

}
