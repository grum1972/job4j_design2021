package ru.job4j.io;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private String key;
    private String value;
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This is key not exist");
        }
        return values.get(key);
    }

    private void validate(String arg) {

        if (arg.charAt(0) != '-') {
            throw new IllegalArgumentException("Illegal format arg, expected - on first position");
        }
        String str = arg.substring(1);
        if (str.indexOf('=') == -1) {
            throw new IllegalArgumentException("Illegal format arg, expected  =");
        }
        key = str.substring(0, str.indexOf('='));
        value = str.substring(str.indexOf('=') + 1);
        if (key.length() == 0) {
            throw new IllegalArgumentException("Illegal format arg, expected key");
        }
        if (value.length() == 0) {
            throw new IllegalArgumentException("Illegal format arg, expected value");
        }
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No args");
        }
        for (String arg : args) {
            validate(arg);
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}