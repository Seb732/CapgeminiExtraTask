package root;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TimeWindowFinder {

    private BusyTimesReader busyTimesReader = new BusyTimesReader();

    private List<int[]> busyTimes;

    private List<int[]> availableTimes = new ArrayList<>();

    private int[] dailyTimeInMinutes = new int[1440];

    private int sum;

    private LocalTime startTime = LocalTime.MIDNIGHT;

    private StringBuilder stringBuilder = new StringBuilder();


    public final String findAvailableTimes(String filename, int durationMins) {

        try {
            busyTimes = busyTimesReader.readBusyTimes(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Arrays.fill(dailyTimeInMinutes, 1);


        for (int[] busyTime : busyTimes) {
            Arrays.fill(dailyTimeInMinutes, busyTime[0], busyTime[1], 0);
        }


        for (int i = 1; i < dailyTimeInMinutes.length - 1; i++) {
            if (dailyTimeInMinutes[i - 1] == 1) {
                sum += 1;
                if (sum == durationMins) {
                    availableTimes.add(new int[]{i - sum, i});
                }
            }
            else {
                sum = 0;
            }
        }
        if (availableTimes.size() == 0) {
            return "No available periods";
        }
        stringBuilder.append(availableTimes.stream().map(period -> startTime.plusMinutes(period[0]) + "-"
                + startTime.plusMinutes(period[1]) + "\n").collect(Collectors.joining()));

        return """
                
                Earliest available period: %s - %s
                
                All available periods:
                %s
                
                """
        .formatted(startTime.plusMinutes(availableTimes.get(0)[0]),
                startTime.plusMinutes(availableTimes.get(0)[1]), stringBuilder.toString());

    }

}
