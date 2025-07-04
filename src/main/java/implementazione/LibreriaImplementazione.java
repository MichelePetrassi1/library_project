package implementazione;

import ordinamento.OrdinamentoContext;

import java.util.Iterator;
import java.util.List;

public class LibreriaImplementazione implements Libreria {

    private OrdinamentoContext strategy;

    @Override
    public List<Libro> getLibri() {
        return null;
    }

    @Override
    public void aggiungiLibro(Libro libro) {

    }

    @Override
    public void rimuoviLibro(Libro libro) {

    }

    @Override
    public void setOrdine(OrdinamentoContext strategy) {
        this.strategy = strategy;

    }

    @Override
    public void setFiltro() {

    }

    @Override
    public Iterator<Libro> iterator() {
        return getLibri().iterator();
    }
}
