/*
Author: Ria Mehta
File Version: 5.1
Time required: 25 hours
Description: Controller for the Play Button on the Frame. Plays and Pauses the simulation
Input: Action from Button.
*/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class PlayButton extends JButton
{
    public static DrawPanel pane;


    /**
     * Constructor
     */
    public PlayButton()
    {
        initComponents();
    }

    /**
     * Initializes components
     */
    public void initComponents()
    {
        setPreferredSize(new Dimension(100, 100));
        setText("Start");
        addActionListener(new ButtonListener());
        Border blackline1 = BorderFactory.createTitledBorder("Simulation");
       
        pane= new DrawPanel();
        pane.setBorder(blackline1);
        pane.setBounds(50,200,1300,560);
        Main.frame.add(pane);
    }



    class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent evt)
        {
            if(!DrawPanelRunner.isStopped)
            {
                setText("Start");
                DrawPanelRunner.stop();
                repaint();
            }
            else if(DrawPanelRunner.isStopped)
            {
                setText("Pause");
                DrawPanelRunner.start();
                repaint();
            }

        }
    }
}