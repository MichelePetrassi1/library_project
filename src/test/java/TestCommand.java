import filtro.Filtro;
import filtro.FiltroBuilderImpl;
import gui.command.*;
import implementazione.LibreriaImplementazione;
import implementazione.Libro;
import ordinamento.OrdinamentoPerTitolo;
import ordinamento.OrdinamentoPerValutazione;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCommand {
    private LibreriaImplementazione libreria;
    private CommandInvoker invoker;
    private Libro l1, l2;

    @BeforeEach
    void setUp() throws Exception {
        String userDir = System.getProperty("user.dir");
        java.nio.file.Path json = java.nio.file.Path.of(userDir, "libri.json");
        java.nio.file.Files.deleteIfExists(json);

        libreria = new LibreriaImplementazione();
        invoker = new CommandInvoker();
        l1 = new Libro("T1","A",Libro.Genere.ROMANZO,"1",3,Libro.StatoLettura.LETTO);
        l2 = new Libro("T2","B",Libro.Genere.SAGGIO,"2",5,Libro.StatoLettura.INLETTURA);
    }

    @Test
    void testCommandAggiungiLibro() {
        CommandAggiungiLibro cmd = new CommandAggiungiLibro(libreria, l1);
        cmd.esegui();
        List<Libro> lista = libreria.getLibri();
        assertEquals(1, lista.size());
        assertTrue(lista.contains(l1));
        CommandUndo undo = new CommandUndo(libreria);
        undo.esegui();
        assertTrue(libreria.getLibri().isEmpty());
        CommandRedo redo = new CommandRedo(libreria);
        redo.esegui();
        assertTrue(libreria.getLibri().contains(l1));
    }

    @Test
    void testCommandRimuoviLibro() {
        libreria.aggiungiLibro(l1);
        CommandRimuoviLibro cmd = new CommandRimuoviLibro(libreria, l1);
        cmd.esegui();
        assertFalse(libreria.getLibri().contains(l1));
        // Undo
        CommandUndo undo = new CommandUndo(libreria);
        undo.esegui();
        assertTrue(libreria.getLibri().contains(l1));
    }

    @Test
    void testCommandModificaLibro() {
        libreria.aggiungiLibro(l1);
        Libro updated = new Libro("T1mod","A",Libro.Genere.ROMANZO,"1",4,Libro.StatoLettura.LETTO);
        CommandModificaLibro cmd = new CommandModificaLibro(libreria, l1, updated);
        cmd.esegui();
        List<Libro> lista = libreria.getLibri();
        assertEquals(1, lista.size());
        assertEquals("T1mod", lista.get(0).getTitolo());
        CommandUndo undo = new CommandUndo(libreria);
        undo.esegui();
        List<Libro> undoList = libreria.getLibri();
        assertEquals("T1", undoList.get(0).getTitolo());
    }

    @Test
    void testCommandFiltro() {
        libreria.aggiungiLibro(l1);
        libreria.aggiungiLibro(l2);
        Filtro filtro = new FiltroBuilderImpl().setFiltroAutore("A").build();
        CommandFiltro cmd = new CommandFiltro(libreria, filtro);
        cmd.esegui();
        List<Libro> lista = libreria.getLibri();
        assertEquals(1, lista.size());
        assertEquals("A", lista.get(0).getAutore());
    }

    @Test
    void testCommandOrdinamento() {
        libreria.aggiungiLibro(l1);
        libreria.aggiungiLibro(l2);
        CommandOrdinamento cmdTitolo = new CommandOrdinamento(libreria, new OrdinamentoPerTitolo());
        cmdTitolo.esegui();
        List<Libro> byTitle = libreria.getLibri();
        assertEquals("T1", byTitle.get(0).getTitolo());
        CommandOrdinamento cmdVal = new CommandOrdinamento(libreria, new OrdinamentoPerValutazione());
        cmdVal.esegui();
        List<Libro> byVal = libreria.getLibri();
        assertEquals(5, byVal.get(0).getValutazione());
    }

    @Test
    void testCommandUndoRedo() {
        invoker.eseguiComando(new CommandAggiungiLibro(libreria, l1));
        invoker.eseguiComando(new CommandRimuoviLibro(libreria, l1));
        assertTrue(libreria.getLibri().isEmpty());
        invoker.eseguiComando(new CommandUndo(libreria));
        assertTrue(libreria.getLibri().contains(l1));
        invoker.eseguiComando(new CommandRedo(libreria));
        assertTrue(libreria.getLibri().isEmpty());
    }
}
