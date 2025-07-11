import implementazione.Libro;
import ordinamento.OrdinamentoPerAutore;
import ordinamento.OrdinamentoPerTitolo;
import ordinamento.OrdinamentoPerValutazione;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TestOrdinamento {
    private Libro l1,l2,l3;

    @BeforeEach
    void init() {
        l1 = new Libro("B","A",Libro.Genere.ROMANZO,"1",3,Libro.StatoLettura.LETTO);
        l2 = new Libro("A","B",Libro.Genere.SAGGIO,"2",5,Libro.StatoLettura.DALEGGERE);
        l3 = new Libro("C","C",Libro.Genere.FANTASCIENZA,"3",1,Libro.StatoLettura.INLETTURA);
    }

    @Test
    void testOrdinaPerTitolo() {
        OrdinamentoPerTitolo s = new OrdinamentoPerTitolo();
        List<Libro> sorted = s.ordinaLibri(List.of(l1,l2,l3));
        assertEquals("A", sorted.get(0).getTitolo());
    }

    @Test
    void testOrdinaPerValutazione() {
        OrdinamentoPerValutazione s = new OrdinamentoPerValutazione();
        List<Libro> sorted = s.ordinaLibri(List.of(l1,l2,l3));
        assertEquals(5, sorted.get(0).getValutazione());
    }

    @Test
    void testOrdinaPerAutore() {
        OrdinamentoPerAutore s = new OrdinamentoPerAutore();
        List<Libro> sorted = s.ordinaLibri(List.of(l1, l2, l3));
        assertEquals("A", sorted.get(0).getAutore(), "Il primo autore dovrebbe essere A");
    }
}
