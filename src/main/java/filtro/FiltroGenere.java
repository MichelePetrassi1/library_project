package filtro;

import implementazione.Libro;
import implementazione.Libro.Genere;

public class FiltroGenere implements Filtro {
    private final Genere genere;
    public FiltroGenere(Genere genere) {
        this.genere = genere;
    }
    @Override
    public boolean test(Libro libro) {
        return libro.getGenere() != null && libro.getGenere().equals(genere);
    }
}
