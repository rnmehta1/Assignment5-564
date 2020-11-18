/*
Author: Ria Mehta
File Version: 3.4
Time required: 25 hours
Description: Actual Class to solve the travelling salesman problem.
Input: Adjacency matrix from TSPSolver.java (Inherits TSPSolver so no need to actually pass data)
Output: Path, Cost of travel on Command line
*/

import java.util.*;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class TSPNearestNeighbour extends TSPSolver {
    private int numberOfNodes;
    private Stack<Integer> stack;
    private Stack<Integer> stack2;
    private Stack<Integer> stack3;

    public static Queue<Integer> cityQueue1 = new LinkedList<>();
    public static Queue<Integer> cityQueue2 = new LinkedList<>();
    public static Queue<Integer> cityQueue3 = new LinkedList<>();


    double distance=0;
    double distance2=0;
    double distance3=0;


    public TSPNearestNeighbour() {
        stack = new Stack<Integer>();
    }

    public String getDistance() {
        return String.valueOf(distance);
    }

    public void tsp(double adjacencyMatrix[][]) {
        System.out.println("Cities are visited as:");
        int prev=1;
        numberOfNodes = adjacencyMatrix[1].length - 1;
        System.out.println("number of nodes"+numberOfNodes);
        int[] visited = new int[numberOfNodes + 1];
        int sourc = ThreadLocalRandom.current().nextInt(1, numberOfNodes);
//        int sourc= 1000;  //Set source city
        visited[sourc] = 1;
        stack.push(sourc);
        int element, destn = 0, i;
        double min = Double.MAX_VALUE;
        boolean minFlag = false;
        System.out.print(sourc);
        cityQueue1.add(sourc);

        while (!stack.isEmpty()) {
            element = stack.peek();
            i = 1;
            min = Integer.MAX_VALUE;
            while (i <= numberOfNodes) {
                if (adjacencyMatrix[element][i] > 1 && visited[i] == 0) {
                    if (min > adjacencyMatrix[element][i]) {
                        min = adjacencyMatrix[element][i];
                        destn = i;
                        minFlag = true;
                    }
                }
                i++;
            }
            if (minFlag) {
                visited[destn] = 1;
                stack.push(destn);
                distance+=adjacencyMatrix[prev][destn];
                prev=destn;
                System.out.print("->" + destn);
                cityQueue1.add(destn);
                minFlag = false;
                continue;
            }
            stack.pop();
        }
        System.out.println();
        System.out.println("Total Distance:"+distance);



        //
        System.out.println("Cities are visited as:");
        int prev2=1;
        numberOfNodes = adjacencyMatrix[1].length - 1;
        System.out.println("number of nodes"+numberOfNodes);
        int[] visited2 = new int[numberOfNodes + 1];
        int sourc2= ThreadLocalRandom.current().nextInt(1, numberOfNodes);  //Set source city
        visited2[sourc2] = 1;
        stack2= new Stack<>();
        stack2.push(sourc2);
        int element2, destn2 = 0, i2;
        double min2 = Double.MAX_VALUE;
        boolean minFlag2 = false;
        System.out.print(sourc2);
        cityQueue1.add(sourc2);

        while (!stack2.isEmpty()) {
            element2 = stack2.peek();
            i2 = 1;
            min2 = Integer.MAX_VALUE;
            while (i2 <= numberOfNodes) {
                if (adjacencyMatrix[element2][i2] > 1 && visited2[i2] == 0) {
                    if (min2 > adjacencyMatrix[element2][i2]) {
                        min2 = adjacencyMatrix[element2][i2];
                        destn2 = i2;
                        minFlag2 = true;
                    }
                }
                i2++;
            }
            if (minFlag2) {
                visited2[destn2] = 1;
                stack2.push(destn2);
                distance2+=adjacencyMatrix[prev2][destn2];
                prev2=destn2;
                System.out.print("->" + destn2);
                cityQueue2.add(destn2);
                minFlag2 = false;
                continue;
            }
            stack2.pop();
        }
        System.out.println();
        System.out.println("Total Distance:"+distance2);

    //
        System.out.println("Cities are visited as:");
        int prev3=1;
        numberOfNodes = adjacencyMatrix[1].length - 1;
        System.out.println("number of nodes"+numberOfNodes);
        int[] visited3 = new int[numberOfNodes + 1];
        int sourc3= ThreadLocalRandom.current().nextInt(1, numberOfNodes);
        visited3[sourc3] = 1;
        stack3= new Stack<>();
        stack3.push(sourc3);
        int element3, destn3 = 0, i3;
        double min3 = Double.MAX_VALUE;
        boolean minFlag3 = false;
        System.out.print(sourc3);
        cityQueue1.add(sourc3);

        while (!stack3.isEmpty()) {
            element3 = stack3.peek();
            i3 = 1;
            min3 = Integer.MAX_VALUE;
            while (i3 <= numberOfNodes) {
                if (adjacencyMatrix[element3][i3] > 1 && visited3[i3] == 0) {
                    if (min3 > adjacencyMatrix[element3][i3]) {
                        min3 = adjacencyMatrix[element3][i3];
                        destn3 = i3;
                        minFlag3 = true;
                    }
                }
                i3++;
            }
            if (minFlag3) {
                visited3[destn3] = 1;
                stack3.push(destn3);
                distance3+=adjacencyMatrix[prev3][destn3];
                prev3=destn3;
                System.out.print("->" + destn3);
                cityQueue3.add(destn3);
                minFlag3 = false;
                continue;
            }
            stack3.pop();
        }
        System.out.println();
        System.out.println("Total Distance:"+distance3);
    }


}

