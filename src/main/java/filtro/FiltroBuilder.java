package filtro;

import implementazione.Libro.Genere;
import implementazione.Libro.StatoLettura;

public interface FiltroBuilder {
    FiltroBuilder setFiltroAutore(String autore);
    FiltroBuilder setFiltroGenere(Genere genere);
    FiltroBuilder setFiltroValutazione(int valutazione);
    FiltroBuilder setFiltroStato(StatoLettura stato);

    FiltroBuilder rimuoviFiltroAutore();
    FiltroBuilder rimuoviFiltroGenere();
    FiltroBuilder rimuoviFiltroValutazione();
    FiltroBuilder rimuoviFiltroStato();
    FiltroBuilder reset();

    Filtro build();
}
