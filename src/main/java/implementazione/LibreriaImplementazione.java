package implementazione;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import filtro.FiltroBuilder;
import ordinamento.*;
import persistenza.*;
import filtro.Filtro;

public class LibreriaImplementazione implements Libreria {

    private LibreriaOriginator originator;
    private OrdinamentoContext ordinamento;
    private Filtro filtro;

    public LibreriaImplementazione() {
        this.originator = new LibreriaOriginator();
        this.ordinamento = new OrdinamentoContext(null);
    }

    @Override
    public List<Libro> getLibri() {
        List<Libro> base = originator.getLibri();
        List<Libro> risultato = new ArrayList<>(base);

        if (filtro != null) {
            Predicate<Libro> criterio = libro -> filtro.test(libro);
            risultato = risultato.stream().filter(criterio).collect(Collectors.toList());
        }

        if (ordinamento != null) {
            risultato = ordinamento.eseguiOrdinamento(risultato);
        }

        return risultato;

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
    }

    @Override
    public void setOrdine(OrdinamentoContext context) { this.ordinamento = context; }

    @Override
    public void undo() { originator.undo(); }

    @Override
    public void redo() { originator.redo();}

    @Override
    public void setFiltro(Filtro filtro) {
        this.filtro = filtro;
    }

    @Override
    public void modificaLibro(Libro originale, Libro nuovo) {
        originator.modificaLibro(originale,nuovo);
    }

}
