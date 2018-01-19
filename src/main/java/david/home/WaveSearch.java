package david.home;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The class (@code WaveSearch) contains two methods, to find a short path from the
 * starting point to the end, bypassing the effects.
 * <p>
 * <p>This class has an auxiliary class (@WaveSearchUtility code), for filling the field
 * with obstacles and for indicating the start and end points of the path.
 * <p>
 * Created by (@author David Karchikyan) on 1/18/2018.
 *
 * @since JDK1.6
 */

public class WaveSearch {

    private List<WaveSearch.Point> pointsList;
    private List<WaveSearch.Point> path;
    private int step;


    /**
     * The constructor creates an object with a field filled with obstacles
     *
     * @param fieldWidth  field width size
     * @param fieldHeight field height size
     * @param wallPlacing two-dimensional array with the coordinates of the walls,
     *                    an example int testWallCoordinate [][] = {{1,0}, {1,1}, {1,2}, {1,3}, {1,4}, {1,5}};
     */
    public WaveSearch(int fieldWidth, int fieldHeight, int wallPlacing[][]) {
        pointsList = new ArrayList<WaveSearch.Point>();
        path = new ArrayList<WaveSearch.Point>();
        WaveSearchUtility.fillField(fieldWidth, fieldHeight);
        WaveSearchUtility.wallArrangement(wallPlacing);
    }

    /**
     * The method of finding the minimum number of moves, from the initial to the end point
     *
     * @param startPointXCoordinate coordinate of the width of the starting point
     * @param startPointYCoordinate the height coordinate of the starting point
     * @param endPointXCoordinate   coordinate of the width of the end point
     * @param endPointYCoordinate   coordinate of the height of the end point
     */
    public void searchMinimalStep(int startPointXCoordinate, int startPointYCoordinate, int endPointXCoordinate, int endPointYCoordinate) {

        WaveSearchUtility.startPointArrangement(startPointXCoordinate, startPointYCoordinate);
        WaveSearchUtility.endPointArrangement(endPointXCoordinate, endPointYCoordinate);
        pointsList.add(new Point(startPointXCoordinate, startPointYCoordinate));
        int i = 0;

        while (true) {
            if (pointsList.get(i).x < WaveSearchUtility.getField().length - 1
                    && WaveSearchUtility.getField()[pointsList.get(i).x + 1][pointsList.get(i).y] == -1) {
                WaveSearchUtility.getField()[pointsList.get(i).x + 1][pointsList.get(i).y] = WaveSearchUtility.getField()[pointsList.get(i).x][pointsList.get(i).y] + 1;
                pointsList.add(new Point(pointsList.get(i).x + 1, pointsList.get(i).y));
            } else if (pointsList.get(i).x < WaveSearchUtility.getField().length - 1
                    && WaveSearchUtility.getField()[pointsList.get(i).x + 1][pointsList.get(i).y] == -3) {
                path.add(new Point(pointsList.get(i).x + 1, pointsList.get(i).y));
                path.add(new Point(pointsList.get(i).x, pointsList.get(i).y));
                step = WaveSearchUtility.getField()[pointsList.get(i).x][pointsList.get(i).y] + 1;
                break;
            }
            if (pointsList.get(i).x > 0
                    && WaveSearchUtility.getField()[pointsList.get(i).x - 1][pointsList.get(i).y] == -1) {
                WaveSearchUtility.getField()[pointsList.get(i).x - 1][pointsList.get(i).y] = WaveSearchUtility.getField()[pointsList.get(i).x][pointsList.get(i).y] + 1;
                pointsList.add(new Point(pointsList.get(i).x - 1, pointsList.get(i).y));
            } else if (pointsList.get(i).x > 0
                    && WaveSearchUtility.getField()[pointsList.get(i).x - 1][pointsList.get(i).y] == -3) {
                path.add(new Point(pointsList.get(i).x - 1, pointsList.get(i).y));
                path.add(new Point(pointsList.get(i).x, pointsList.get(i).y));
                step = WaveSearchUtility.getField()[pointsList.get(i).x][pointsList.get(i).y] + 1;
                break;
            }
            if (pointsList.get(i).y < WaveSearchUtility.getField()[0].length - 1
                    && WaveSearchUtility.getField()[pointsList.get(i).x][pointsList.get(i).y + 1] == -1) {
                WaveSearchUtility.getField()[pointsList.get(i).x][pointsList.get(i).y + 1] = WaveSearchUtility.getField()[pointsList.get(i).x][pointsList.get(i).y] + 1;
                pointsList.add(new Point(pointsList.get(i).x, pointsList.get(i).y + 1));
            } else if (pointsList.get(i).y < WaveSearchUtility.getField()[0].length - 1
                    && WaveSearchUtility.getField()[pointsList.get(i).x][pointsList.get(i).y + 1] == -3) {
                path.add(new Point(pointsList.get(i).x, pointsList.get(i).y + 1));
                path.add(new Point(pointsList.get(i).x, pointsList.get(i).y));
                step = WaveSearchUtility.getField()[pointsList.get(i).x][pointsList.get(i).y] + 1;
                break;
            }
            if (pointsList.get(i).y > 0
                    && WaveSearchUtility.getField()[pointsList.get(i).x][pointsList.get(i).y - 1] == -1) {
                WaveSearchUtility.getField()[pointsList.get(i).x][pointsList.get(i).y - 1] = WaveSearchUtility.getField()[pointsList.get(i).x][pointsList.get(i).y] + 1;
                pointsList.add(new Point(pointsList.get(i).x, pointsList.get(i).y - 1));
            } else if (pointsList.get(i).y > 0
                    && WaveSearchUtility.getField()[pointsList.get(i).x][pointsList.get(i).y - 1] == -3) {
                path.add(new Point(pointsList.get(i).x, pointsList.get(i).y - 1));
                path.add(new Point(pointsList.get(i).x, pointsList.get(i).y));
                step = WaveSearchUtility.getField()[pointsList.get(i).x][pointsList.get(i).y] + 1;
                break;
            }

            i++;

        }
    }

