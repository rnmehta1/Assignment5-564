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
    public static DrawPanelRunner runner, runner2, runner3;
    public static DrawPanel pane;
//    static PlayButton playButton;
    public static JFrame frame;
    public static JLabel countLabel;
    static JTextField source;
    static JMenuBar menuBar;
    static JDialog d;
    static JMenu fileMenu,projectMenu, about;
    static JMenuItem open,save,newProject,run,stop;
    TSPSolver tspSolver;
    TSPNearestNeighbour solve;
    public static boolean pressedNew=false;

    //For the OPen File button
    public void actionPerformed(ActionEvent evt)  {

            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int r = j.showOpenDialog(null);

            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected file
                label.setText("Last File Chosen:"+j.getSelectedFile().getAbsolutePath());
                File tspFile= new File(j.getSelectedFile().getAbsolutePath());
                try {
                    BufferedReader br = new BufferedReader(new FileReader(tspFile));
                    tspSolver= new TSPSolver();
                    tspSolver.dataReader(br);
                    tspSolver.dataGenerator(tspSolver.cityCoordDraw);

                    // Solve the TSP
                    solve = new TSPNearestNeighbour();
                    solve.tsp(tspSolver.distMat);
                    pane.setCityCoord(tspSolver.cityCoordDraw);
                    pane.setCityQueue(solve.cityQueue1);
                    runner.setNumC(solve.cityQueue1.size()+solve.newdotCount);
                    pane.repaint();

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
        try {
            File myObj = new File("newPoints.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                myObj.delete();
                myObj.createNewFile();
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Main mainClass = new Main();

        frame = new JFrame();//creating instance of JFrame
        frame.setSize(1440, 900);
        frame.setTitle("Travelling Salesman Problem Graph Simulation");
        frame.setLayout(null);//using no layout managers
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a menubar
        menuBar= new JMenuBar();

        // create a menu
        fileMenu = new JMenu("File");

        // create menuitems
        open = new JMenuItem("Open");
        open.addActionListener(mainClass);
        save = new JMenuItem("Save");

        // add menu items to menu
        fileMenu.add(open);
        fileMenu.add(save);

        // add menu to menu bar
        menuBar.add(fileMenu);

        //Project Menu
        projectMenu = new JMenu("Project");

        // create menuitems
        newProject = new JMenuItem("New");


        ActionListener newListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                pressedNew=true;
                pane.repaint();

            }
        };


        newProject.addActionListener(newListener);
        run = new PlayButton();

        stop = new StopButton();


        // add menu items to menu
        projectMenu.add(newProject);
        projectMenu.add(run);
        projectMenu.add(stop);

        menuBar.add(projectMenu);

        about= new JMenu("About");

        about.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                final JPopupMenu popup = new JPopupMenu();
                popup.add(new JMenuItem(new AbstractAction("The team - Ria Mehta ; Anurag Mishra ; Akshay Dileep Kumar") {
                    public void actionPerformed(ActionEvent e) {

                        JOptionPane.showMessageDialog(frame, "Ria Mehta - MS Software Engineering \nAnurag Mishra - MSC Software Engineering " +
                                "\nAkshay Dileep Kumar - MS Software Engineering");
                    }
                }));
                popup.show(e.getComponent(), e.getX(), e.getY());
            }});

//        about.addActionListener(aboutListener);

        menuBar.add(about);

        frame.setJMenuBar(menuBar);

        label = new JLabel("no file selected");
        label.setBounds(50, 40, 500, 40);

        countLabel= new JLabel();    //To display the Iterations
        countLabel.setBounds(550, 130, 500, 40);


        JLabel sourceLabel = new JLabel("Choose Source City:");
        sourceLabel.setBounds(50,90,130,40);

        source= new JTextField();
        source.setBounds(180, 90, 100, 40);

        source.setText("1");
        Border blackline1 = BorderFactory.createTitledBorder("Simulation");

        pane= new DrawPanel();
        pane.setBorder(blackline1);
        pane.setBounds(50,200,1300,560);

        //Add all the Swing Components to JFrame
//        frame.add(countLabel);
        frame.add(pane);
//        frame.add(sourceLabel);
//        frame.add(label);

//        frame.add(source);

        EventQueue.invokeLater(() -> {
            runner = new DrawPanelRunner(pane, 1);
            frame.setVisible(true);
            Thread t1 = new Thread(runner, "T1");
            System.out.println("Thread is starting");
            t1.start();

            //runner2
            runner2 = new DrawPanelRunner(pane, 2);
            frame.setVisible(true);
            Thread t2 = new Thread(runner2, "T2");
            System.out.println("Thread is starting");
            t2.start();

            runner3 = new DrawPanelRunner(pane, 3);
            frame.setVisible(true);
            Thread t3 = new Thread(runner3, "T3");
            System.out.println("Thread is starting");
            t3.start();
        });
    }
}
