package MatrizAdj.Direcionado;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TGrafoDRotulado {
	private	int n; // Número de vértices
	private	int m; // Número de arestas
	private	Float adj[][]; // Matriz de adjacência

    // Método Construtor
    // Argumentos: Número de vértices do grafo
	public TGrafoDRotulado(int n) {  
	    this.n = n;
	    this.m = 0; 
	    this.adj = new Float[n][n];

		for(int i = 0; i< n; i++) {
            for(int j = 0; j< n; j++) {
                this.adj[i][j] = Float.POSITIVE_INFINITY;	
            }
        }
	}

	public int getVertices() { return this.n; }

    // Método que insere uma aresta no grafo direcionado
    // Argumentos: Vértice de origem, vértice de destino
	public void insereA(int v, int w, float peso) {
	    if(this.adj[v][w] == Float.POSITIVE_INFINITY ) { // Verifica se aresta ainda não existe
	        this.adj[v][w] = peso; // Cria aresta na matriz de adjacência
	        this.m++; // Atualiza número de arestas do grafo
	    }
	}
	
    // Método que remove uma aresta do grafo direcionado
    // Argumentos: Vértice de origem, vértice de destino
	public void removeA(int v, int w) {
	    if(this.adj[v][w] != Float.POSITIVE_INFINITY) { // Verifica se aresta existe
	        this.adj[v][w] = Float.POSITIVE_INFINITY; // Remove aresta da matriz de adjacência
	        this.m--; // Atualiza número de arestas do grafo
	    }
	}
	
    // Método que exibe número de vértices, arestas e matriz de adjacência
	public void show() {
	    System.out.println("Vértices: " + n);
	    System.out.println("Arestas: " + m);
	    for(int i=0; i < n; i++) {
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

    // Exercício 01
    // Método que calcula grau de entrada de vértice em grafo direcionado
    // Argumentos: Vértice a ter grau de entrada determinado
    public int inDegree(int v) {
		int inDegree = 0; 

		for (int i = 0; i < n ; i++) {
			if(this.adj[i][v] != Float.POSITIVE_INFINITY) {
				inDegree++;
			}
		}
		return inDegree;
	}

    // Exercício 02
	// Método que calcula grau de saída de vértice em grafo direcionado
    // Argumentos: Vértice a ter grau de saída determinado
	public int outDegree(int v) {
		int outDegree = 0;

		for (int j = 0; j < n; j++) {
			if (this.adj[v][j] != Float.POSITIVE_INFINITY) {
				outDegree++;
			}
		}
		return outDegree;
	}

	// Exercício 03
	// Método que retorna 1 se vértice for uma fonte (grau de saída 
	// maior que zero e grau de entrada igual a 0), ou 0 caso contrário.
    // Argumentos: Vértice a ser determinado como fonte ou não
	public int isFonte(int v) {
		if (outDegree(v) > 0 && inDegree(v) == 0) { return 1; } 
		return 0;
	}

	// Exercício 04
	// Método que retorna 1 se vértice for um Sorvedouro (grau de entrada
	// maior que zero e grau de saída igual a 0), ou 0 caso contrário.
    // Argumentos: Vértice a ser determinado como sorvedouro ou não
	public int isSorvedouro(int v) {
		if (inDegree(v) > 0 && outDegree(v) == 0) { return 1; } 
		return 0;
	}

	// Exercício 05
	// Método retorna 1 se o grafo dirigido for simétrico, ou 0 caso contrário.
	public int isSimetrico() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++){
				if (adj[i][j] != adj[j][i]) {
					return 0;
				}
			}
		}
		return 1;
	}

    // Exercício 06
    // Método Construtor
    // Argumentos: Caminho para arquivo de leitura
    public TGrafoDRotulado(String arquivo) {
		try {
			int origem, destino;
            Float peso;

			Scanner scanner = new Scanner(new File(arquivo));
			this.n = scanner.nextInt();
			int linhas = scanner.nextInt();

			this.adj = new Float[this.n][this.n];
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					this.adj[i][j] = Float.POSITIVE_INFINITY;
				}
			}

			for(int k = 0; k < linhas; k++) {
				origem = scanner.nextInt();
				destino = scanner.nextInt();
                peso = Float.parseFloat(scanner.nextLine());
				if(this.adj[origem][destino] == Float.POSITIVE_INFINITY) {
					this.adj[origem][destino] = peso;
					this.m++; // atualiza qtd arestas
				}
			}

			scanner.close();

		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado.");
		}
	}

	// Exercício 09
	// Método que remove vértice do grafo direcionado
	// Argumentos: Vértice a ser removido
	public void removeV(int v) {
		if(v >= this.n) {
			System.err.println("Não há vértice " + v + " no grafo.\nNão é " +
				"possível realizar remoção.");
			return;
		}

		int row, column;
		Float novaMatriz[][] = new Float[this.n-1][this.n-1];

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

	// Exercício 11
	// Método que verifica se grafo direcionado é completo
	public int isCompleto() {
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