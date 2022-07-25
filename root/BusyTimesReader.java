package root;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusyTimesReader {

    private List<int[]> busyTimes = new ArrayList<>();
    public List<int[]> readBusyTimes(String fileName) throws IOException {

        String line;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName + ".txt"));

        while ((line = bufferedReader.readLine()) != null) {
            busyTimes.add(new int[]{Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1])});
        }
        return busyTimes;
    }
}
