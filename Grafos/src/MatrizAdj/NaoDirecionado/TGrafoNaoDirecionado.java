package MatrizAdj.NaoDirecionado;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Coloracao.ClasseColoracao;
import MatrizAdj.FFilaCircular.*;
import java.io.FileNotFoundException;

public class TGrafoNaoDirecionado {
	private int n; // Número de vértices
	private int m; // Número de arestas
	private int adj[][]; // Matriz de adjacência

	// Método Construtor
	// Argumentos: Número de vértices do grafo
	public TGrafoNaoDirecionado(int n) {
		this.n = n;
		this.m = 0;
		this.adj = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.adj[i][j] = 0;
			}
		}
	}

	public int getVertices() {
		return this.n;
	}

	// Exercício 07
	// Método que insere uma aresta no grafo não direcionado
	// Argumentos: Vértice de origem, vértice de destino
	public void insereA(int v, int w) {
		if (this.adj[v][w] == 0) { // Verifica se aresta ainda não existe
			this.adj[v][w] = 1; // Cria aresta na matriz de adjacência
			this.adj[w][v] = 1; // Se [v][w] = 1, [w][v] = 1
			this.m++; // Atualiza número de arestas do grafo
		}
	}

	// Exercício 07
	// Método que remove uma aresta do grafo não direcionado
	// Argumentos: Vértice de origem, vértice de destino
	public void removeA(int v, int w) {
		if (this.adj[v][w] == 1) { // Verifica se aresta existe
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
		for (int i = 0; i < n; i++) {
			System.out.println();
			for (int w = 0; w < n; w++) {
				if (this.adj[i][w] == 1) {
					System.out.print("Adj[" + i + "," + w + "] = 1" + " ");
				} else {
					System.out.print("Adj[" + i + "," + w + "] = 0" + " ");
				}
			}
		}
		System.out.println();
	}

	public TGrafoNaoDirecionado(String arquivo) {
		try {
			int origem, destino;

			Scanner scanner = new Scanner(new File(arquivo));
			this.n = scanner.nextInt();
			int linhas = scanner.nextInt();

			this.adj = new int[this.n][this.n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					this.adj[i][j] = 0;
				}
			}

			for (int k = 0; k < linhas; k++) {
				origem = scanner.nextInt();
				destino = scanner.nextInt();
				if (this.adj[origem][destino] == 0) {
					this.adj[origem][destino] = 1;
					this.adj[destino][origem] = 1;
					m++; // atualiza qtd arestas
				}
			}

			scanner.close();

		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado.");
		}
	}

	public int getDegree(int v) {
		int degree = 0;

		for (int j = 0; j < n; j++) {
			if (adj[v][j] != 0) {
				degree++;
			}
		}

		return degree;
	}

	// Exercício 09
	// Método que remove vértice de grafo não direcionado
	// Argumentos: Vértice a ser removido-
	public void removeV(int v) {
		if (v >= this.n) {
			System.err.println("Não há vértice " + v + " no grafo.\nNão é " +
					"possível realizar remoção.");
			return;
		}

		int row, column;
		int novaMatriz[][] = new int[this.n - 1][this.n - 1];

		for (int i = 0; i < this.n - 1; i++) {
			row = i;
			if (i >= v) {
				row++;
			}
			for (int j = 0; j < this.n - 1; j++) {
				column = j;
				if (j >= v) {
					column++;
				}
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
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.n; j++) {
				if (i == j && this.adj[i][j] != 0) {
					return 0;
				}
				// Todos os outros números são diferentes de INFINTY
				if (i != j && this.adj[i][j] == 0) {
					return 0;
				}
			}
		}
		return 1;
	}

	// Exercício 12
	// Método que retorna a matriz do grafo complementar de um não direcionado
	public TGrafoNaoDirecionado getCompMatriz() {
		TGrafoNaoDirecionado temp = new TGrafoNaoDirecionado(this.n);

		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.n; j++) {
				if (i != j && this.adj[i][j] == 0) {
					temp.insereA(i, j);
				}
			}
		}

		return temp;
	}

	// Exercício 13
	// Método que retorna se um grafo não direcionado é conexo (0) ou desconexo (1)
	public int getConexidade() {
		int numVmarcados = this.depthFirstSearch(0).replaceAll("\\s", "").length();
		if (numVmarcados == this.n)
			return 0;
		return 1;
	}

	public String depthFirstSearch(int src) {
		boolean[] visited = new boolean[this.n];
		StringBuilder sb = new StringBuilder();
		dFSHelper(src, visited, sb);
		return sb.toString();
	}

	private void dFSHelper(int src, boolean[] visited, StringBuilder sb) {
		if (visited[src])
			return;
		visited[src] = true;
		sb.append(src).append(" ");

		for (int i = 0; i < this.n; i++) {
			if (this.adj[src][i] == 1) {
				dFSHelper(i, visited, sb);
			}
		}

		return;
	}

	public void breadthFirstSearch(int src) {
		FilaCircular queue = new FilaCircular();
		boolean[] visited = new boolean[this.n];

		queue.enqueue(src);
		visited[src] = true;

		while (!queue.qIsEmpty()) {
			src = queue.dequeue();
			System.out.print(src + " ");

			for (int i = 0; i < this.n; i++) {
				if (this.adj[src][i] == 1 && !visited[i]) {
					queue.enqueue(i);
					visited[i] = true;
				}
			}
		}
	}

	private ArrayList<Integer> getVizinhos(int vertice) {
		ArrayList<Integer> vizinhos = new ArrayList<Integer>();

		for(int i = 0; i < this.n; i++) {
			if(this.adj[vertice][i] != 0) {
				vizinhos.add(i);
			}
		}

		return vizinhos;
	}

	private ArrayList<Integer> getIntersec(ArrayList<Integer> array1, ArrayList<Integer> array2) {
		ArrayList<Integer> intersec = new ArrayList<Integer>();

		for (Integer vertice : array1) {
			if(array2.contains(vertice)) {
				intersec.add(vertice);
			}
		}

		return intersec;
	}

	public ClasseColoracao[] getColoracaoWelshPowell() {
		int k = 0;
		ArrayList<Integer> aux = new ArrayList<Integer>();
		ArrayList<Integer> toBeRemoved = new ArrayList<Integer>();
		ClasseColoracao[] classes = new ClasseColoracao[this.n];

		for(int i = 0; i < this.n; i++) {
			classes[i] = new ClasseColoracao();
			aux.add(i);
		}

		while(!aux.isEmpty()) {
			for (Integer vertice : aux) {
				ArrayList<Integer> vizinhos = getVizinhos(vertice);
				ArrayList<Integer> intersec = getIntersec(vizinhos, classes[k].getVertices());
				
				if(intersec.isEmpty()) {
					classes[k].addVertice(vertice);
					toBeRemoved.add(vertice);
				}
			}

			aux.removeAll(toBeRemoved);
			toBeRemoved.clear();
			k++;
		}

		return classes;
	}

	public void showClassesColoracao() {
		int numClasse = 0;
		ClasseColoracao[] classesColoracao = getColoracaoWelshPowell();

		System.out.println("Classes de coloração do Grafo: ");

		for (ClasseColoracao classeColoracao : classesColoracao) {
			if(!classeColoracao.getVertices().isEmpty()) {
				System.out.println("Classe " + numClasse + ": " + 
				classeColoracao.getVertices());

				numClasse++;
			}
		}
	}
}