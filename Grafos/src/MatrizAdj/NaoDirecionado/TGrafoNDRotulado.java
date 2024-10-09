package MatrizAdj.NaoDirecionado;

/* INTEGRANTES DO GRUPO
 * Jônatas Garcia de Oliveira		10396490
 * Livia Alabarse dos Santos		10403046
 * Pedro Henrique Araujo Farias		10265432 
*/

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class TGrafoNDRotulado {
	private int n; // Número de vértices
	private int m; // Número de arestas
	private Float adj[][]; // Matriz de adjacência

	// Método Construtor
	// Argumentos: Número de vértices do grafo
	public TGrafoNDRotulado(int n) {
		this.n = n;
		this.m = 0;
		this.adj = new Float[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.adj[i][j] = Float.POSITIVE_INFINITY;
			}
		}
	}

	public TGrafoNDRotulado(String arquivo) {
		try {
			int origem, destino;
			Float peso;

			Scanner scanner = new Scanner(new File(arquivo));
			this.n = scanner.nextInt();
			int linhas = scanner.nextInt();

			this.adj = new Float[this.n][this.n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					this.adj[i][j] = Float.POSITIVE_INFINITY;
				}
			}

			for (int k = 0; k < linhas; k++) {
				origem = scanner.nextInt();
				destino = scanner.nextInt();
				peso = Float.parseFloat(scanner.nextLine());
				if (this.adj[origem][destino] == Float.POSITIVE_INFINITY) {
					this.adj[origem][destino] = peso;
					this.adj[destino][origem] = peso;
					this.m++; // atualiza qtd arestas
				}
			}

			scanner.close();

		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado.");
		}
	}

	// Método que insere uma aresta no grafo não direcionado
	// Argumentos: Vértice de origem, vértice de destino, peso da aresta
	public void insereA(int v, int w, float peso) {
		if (this.adj[v][w] == Float.POSITIVE_INFINITY) { // Verifica se aresta ainda não existe
			this.adj[v][w] = peso; // Cria aresta na matriz de adjacência
			this.adj[w][v] = peso; // Se [v][w] = peso, [w][v] = peso
			this.m++; // Atualiza número de arestas do grafo
		}
	}

	// Método que remove uma aresta do grafo não direcionado
	// Argumentos: Vértice de origem, vértice de destino
	public void removeA(int v, int w) {
		if (this.adj[v][w] != Float.POSITIVE_INFINITY) { // Verifica se aresta existe
			this.adj[v][w] = Float.POSITIVE_INFINITY; // Remove aresta da matriz de adjacência
			this.adj[w][v] = Float.POSITIVE_INFINITY; // Se [v][w] = 0, [w][v] = 0
			this.m--; // Atualiza número de arestas do grafo
		}
	}

	// Método que exibe número de vértices, arestas e matriz de adjacência
	public void show() {
		System.out.println("Vértices: " + n);
		System.out.println("Arestas: " + m);
		for (int i = 0; i < n; i++) {
			System.out.println();
			for (int w = 0; w < n; w++) {
				if (this.adj[i][w] != Float.POSITIVE_INFINITY) {
					System.out.print("Adj[" + i + "," + w + "] = " + this.adj[i][w] + " ");
				} else {
					System.out.print("Adj[" + i + "," + w + "] = inf ");
				}
			}
		}
		System.out.println();
	}

	public void printIntVetor(int[] vetor, String nome) {
		System.out.print(nome + ": ");
		for (int i = 0; i < this.n; i++) {
			System.out.print(vetor[i] + " ");
		}
		System.out.println();
	}

	public void printFloatVetor(float[] vetor, String nome) {
		System.out.print(nome + ": ");
		for (int i = 0; i < this.n; i++) {
			System.out.print(vetor[i] + " ");
		}
		System.out.println();
	}

	public ArrayList<Integer> getSucessores(int vertice) {
		ArrayList<Integer> sucessores = new ArrayList<Integer>();

		for (int i = 0; i < this.n; i++) { // percorre linha do vertice
			if (this.adj[vertice][i] != Float.POSITIVE_INFINITY) { // verifica se ha peso
				sucessores.add(i); // adiciona sucessor
			}
		}

		return sucessores;
	}

	// metodo que determina r
	public int getVerticeDistanciaMinima(float[] distancias, ArrayList<Integer> abertos) {
		float min = distancias[abertos.getFirst()]; // Menor distancia inicial
		int verticemin = abertos.getFirst(); // Vertice minimo e o primeiro vertice nos abertos

		for (Integer vertice : abertos) { // Percorre conjunto de abertos
			if (distancias[vertice] < min) { // Se distancia menor for encontrada
				min = distancias[vertice]; // Atualiza menor distancia
				verticemin = vertice; // Atualiza vertice da menor distancia
			}
		}

		return verticemin;
	}

	public ArrayList<Integer> getVizinhosDisponiveis(ArrayList<Integer> abertos, ArrayList<Integer> sucessores) {
		ArrayList<Integer> vizinhosDisponiveis = new ArrayList<Integer>();

		for (Integer vertice : sucessores) {
			if (abertos.indexOf(vertice) != -1) { // Se retorno != -1,
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
		for (int i = 0; i < this.n; i++) {
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

			for (Integer vizinho : vizinhosDisponiveis) { // Percorre elementos de S
				// p = min(d_i, (d_r + v_ri))
				concorrente = Math.min(distancias[vizinho], (distancias[atual] + this.adj[atual][vizinho]));
				if (concorrente < distancias[vizinho]) { // Se menor distancia encontrada
					distancias[vizinho] = concorrente; // Atualiza distancia ate vertice vizinho
					rotas[vizinho] = atual; // Atualiza rota para vertice vizinho
				}
			}
		}

		printFloatVetor(distancias, "Distâncias");
		printIntVetor(rotas, "Rotas");
	}

	public void getMinimalSpanningTree(int vertex) {
		float cost = 0.0f;
		float value = Float.POSITIVE_INFINITY;
		ArrayList<Integer> tree = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
		tree.add(vertex);

		cost = mstPrim(tree, edges, value);

		System.out.println("Custo minimo: " + cost);
		System.out.println("Arestas da arvore: " + edges);
	}

	private float mstPrim(ArrayList<Integer> tree, ArrayList<ArrayList<Integer>> edges, float value) {
		Integer origin = 0, destiny = 0;
		ArrayList<Integer> notTree = getNotTree(tree);

		for (Integer k : tree) {
			for (Integer i : notTree) {
				if (this.adj[k][i] < value) {
					value = this.adj[k][i];
					origin = k;
					destiny = i;
				}
			}
		}

		float cost = value;
		tree.add(destiny);
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(origin);
		array.add(destiny);
		edges.add(array);
		value = Float.POSITIVE_INFINITY;

		if (tree.size() == this.n)
			return cost;
		return cost + mstPrim(tree, edges, value);
	}

	private ArrayList<Integer> getNotTree(ArrayList<Integer> tree) {
		ArrayList<Integer> graphVertices = new ArrayList<Integer>();
		for (int i = 0; i < this.n; i++)
			graphVertices.add(i);

		for (Integer vertex : tree)
			graphVertices.remove(vertex);

		return graphVertices;
	}

}
