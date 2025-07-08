package gui.command;

import implementazione.Libreria;
import ordinamento.OrdinamentoContext;
import ordinamento.OrdinamentoStrategy;

public class CommandOrdinamento implements CommandIF {
    private final Libreria libreria;
    private final OrdinamentoStrategy strategy;

    public CommandOrdinamento(Libreria libreria, OrdinamentoStrategy strategy) {
        this.libreria = libreria;
        this.strategy = strategy;
    }

    @Override
    public void esegui() {
        OrdinamentoContext context = new OrdinamentoContext(strategy);
        libreria.setOrdine(context);
    }
}
