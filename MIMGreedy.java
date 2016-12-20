import java.util.*;
import adt.*;
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
	public String toString(){
		StringBuilder s = new StringBuilder();
		for(Edge edge: edges){
			s.append("Edge:{"+edge.u+","+edge.v+"}"+"\n");
		}
		return s.toString();
	}
}
public class MIMGreedy{
	MIM mim;
	public MIMGreedy(Graph G){
		mim = new MIM();
		int vertex = G.adj(0).iterator().next();
		mim.add(0,vertex);
		for(int u: G.adj(vertex)){
			G.deleteVertex(u);
		}
		for(int u: G.adj(0)){
			G.deleteVertex(u);
		}
		G.deleteVertex(0);
		G.deleteVertex(vertex);
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
					for(int x: G.adj(v)){
						G.deleteVertex(x);
					}
					for(int x: G.adj(u)){
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
		
	}
}
