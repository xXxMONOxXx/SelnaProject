package by.mishastoma.di.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class PropReader {
    public Map<String, String> readPropFile(String fileName) throws FileNotFoundException {
        String path = ClassLoader.getSystemClassLoader().getResource(fileName).getPath();
        Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
        return lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0].trim(), arr -> arr[1].trim()));
    }
}
