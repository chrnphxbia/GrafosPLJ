package MatrizAdj.Direcionado;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

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

	public void printIntVetor(int[] vetor, String nome) {
		System.out.print(nome + ": ");
		for(int i = 0; i < this.n; i++) {
			System.out.print(vetor[i] + " ");
		}
		System.out.println();
	}

	public void printFloatVetor(float[] vetor, String nome) {
		System.out.print(nome + ": ");
		for(int i = 0; i < this.n; i++) {
			System.out.print(vetor[i] + " ");
		}
		System.out.println();
	}

	public ArrayList<Integer> getSucessores(int vertice) {
		ArrayList<Integer> sucessores = new ArrayList<Integer>();

		for(int i = 0; i < this.n; i++) { // percorre linha do vertice
			if(this.adj[vertice][i] != Float.POSITIVE_INFINITY) { // verifica se ha peso
				sucessores.add(i); // adiciona sucessor
			}
		}

		return sucessores;
	}

	// metodo que determina r
	public int getVerticeDistanciaMinima(float[] distancias, ArrayList<Integer> abertos) {
		float min = distancias[abertos.getFirst()]; // Menor distancia inicial
		int verticemin = abertos.getFirst(); // Vertice minimo e o primeiro vertice nos abertos

		for(Integer vertice : abertos) { // Percorre conjunto de abertos
			if(distancias[vertice] < min) { // Se distancia menor for encontrada
				min = distancias[vertice]; // Atualiza menor distancia
				verticemin = vertice; // Atualiza vertice da menor distancia
			}	
		}

		return verticemin;
	}

	public ArrayList<Integer> getVizinhosDisponiveis(ArrayList<Integer> abertos, ArrayList<Integer> sucessores) {
		ArrayList<Integer> vizinhosDisponiveis = new ArrayList<Integer>();
		
		for(Integer vertice : sucessores) {
			if(abertos.indexOf(vertice) != -1) { // Se retorno != -1,
				vizinhosDisponiveis.add(vertice); // entao vizinho esta disponivel
			}
		}
		
		return vizinhosDisponiveis;
	}

	public void getDijkstra(int inicio) {
		ArrayList<Integer> vizinhosDisponiveis = new ArrayList<Integer>(); // S
		ArrayList<Integer> abertos = new ArrayList<Integer>(); // Conjunto de vertices abertos (ainda nao visitados)
		float[] distancias = new float[this.n]; // Vetor de distancias 
		int[] rotas = new int[this.n]; // Vetor de rotas
		float concorrente; // Distancia concorrente como distancia minima
		int atual; // Vertice atualmente visitado, r

		// Inicializando vetores
		for(int i = 0; i < this.n; i++) {
			rotas[i] = 0; 
			abertos.add(i); 
			distancias[i] = Float.POSITIVE_INFINITY;
		}
		
		distancias[inicio] = 0; // Vertice inicial tem distancia 0 de si mesmo
		vizinhosDisponiveis.add(inicio); // S e iniciado como inicio
		
		while (!abertos.isEmpty()) { // Enquanto ha vertices nao visitados
			atual = getVerticeDistanciaMinima(distancias, abertos); // Obtem r
			abertos.remove(abertos.indexOf(atual)); // Remove r dos abertos
			vizinhosDisponiveis = getVizinhosDisponiveis(abertos, getSucessores(atual)); // Obtem S

			for(Integer vizinho : vizinhosDisponiveis) { // Percorre elementos de S
				// p = min(d_i, (d_r + v_ri))
				concorrente = Math.min(distancias[vizinho], (distancias[atual] + this.adj[atual][vizinho]));
				if(concorrente < distancias[vizinho]) { // Se menor distancia encontrada
					distancias[vizinho] = concorrente; // Atualiza distancia ate vertice vizinho
					rotas[vizinho] = atual; // Atualiza rota para vertice vizinho
				}
			}
		}

		printFloatVetor(distancias, "Distâncias");
		printIntVetor(rotas, "Rotas");
	}
}