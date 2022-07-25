package root;

public class Main {


    public static void main(String[] args) {
        TimeWindowFinder timeWindowFinder = new TimeWindowFinder();
        System.out.println(timeWindowFinder.findAvailableTimes("busyTimes", 60));
    }


}
