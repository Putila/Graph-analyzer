package ht;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    LinkedList<Node> depthVisitedNodes = new LinkedList<Node>();
    LinkedList<Node> breadthVisitedNodes = new LinkedList<Node>();
    LinkedList<Node> list = new LinkedList<Node>();
    private int count = 0;

    public LinkedList<Node> getList() {
        return list;
    }
    public LinkedList<Node> getDepthVisitedNodes() {
        return depthVisitedNodes;
    }
    public LinkedList<Node> getBreadthVisitedNodes() {
        return breadthVisitedNodes;
    }

    //method which creates nodes from the arrays
    public void createNodes(float x[], float y[]) {
        for (int i = 0; i < x.length; i++) {
            Node node = new Node(x[i], y[i], count);
            list.add(node);
            count++;
        }
    }

    //Method that sets the neighbours and arcs for all the nodes
    public void setNeighbours(float x[], float y[]) {
        for (int i = 0; i < x.length; i++) {

            //initializing the variables used to find the neighbours
            double minDist = 100;
            double minDist2 = 100;
            int neighbour = 0;
            int neighbour2 = 0;

            for (int j = 0; j < x.length; j++) {

                //calculating the distance between two nodes
                double dist = Math.sqrt((y[j] - y[i]) * (y[j] - y[i]) + (x[j] - x[i]) * (x[j] - x[i]));

                //setting the nearest and second nearest nodes
                if (dist < minDist && dist != 0) {
                    neighbour2 = neighbour;
                    minDist2 = minDist;
                    minDist = dist;
                    neighbour = j;
                } else if (dist > minDist && dist < minDist2) {
                    minDist2 = dist;
                    neighbour2 = j;
                }
            }
            //Naming nodes to clear up code and setting neighbours
            Node mainNode = list.get(i);
            Node firstNeighbour = list.get(neighbour);
            Node secondNeighbour = list.get(neighbour2);

            //calling node classes setting methods
            mainNode.setNeighbours(firstNeighbour, secondNeighbour);
            mainNode.setArcs(firstNeighbour, secondNeighbour);
            firstNeighbour.addInDegrees();
            secondNeighbour.addInDegrees();
        }
    }
    //calls the DFS method with every unvisited node
    public void DFS(LinkedList<Node> list) {

        //call the function if the node is not visited
        for (Node node : list) {
            if (!node.isVisited()) {
                DFS(node);
            }
        }
    }

    public void DFS(Node n) {

        //setting node n as visited
        n.setVisited();

        //adding it to the visited list
        depthVisitedNodes.add(n);
        int i = 0;

        //for each arc of n do:
        for (Arc arc : n.getArcs()) {
            //if endingpoint is not visited
            if (!n.getArcs()[i].getEndingPoint().isVisited()) {
                Node curr = n.getArcs()[i].getEndingPoint();
                //call the function again with this node
                DFS(curr);
            }
            i++;
        }
    }

    //calls the BFS method with every unvisited node
    public void BFS(LinkedList<Node> list) {
       //call the function if the node is not visited
        for (Node node : list) {
            if (!node.isVisited()) {
                BFS(node);
            }
        }
    }

    public void BFS(Node n) {
        //create a queue and add the starting node to it
        Queue<Node> queue = new LinkedList<>();
        queue.add(n);
        //mark the node as visited
        n.setVisited();

        while (!queue.isEmpty()) {
            //remove the first node and add it to the list of visited nodes
            Node curr = queue.remove();
            breadthVisitedNodes.add(curr);
            int i = 0;

            //for each arc do
            for (Arc arc : curr.getArcs()) {
                Node next = curr.getArcs()[i].getEndingPoint();
                //if arc's endpoint is not visited add it to the queue and mark it as visited
                if (!next.isVisited()) {
                    queue.add(next);
                    next.setVisited();
                }
                i++;
            }
        }
    }


    //method which prints all the nodes in a list
    public void printNodes(LinkedList<Node> list) {
        for (Node node : list) {
            System.out.println(node.toString());
        }
    }
    //resets every nodes visited status
    public void resetNodes(){
        for (Node node : list) {
            node.setVisited(false);
        }
    }
    public void resetLists(){
        breadthVisitedNodes.clear();
        depthVisitedNodes.clear();
    }



}