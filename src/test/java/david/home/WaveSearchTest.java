package david.home;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by David on 1/18/2018.
 */
public class WaveSearchTest {

    private WaveSearch waveSearch;
    private int testBarriersCoordinate[][] = {{1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5},
            {3, 1}, {3, 2}, {3, 3}, {3, 4}, {3, 5}, {3, 6},
            {5, 1}, {5, 2}, {5, 3}, {5, 4}, {5, 5}, {6, 5}};

    @Test
    public void initializedFieldWithBarriersTest() {

        int incorrectBarriersArray[][] = {{-1, 3}, {0, 1}, {6, -8}, {0, 1}};
        int barriersCount = 0;

        try {
            new WaveSearch(4, 4, incorrectBarriersArray);
            fail("Coordinate of the barriers is not included in the size range you specified field");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void searchMinimalStepTest() {

                /*The path is locked*/
        int incorrectBarriersArray[][] = {{2, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5},
                {0, 5}};

        waveSearch = new WaveSearch(7, 7, incorrectBarriersArray);

        assertEquals(waveSearch.getPath().size(), 0);


        waveSearch = new WaveSearch(7, 7, testBarriersCoordinate);

        /* set start point tests*/
        try {
            waveSearch.searchMinimalStep(-5, 56, 6, 6);
            fail("Coordinate of the start point is not included in the size range you specified field");
        } catch (IllegalArgumentException e) {
        }

        try {
            waveSearch.searchMinimalStep(1, 0, 6, 6);
            fail("The cell you selected is busy");
        } catch (IllegalArgumentException e) {
        }

        /* set end point tests*/
        try {
            waveSearch.searchMinimalStep(0, 0, -7, 75);
            fail("Coordinate of the start point is not included in the size range you specified field");
        } catch (IllegalArgumentException e) {
        }

        try {
            waveSearch.searchMinimalStep(0, 0, 6, 5);
            fail("The cell you selected is busy");
        } catch (IllegalArgumentException e) {
        }


        waveSearch = new WaveSearch(7, 7, testBarriersCoordinate);

        waveSearch.searchMinimalStep(0, 0, 6, 6);
        assertEquals(waveSearch.getStepsCount(), 24);
    }

    @Test
    public void searchShortWayTest() {

        waveSearch = new WaveSearch(7, 7, testBarriersCoordinate);
        waveSearch.searchShortWay(0, 0, 6, 6);
        assertEquals(waveSearch.getPath().size(), 24);
    }
}
