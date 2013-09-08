import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: onurb_000
 * Date: 8/30/13
 * Time: 3:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class Grid extends JPanel{
    public static final int GridHeight=20;
    public static final int GridWidth=20;

    public Node start;
    public Node end;

    Node[][] gridMatrice = new Node[GridWidth][GridHeight];

    public Grid(){
        for(int x=0;x< gridMatrice.length;x++){
            for(int y=0;y< gridMatrice[0].length;y++){
                gridMatrice[x][y]=new Node(this,x,y);
            }
        } for(int x=0;x< gridMatrice.length;x++){
            for(int y=0;y< gridMatrice[0].length;y++){
                gridMatrice[x][y].setNeighbours();
            }
        }
    }
    public boolean isInsideGrid(int x,int y){

        return!(x<0||x>=GridWidth||y<0||y>=GridHeight);
    }
    public Node getNode(int x,int y){
        return  gridMatrice[x][y];
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(int x=0;x< gridMatrice.length;x++){
            for(int y=0;y< gridMatrice[0].length;y++){
                gridMatrice[x][y].DrawOn(g);
            }

        }
    }
    public int getNodeWidth(){
        return this.getWidth()/GridWidth;
    }
    public int getNodeHeight(){
        return this.getHeight()/GridHeight;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public void setStart(Node node){
        if(start!=null&&start!=node)start.setFloor();
        start=node;
    }
    public void setEnd(Node node){
        if(end!=null&&end!=node)end.setFloor();
        end=node;
    }


}
