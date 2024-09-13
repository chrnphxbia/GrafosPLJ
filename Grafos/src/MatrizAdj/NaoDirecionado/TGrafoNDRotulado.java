package MatrizAdj.NaoDirecionado;

public class TGrafoNDRotulado {
    private	int n; // Número de vértices
	private	int m; // Número de arestas
	private	Float adj[][]; // Matriz de adjacência

    // Método Construtor
    // Argumentos: Número de vértices do grafo
    public TGrafoNDRotulado(int n) {  
	    this.n = n;
	    this.m = 0; 
	    this.adj = new Float[n][n];

		for(int i = 0; i< n; i++) {
            for(int j = 0; j< n; j++) {
                this.adj[i][j] = Float.POSITIVE_INFINITY;	
            }
        }
	}

    // Método que insere uma aresta no grafo não direcionado
    // Argumentos: Vértice de origem, vértice de destino, peso da aresta
	public void insereA(int v, int w, float peso) {
	    if(this.adj[v][w] == Float.POSITIVE_INFINITY ) { // Verifica se aresta ainda não existe
	        this.adj[v][w] = peso; // Cria aresta na matriz de adjacência
            this.adj[w][v] = peso; // Se [v][w] = peso, [w][v] = peso
	        this.m++; // Atualiza número de arestas do grafo
	    }
	}

    // Método que remove uma aresta do grafo não direcionado
    // Argumentos: Vértice de origem, vértice de destino
	public void removeA(int v, int w) {
	    if(this.adj[v][w] != Float.POSITIVE_INFINITY ) { // Verifica se aresta existe
	        this.adj[v][w] = Float.POSITIVE_INFINITY; // Remove aresta da matriz de adjacência
            this.adj[w][v] = Float.POSITIVE_INFINITY; // Se [v][w] = 0, [w][v] = 0
	        this.m--; // Atualiza número de arestas do grafo
	    }
	}

    // Método que exibe número de vértices, arestas e matriz de adjacência
	public void show() {
	    System.out.println("Vértices: " + n);
	    System.out.println("Arestas: " + m);
	    for(int i = 0; i < n; i++) {
	    	System.out.println();
	        for(int w = 0; w < n; w++) {
                if(this.adj[i][w] != Float.POSITIVE_INFINITY) {
                    System.out.print("Adj[" + i + "," + w + "] = " + this.adj[i][w] + " ");
                } else {
                    System.out.print("Adj[" + i + "," + w + "] = " + Float.POSITIVE_INFINITY + " ");
                }
            }
	    }
        System.out.println();
	}
}
