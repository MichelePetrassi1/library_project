package filtro;

import implementazione.Libro;
import implementazione.Libro.StatoLettura;

public class FiltroStato implements Filtro {
    private final StatoLettura stato;
    public FiltroStato(StatoLettura stato) {
        this.stato = stato;
    }
    @Override
    public boolean test(Libro libro) {
        return libro.getStatoLettura() != null && libro.getStatoLettura().equals(stato);
    }
}
