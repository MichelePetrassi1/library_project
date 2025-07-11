import filtro.Filtro;
import filtro.FiltroBuilderImpl;
import implementazione.Libro;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
public class TestFiltri {
    private Libro l1,l2,l3;

    @BeforeEach
    void setUp() {
        l1 = new Libro("T1","A",Libro.Genere.ROMANZO,"1",2,Libro.StatoLettura.LETTO);
        l2 = new Libro("T2","B",Libro.Genere.SAGGIO,"2",5,Libro.StatoLettura.DALEGGERE);
        l3 = new Libro("T3","A",Libro.Genere.ROMANZO,"3",4,Libro.StatoLettura.INLETTURA);
    }

    @Test
    void testFilterByAutore() {
        FiltroBuilderImpl b = new FiltroBuilderImpl();
        b.setFiltroAutore("A");
        Filtro f = b.build();
        List<Libro> in = List.of(l1,l2,l3);
        List<Libro> out = in.stream().filter(f::test).toList();
        assertEquals(2, out.size());
    }

    @Test
    void testFilterByValutazione() {
        FiltroBuilderImpl b = new FiltroBuilderImpl();
        b.setFiltroValutazione(4);
        Filtro f = b.build();
        List<Libro> in = List.of(l1,l2,l3);
        List<Libro> out = in.stream().filter(f::test).toList();
        assertEquals(1, out.size());
    }

    @Test
    void testFilterByGenere() {
        FiltroBuilderImpl b = new FiltroBuilderImpl();
        b.setFiltroGenere(Libro.Genere.ROMANZO);
        Filtro f = b.build();
        List<Libro> in = List.of(l1,l2,l3);
        List<Libro> out = in.stream().filter(f::test).toList();
        assertEquals(2, out.size(), "Dovrebbero passare solo i libri di genere ROMANZO");
    }

    @Test
    void testFilterByStatoLettura() {
        FiltroBuilderImpl b = new FiltroBuilderImpl();
        b.setFiltroStato(Libro.StatoLettura.INLETTURA);
        Filtro f = b.build();
        List<Libro> in = List.of(l1,l2,l3);
        List<Libro> out = in.stream().filter(f::test).toList();
        assertEquals(1, out.size(), "Dovrebbe passare solo il libro in lettura");
    }
}
