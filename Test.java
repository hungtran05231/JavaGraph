import java.io.*;
public class Test{
	public static void main(String[] args) {
		try{
			FileReader file = new FileReader(args[0]);
			BufferedReader reader = new BufferedReader(file);
			Graph graph = new Graph(reader);
			MIMGreedy mimgreedy = new MIMGreedy(graph);
			//System.out.println(graph.minAdjDegree(1));
			System.out.println(mimgreedy.mim);
			System.out.println(graph);
		}catch(IOException e){
			System.out.println(e.toString());
		}
	}
}
