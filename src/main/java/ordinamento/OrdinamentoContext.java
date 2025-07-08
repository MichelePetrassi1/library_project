package ordinamento;

import implementazione.Libro;

import java.util.List;

public class OrdinamentoContext {
    private OrdinamentoStrategy strategy;

    public OrdinamentoContext(OrdinamentoStrategy strategy){
        this.strategy = strategy;
    }

    //public void setStrategy(OrdinamentoStrategy strategy) {
        //this.strategy = strategy;
    //}

    public List<Libro> eseguiOrdinamento(List<Libro> libri) {
        if (strategy == null) {
            return libri; //Nel caso la strategy non fosse specificato, restituiamo la lista di default
        }
        return strategy.ordinaLibri(libri);
    }
}
