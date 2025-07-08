package gui.command;

import implementazione.Libreria;
import implementazione.Libro;

public class CommandRimuoviLibro implements CommandIF {
    private final Libreria libreria;
    private final Libro libro;

    public CommandRimuoviLibro(Libreria libreria, Libro libro) {
        this.libreria = libreria;
        this.libro = libro;
    }

    @Override
    public void esegui() {
        libreria.rimuoviLibro(libro);
    }
}
