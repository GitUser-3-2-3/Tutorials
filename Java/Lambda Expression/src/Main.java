import com.parth.funcasparam.Hotel;
import com.parth.funcasparam.HotelService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println();

        HotelService hotelService = new HotelService();

        List<Hotel> hotels1 = hotelService.filterHotels(
            hotel -> hotel.getPricePerNight() <= 2000
        );
        System.out.println("List of hotels less than 2k: " + hotels1);
    }
}
