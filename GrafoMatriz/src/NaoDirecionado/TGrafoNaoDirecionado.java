package NaoDirecionado;

public class TGrafoNaoDirecionado {
	private	int n; // Número de vértices
	private	int m; // Número de arestas
	private	int adj[][]; // Matriz de adjacência

    // Método Construtor
    // Argumentos: Número de vértices do grafo
	public TGrafoNaoDirecionado(int n) {  
	    this.n = n;
	    this.m = 0; 
	    this.adj = new int [n][n];

		for(int i = 0; i< n; i++) {
            for(int j = 0; j< n; j++) {
                this.adj[i][j] = 0;	
            }
        }
	}

	public int getVertices() { return this.n; }

    // Exercício 07
    // Método que insere uma aresta no grafo não direcionado
    // Argumentos: Vértice de origem, vértice de destino
	public void insereA(int v, int w) {
	    if(this.adj[v][w] == 0 ) { // Verifica se aresta ainda não existe
	        this.adj[v][w] = 1; // Cria aresta na matriz de adjacência
            this.adj[w][v] = 1; // Se [v][w] = 1, [w][v] = 1
	        this.m++; // Atualiza número de arestas do grafo
	    }
	}
	
    // Exercício 07
    // Método que remove uma aresta do grafo não direcionado
    // Argumentos: Vértice de origem, vértice de destino
	public void removeA(int v, int w) {
	    if(this.adj[v][w] == 1 ) { // Verifica se aresta existe
	        this.adj[v][w] = 0; // Remove aresta da matriz de adjacência
            this.adj[w][v] = 0; // Se [v][w] = 0, [w][v] = 0
	        this.m--; // Atualiza número de arestas do grafo
	    }
	}
	
    // Exercício 07
    // Método que exibe número de vértices, arestas e matriz de adjacência
	public void show() {
	    System.out.println("Vértices: " + n);
	    System.out.println("Arestas: " + m);
	    for(int i = 0; i < n; i++) {
	    	System.out.println();
	        for(int w = 0; w < n; w++) {
                if(this.adj[i][w] == 1) {
                    System.out.print("Adj[" + i + "," + w + "] = 1" + " ");
                } else {
                    System.out.print("Adj[" + i + "," + w + "] = 0" + " ");
                }
            }
	    }
        System.out.println();
	}

	public int getDegree(int v) {
		int degree = 0;

		for(int j = 0; j < n; j++) {
			if(adj[v][j] != 0) {
				degree++;
			}
		}

		return degree;
	}

	// Exercício 09
	// Método que remove vértice de grafo não direcionado
	// Argumentos: Vértice a ser removido
	public void removeV(int v) {
		if(v >= this.n) {
			System.err.println("Não há vértice " + v + " no grafo.\nNão é " +
				"possível realizar remoção.");
			return;
		}

		int row, column;
		int novaMatriz[][] = new int[this.n-1][this.n-1];

		for(int i = 0; i < this.n - 1; i++) {
			row = i;
			if(i >= v) { row++; }
			for(int j = 0; j < this.n - 1; j++) {
				column = j;
				if(j >= v) { column++; }
				novaMatriz[i][j] = this.adj[row][column];
			}
		}
	
		this.m = this.m - getDegree(v);
		this.n--;
		this.adj = novaMatriz;
	}

	// Exercício 10
	// Método que verifica se grafo não direcionado é completo
    public int verificarGrafoCompleto() {
        for(int i = 0; i < this.n; i++) {
            for(int j = 0; j < this.n; j++) {
                // Diagonal principal precisa ser INFINITY
                
                if(i == j  && this.adj[i][j] != 0) {
                    return 0;
                }
                // Todos os outros números são diferentes de INFINTY
                if(i != j && this.adj[i][j] == 0) {
                    return 0;
                }
            }
        }
        return 1;
    }

	// Exercício 12
	// Método que retorna a matriz do grafo complementar de um não direcionado
	public int[][] getCompMatriz() {
		TGrafoNaoDirecionado temp = new TGrafoNaoDirecionado(this.n);

		for(int i = 0; i < this.n; i++) {
			for(int j = 0; j < this.n; j++) {
				if(i != j && this.adj[i][j] == 0) {
					temp.insereA(i, j);
				}
			}
		}

		return temp.adj;
	}
}
