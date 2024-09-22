package graph;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class WUGraph {
    private	int n; // Número de vértices
	private	int m; // Número de arestas
	private	Integer adj[][]; // Matriz de adjacência
	private String rotulos[]; // Rótulos dos vértices

    // Método Construtor
    // Argumentos: Número de vértices do grafo
    public WUGraph(int n) {  
	    this.n = n;
	    this.m = 0; 
	    this.adj = new Integer[n][n];

		for(int i = 0; i< n; i++) {
            for(int j = 0; j< n; j++) {
                this.adj[i][j] = Integer.MAX_VALUE;	
            }
        }
	}

	// Método Construtor
	// Argumentos: Nome do arquivo a ser lido (grafo.txt)
	public WUGraph(String arquivo) {
		String rotuloAtual;
		int vertice, arestas, origem, destino, peso;

		try {
			Scanner scanner = new Scanner(new File(arquivo));

			scanner.nextLine(); // Lê primeira linha com tipo de grafo
			this.n = scanner.nextInt(); // Lê segunda linha com número de vértices
			this.adj = new Integer[this.n][this.n]; // Cria matriz com n vértices
			this.rotulos = new String[this.n]; // Cria vetor de rótulos das aves

			for(int i = 0; i < this.n; i++) {
				for(int j = 0; j < this.n; j++) { // Inicializa matriz com INF
					this.adj[i][j] = Integer.MAX_VALUE;
				}
			}

			for(int i = 0; i < this.n; i++) {
				vertice = scanner.nextInt(); // Lê vértice
				rotuloAtual = scanner.nextLine().stripLeading(); // Lê rótulo
				this.rotulos[vertice] = rotuloAtual; // Atribui rótulo na posição do vértice
			}

			arestas = scanner.nextInt(); // Lê número de arestas

			for(int i = 0; i < arestas; i++) {
				origem = scanner.nextInt(); // Lê vértice de origem
				destino = scanner.nextInt(); // Lê vértice de destino
				peso = scanner.nextInt(); // Lê peso da aresta
				this.adj[origem][destino] = peso; // Cria aresta com peso
				this.adj[destino][origem] = peso; // [v][w] = [w][v]
				this.m++; // Atualiza quantidade de arestas
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Getters dos atributos privados do Grafo
	public int getVertices() { return this.n; }
	public int getArestas() { return this.m; }
	public Integer[][] getMatrizAdj() { return this.adj; }
	public String[] getRotulos() { return this.rotulos; }

	// Método que insere vértice no grafo
	// Argumentos: Rótulo do vértice a ser adicionado
	public void insereV(String rotulo) {
		Integer[][] novaMatriz = new Integer[this.n + 1][this.n + 1];
		String[] novoVetor = new String[this.n + 1];

		for(int i = 0; i < this.n; i++) {
			for(int j = 0; j < this.n; j++) {
				novaMatriz[i][j] = this.adj[i][j];
				novaMatriz[j][this.n] = Integer.MAX_VALUE;
			}

			novaMatriz[this.n][i] = Integer.MAX_VALUE;
			novoVetor[i] = this.rotulos[i];
		}

		novaMatriz[this.n][this.n] = Integer.MAX_VALUE;

		this.adj = novaMatriz;
		novoVetor[this.n] = rotulo;
		this.rotulos = novoVetor;
		this.n++;
	}

    // Método que insere uma aresta no grafo não direcionado
    // Argumentos: Vértice de origem, vértice de destino, peso da aresta
	public void insereA(int v, int w, int peso) {
	    if(this.adj[v][w] == Integer.MAX_VALUE ) { // Verifica se aresta ainda não existe
	        this.adj[v][w] = peso; // Cria aresta na matriz de adjacência
            this.adj[w][v] = peso; // Se [v][w] = peso, [w][v] = peso
	        this.m++; // Atualiza número de arestas do grafo
	    }
	}

	// Método que remove um vértice do grafo não direcionado
	// Argumentos: Vértice a ser removido
	public void removeV(int vertice) {
		if(vertice >= this.n) {
			System.err.println("Não há vértice " + vertice + " no grafo.\nNão é " +
				"possível realizar remoção.");
			return;
		}

		int row, column;
		String[] novoVetor = new String[this.n - 1];
		Integer novaMatriz[][] = new Integer[this.n-1][this.n-1];

		for(int i = 0; i < this.n - 1; i++) {
			row = i;
			if(i >= vertice) row++; 

			for(int j = 0; j < this.n - 1; j++) {
				column = j;
				if(j >= vertice) column++;
				novaMatriz[i][j] = this.adj[row][column];
			}

			novoVetor[i] = this.rotulos[row];
		}
	
		this.m = this.m - getDegree(vertice);
		this.n--;
		this.adj = novaMatriz;
		this.rotulos = novoVetor;
	}

    // Método que remove uma aresta do grafo não direcionado
    // Argumentos: Vértice de origem, vértice de destino
	public void removeA(int v, int w) {
	    if(this.adj[v][w] != Integer.MAX_VALUE ) { // Verifica se aresta existe
	        this.adj[v][w] = Integer.MAX_VALUE; // Remove aresta da matriz de adjacência
            this.adj[w][v] = Integer.MAX_VALUE; // [v][w] = [w][v]
	        this.m--; // Atualiza número de arestas do grafo
	    }
	}

	// Método que retorna o grau de um vértice no grafo não direcionado
	// Argumentos: vértice a ter seu grau calculado
	public int getDegree(int vertice) {
		int degree = 0;

		for(int j = 0; j < n; j++) {
			if(this.adj[vertice][j] != Integer.MAX_VALUE) {
				degree++;
			}
		}

		return degree;
	}

	// Método que retorna se grafo é conexo (0) ou desconexo (1)
	public int getConexidade() {
		int numVmarcados = this.depthFirstSearch(0).replaceAll("\\s","").length();
		if(numVmarcados == this.n) return 0;
		return 1;
	}

	// Método que realiza busca em profundidade no grafo
	// Argumentos: Vértice de origem
	public String depthFirstSearch(int src) {
		boolean[] visited = new boolean[this.n];
		StringBuilder sb = new StringBuilder();
		dFSHelper(src, visited, sb);
		return sb.toString();
	}

	// Método auxiliar para busca em profundidade no grafo 
	// Argumentos: Vértice de origem, vetor de vértices visitados, String de caminho em profundidade
	private void dFSHelper(int src, boolean[] visited, StringBuilder sb) {
		if(visited[src]) return;
		visited[src] = true;
		sb.append(src).append(" ");

		for(int i = 0; i < this.n; i++) {
			if(this.adj[src][i] != Integer.MAX_VALUE) {
				dFSHelper(i, visited, sb);
			}
		}

		return;
	}

    // Método que exibe número de vértices, arestas e matriz de adjacência
	public void show() {
	    System.out.println("Vértices: " + n);
	    System.out.println("Arestas: " + m);
	    for(int i = 0; i < n; i++) {
	    	System.out.println();
	        for(int w = 0; w < n; w++) {
                if(this.adj[i][w] != Integer.MAX_VALUE) {
                    System.out.print("[" + i + "," + w + "] = " + this.adj[i][w] + " ");
                } else {
                    System.out.print("[" + i + "," + w + "] = inf ");
                }
            }
	    }
        System.out.println();
	}

	// Método que escreve a matriz de adjacência em um .txt, em um formato aceito pelo GraphOnline
	public void writeAdjMatrixGraphOnline() {
		try {
			FileWriter writer = new FileWriter("assets/GraphOnlineFile.txt");

			for(int i = 0; i < this.n; i++) {
				for(int j = 0; j < this.n; j++) {
					if(i != j) writer.write(Integer.toString(this.adj[i][j]));
					else writer.write("0");
					writer.write(", ");
				}

				writer.write("\n");
			}

			System.out.println("Grafo escrito em assets/GraphOnlineFile.txt");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Método que guarda os dados do grafo de acordo com formato definido em grafo.txt
	// Argumentos: Nome do arquivo onde serão gravados os dados (grafo.txt)
	public void writeToFile(String fileName) {
		String path = "assets/";
		int origem, destino, peso;

		try {
			FileWriter writer = new FileWriter(path.concat(fileName));

			writer.write("2\n"); // Escrevendo tipo do grafo
			writer.write(this.n + "\n"); // Escrevendo número de vértices

			for(int i = 0; i < this.n; i++) {
				writer.write(i + " "); // Escrevendo número do vértice
				writer.write(this.rotulos[i] + "\n"); // Escrevendo rótulo do vértice
			}

			writer.write(this.m + "\n"); // Escrevendo número de arestas

			int aux[][] = new int[this.n][this.n]; // Criando matriz auxiliar

			for(int i = 0; i < this.n; i++) { // Inicializando matriz auxiliar
				for(int j = 0; j < this.n; j++) {
					if(i != j) aux[i][j] = 0; 
					else aux[i][j] = 1; // Ignora-se laços
				}
			}

			// Como em grafos não direcionados, se [v][w] existe então [w][v]
			// também, é ideal evitar a duplicação de arestas no arquivo.
			// Utiliza-se a matriz auxiliar para verificar se a aresta [v][w]
			// já foi gravada no arquivo quando tentar gravar a aresta [w][v]

			for(int i = 0; i < this.n; i++) {
				for(int j = 0; j < this.n; j++) {
					if(aux[i][j] != 1) { // Se aresta ainda não foi gravada
						origem = i; // Vértice de origem
						destino = j; // Vértice de destino
						peso = this.adj[origem][destino]; // Peso da aresta

						// Escreve-se "origem destino peso" no arquivo
						writer.write(origem + " " + destino + " " + peso + "\n");

						// Marca aresta como gravada na matriz auxiliar
						aux[i][j] = 1;
						aux[j][i] = 1;
					}
				}
			}

			writer.close();
			System.out.println("Dados gravados em grafo.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
