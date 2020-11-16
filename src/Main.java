/*
Author: Ria Mehta
File Version: 5.0
Time required: 30 hours
Description: Driver class for the project. Project starts and ends here.
Input: Filename from GUI
Output: Simulation on GUI
*/

import javax.swing.*;
import javax.swing.JFileChooser;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.filechooser.*;


public class Main implements ActionListener {
    static JLabel label;
    public static DrawPanelRunner runner;
    public static JLabel distLabel;
    public static DrawPanel pane;
    static PlayButton playButton;
    public static JFrame frame;
    public static JLabel countLabel;
    static JTextField source;
    TSPSolver tspSolver;
    TSPNearestNeighbour solve;

    //For the OPen File button
    public void actionPerformed(ActionEvent evt)  {

            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int r = j.showOpenDialog(null);

            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected file
                label.setText(j.getSelectedFile().getAbsolutePath());
                File tspFile= new File(j.getSelectedFile().getAbsolutePath());
                try {
                    BufferedReader br = new BufferedReader(new FileReader(tspFile));
                    tspSolver= new TSPSolver();
                    tspSolver.dataReader(br);
                    tspSolver.dataGenerator(tspSolver.cityCoord);

                    // Solve the TSP
                    solve = new TSPNearestNeighbour();
                    solve.tsp(tspSolver.distMat);
                    pane.setCityCoord(tspSolver.cityCoord);
                    pane.setCityQueue(solve.cityQueue);
                    runner.setNumC(solve.cityQueue.size());
                    pane.repaint();
                    distLabel.setText("Total Distance Travelled:"+solve.getDistance());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // if the user cancelled the operation
            else
                label.setText("the user cancelled the operation");

    }


    public Main() {
    }

    public static void main(String[] args) {
        Main mainClass = new Main();
        frame = new JFrame();//creating instance of JFrame
        frame.setSize(1440, 900);
        frame.setTitle("Travelling Salesman Problem Graph Simulation");
        frame.setLayout(null);//using no layout managers
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel("no file selected");
        label.setBounds(150, 90, 500, 40);

        distLabel = new JLabel();       //To display the total distance travelled
        distLabel.setBounds(550, 90, 500, 40);

        countLabel= new JLabel();    //To display the Iterations
        countLabel.setBounds(550, 130, 500, 40);

        JButton openButton = new JButton("Open File");//creating instance of JButton open button
        openButton.setBounds(50, 90, 100, 40);//x axis, y axis, width, height
        openButton.addActionListener(mainClass);

        JLabel sourceLabel = new JLabel("Choose Source City:");
        sourceLabel.setBounds(50,40,130,40);

        source= new JTextField();
        source.setBounds(180, 40, 100, 40);

        source.setText("1");
        Border blackline1 = BorderFactory.createTitledBorder("Simulation");

        pane= new DrawPanel();
        pane.setBorder(blackline1);
        pane.setBounds(50,200,1300,560);
        playButton= new PlayButton();
        playButton.setBounds(50, 140, 100, 40);
        playButton.setVisible(true);
        //Add all the Swing Components to JFrame
        frame.add(countLabel);
        frame.add(playButton);
        frame.add(pane);
        frame.add(sourceLabel);
        frame.add(source);
        frame.add(label);
        frame.add(distLabel);
        frame.add(openButton);//adding button in JFrame

        EventQueue.invokeLater(() -> {
            runner = new DrawPanelRunner(pane);
            frame.setVisible(true);
            Thread t1 = new Thread(runner, "T1");
            System.out.println("Thread is starting");
            t1.start();
        });
    }
}
