package implementazione;

import implementazione.Libro;
import ordinamento.OrdinamentoContext;
import ordinamento.OrdinamentoStrategy;

import java.util.*;

public  interface Libreria extends Iterable<Libro> {
    /**
     * Restituisce la lista dei libri
     */
    List<Libro> getLibri();

    /**
     * Aggiunge un libro
     * @param libro
     * @throws IllegalArgumentException se il libro è null o è già presente
     */
    void aggiungiLibro(Libro libro);

    /**
     * Rimuove un libro
     * @param libro
     * @throws IllegalArgumentException se il libro è null
     */
    void rimuoviLibro(Libro libro);

    /**
     * Imposta l'ordinamento dei libri.
     * @param strategy se la strategy è null, restituiamo la lista di default
     */
    void setOrdine(OrdinamentoStrategy strategy);

    /**
     * Ripristina l'ultimo salvataggio se possibile
     */
    void undo();

    /**
     * #TODO
     */
    void setFiltro(/** #TODO */);
}
