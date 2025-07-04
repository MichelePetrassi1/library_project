package persistenza;

import implementazione.Libro;
import ordinamento.OrdinamentoContext;

import java.util.List;

/**
 * Si implementerà la persistenza dei dati tramite l'suo di file Json,
 * con operazioni CRUD e metodi per il recupero.
 * Nota: la scelta per la implementazione della persistenza potrebbe cambiare in futuro.
 */

public interface LibreriaPersistente {
    /**
     * #TODO
     */
    void salvaLibro(Libro libro);

    /**
     * #TODO
     */
    void rimuoviLibro(Libro libro);

    /**
     * #TODO
     *
     * @param strategy #TODO
     */
    List<Libro> caricaLibreria(OrdinamentoContext strategy);
}
