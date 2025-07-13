package filtro;

import implementazione.Libro.Genere;
import implementazione.Libro.StatoLettura;
import java.util.*;

public class FiltroBuilderImpl implements FiltroBuilder {
    private FiltroAutore filtroAutore;
    private FiltroGenere filtroGenere;
    private FiltroValutazione filtroValutazione;
    private FiltroStato filtroStato;

    public FiltroBuilderImpl(){
        setFiltroAutore(null);
        setFiltroValutazione(0);
        setFiltroGenere(null);
        setFiltroStato(null);
    }

    @Override
    public FiltroBuilder setFiltroAutore(String autore) {
        this.filtroAutore = (autore == null || autore.isEmpty()) ? null : new FiltroAutore(autore);
        return this;
    }

    @Override
    public FiltroBuilder setFiltroGenere(Genere genere) {
        this.filtroGenere = genere == null ? null : new FiltroGenere(genere);
        return this;
    }

    @Override
    public FiltroBuilder setFiltroValutazione(int valutazione) {
        this.filtroValutazione = (valutazione <= 0) ? null : new FiltroValutazione(valutazione);
        return this;
    }

    @Override
    public FiltroBuilder setFiltroStato(StatoLettura stato) {
        this.filtroStato = stato == null ? null : new FiltroStato(stato);
        return this;
    }

    @Override
    public FiltroBuilder rimuoviFiltroAutore() {
        this.filtroAutore = null;
        return this;
    }

    @Override
    public FiltroBuilder rimuoviFiltroGenere() {
        this.filtroGenere = null;
        return this;
    }

    @Override
    public FiltroBuilder rimuoviFiltroValutazione() {
        this.filtroValutazione = null;
        return this;
    }

    @Override
    public FiltroBuilder rimuoviFiltroStato() {
        this.filtroStato = null;
        return this;
    }

    @Override
    public FiltroBuilder reset() {
        filtroAutore = null;
        filtroGenere = null;
        filtroValutazione = null;
        filtroStato = null;
        return this;
    }


    @Override
    public Filtro build() {
        FiltroProduct product = new FiltroProduct();

        if (filtroAutore != null) product.aggiungiFiltro(filtroAutore);
        if (filtroGenere != null) product.aggiungiFiltro(filtroGenere);
        if (filtroValutazione != null) product.aggiungiFiltro(filtroValutazione);
        if (filtroStato != null) product.aggiungiFiltro(filtroStato);

        return product;
    }
}
