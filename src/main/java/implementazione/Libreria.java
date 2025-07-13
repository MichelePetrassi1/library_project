package implementazione;

import filtro.Filtro;
import ordinamento.OrdinamentoContext;
import ordinamento.OrdinamentoStrategy;

import java.util.*;

public  interface Libreria {
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
     * Ripristina l'ultima modifica fatta da undo()
     */
    void redo();

    /**
     * Inserisce un filtro
     */
    void setFiltro(Filtro filtro);

    /**
     * Modifica il contenuto di un libro. In pratica, viene sostituito il libro
     * originale con quello nuovo
     * @param originale
     * @param nuovo
     */
    void modificaLibro(Libro originale, Libro nuovo);
}
