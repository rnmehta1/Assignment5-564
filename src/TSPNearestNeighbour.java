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

public class TSPNearestNeighbour extends TSPSolver {
    private int numberOfNodes;
    private Stack<Integer> stack;
    public Queue<Integer> cityQueue = new LinkedList<>();

    double distance=0;

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
        int[] visited = new int[numberOfNodes + 1];
        int sourc= Integer.parseInt(source.getText());
        visited[sourc] = 1;
        stack.push(sourc);
        int element, destn = 0, i;
        double min = Double.MAX_VALUE;
        boolean minFlag = false;
        System.out.print(sourc);
        cityQueue.add(sourc);

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
                cityQueue.add(destn);
                minFlag = false;
                continue;
            }
            stack.pop();
        }
        System.out.println();
        System.out.println("Total Distance:"+distance);
    }
}
