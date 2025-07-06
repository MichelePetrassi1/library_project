package filtro;

import implementazione.Libro.Genere;
import implementazione.Libro.StatoLettura;

//Prova
public class FiltroDirector {
    private final FiltroBuilder builder;

    public FiltroDirector(FiltroBuilder builder) {
        this.builder = builder;
    }

    /**
     * Costruisce un filtro combinato con autore e valutazione
     */
    public Filtro costruisciFiltroAutoreValutazione(String autore, int valutazioneMinima) {
        builder.reset();
        if (autore != null && !autore.isEmpty()) {
            builder.setFiltroAutore(autore);
        }
        builder.setFiltroValutazione(valutazioneMinima);
        return builder.build();
    }

    /**
     * Costruisce un filtro combinato con genere e stato di lettura
     */
    public Filtro costruisciFiltroGenereStato(Genere genere, StatoLettura stato) {
        builder.reset();
        if (genere != null) {
            builder.setFiltroGenere(genere);
        }
        if (stato != null) {
            builder.setFiltroStato(stato);
        }
        return builder.build();
    }

    // Puoi aggiungere altri metodi per combinazioni diverse
}
