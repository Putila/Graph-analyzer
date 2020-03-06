/* Tira 2018 ht
 * Roope Putila
 * 422600
 * roope.putila@tuni.fi
 */
import ht.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Float;
import java.util.LinkedList;


public class T2018 {

    private	String line;
    private static float x[];
    private static float y[];


    private void readInput() {

        x=new float[400];
        y=new float[400];
        try{
            BufferedReader br = new BufferedReader( new FileReader("Tdata.txt"));
            for(int i=0; i<400; i++){
                line=br.readLine();
                String[] values=line.split(",");
                x[i]=Float.parseFloat(values[0]);
                y[i]=Float.parseFloat(values[1]);
                //System.out.print(x[i]+" , "+y[i]+"\n");
            }
        } catch(IOException e){
            System.out.println("File not found.");
        }
    }
    private void writeOutput(LinkedList<Node> list, String name) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(name));
            int i = 0;
            for(Node node: list) {
                bw.write(Float.toString(list.get(i).getX()));
                bw.write(",");
                bw.write(Float.toString(list.get(i).getY()));
                bw.newLine();
                i++;
            }
            bw.close();

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println(name + " written");
    }


    private void writeDegrees(LinkedList<Node> list) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Degrees.txt"));
            int i = 0;
            for(Node node: list) {
                bw.write(Float.toString(list.get(i).getX()));
                bw.write(" , ");
                bw.write(Float.toString(list.get(i).getY()));
                bw.write("  | in degrees: ");
                bw.write(Integer.toString(list.get(i).getInDegrees()));
                bw.write(" | out degrees: ");
                bw.write(Integer.toString(list.get(i).getOutDegrees()));
                bw.write(" |");
                bw.newLine();
                i++;
            }
            bw.close();

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println("Degrees.txt written");
    }


    public static void main(String[] args) {

        T2018 ht=new T2018();
        ht.readInput();

        Graph graph = new Graph();
        //creating nodes and neighbours
        graph.createNodes(x, y);
        graph.setNeighbours(x, y);

        //BFS
        graph.BFS(graph.getList());
        ht.writeOutput(graph.getBreadthVisitedNodes(),"BFS.txt");

        //resets the node's visited status
        graph.resetNodes();
        //DFS
        graph.DFS(graph.getList());
        ht.writeOutput(graph.getDepthVisitedNodes(),"DFS.txt");
        //Degrees
        ht.writeDegrees(graph.getDepthVisitedNodes());
    }
}