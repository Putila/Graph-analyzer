package ht;
public class Arc {

    private Node startingPoint;
    private Node endingPoint;
    private boolean visited;

    public Node getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Node startingPoint) {
        this.startingPoint = startingPoint;
    }

    public Node getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(Node endingPoint) {
        this.endingPoint = endingPoint;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    //constructor
    public Arc(Node sp, Node ed){
        setStartingPoint(sp);
        setEndingPoint(ed);
        visited = false;
    }

}
