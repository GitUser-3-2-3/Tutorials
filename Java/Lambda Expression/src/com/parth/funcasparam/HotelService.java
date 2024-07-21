package com.parth.funcasparam;

import java.util.ArrayList;
import java.util.List;

public class HotelService {
    List<Hotel> hotels = new ArrayList<>();

    public HotelService() {
        hotels.add(new Hotel(2000, 2, HotelType.THREE_STAR));
        hotels.add(new Hotel(2400, 5, HotelType.THREE_STAR));
        hotels.add(new Hotel(3000, 4, HotelType.FOUR_STAR));
        hotels.add(new Hotel(2000, 2, HotelType.THREE_STAR));
        hotels.add(new Hotel(5000, 3, HotelType.FIVE_STAR));
    }
/*

    public List<Hotel> filterHotelsLessThan(int price) {
        List<Hotel> filteredHotels = new ArrayList<>();

        for (Hotel hotel : hotels) {
            if (isHotelLessThan(price, hotel)) {
                filteredHotels.add(hotel);
            }
        }
        return filteredHotels;
    }

    public List<Hotel> filterHotelsByFiveStar() {
        List<Hotel> filteredHotels = new ArrayList<>();

        for (Hotel hotel : hotels) {
            if (isHotelFiveStar(hotel)) {
                filteredHotels.add(hotel);
            }
        }
        return filteredHotels;
    }

    private boolean isHotelFiveStar(Hotel hotel) {
        return hotel.getHotelType().equals(HotelType.FIVE_STAR);
    }

    private boolean isHotelLessThan(int price, Hotel hotel) {
        return hotel.getPricePerNight() <= price;
    }
*/

    public List<Hotel> filterHotels(FilteringCondition condition) {
        List<Hotel> filteredHotels = new ArrayList<>();

        for (Hotel hotel : hotels) {
            if (condition.test(hotel)) {
                filteredHotels.add(hotel);
            }
        }
        return filteredHotels;
    }
}
