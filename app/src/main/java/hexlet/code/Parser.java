package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    private static ObjectMapper mapper;

    public static Map<String, Object> toMap(String path) throws IOException {
        if (path.endsWith(".json")) {
            mapper = new ObjectMapper();
        } else if (path.endsWith(".yml") || path.endsWith(".yaml")) {
            mapper = new YAMLMapper();
        } else {
            throw new IOException("wrong file extension");
        }

        File file = Paths.get(path).normalize().toAbsolutePath().toFile();
        return mapper.readValue(file, new TypeReference<>() {
        });
    }

    public static String toString(Object object) throws JsonProcessingException {
        mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object).replace("\r\n", "\n");
    }
}
