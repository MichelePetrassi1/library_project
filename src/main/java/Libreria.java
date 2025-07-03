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
     * #TODO
     */
    void setOrdine(/** #TODO */);
    /**
     * #TODO
     */
    void setFiltro(/** #TODO */);
}
