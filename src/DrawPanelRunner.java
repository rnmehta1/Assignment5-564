import java.awt.*;

class DrawPanelRunner implements Runnable  {
    public static volatile boolean isStopped = true;
    public static volatile int numC;
    public DrawPanel drawlines;
    int id=0;

    DrawPanelRunner(DrawPanel drawlines, int id) {
        this.drawlines = drawlines;
        this.id=id;
    }


    private void setDrawLines1(int pos) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Color Black= new Color(0);
                drawlines.setPos(pos, Black,1);
            }
        });
    }

    private void setDrawLines2(int pos) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Color Black= new Color(100000);
                drawlines.setPos(pos, Black,2);
            }
        });
    }private void setDrawLines3(int pos) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Color Black= new Color(200000000);
                drawlines.setPos(pos, Black,3);
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
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                }
                if(id==1) {
                    setDrawLines1(i);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                    }
                } else if (id==2){
                    setDrawLines2(i+10);
                    try {
                        Thread.sleep(1700);
                    } catch (InterruptedException e) {
                    }
                } else if (id==3){
                    setDrawLines3(i+15);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }

            }
        }
    }
}
