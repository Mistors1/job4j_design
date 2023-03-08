package assertj;

import org.junit.jupiter.api.Test;
import ru.job4j.assertj.NameLoad;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkNoWord() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void checkWordWithoutEquals() {
        NameLoad nameLoad = new NameLoad();
        String example = "HGdfjw";
        assertThatThrownBy(() -> nameLoad.parse(example))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain the symbol \"=\"")
                .hasMessageContaining(example);
    }

    @Test
    void checkStartsWitEquals() {
        NameLoad nameLoad = new NameLoad();
        String example = "= yauag";
        assertThatThrownBy(() -> nameLoad.parse(example))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(example)
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void checkEndWithEquals() {
        NameLoad nameLoad = new NameLoad();
        String example = "jjfg =";
        assertThatThrownBy(() -> nameLoad.parse(example))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(example)
                .hasMessageContaining(" does not contain a value");
    }
}