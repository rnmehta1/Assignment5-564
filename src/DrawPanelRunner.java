import java.awt.*;

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
                        Thread.sleep(500);
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
