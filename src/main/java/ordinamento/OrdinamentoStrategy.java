package ordinamento;

import implementazione.Libro;
import java.util.List;

public interface OrdinamentoStrategy {
    List<Libro> ordinaLibri(List<Libro> libri);
}
