package javaParties;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Map<Integer, Double>> data = Csv.read("src/main/java/javaParties/partiesdata.tsv");
        data.keySet().stream().forEach(s-> System.out.println(s));
        data.values().stream().forEach(s-> System.out.println(s));
    }
}
