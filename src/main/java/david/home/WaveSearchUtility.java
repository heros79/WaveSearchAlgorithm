package david.home;

/**
 * The class (@code WaveSearchUtility) contains auxiliary methods for class (@code WaveSearch)
 *
 * Created by (@author David Karchikyan) on 1/18/2018.
 *
 * @since JDK1.6
 */
public class WaveSearchUtility {

    private static int field [][];

    protected static  int[][] fillField(int width, int height) {
        field = new int[width][height];
        for (int i = 0; i < field.length; i ++) {
            for (int j = 0; j < field[0].length; j ++) {
                field[i][j] = -1;
            }
        }

        return field;
    }

    protected static int[][] wallArrangement (int wallPlacing [][]) {

        for (int i = 0; i < wallPlacing.length; i++) {

            if (wallPlacing[i][0] >= field.length || wallPlacing[i][1] >= field[0].length ||
                    wallPlacing[i][0] < 0 || wallPlacing[i][1] < 0) {
                throw new IllegalArgumentException("Coordinate of the walls is not included in the size range you specified field");
            }

                field[wallPlacing[i][0]][wallPlacing[i][1]] = -2;
        }

        return field;
    }

    protected static int[][] startPointArrangement (int startPointXCoordinate, int startPointYCoordinate) {

        if (startPointXCoordinate >= field.length || startPointXCoordinate < 0 ||
                startPointYCoordinate >= field[0].length || startPointYCoordinate < 0) {
            throw new IllegalArgumentException("Coordinate of the start point is not included in the size range you specified field");
        }

        if (field[startPointXCoordinate][startPointYCoordinate] != -1) {
            throw new IllegalArgumentException("The cell you selected is busy");
        }

        field[startPointXCoordinate][startPointYCoordinate] = 0;

        return field;
    }

    protected static int[][] endPointArrangement(int endPointXCoordinate, int endPointYCoordinate) {

        if (endPointXCoordinate >= field.length || endPointXCoordinate < 0 ||
                endPointYCoordinate >= field[0].length || endPointYCoordinate < 0) {
            throw new IllegalArgumentException("Coordinate of the end point is not included in the size range you specified field");
        }

        if (field[endPointXCoordinate][endPointYCoordinate] != -1) {
            throw new IllegalArgumentException("The cell you selected is busy");
        }

        field[endPointXCoordinate][endPointYCoordinate] = -3;

        return field;
    }

    public static int[][] getField() {
        return field;
    }
}
