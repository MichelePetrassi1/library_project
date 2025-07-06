package filtro;

import implementazione.Libro;

public class FiltroAutore implements Filtro{
    private final String autore;
    public FiltroAutore(String autore) {
        this.autore = autore;
    }
    @Override
    public boolean test(Libro libro) {
        return libro.getAutore() != null && libro.getAutore().equalsIgnoreCase(autore);
    }
}
