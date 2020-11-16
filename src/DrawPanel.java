/*
Author: Ria Mehta
File Version: 3.2
Time required: 25 hours
Description: Controller for the Simulation Panel
Input: The order of cities to simulate travel
*/
import javax.swing.*;
import java.awt.*;
import java.util.Queue;

class DrawPanel extends JPanel{
    private int pos = 0;

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
    }
    void setCityCoord(double cityCoord[][]){
        this.cityCoord=cityCoord;

    }

   public void drawLines(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        if(cityQueue==null)
            return;
        for (int i = 1; i < pos; i++) {

            Dimension size = getSize();
            Insets insets = getInsets();

            int w = size.width - insets.left - insets.right;
            int h = size.height - insets.top - insets.bottom;

            //City1
            int x = (int) (Math.abs(Math.ceil(cityCoord[cities[i]][0])) % w);
            int y = (int) (Math.abs(Math.ceil(cityCoord[cities[i]][1])) % h)+10;

            //City2
            int x1 = (int) (Math.abs(Math.ceil(cityCoord[cities[i - 1]][0])) % w);
            int y1 = (int) (Math.abs(Math.ceil(cityCoord[cities[i - 1]][1])) % h)+10;

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

            Dimension size = getSize();
            Insets insets = getInsets();

            int w = size.width - insets.left - insets.right;
            int h = size.height - insets.top - insets.bottom;

            int x = (int) (Math.abs(Math.ceil(cityCoord[i][0])) % w);
            int y = (int) (Math.abs(Math.ceil(cityCoord[i][1])) % h)+10;
            if(cityCoord.length<200)
                g2d.drawString(Integer.toString(i),x,y);
            g2d.drawLine(x, y, x, y);
        }
    }



    @Override
    public void paintComponent(Graphics g) {
        gthis=g;
        super.paintComponent(g);

        doDrawing(gthis);
        drawLines(gthis);
    }

}

class DrawPanelRunner implements Runnable  {
    public static volatile boolean isStopped = true;
    public static volatile int numC;
    public DrawPanel drawlines;

    DrawPanelRunner(DrawPanel drawlines) {
        this.drawlines = drawlines;
    }


    private void setDrawLines(int pos) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                drawlines.setPos(pos);
            }
        });
    }

    public void setNumC(int num){
        numC=num;
    }

    public int getNumC(){
        return numC;
    }

    public static void stop(){
        isStopped = true;
    }
    public static void start(){
        isStopped = false;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 1; i < numC; i++) {

                while (isStopped) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
                setDrawLines(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
