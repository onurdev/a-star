import java.awt.*;
import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: onurb_000
 * Date: 8/30/13
 * Time: 2:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class Node {
    public static final Color FLOOR = Color.WHITE;
    public static final Color WALL = Color.GRAY;
    public static final Color START = Color.GREEN;
    public static final Color END = Color.RED;
    public static final Color BORDER = Color.BLACK;
    int x;
    int y;
    int g;        //from start
    int h;        //from end
    Node parent;
    Boolean walkable = true;
    Color color = FLOOR;
    ArrayList<Node> neighbours = new ArrayList<Node>();
    Grid grid;

    public Node(Grid grid, int x, int y) {
        this.grid = grid;
        this.x = x;
        this.y = y;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getF() {
        return g + h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Boolean isWalkable() {
        return walkable;
    }

    public void setNeighbours() {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (grid.isInsideGrid(x + i, y + j)) neighbours.add(grid.getNode(x + i, y + j));
            }
        }

    }

    public ArrayList<Node> getNeighbours() {
        return neighbours;
    }

    public void toggleWalkable() {
        if (walkable) {
            walkable = false;
            color = WALL;
        } else {
            walkable = true;
            color = FLOOR;
        }
    }

    public void DrawOn(Graphics pen) {
        pen.setColor(color);
        int nodeWidthOnCanvas = grid.getNodeWidth();
        int nodeHeightOnCanvas = grid.getNodeHeight();
        int xPosOnCanvas = x * nodeWidthOnCanvas;
        int yPosOnCanvas = y * nodeHeightOnCanvas;
        int fontSize = (nodeHeightOnCanvas + nodeWidthOnCanvas) / 10;

        pen.fillRect(xPosOnCanvas, yPosOnCanvas, nodeWidthOnCanvas, nodeHeightOnCanvas);
        pen.setColor(BORDER);
        pen.drawRect(xPosOnCanvas, yPosOnCanvas, nodeWidthOnCanvas, nodeHeightOnCanvas);

        pen.setColor(Color.black);
        pen.setFont(new Font(null, Font.PLAIN, fontSize));
        pen.drawString("" + x + "," + y + "", xPosOnCanvas + 2, yPosOnCanvas + fontSize);
        if (getF() != 0) {
            pen.drawString("" + g, xPosOnCanvas + 2, yPosOnCanvas + nodeHeightOnCanvas);
            pen.drawString("" + h, xPosOnCanvas + nodeWidthOnCanvas - fontSize, yPosOnCanvas + fontSize);
            pen.drawString("" + getF(), xPosOnCanvas + nodeWidthOnCanvas - fontSize - 5, yPosOnCanvas + nodeHeightOnCanvas);
        }
        if (parent != null) {
            int xPosOfCenter = xPosOnCanvas + nodeWidthOnCanvas / 2;
            int yPosOfCenter = yPosOnCanvas + nodeHeightOnCanvas / 2;
            int xCenterOfParent = parent.getX() * nodeWidthOnCanvas + nodeWidthOnCanvas / 2;
            int yCenterOfParent = parent.getY() * nodeHeightOnCanvas + nodeHeightOnCanvas / 2;
            pen.fillOval(xPosOfCenter - fontSize / 2, yPosOfCenter - fontSize / 2, fontSize, fontSize);
            pen.drawLine(xPosOfCenter, yPosOfCenter, (xCenterOfParent + xPosOfCenter) / 2, (yCenterOfParent + yPosOfCenter) / 2);

        }
    }

    public void setStart() {
        color = START;
        grid.setStart(this);
    }

    public void setFloor() {
        color = FLOOR;
    }

    public void setEnd() {
        color = END;
        grid.setEnd(this);
    }
}
