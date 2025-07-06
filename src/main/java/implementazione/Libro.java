package implementazione;

import java.io.Serializable;

public class Libro implements Serializable {
    //Attributi richiesti per il libro:
    String titolo, autore, codiceISBN;
    int valutazione; //la valutazione sarà da 1 a 5
    public enum StatoLettura {LETTO, DALEGGERE, INLETTURA};
    StatoLettura statoLettura;
    public enum Genere {
        ROMANZO,
        GIALLO,
        FANTASY,
        FANTASCIENZA,
        STORICO,
        BIOGRAFIA,
        SAGGIO,
        DISTOPIA,
        HORROR,
        AVVENTURA,
        POESIA,
        CLASSICO,
        THRILLER,
        ALTRO;
    }
    Genere genere;

    /**
     * Generiamo i costruttori che ci sono utili, i getters e i setters
     */

    public Libro() { }

    public Libro(String titolo, String autore, Genere genere, String codiceISBN, int valutazione, StatoLettura statoLettura) {
        this(titolo, autore, genere, codiceISBN);
        if(valutazione < 1 || valutazione > 5)
            throw new IllegalArgumentException("La valutazione va da 1 a 5 stelle");
        this.valutazione = valutazione;
        this.statoLettura = statoLettura;
    }
    public Libro(String titolo, String autore, Genere genere, String codiceISBN) {
        //if(codiceISBN == null || !(isValidIsbn10(codiceISBN) || isValidIsbn13(codiceISBN)) || codiceISBN.isEmpty())
        //    throw new IllegalArgumentException("Il codice ISBN del libro non è valido");
        if(titolo == null || titolo.isEmpty())
            throw new IllegalArgumentException("Inserisci il titolo");
        if(autore == null || autore.isEmpty())
            throw new IllegalArgumentException("Inserisci l'autore");
        this.codiceISBN = codiceISBN;
        this.titolo = titolo;
        this.autore = autore;
        this.genere = genere;
        this.valutazione = 0;
        this.statoLettura = StatoLettura.DALEGGERE;
    }

    public Libro(Libro libro){
        this(libro.titolo, libro.autore, libro.genere, libro.codiceISBN, libro.valutazione, libro.statoLettura);
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public StatoLettura getStatoLettura() {
        return statoLettura;
    }

    public void setStatoLettura(StatoLettura statoLettura) {
        this.statoLettura = statoLettura;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    public String getCodiceISBN() {
        return codiceISBN;
    }

    /**
     * Implementiamo i metodi per la verifica del codice ISBN, secondo le
     * regole dell'ISBN-10 e dell'ISBN-13
     */

    private boolean isValidIsbn10(String isbn) {
        isbn = isbn.replaceAll("[\\s-]", "");
        if (isbn.length() != 10) return false;
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            char c = isbn.charAt(i);
            if (!Character.isDigit(c)) return false;
            sum += (c - '0') * (10 - i);
        }
        char last = isbn.charAt(9);
        if (last != 'X' && last != 'x' && !Character.isDigit(last)) return false;
        sum += (last == 'X' || last == 'x') ? 10 : (last - '0');
        return sum % 11 == 0;
    }

    private boolean isValidIsbn13(String isbn) {
        isbn = isbn.replaceAll("[\\s-]", "");
        if (isbn.length() != 13) return false;
        int sum = 0;
        for (int i = 0; i < 13; i++) {
            char c = isbn.charAt(i);
            if (!Character.isDigit(c)) return false;
            int digit = c - '0';
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        return sum % 10 == 0;
    }

    /**
     * Costruiamo l'hashCode, il toString e l'equals
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return codiceISBN.equals(libro.getCodiceISBN());
    }

    @Override
    public int hashCode() {
        return codiceISBN == null ? 0 : codiceISBN.hashCode();
    }

    @Override
    public String toString() {
        return "implementazione.Libro{" +
                "titolo='" + titolo + '\'' +
                ", autore='" + autore + '\'' +
                ", codiceISBN='" + codiceISBN + '\'' +
                ", valutazione=" + valutazione +
                ", statoLettura=" + statoLettura +
                ", genere=" + genere +
                '}';
    }
}
