package ru.job4j;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenAppProperties() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(nullValue()));
    }

    @Test
    public void whenPairWithCommentAndEmptyLines() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenErrorTemplateThenMustBeException() {
        String path = "./data/pair_with_error_template.properties";
        Config config = new Config(path);
        config.load();
    }
}