package gui.command;

import filtro.Filtro;
import implementazione.Libreria;

public class CommandFiltro implements CommandIF {
    private final Libreria libreria;
    private final Filtro filtro;

    public CommandFiltro(Libreria libreria, Filtro filtro) {
        this.libreria = libreria;
        this.filtro = filtro;
    }

    @Override
    public void esegui() {
        libreria.setFiltro(filtro);
    }
}
