import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class TourManager {
    private List<TourPackage> tours;

    public TourManager() {
        tours = new ArrayList<>();
    }

    public void addTour(TourPackage tour) {
        tours.add(tour);
    }

    public void displayTours() {
        for (TourPackage tour : tours) {
            System.out.println(tour.toString());
        }

    }

    public void sortToursByDays() {
        tours.sort(Comparator.comparingInt(TourPackage::getDays));
    }

    public List<TourPackage> getTours() {
        return tours;
    }
}