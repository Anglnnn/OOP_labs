import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TourManagerTest {

    @Test
    void testAddTour() {
        TourManager manager = new TourManager();
        manager.addTour(new TourPackage("Відпочинок", "Автобус", "Все включено", 7));
        manager.addTour(new TourPackage("Екскурсія", "Літак", "Половинне харчування", 5));
        assertEquals(2, manager.getTours().size());
    }


    @Test
    void testSortToursByDays() {
        TourManager manager = new TourManager();
        manager.addTour(new TourPackage("Відпочинок", "Автобус", "Все включено", 7));
        manager.addTour(new TourPackage("Екскурсія", "Літак", "Половинне харчування", 5));
        manager.addTour(new TourPackage("Здоров'я", "Поїзд", "Самообслуговування", 10));

        manager.sortToursByDays();
        assertEquals(5, manager.getTours().get(0).getDays());
        assertEquals(7, manager.getTours().get(1).getDays());
        assertEquals(10, manager.getTours().get(2).getDays());
    }
}
