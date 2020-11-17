/*
Author: Ria Mehta
File Version: 3.2
Time required: 25 hours
Description: Controller for the Simulation Panel
Input: The order of cities to simulate travel
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class DrawPanel extends JPanel implements MouseListener {
    private int pos = 0;
    Point point = null;
    List<String> lines = new ArrayList<String>();
    String line = null;
    public void setPos(int pos) {
        this.pos = pos;
        repaint();
    }

    double cityCoord[][];

    int cities[];
    public static Graphics gthis;
    Queue<Integer> cityQueue;

    void setCityQueue(Queue<Integer>cityQueue){
        this.cityQueue=cityQueue;
        cities=new int[cityQueue.size()];
        int i=0;
        for(int c: cityQueue){
            cities[i]=c;
            i++;
        }
    }


    DrawPanel(){
        addMouseListener(this);

    }
    void setCityCoord(double cityCoordDraw[][]){

        this.cityCoord=cityCoordDraw;
    }

   public void drawLines(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        if(cityQueue==null)
            return;
        for (int i = 1; i < pos; i++) {

            //City1
            int x = (int) cityCoord[cities[i]][0];
            int y = (int) cityCoord[cities[i]][1]+10;
            //City2
            int x1 = (int) cityCoord[cities[i - 1]][0];
            int y1 = (int) cityCoord[cities[i - 1]][1]+10;


            Main.countLabel.setText("Cities Travelled:"+pos);
            g2d.drawLine(x, y, x1, y1);
        }
    }


    void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.blue);
        if(cityCoord==null){
            return;
        }
            for (int i = 0; i < cityCoord.length; i++) {

//            Dimension size = getSize();
//            Insets insets = getInsets();
//
//            int w = size.width - insets.left - insets.right;
//                System.out.println(w+" width");
//            int h = size.height - insets.top - insets.bottom;
//                System.out.println(h+" height");

            int x = (int) cityCoord[i][0];
            int y = (int) cityCoord[i][1];
            gthis.fillRect(x , y, 1, 1);

            if(cityCoord.length<200)
                g2d.drawString(Integer.toString(i),x,y);
        }


    }

    @Override
    public void paintComponent(Graphics g) {

        if(Main.pressedNew==false) {
            gthis = g;
            super.paintComponent(g);
            doDrawing(gthis);
            drawLines(gthis);

            // If user has chosen a point, paint a small dot on top.

            if (point != null) {
                gthis.fillRect(point.x - 3, point.y - 3, 7, 7);
            }
            try {
                File f1 = new File("newPoints.txt");
                Scanner myReader = new Scanner(f1);
                while (myReader.hasNextLine()) {
                    line = myReader.nextLine();
                    lines.add(line);
                    int pointx, pointy;
                    pointx = Integer.parseInt(line.split(" ")[0]);
                    pointy = Integer.parseInt(line.split(" ")[1]);
                    gthis.fillRect(pointx - 3, pointy - 3, 7, 7);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            Main.pressedNew=false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        drawPoint=true;
        int x = e.getX();
        int y = e.getY();
        if (point == null) {
            point = new Point(x, y);
        } else {
            point.x = x;
            point.y = y;
        }

        //Write new points to a file
        try
        {
            File f1 = new File("newPoints.txt");

            FileWriter fw = new FileWriter(f1,true);

            fw.write(x+ " " +y+" \n");
            fw.close();



        } catch (Exception ex) {
            ex.printStackTrace();
        }

        repaint();

    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}

