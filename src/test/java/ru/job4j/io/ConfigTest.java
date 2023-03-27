package ru.job4j.io;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        String ex = "name";
        assertThatThrownBy(() -> config.value(ex))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Element not found");
    }


}