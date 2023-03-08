package assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import ru.job4j.assertj.SimpleConvert;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList () {
        SimpleConvert simpleConvert =new SimpleConvert();
        List<String> list = simpleConvert.toList("1", "2", "3", "four");
        assertThat(list).hasSize(4)
                .contains("3", "four")
                .contains("2", Index.atIndex(1))
                .first().isEqualTo("1")
                .isNotNull();
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("key1", "key2");
        assertThat(map).isNotNull()
                .containsValues(1, 2)
                .containsKey("key2")
                .doesNotContainKey("key3")
                .hasSize(2)
                .containsEntry("key1", 1)
                .doesNotContainEntry("key1", 2);
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("V1", "v1", "V1", "v2", "v1");
        assertThat(set).isNotNull()
                .hasSize(3)
                .containsExactly("V1", "v1", "v2")
                .doesNotContain("v3")
                .containsSequence("V1", "v1", "v2")
                .endsWith("v2");

    }
}