package gui.command;

import implementazione.Libreria;
import implementazione.Libro;

public class CommandModificaLibro implements CommandIF {
    private final Libreria libreria;
    private final Libro originale, nuovo;

    public CommandModificaLibro(Libreria libreria, Libro originale, Libro nuovo) {
        this.libreria = libreria;
        this.originale = originale;
        this.nuovo  = nuovo;
    }

    @Override
    public void esegui() {
        libreria.modificaLibro(originale, nuovo);
    }
}
