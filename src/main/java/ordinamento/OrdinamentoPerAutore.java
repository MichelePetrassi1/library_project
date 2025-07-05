package ordinamento;

import implementazione.Libro;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrdinamentoPerAutore implements OrdinamentoStrategy {
    public OrdinamentoPerAutore(){}
    @Override
    public List<Libro> ordinaLibri(List<Libro> libri) {
        return libri.stream()
                .sorted(Comparator.comparing(Libro::getAutore, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }
}
