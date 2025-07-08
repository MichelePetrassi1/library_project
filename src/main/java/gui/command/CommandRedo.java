package gui.command;

import implementazione.Libreria;

public class CommandRedo implements CommandIF {
    private final Libreria libreria;

    public CommandRedo(Libreria libreria) {
        this.libreria = libreria;
    }

    @Override
    public void esegui() {
        libreria.redo();
    }
}
