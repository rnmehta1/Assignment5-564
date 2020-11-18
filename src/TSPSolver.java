/*
Author: Ria Mehta
File Version: 2.2
Time required: 25 hours
Description: Class to prepare data from input file i.e. Map the data into a weight matrix (Adjacency Matrix)
Input: Filename from Main.java (Inherits Main so no need to actually pass the data)
Output: Weight matrix to mainTSP.cpp
*/


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class TSPSolver extends Main{
    public double cityCoord[][];
    public double cityCoordDraw[][];

    public int numCities=0;
    int newdotCount=0;

    public double distMat [][];
    public int widthPanelMax=1288;
    public int heightPanelMax=530;
    int heightPanelMin=10;
    int widthPanelMin=6;
    double maxX=0;
    double minX=Double.MAX_VALUE;
    double maxY=0, minY=Double.MAX_VALUE;


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

            try {
                File f1 = new File("newPoints.txt");
                Scanner myReader = new Scanner(f1);
                while (myReader.nextLine()!=null){
                    newdotCount++;
                }
            } catch (Exception ex){

            }

            cityCoord = new double[numCities+newdotCount][2];
            cityCoordDraw= new double[numCities+newdotCount][2];

            if (st.contains("NODE_COORD_SECTION")) {
                break;
            }
        }


        String st1;
        int i=0;

        while (i < numCities) {
            st1=br.readLine();
            double x = Double.parseDouble(st1.split(" ")[1]);

            double y = Double.parseDouble(st1.split(" ")[2]);

            System.out.println("i:"+i + ", " + x + ", " + y);
            cityCoord[i][0]=x;
            if(x>maxX){
                maxX= x;
            }
            cityCoord[i][1]=y;
            if(maxY<y){
                maxY=y;
            }
            if(minX>x){
                minX=x;
            }
            if(minY>y){
                minY=y;
            }

            i++;
        }

        double oldX =maxX-minX;
        double oldy = maxY-minY;
        double newX= widthPanelMax-widthPanelMin;
        double newY = heightPanelMax-heightPanelMin;
        for(int j=0;j<numCities;j++){
            double newx = (((cityCoord[j][0]-minX)*newX) /oldX) + widthPanelMin;
            double newy = (((cityCoord[j][1]-minY)*newY) /oldy) + heightPanelMin;

            cityCoordDraw[j][0]= newx;
            cityCoordDraw[j][1]=newy;
        }


            try {
            File f1 = new File("newPoints.txt");
            Scanner myReader = new Scanner(f1);
        for(int r=0;r<=newdotCount;r++ ){
            if(myReader.hasNextLine()) {
                String line = myReader.nextLine();
                cityCoordDraw[numCities + r][0] = Integer.parseInt(line.split(" ")[0]);
                cityCoordDraw[numCities + r][1] = Integer.parseInt(line.split(" ")[1]);
            }
        }
        }catch (Exception ex){

        }
        System.out.println("City Co-ordinates draw:");
//        for(int j=0;j<cityCoordDraw.length;j++)
//            System.out.println(cityCoordDraw[j][0]+" "+cityCoordDraw[j][1]);

        return cityCoordDraw;
    }

    public double[][] dataGenerator(double cityCoord[][]){
       distMat = new double[numCities+newdotCount][numCities+newdotCount];

        for(int i=0;i<numCities+newdotCount;i++){
            for(int j=i;j<numCities+newdotCount;j++){
                if(i==j){
                    distMat[i][j] = Double.MAX_VALUE;
                } else{
                    distMat[i][j] = euclideanDistance(cityCoord[i][0],cityCoord[i][1],cityCoord[j][0],cityCoord[j][1]);
                    distMat[j][i] = distMat[i][j];
                }
            }
        }

        System.out.println("Length of Matrix"+distMat.length+" "+ distMat[0].length);
        System.out.println("Distance matrix");
        for(int i=0;i<numCities+newdotCount;i++){
            for(int j=i;j<numCities+newdotCount;j++) {
                System.out.println(distMat[i][j]);
            }
        }

        return distMat;
    }
}
