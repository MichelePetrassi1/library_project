package gui.command;

import implementazione.Libreria;

public class CommandUndo implements CommandIF {
    private final Libreria libreria;

    public CommandUndo(Libreria libreria) {
        this.libreria = libreria;
    }

    @Override
    public void esegui() {
        libreria.undo();
    }
}
