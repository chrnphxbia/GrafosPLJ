//definição de uma estrutura Matriz de Adjacência para armezanar um grafo
public class TGrafo {
	// Atributos Privados
	private	int n; // quantidade de vértices
	private	int m; // quantidade de arestas
	private	int adj[][]; //matriz de adjacência
	// Métodos Públicos
	public TGrafo( int n) {  // construtor
	    this.n = n;
	    // No início dos tempos não há arestas
	    this.m = 0; 
	    // alocação da matriz do TGrafo
	    this.adj = new int [n][n];

	    // Inicia a matriz com zeros
		for(int i = 0; i< n; i++)
			for(int j = 0; j< n; j++)
				this.adj[i][j]=0;	
	}

	// Insere uma aresta no Grafo tal que
	// v é adjacente a w
	public void insereA(int v, int w) {
	    // testa se nao temos a aresta
	    if(adj[v][w] == 0 ){
	        adj[v][w] = 1;
	        m++; // atualiza qtd arestas
	    }
	}
	
	// remove uma aresta v->w do Grafo	
	public void removeA(int v, int w) {
	    // testa se temos a aresta
	    if(adj[v][w] == 1 ){
	        adj[v][w] = 0;
	        m--; // atualiza qtd arestas
	    }
	}
	// Apresenta o Grafo contendo
	// número de vértices, arestas
	// e a matriz de adjacência obtida	
	public void show() {
	    System.out.println("n: " + n );
	    System.out.println("m: " + m );
	    for( int i=0; i < n; i++){
	    	System.out.print("\n");
	        for( int w=0; w < n; w++)
	            if(adj[i][w] == 1)
	            	System.out.print("Adj[" + i + "," + w + "]= 1" + " ");
	            else System.out.print("Adj[" + i + "," + w + "]= 0" + " ");
	    }
	    System.out.println("\n\nfim da impressao do grafo." );
	}

	public int inDegree(int v){
		int inDegree = 0;

		for (int i = 0; i < n ; i++) {
			if (adj[i][v]==1){
				inDegree++;
			}
		}
		return inDegree;
	}

	public int outDegree(int v){
		int outDegree = 0;

		for (int j = 0; j < n; j++) {
			if (adj[v][j] == 1) {
				outDegree++;
			}
		}
		return outDegree;
	}
	
	public int isFonte(int v) {
		if (outDegree(v) > 0 && inDegree(v) == 0) { return 1; } 
		return 0;
	}

	public int isSorvedouro(int v){
		if (inDegree(v) > 0 && outDegree(v) == 0) { return 1; } 
		return 0;
	}

	public int isSimetrico(){
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++){
				if (adj[i][j] != adj[j][i]) {
					return 0;
				}
			}
		}
		return 1;
	}


}
