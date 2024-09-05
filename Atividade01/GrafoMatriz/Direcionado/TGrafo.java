import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

	public TGrafo(String arquivo) {
		try {
			int origem, destino;

			Scanner scanner = new Scanner(new File(arquivo));
			this.n = scanner.nextInt();
			int linhas = scanner.nextInt();

			this.adj = new int[this.n][this.n];
			
			for(int i = 0; i< n; i++) {
				for(int j = 0; j< n; j++) {
					this.adj[i][j]=0;
				}
			}

			for(int k = 0; k < linhas; k++) {
				origem = scanner.nextInt();
				destino = scanner.nextInt();
				insereA(origem, destino);
			}

			scanner.close();

		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado.");
		}
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

	/*
	(Exercício 1)
	Método que calcula e retorna o grau de entrada
	de um vértice v de um grafo dirigido
	*/
	public int inDegree(int v){
		int inDegree = 0;

		for (int i = 0; i < n ; i++) {
			if (adj[i][v]==1){
				inDegree++;
			}
		}
		return inDegree;
	}

	/*
	(Exercício 2)
	Método que calcula e retorna o grau de saída
	de um vértice v de um grafo dirigido
	*/
	public int outDegree(int v){
		int outDegree = 0;

		for (int j = 0; j < n; j++) {
			if (adj[v][j] == 1) {
				outDegree++;
			}
		}
		return outDegree;
	}
	
	/*
	(Exercício 3)
	Método que retorna 1 se vértice for uma fonte (grau de saída 
	maior que zero e grau de entrada igual a 0), ou 0 caso contrário.
	*/
	public int isFonte(int v) {
		if (outDegree(v) > 0 && inDegree(v) == 0) { return 1; } 
		return 0;
	}
	
	/*
	(Exercício 4)
	Método que retorna 1 se vértice for um Sorvedouro (grau de entrada
	maior que zero e grau de saída igual a 0), ou 0 caso contrário.
	*/
	public int isSorvedouro(int v){
		if (inDegree(v) > 0 && outDegree(v) == 0) { return 1; } 
		return 0;
	}

	/*
	(Exercício 5)
	Método que retorna 1 se se o grafo dirigido 
	for simétrico, ou 0 caso contrário.
	*/
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
	
		this.m = this.m - inDegree(v) - outDegree(v);
		this.n--;
		this.adj = novaMatriz;
	}


	/*
	(Exercício 11)
	Método que verifica e retorna se o grafo
	dirigido é completo. 
	*/
	public int isCompleto(){
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j && adj[i][j] == 0) {
					return 0;
				}
			}
		}
		return 1;
	}
}
