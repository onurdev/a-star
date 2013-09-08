import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: onurb_000
 * Date: 8/30/13
 * Time: 2:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    static JFrame frame = new JFrame("A*");
    static JPanel buttons= new JPanel();
    static Grid grid = new Grid();

    static ButtonGroup group=new ButtonGroup();
    static JRadioButton toggleWalls=new JRadioButton("Toggle Walls");
    static JRadioButton setStart=new JRadioButton("Set Start",true);
    static JRadioButton setEnd=new JRadioButton("Set End");

    static JButton findButton=new JButton("FIND");

    public static void main(String a[]) {

        group.add(toggleWalls);
        group.add(setStart);
        group.add(setEnd);


        frame.add(grid);
        buttons.add(toggleWalls);
        buttons.add(setStart);
        buttons.add(setEnd);
        buttons.add(findButton);
        frame.add(buttons, BorderLayout.NORTH);

        grid.addMouseListener(new MouseHandler(grid));
        findButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PathFinder(grid).start();
            }
        });

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Thread() {
            @Override
            public void run() {
                for (; ; ) {
                    Main.grid.repaint();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        }.start();   //repaint

    }
}
