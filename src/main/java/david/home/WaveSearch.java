package david.home;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by David on 1/18/2018.
 */
public class WaveSearch {

    private List<WaveSearch.Point> pointsList;
    private List<WaveSearch.Point> path;
    private int step;

    public WaveSearch(int field [][]) {
        pointsList = new ArrayList<WaveSearch.Point>();
        path = new ArrayList<WaveSearch.Point>();
        searchStartPoint(field);
    }

    public void searchStartPoint (int field [] []) {

            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length; j++) {
                    if (field[i][j] == 0) {
                        pointsList.add(new Point(i, j));
                    }
                }
            }
            if (pointsList.size() < 1) {
                throw new IllegalArgumentException("Field must be start point");
            }

    }

    /* The method of finding the minimum number of moves, from the initial to the end point */
    public void searchMinimalStep(int field [] []) {

        int i = 0;

        while (true) {
            if (pointsList.get(i).x < field.length - 1
                    && field[pointsList.get(i).x + 1][pointsList.get(i).y] == -1) {
                field[pointsList.get(i).x + 1][pointsList.get(i).y] = field[pointsList.get(i).x][pointsList.get(i).y] + 1;
                pointsList.add(new Point(pointsList.get(i).x + 1, pointsList.get(i).y));
            } else if (pointsList.get(i).x < field.length - 1
                    && field[pointsList.get(i).x + 1][pointsList.get(i).y] == -3) {
                path.add(new Point(pointsList.get(i).x + 1, pointsList.get(i).y));
                path.add(new Point(pointsList.get(i).x, pointsList.get(i).y));
                step = field[pointsList.get(i).x][pointsList.get(i).y] + 1;
                break;
            }
            if (pointsList.get(i).x > 0
                    && field[pointsList.get(i).x - 1][pointsList.get(i).y] == -1) {
                field[pointsList.get(i).x - 1][pointsList.get(i).y] = field[pointsList.get(i).x][pointsList.get(i).y] + 1;
                pointsList.add(new Point(pointsList.get(i).x - 1, pointsList.get(i).y));
            } else if (pointsList.get(i).x > 0
                    && field[pointsList.get(i).x - 1][pointsList.get(i).y] == -3) {
                path.add(new Point(pointsList.get(i).x - 1, pointsList.get(i).y));
                path.add(new Point(pointsList.get(i).x, pointsList.get(i).y));
                step = field[pointsList.get(i).x][pointsList.get(i).y] + 1;
                break;
            }
            if (pointsList.get(i).y < field[0].length - 1
                    && field[pointsList.get(i).x][pointsList.get(i).y + 1] == -1) {
                field[pointsList.get(i).x][pointsList.get(i).y + 1] = field[pointsList.get(i).x][pointsList.get(i).y] + 1;
                pointsList.add(new Point(pointsList.get(i).x, pointsList.get(i).y + 1));
            } else if (pointsList.get(i).y < field[0].length - 1
                    && field[pointsList.get(i).x][pointsList.get(i).y + 1] == -3) {
                path.add(new Point(pointsList.get(i).x, pointsList.get(i).y + 1));
                path.add(new Point(pointsList.get(i).x, pointsList.get(i).y));
                step = field[pointsList.get(i).x][pointsList.get(i).y] + 1;
                break;
            }
            if (pointsList.get(i).y > 0
                    && field[pointsList.get(i).x][pointsList.get(i).y - 1] == -1) {
                field[pointsList.get(i).x][pointsList.get(i).y - 1] = field[pointsList.get(i).x][pointsList.get(i).y] + 1;
                pointsList.add(new Point(pointsList.get(i).x, pointsList.get(i).y - 1));
            } else if (pointsList.get(i).y > 0
                    && field[pointsList.get(i).x][pointsList.get(i).y - 1] == -3) {
                path.add(new Point(pointsList.get(i).x, pointsList.get(i).y - 1));
                path.add(new Point(pointsList.get(i).x, pointsList.get(i).y));
                step = field[pointsList.get(i).x][pointsList.get(i).y] + 1;
                break;
            }

            i++;

            if (i >= field.length * field[0].length) {
                throw new IllegalArgumentException("Field must be End point");
            }
        }
    }

    /* The method of finding the shortest path, from the initial to the final point, with the derivation of the coordinates of displacements */
    public void searchShortWay (int field [] []) {

        searchMinimalStep(field);

        int i = 1;

        while (true) {

            if (path.get(i).x < field.length - 1 && field[path.get(i).x + 1][path.get(i).y] == 0) {
                break;
            } else if (path.get(i).x < field.length - 1 && field[path.get(i).x][path.get(i).y] == field[path.get(i).x + 1][path.get(i).y] + 1) {
                path.add(new Point(path.get(i).x + 1, path.get(i).y));
                i++;
                continue;
            }
            if (path.get(i).x > 0 && field[path.get(i).x - 1][path.get(i).y] == 0) {
                break;
            } else if (path.get(i).x > 0 && field[path.get(i).x][path.get(i).y] == field[path.get(i).x - 1][path.get(i).y] + 1) {
                path.add(new Point(path.get(i).x - 1, path.get(i).y));
                i++;
                continue;
            }
            if (path.get(i).y < field[0].length - 1 && field[path.get(i).x][path.get(i).y + 1] == 0) {
                break;
            } else if (path.get(i).y < field[0].length - 1 && field[path.get(i).x][path.get(i).y] == field[path.get(i).x][path.get(i).y + 1] + 1) {
                path.add(new Point(path.get(i).x, path.get(i).y + 1));
                i++;
                continue;
            }
            if (path.get(i).y > 0 && field[path.get(i).x][path.get(i).y - 1] == 0) {
                break;
            } else if (path.get(i).y > 0 && field[path.get(i).x][path.get(i).y] == field[path.get(i).x][path.get(i).y - 1] + 1) {
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
