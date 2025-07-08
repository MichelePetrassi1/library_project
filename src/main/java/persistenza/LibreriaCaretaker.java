package persistenza;

import java.util.Stack;

public class LibreriaCaretaker {

    private final Stack<LibreriaMemento> undoStack = new Stack<>();
    private final Stack<LibreriaMemento> redoStack = new Stack<>();


    public void salvaMemento(LibreriaMemento m) {
        undoStack.push(m);
        redoStack.clear(); //a ogni nuova azione, puliamo lo stack di redo
    }

    public void pushRedo(LibreriaMemento m) {
        redoStack.push(m);
    }

    public void pushUndo(LibreriaMemento m) {
        undoStack.push(m);
    }

    public boolean haMementoUndo() { return !undoStack.isEmpty(); }
    public boolean haMementoRedo() { return !redoStack.isEmpty(); }

    public LibreriaMemento popUndo() { return undoStack.pop(); }
    public LibreriaMemento popRedo() { return redoStack.pop(); }

}
