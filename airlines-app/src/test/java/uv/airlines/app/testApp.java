package uv.airlines.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.SourceType;

/**
 * testApp
 */
public class testApp {

    public static void main(String[] args) {

        List<String> seatBusy = new ArrayList<>();
        Integer planeCapacity = 30;
        HashMap<String, ArrayList<Integer>> test = new HashMap<>();
        ArrayList<Integer> seatNumber = new ArrayList<>();
        Integer passengerWithoutSeat = 10;

        seatBusy.add("A2");
        seatBusy.add("B5");
        seatBusy.add("B8");

        for (int i = 1; i <= 10; i++) {
            seatNumber.add(i);
        }

        test.put("A", seatNumber);
        test.put("B", seatNumber);
        test.put("C", seatNumber);
        Integer seatFreeCount = 0;

        for (String seat : seatBusy) {
            String letter = seat.substring(0, 1);
            String number = seat.substring(1);
            ArrayList<Integer> seatFree = (ArrayList<Integer>) test.get(letter).stream()
                    .filter(s -> s != Integer.parseInt(number)).collect(Collectors.toList());

            test.put(letter, seatFree);
            seatFreeCount = seatFreeCount + seatFree.size();
            if (seatFreeCount > passengerWithoutSeat) {
                break;
            }
         }

        test.forEach((k, v) -> {
            v.stream().forEach(i -> System.out.print(k + i + " "));
            System.out.println("");
        });
    }

}