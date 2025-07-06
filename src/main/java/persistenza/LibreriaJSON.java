package persistenza;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import implementazione.Libro;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class LibreriaJSON {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private LibreriaJSON() {  }

    private static File getFile() {
        Path primo = Path.of("library_project", "libri.json");
        if (primo.toFile().exists()) return primo.toFile();

        return Path.of("libri.json").toFile();
    }

    public static void salva(List<Libro> libri) throws IOException {
        MAPPER.writerWithDefaultPrettyPrinter().writeValue(getFile(), libri);
    }

    public static List<Libro> carica() throws IOException {
        File file = getFile();
        if (!file.exists()) return new ArrayList<>();

        CollectionType listType = MAPPER.getTypeFactory()
                .constructCollectionType(List.class, Libro.class);
        return new ArrayList<>(MAPPER.readValue(file, listType));
    }
}
