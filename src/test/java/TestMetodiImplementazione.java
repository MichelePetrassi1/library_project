import implementazione.LibreriaImplementazione;
import implementazione.Libro;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
public class TestMetodiImplementazione {
    private LibreriaImplementazione libreria;
    private Libro l1, l2;

    @BeforeEach
    void setUp() throws Exception{
        String userDir = System.getProperty("user.dir");
        java.nio.file.Path json = java.nio.file.Path.of(userDir, "libri.json");
        java.nio.file.Files.deleteIfExists(json);
        libreria = new LibreriaImplementazione();
        l1 = new Libro("Titolo A", "Autore A", Libro.Genere.ROMANZO, "ISBN1", 3, Libro.StatoLettura.LETTO);
        l2 = new Libro("Titolo B", "Autore B", Libro.Genere.SAGGIO,  "ISBN2", 5, Libro.StatoLettura.INLETTURA);
    }

    @Test
    void testAddAndGet() {
        libreria.aggiungiLibro(l1);
        List<Libro> libri = libreria.getLibri();
        assertTrue(libri.contains(l1));
    }

    @Test
    void testRemove() {
        libreria.aggiungiLibro(l1);
        libreria.rimuoviLibro(l1);
        assertFalse(libreria.getLibri().contains(l1));
    }

    @Test
    void testUndoRedo() {
        libreria.aggiungiLibro(l1);
        libreria.aggiungiLibro(l2);
        assertEquals(2, libreria.getLibri().size());

        libreria.undo();
        List<Libro> afterUndo = libreria.getLibri();
        assertEquals(1, afterUndo.size());
        assertTrue(afterUndo.contains(l1));

        libreria.redo();
        List<Libro> afterRedo = libreria.getLibri();
        assertEquals(2, afterRedo.size());
        assertTrue(afterRedo.contains(l2));
    }

    @Test
    void testPersistence() throws Exception {
        Path json = Path.of(System.getProperty("user.dir"), "libri.json");
        Files.deleteIfExists(json);

        libreria.aggiungiLibro(l1);

        LibreriaImplementazione lib2 = new LibreriaImplementazione();
        assertTrue(lib2.getLibri().contains(l1));
    }

    /*
    * Piccola nota: il metodo per il testing di modificaLibro() è più lungo,
    * ossia ha più verifiche per dei motivi relegati all'undo/redo
    * (si controlli il file Word, parte dei task più difficili, per capire meglio)
     */
    @Test
    void testModificaLibro() {
        libreria.aggiungiLibro(l1);
        assertTrue(libreria.getLibri().contains(l1));

        Libro aggiornato = new Libro(
                "Titolo Modificato",
                l1.getAutore(),
                l1.getGenere(),
                l1.getCodiceISBN(),
                l1.getValutazione(),
                l1.getStatoLettura()
        );

        libreria.modificaLibro(l1, aggiornato);

        // verifichiamo che ne il nuovo ne il vecchio libro siano stati salvati
        List<Libro> dopoModifica = libreria.getLibri();
        assertEquals(1, dopoModifica.size(), "Dovrebbe esserci un solo libro dopo la modifica");
        assertEquals("Titolo Modificato", dopoModifica.get(0).getTitolo(), "Il titolo dovrebbe essere aggiornato");

        // verifichiamo l'undo
        libreria.undo();
        List<Libro> dopoUndo = libreria.getLibri();
        assertEquals(1, dopoUndo.size(), "Dopo undo dovrebbe esserci un solo libro");
        assertEquals("Titolo A", dopoUndo.get(0).getTitolo(), "Dopo undo il titolo dovrebbe tornare all'originale");

        // verifichiamo la redo
        libreria.redo();
        List<Libro> dopoRedo = libreria.getLibri();
        assertEquals(1, dopoRedo.size(), "Dopo redo dovrebbe esserci un solo libro");
        assertEquals("Titolo Modificato", dopoRedo.get(0).getTitolo(), "Dopo redo il titolo dovrebbe essere nuovamente aggiornato");
    }
}
