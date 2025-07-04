package ordinamento;

import implementazione.Libro;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrdinamentoPerValutazione implements OrdinamentoStrategy {
    @Override
    public List<Libro> ordinaLibri(List<Libro> libri) {
        return libri.stream()
                .sorted(Comparator.comparingInt(Libro::getValutazione).reversed())
                .collect(Collectors.toList());
    }
}
