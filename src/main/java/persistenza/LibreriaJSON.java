package persistenza;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import implementazione.Libro;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class LibreriaJSON {
    private static final String FILE_PATH = "libri.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void salva(List<Libro> libri) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), libri);
    }

    public static List<Libro> carica() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();

        CollectionType listType = mapper.getTypeFactory()
                .constructCollectionType(List.class, Libro.class);
        return new ArrayList<>(mapper.readValue(file, listType));
    }
}
