package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.JsonModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class JsonTest {
    private static String jsonFile = "json/Example.json";
    private static ClassLoader classLoader = JsonTest.class.getClassLoader();
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Парсинг JSON и сверка данных")
    void jsonTest() throws IOException {
        try (InputStream stream = classLoader.getResourceAsStream(jsonFile);
        Reader reader = new InputStreamReader(stream)) {

            JsonModel model = objectMapper.readValue(reader, JsonModel.class);

            assertThat(model.getFirstName()).isEqualTo("Naruto");
            assertThat(model.getLastName()).isEqualTo("Uzumaki");
            assertThat(model.getPhoneNumber()).isEqualTo("999-888-77-66");
            assertThat(model.getDateOfBirth()).isEqualTo("10-10-1999");
            assertThat(model.getInterests()).contains("village");
            assertThat(model.getInterests()).contains("friends");
            assertThat(model.getInterests()).contains("sport");
            assertThat(model.getAge()).isEqualTo(23);
        }
    }
}
