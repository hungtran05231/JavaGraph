import java.util.*;
import adt.*;
import java.io.*;
class MIM{
	private ArrayList<Edge> edges;
	private static class Edge{
		private final int u;
		private final int v;
		public Edge(int u, int v){
			this.u = u;
			this.v = v;
		}
		int u(){
			return u;
		}
		int v(){
			return v;
		}
	}
	public MIM(){
		edges = new ArrayList<Edge>();
	}
	public void add(int u, int v){
		Edge edge = new Edge(u,v);
		edges.add(edge);
	}
	public int size(){
		return edges.size();
	}
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("Number Of Edges: "+size()+"\n");
		for(Edge edge: edges){
			s.append("Edge:{"+edge.u+","+edge.v+"}"+"\n");
		}
		return s.toString();
	}
}
public class MIMGreedy{
	MIM mim;
	public MIMGreedy(){
		mim = new MIM();
	}
	public void caculateMIM(Graph G){
		int start;
		for(start=0; start<G.V(); start++ ){
			if(G.checkVertex(start) && G.adj(start).iterator().hasNext()) break;
		}
		if(!G.adj(start).iterator().hasNext()) return;
		int finish = G.adj(start).iterator().next();
		mim.add(start,finish);
		System.out.println("start::::"+start+"::::finish::::"+finish);
		for(int x: G.adj(start,finish)){
			G.deleteVertex(x);
		}
		G.deleteVertex(start);
		G.deleteVertex(finish);
		while(G.E()!=0){
			int min = G.minDegree();
			int u;
			for(u=0; u<G.V() ; u++){
				if( G.checkVertex(u) && G.degree(u)==min ) break;
			}
			min = G.minAdjDegree(u);
			for(int v: G.adj(u)){
				if(G.checkVertex(v)==true && min==G.degree(v)){
					mim.add(u,v);
					for(int x: G.adj(u,v)){
						G.deleteVertex(x);
					}
					G.deleteVertex(u);
					G.deleteVertex(v);

					break;
				}
			}
		}
	}
	public static void main(String[] args) {
		try{
			FileReader file = new FileReader(args[0]);
			BufferedReader reader = new BufferedReader(file);
			Graph graph = new Graph(reader);
			CC cc = new CC(graph);
			MIMGreedy mimgreedy = new MIMGreedy();
			for(int i=0; i<cc.count(); i++){
				Graph subgraph = new Graph(graph, cc.verticeList(i));
				System.out.println(subgraph);
				//mimgreedy.caculateMIM(subgraph);
			}
			//System.out.println(mimgreedy.mim);
		}catch(IOException e){
			System.out.println(e.toString());
		}	
	}
}