    /**
     * The method of finding the shortest path, from the initial to the final point,
     * with the derivation of the coordinates of displacements
     *
     * @param startPointXCoordinate coordinate of the width of the starting point
     * @param startPointYCoordinate the height coordinate of the starting point
     * @param endPointXCoordinate   coordinate of the width of the end point
     * @param endPointYCoordinate   coordinate of the height of the end point
     * */
    public void searchShortWay(int startPointXCoordinate, int startPointYCoordinate, int endPointXCoordinate, int endPointYCoordinate) {

        searchMinimalStep(startPointXCoordinate, startPointYCoordinate, endPointXCoordinate, endPointYCoordinate);

        int i = 1;

        while (true) {

            if (path.get(i).x < WaveSearchUtility.getField().length - 1 && WaveSearchUtility.getField()[path.get(i).x + 1][path.get(i).y] == 0) {
                break;
            } else if (path.get(i).x < WaveSearchUtility.getField().length - 1 && WaveSearchUtility.getField()[path.get(i).x][path.get(i).y] == WaveSearchUtility.getField()[path.get(i).x + 1][path.get(i).y] + 1) {
                path.add(new Point(path.get(i).x + 1, path.get(i).y));
                i++;
                continue;
            }
            if (path.get(i).x > 0 && WaveSearchUtility.getField()[path.get(i).x - 1][path.get(i).y] == 0) {
                break;
            } else if (path.get(i).x > 0 && WaveSearchUtility.getField()[path.get(i).x][path.get(i).y] == WaveSearchUtility.getField()[path.get(i).x - 1][path.get(i).y] + 1) {
                path.add(new Point(path.get(i).x - 1, path.get(i).y));
                i++;
                continue;
            }
            if (path.get(i).y < WaveSearchUtility.getField()[0].length - 1 && WaveSearchUtility.getField()[path.get(i).x][path.get(i).y + 1] == 0) {
                break;
            } else if (path.get(i).y < WaveSearchUtility.getField()[0].length - 1 && WaveSearchUtility.getField()[path.get(i).x][path.get(i).y] == WaveSearchUtility.getField()[path.get(i).x][path.get(i).y + 1] + 1) {
                path.add(new Point(path.get(i).x, path.get(i).y + 1));
                i++;
                continue;
            }
            if (path.get(i).y > 0 && WaveSearchUtility.getField()[path.get(i).x][path.get(i).y - 1] == 0) {
                break;
            } else if (path.get(i).y > 0 && WaveSearchUtility.getField()[path.get(i).x][path.get(i).y] == WaveSearchUtility.getField()[path.get(i).x][path.get(i).y - 1] + 1) {
                path.add(new Point(path.get(i).x, path.get(i).y - 1));
                i++;
                continue;
            }
        }

        Collections.reverse(path);
    }

    /* Outputting coordinates of the path to the console */
    public void printPath() {
        System.out.print("Start => ");
        for (Point p : path) {
            System.out.print("(" + p.x + "," + p.y + ") =>");
        }
        System.out.println(" Finish!");
    }

    /* Outputting the coordinates of the path to a file or .... */
    public void printPath(PrintWriter out) {
        out.append("Start => ");
        for (Point p : path) {
            out.append("(").append(String.valueOf(p.x)).append(",").append(String.valueOf(p.y)).append(") =>");
        }
        out.append(" Finish!");
    }

    public List<Point> getPath() {
        return path;
    }

    public int getStep() {
        return step;
    }

    private static class Point {
        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
