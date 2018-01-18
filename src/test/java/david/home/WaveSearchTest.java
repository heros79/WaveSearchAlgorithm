package david.home;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by David on 1/18/2018.
 */
public class WaveSearchTest {

    int testArray[][] = new int [7][7];

    @Test
    public void searchStartPointTest() {
        for (int i = 0; i < testArray.length; i ++) {
            for (int j = 0; j < testArray[i].length; j ++) {
                testArray[i][j] = -1;
            }
        }

        try {
            new WaveSearch(testArray);
            fail("The Field haven`t start point");
        }
        catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void searchMinimalStep() {
        for (int i = 0; i < testArray.length; i ++) {
            for (int j = 0; j < testArray[i].length; j ++) {
                testArray[i][j] = -1;
            }
        }

        /*Start point*/
        testArray[0][0] = 0;

        /*End point*/
        testArray[6][6] = -3;

        /*Pain wall*/
        testArray[1][0] = testArray[1][1] = testArray[1][2] = testArray[1][3] = testArray[1][4] = testArray[1][5] =
        testArray[3][1] = testArray[3][2] = testArray[3][3] = testArray[3][4] = testArray[3][5] = testArray[3][6] =
        testArray[5][1] = testArray[5][2] = testArray[5][3] = testArray[5][4] = testArray[5][5] = testArray[6][5] = -2;

        WaveSearch waveSearch = new WaveSearch(testArray);
        waveSearch.searchMinimalStep(testArray);
        assertEquals(waveSearch.getStep(), 24);
        System.out.println("The shortest path consists of " + waveSearch.getStep() + " movements");
    }

    @Test
    public void searchShortWay() {
        for (int i = 0; i < testArray.length; i ++) {
            for (int j = 0; j < testArray[i].length; j ++) {
                testArray[i][j] = -1;
            }
        }

        /*Start point*/
        testArray[0][0] = 0;

        /*End point*/
        testArray[6][6] = -3;

        /*Pain wall*/
        testArray[1][0] = testArray[1][1] = testArray[1][2] = testArray[1][3] = testArray[1][4] = testArray[1][5] =
        testArray[3][1] = testArray[3][2] = testArray[3][3] = testArray[3][4] = testArray[3][5] = testArray[3][6] =
        testArray[5][1] = testArray[5][2] = testArray[5][3] = testArray[5][4] = testArray[5][5] = testArray[6][5] = -2;

        long startTime = System.currentTimeMillis();

        WaveSearch waveSearch = new WaveSearch(testArray);
        waveSearch.searchShortWay(testArray);
        assertEquals(waveSearch.getPath().size(), 24);

        System.out.println(String.format("Path search took %d milis", System.currentTimeMillis() - startTime));
    }
}
