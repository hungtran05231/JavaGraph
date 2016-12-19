import java.io.*;
public class Test{
	public static void main(String[] args) {
		try{
			Graph graph = new Graph(args[0]);
			System.out.println(graph);
			System.out.println("------------deleted");
			graph.deleteEdge(0,6);
			System.out.println(graph);
		}catch(IOException e){
			System.out.println(e.toString());
		}
	}
}
