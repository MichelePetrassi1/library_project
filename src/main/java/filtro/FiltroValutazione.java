package filtro;

import implementazione.Libro;

public class FiltroValutazione implements Filtro {
    private final int valutazione;
    public FiltroValutazione(int valutazione) {
        this.valutazione = valutazione;
    }
    @Override
    public boolean test(Libro libro) {
        return libro.getValutazione() == valutazione;
    }
}
