import implementazione.Libro;
import org.junit.jupiter.api.*;
import persistenza.LibreriaJSON;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
public class TestPersistenza {
    @BeforeEach
    void clean() throws Exception {
        Path p = Path.of(System.getProperty("user.dir"), "libri.json");
        Files.deleteIfExists(p);
    }

    @Test
    void testSalvaCarica() throws Exception {
        Libro l = new Libro("T1","A1",Libro.Genere.FANTASCIENZA,"X1",4,Libro.StatoLettura.DALEGGERE);
        List<Libro> list = List.of(l);
        LibreriaJSON.salva(list);
        List<Libro> loaded = LibreriaJSON.carica();
        assertEquals(1, loaded.size());
        assertEquals(l.getTitolo(), loaded.get(0).getTitolo());
    }
}
