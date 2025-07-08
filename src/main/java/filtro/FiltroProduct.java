package filtro;

import implementazione.Libro;
import java.util.*;

public class FiltroProduct implements Filtro{
    private final List<Filtro> criteri;

    public FiltroProduct() {
        this.criteri = new ArrayList<>();
    }

    public void aggiungiFiltro(Filtro filtro) {
        if (filtro != null) criteri.add(filtro);
    }

    @Override
    public boolean test(Libro libro) {
        for (Filtro filtro : criteri) {
            if (!filtro.test(libro)) {
                return false;
            }
        }
        return true;
    }
}
