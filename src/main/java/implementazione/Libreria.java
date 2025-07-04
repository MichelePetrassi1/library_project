package implementazione;

import implementazione.Libro;
import ordinamento.OrdinamentoContext;

import java.util.*;

interface Libreria extends Iterable<Libro> {
    /**
     * #TODO
     */
    List<Libro> getLibri();

    /**
     * #TODO
     */
    void aggiungiLibro(Libro libro);

    /**
     * #TODO
     */
    void rimuoviLibro(Libro libro);

    /**
     * Imposta l'ordinamento dei libri.
     * @param strategy se la strategy è null, restituiamo la lista di default
     */
    void setOrdine(OrdinamentoContext strategy);

    /**
     * #TODO
     */
    void setFiltro(/** #TODO */);
}
