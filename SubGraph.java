public class SubGraph extends Graph{
	public SubGraph(Graph G, int[] vertices){
		this(G);
		for(int v=0; v<this.V; v++){
			vertexState[v]=false;
		}
		for(int v: vertices){
			vertexState[v]=true;
		}
	}
}
