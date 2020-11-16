/*
Author: Ria Mehta
File Version: 2.2
Time required: 25 hours
Description: Class to prepare data from input file i.e. Map the data into a weight matrix (Adjacency Matrix)
Input: Filename from Main.java (Inherits Main so no need to actually pass the data)
Output: Weight matrix to mainTSP.cpp
*/


import java.io.BufferedReader;

public class TSPSolver extends Main{
    public double cityCoord[][];
    public int numCities=0;
    public double distMat [][];

   double euclideanDistance(double x1,double y1,double x2,double y2){
        double x = Math.abs(x1 - x2);
        double y = Math.abs(y1 - y2);
        double dist;

        dist = Math.pow(x, 2) + Math.pow(y, 2);
        dist = Math.sqrt(dist);

        return dist;
    }

    public double[][] dataReader(BufferedReader br) throws Exception {
        String st;
        while ((st = br.readLine()) != null) {
            if (st.contains("DIMENSION :")) {
                numCities = Integer.parseInt(st.substring(st.indexOf(":") + 2));
                System.out.println("Cities:"+numCities);
            }
            cityCoord = new double[numCities][2];

            if (st.contains("NODE_COORD_SECTION")) {
                break;
            }
        }
        String st1;
        int i=0;
        while (i < numCities) {
            System.out.println("i:"+i);
            st1=br.readLine();
            double x = Double.parseDouble(st1.split(" ")[1]);

            double y = Double.parseDouble(st1.split(" ")[2]);
            cityCoord[i][0]=x;
            cityCoord[i][1]=y;
            i++;
        }
        System.out.println("City Co-ordinates:");
        for(int j=0;j<cityCoord.length;j++)
            System.out.println(cityCoord[j][0]+" "+cityCoord[j][1]);

        return cityCoord;
    }

    public double[][] dataGenerator(double cityCoord[][]){
       distMat = new double[numCities][numCities];

        for(int i=0;i<numCities;i++){
            for(int j=i;j<numCities;j++){
                if(i==j){
                    distMat[i][j] = Double.MAX_VALUE;
                } else{
                    distMat[i][j] = euclideanDistance(cityCoord[i][0],cityCoord[i][1],cityCoord[j][0],cityCoord[j][1]);
                    distMat[j][i] = distMat[i][j];
                }
            }
        }
        return distMat;
    }
}
