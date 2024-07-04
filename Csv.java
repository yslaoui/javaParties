package finnishParties;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Csv {
    public static Map<String, Map<Integer, Double>> read(String file) {
        ArrayList<Integer> years = new ArrayList<>();
        Map<String, Map<Integer, Double>> data = new HashMap<>();

        try {
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String row = buffer.readLine();
            // Getting the years
            String[] yearsArray = row.split("\t");
            for (int i=1; i<yearsArray.length; i++) {
                years.add( Integer.parseInt(yearsArray[i]));
            }
            row = buffer.readLine(); // Moving to the second row
            // Getting voting data for each party by year
            while (row != null) {
                String[] votingArray= row.split("\t");
                Map<Integer, Double> panelVotes = new HashMap<>();
                for (int i=0; i<years.size(); i++) {
                    try { // Some voting values are - and must be skipped
                        panelVotes.putIfAbsent(years.get(i), Double.parseDouble(votingArray[i+1]));
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid vote value " + votingArray[i+1]);
                    }
                }
                data.putIfAbsent(votingArray[0], panelVotes);
                row = buffer.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        } catch (NumberFormatException e) {
            throw new RuntimeException();
        }
        return data;
    }
}
