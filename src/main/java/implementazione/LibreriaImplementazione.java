package implementazione;

import java.util.Iterator;
import java.util.List;
import ordinamento.*;
import persistenza.*;

public class LibreriaImplementazione implements Libreria {

    private LibreriaOriginator originator;
    private OrdinamentoContext ordinamento;

    public LibreriaImplementazione() {
        this.originator = new LibreriaOriginator();
        this.ordinamento = new OrdinamentoContext(null);
    }
    public LibreriaImplementazione(LibreriaOriginator originator) {
        this.originator = originator;
        this.ordinamento = new OrdinamentoContext(null);
    }

    @Override
    public List<Libro> getLibri() {
        List<Libro> libri = originator.getLibri();
        return ordinamento.eseguiOrdinamento(libri);

    }

    @Override
    public void aggiungiLibro(Libro libro) {
        if (libro == null)
            throw new IllegalArgumentException("Il libro non può essere null.");
        // Controlliamo che il libro non sia già presente
        boolean esiste = originator.getLibri().stream()
                .anyMatch(l -> l.getCodiceISBN().equals(libro.getCodiceISBN()));

        if (esiste)
            throw new IllegalArgumentException("Libro già presente.");

        originator.aggiungiLibro(libro);
        originator.salvaSuFile();
    }

    @Override
    public void rimuoviLibro(Libro libro) {
        if (libro == null)
            throw new IllegalArgumentException("Il libro non può essere null.");
        //Assicuriamoci che il libro sia presente
        boolean presente = originator.getLibri().stream()
                .anyMatch(l -> l.getCodiceISBN().equals(libro.getCodiceISBN()));

        if (!presente)
            throw new IllegalArgumentException("Libro non trovato.");

        originator.rimuoviLibro(libro);
        originator.salvaSuFile();
    }

    @Override
    public void setOrdine(OrdinamentoStrategy strategy) {
        ordinamento.setStrategy(strategy);
    }

    @Override
    public void undo() {
        originator.undo();
    }

    @Override
    public void setFiltro() {
    //#TODO
    }

    @Override
    public Iterator<Libro> iterator() {
        return getLibri().iterator();
    }
}
