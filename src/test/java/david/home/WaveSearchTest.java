package david.home;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by David on 1/18/2018.
 */
public class WaveSearchTest {

    @Test
    public void searchMinimalStepTest() {

        int testWallCoordinate [][] = {{1,0}, {1,1}, {1,2}, {1,3}, {1,4}, {1,5},
                {3,1}, {3,2}, {3,3}, {3,4}, {3,5}, {3,6},
                {5,1}, {5,2}, {5,3}, {5,4}, {5,5}, {6,5}};

        WaveSearch waveSearch = new WaveSearch(7,7, testWallCoordinate);



        waveSearch.searchMinimalStep(0,0,6,6);
        assertEquals(waveSearch.getStep(), 24);
        System.out.println("The shortest path consists of " + waveSearch.getStep() + " movements");
    }

    @Test
    public void searchShortWayTest() {

        long startTime = System.currentTimeMillis();

        int testWallCoordinate [][] = {{1,0}, {1,1}, {1,2}, {1,3}, {1,4}, {1,5},
                {3,1}, {3,2}, {3,3}, {3,4}, {3,5}, {3,6},
                {5,1}, {5,2}, {5,3}, {5,4}, {5,5}, {6,5}};

        WaveSearch waveSearch = new WaveSearch(7, 7, testWallCoordinate);
        waveSearch.searchShortWay(0, 0, 6, 6);
        assertEquals(waveSearch.getPath().size(), 24);

        System.out.println(String.format("Path search took %d milis", System.currentTimeMillis() - startTime));
    }
}
