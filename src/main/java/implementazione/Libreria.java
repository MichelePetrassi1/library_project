package implementazione;

import filtro.Filtro;
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
     * @param context
     */
    void setOrdine(OrdinamentoContext context);

    /**
     * Ripristina l'ultimo salvataggio se possibile
     */
    void undo();

    /**
     * Ripristina l'ultima undo()
     */
    void redo();

    /**
     * #TODO
     */
    void setFiltro(Filtro filtro);
}
