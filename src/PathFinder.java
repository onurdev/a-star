import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: onurb_000
 * Date: 8/31/13
 * Time: 12:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class PathFinder extends Thread{
    ArrayList<Node> openList = new ArrayList<Node>();
    ArrayList<Node> closedList = new ArrayList<Node>();
    Grid grid;
    Node start;
    Node end;

    public PathFinder(Grid grid) {
        this.grid = grid;
        start = grid.getStart();
        end = grid.getEnd();
    }

    public void run() {
        openList.add(start);
        for (;openList.size()>0 ; ) {
            Node current = findLovestF();
            closedList.add(current);
            current.color=Color.pink;
            openList.remove(current);
            if(current==end)break;
            calculateFsOfNeighbours(current);
           /* try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }*/
        }
        Node node=end.parent;
        ArrayList<Node> path= new ArrayList<Node>();
        while(node.getParent()!=null){

            path.add(node);
            node=node.getParent();


        }
        node=null;
        for(Node pathNode: path){
            pathNode.color= Color.yellow;
        }
    }

    public void calculateFsOfNeighbours(Node node) {
        for (Node neighbour : node.getNeighbours()) {
            if (neighbour.isWalkable() && !closedList.contains(neighbour)) {
                if (!openList.contains(neighbour)) {
                    openList.add(neighbour);
                    neighbour.color= SystemColor.cyan;
                    neighbour.setParent(node);
                    neighbour.setG(neighbour.getParent().getG() + diagonalPenalty(node, neighbour));
                    neighbour.setH((Math.abs(end.getX() - neighbour.getX()) + Math.abs(end.getY() - neighbour.getY()))*10);

                } else {
                    int newG = node.getG() + diagonalPenalty(node, neighbour);
                    if (newG < neighbour.getG()) {
                        neighbour.setG(newG);
                        neighbour.setParent(node);
                    }
                }
            }
        }
    }

    public Node findLovestF() {
        Node lovestFNode = openList.get(0);
        int lovestF = openList.get(0).getF();
        for (Node node : openList) {
            if (node.getF() < lovestF) {
                lovestFNode = node;
                lovestF = node.getF();
            }
        }
        return lovestFNode;
    }

    public boolean isDiagonal(Node node, Node neighbour) {
        return (node.getX() - neighbour.getX() + node.getY() - neighbour.getY()) % 2 == 0;
    }

    public int diagonalPenalty(Node node, Node neighbour) {
        return (isDiagonal(node, neighbour) ? 14 : 10);
    }
}
