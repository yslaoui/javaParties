package finnishParties;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Map<Integer, Double>> data = Csv.read("src/finnishParties/partiesdata.tsv");
    }
}
