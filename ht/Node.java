package ht;
public class Node {

    private float x;
    private float y;
    private Node neighbours[];
    private Boolean visited;
    private int index;
    private Arc arcs[];
    private int inDegrees;
    private int outDegrees;


    public int getIndex() {
        return index;
    }

    public float getX(){
        return x;
    }
    public void setX(float f){
        x = f;
    }

    public float getY(){
        return y;
    }
    public void setY(float f){
        y = f;
    }

    public Boolean isVisited() {
        return visited;
    }

    public void setVisited() {
        visited = true;
    }
    public void setVisited(boolean b){
        visited = b;
    }

    public Arc[] getArcs() {
        return arcs;
    }

    public void setInDegrees(int inDegrees) {
        this.inDegrees = inDegrees;
    }

    public int getInDegrees() {
        return inDegrees;
    }

    public void setOutDegrees(int outDegrees) {
        this.outDegrees = outDegrees;
    }

    public int getOutDegrees() {
        return outDegrees;
    }

    public void addOutDegrees(){
        outDegrees++;
    }
    public void addInDegrees(){
        inDegrees++;
    }

    //constructor
    public Node(float tempX, float tempY, int i){
        setX(tempX);
        setY(tempY);
        index = i;
        visited = false;
        setOutDegrees(0);
        setInDegrees(0);
    }
    //method which sets the node's neighbours
    public void setNeighbours(Node n1, Node n2){
        neighbours = new Node[] {n1, n2};
    }
    //method which sets arcs and outdegrees
    public void setArcs(Node n1, Node n2) {
        Arc arc1 = new Arc(this, n1);
        addOutDegrees();
        Arc arc2 = new Arc(this, n2);
        addOutDegrees();
        arcs = new Arc[]{arc1, arc2};
    }
    //tostring method used for testing the program
    @Override
    public String toString() {
        return index + "| X: " + x + "  Y: " + y + "  | visited: " + visited;
    }
}
