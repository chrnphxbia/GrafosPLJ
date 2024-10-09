package MatrizAdj.Direcionado;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import MatrizAdj.PPilha.*;
import java.io.FileNotFoundException;
import MatrizAdj.FFilaCircular.FilaCircular;

public class TGrafoDirecionado {
	private	int n; // Número de vértices
	private	int m; // Número de arestas
	private	int adj[][]; // Matriz de adjacência

    // Método Construtor
    // Argumentos: Número de vértices do grafo
	public TGrafoDirecionado(int n) {  
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

    // Método que insere uma aresta no grafo direcionado
    // Argumentos: Vértice de origem, vértice de destino
	public void insereA(int v, int w) {
	    if(this.adj[v][w] == 0 ) { // Verifica se aresta ainda não existe
	        this.adj[v][w] = 1; // Cria aresta na matriz de adjacência
	        this.m++; // Atualiza número de arestas do grafo
	    }
	}
	
    // Método que remove uma aresta do grafo direcionado
    // Argumentos: Vértice de origem, vértice de destino
	public void removeA(int v, int w) {
	    if(this.adj[v][w] == 1 ) { // Verifica se aresta existe
	        this.adj[v][w] = 0; // Remove aresta da matriz de adjacência
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
                if(this.adj[i][w] == 1) {
                    System.out.print("Adj[" + i + "," + w + "] = 1" + " ");
                } else {
                    System.out.print("Adj[" + i + "," + w + "] = 0" + " ");
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
			if (this.adj[i][v]==1){
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
			if (this.adj[v][j] == 1) {
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
    public TGrafoDirecionado(String arquivo) {
		try {
			int origem, destino;

			Scanner scanner = new Scanner(new File(arquivo));
			this.n = scanner.nextInt();
			int linhas = scanner.nextInt();

			this.adj = new int[this.n][this.n];
			
			for(int i = 0; i< n; i++) {
				for(int j = 0; j< n; j++) {
					this.adj[i][j] = 0;
				}
			}

			for(int k = 0; k < linhas; k++) {
				origem = scanner.nextInt();
				destino = scanner.nextInt();
				if(this.adj[origem][destino] == 0 ){
					this.adj[origem][destino] = 1;
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

	// Exercício 12
	// Método que retorna complemento de um grafo direcionado em forma de matriz
	public TGrafoDirecionado getCompMatriz() {
		TGrafoDirecionado temp = new TGrafoDirecionado(this.n);

		for(int i = 0; i < this.n; i++) {
			for(int j = 0; j < this.n; j++) {
				if(i != j && this.adj[i][j] == 0) {
					temp.insereA(i, j);
				}
			}
		}

		return temp;
	}

	// Exercício 14
	// Método que retorne a categoria de conexidade para um grafo direcionado 
	// (3 – C3, 2 – C2, 1 – C1 ou 0 – c0).
	public int getCategoriaConexidade() {
		// Verifica se é fortemente conexo (C3)
		if (f_conexo()) { return 3; }
		// Verifica se o grafo é fracamente conexo (C2)
		if (sf_conexo()) { return 2; }
 		// Verifica se o grafo é desconexo (C0)
		if (desconexo()) { return 0; }
		// Caso contrário, é unilateralmente conexo (C1)
		return 1;
	}

	private boolean f_conexo() {
		for (int v = 0; v < n; v++){
			ArrayList<Integer> percurso = depthFirstSearch(v); // Realiza o percurso em profundidade
			if (percurso.size() != n) {
				return false; // Se nem todos os vértices foram visitados, não é fortemente conexo
			}
		}
		return true; // Se todos os vértices foram visitados
	}

	private boolean sf_conexo() {
		for (int v = 0; v < n; v++){
			for (int w = 0; w < n; w++){
				if (v != w){
					ArrayList<Integer> percursoV = depthFirstSearch(v); // Percurso a partir de v
					ArrayList<Integer> percursoW = depthFirstSearch(w); // Percurso a partir de w
					if (!percursoV.contains(w) && !percursoW.contains(v)){
						return false; // Se não há conexão entre v e w, o grafo não é fracamente conexo
					}
				}
			}
		}
		return true;
	}

	private boolean desconexo() {
		// Criar a matriz simétrica (não direcionada) do grafo
        	TGrafoDirecionado simetrico = new TGrafoDirecionado(n);

        	for (int i = 0; i < n; i++) {
            		for (int j = 0; j < n; j++) {
               			if (this.adj[i][j] == 1 || this.adj[j][i] == 1) {
                    			simetrico.adj[i][j] = 1;
                    			simetrico.adj[j][i] = 1;
                		}
            		}
        	}

		// Verificar a conectividade na matriz simétrica usando percurso em profundidade
       		ArrayList<Integer> percurso = simetrico.depthFirstSearch(0);
        	return percurso.size() != n; // Se não visitou todos os vértices, o grafo é desconexo
	}

	// Exercício 15
 	// Método que retorne o grafo reduzido de um grafo direcionado no formato de uma matriz de adjacência
	public TGrafoDirecionado getGrafoReduzido() {
		// Passo 1: DFS no grafo original para obter ordem de finalização
		Pilha pilha = new Pilha(n);
		boolean[] visited = new boolean[n];
		
		// Usar um único laço DFS para preencher a pilha de finalização
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfs(i, visited, pilha);
			}
		}
	
		// Passo 2: Obter grafo revertido
		TGrafoDirecionado grafoRevertido = getGrafoRevertido();
	
		// Passo 3: BFS no grafo revertido para identificar componentes fortemente conectadas
		Arrays.fill(visited, false); // Limpar o vetor de visitados
		int[] componenteDeVertice = new int[n];   // Mapear vértices para componentes
		int numComponentes = 0;   // Contador de componentes
	
		// Processar os vértices pela ordem de finalização
		while (!pilha.isEmpty()) {
			int v = pilha.pop();
			if (!visited[v]) {
				bfsComponente(v, grafoRevertido, visited, componenteDeVertice, numComponentes++);
			}
		}
	
		// Criar e preencher o grafo reduzido com arestas entre componentes diferentes
		return construirGrafoReduzido(componenteDeVertice, numComponentes);
	}

	// DFS para ordem de finalização
	private void dfs(int v, boolean[] visited, Pilha pilha) {
		visited[v] = true;
		for (int i = 0; i < n; i++) {
			if (adj[v][i] == 1 && !visited[i]) {
				dfs(i, visited, pilha);
			}
		}
		pilha.push(v); // Adicionar à pilha após a DFS
	}

	// BFS para identificar componentes fortemente conectadas
	private void bfsComponente(int v, TGrafoDirecionado grafoRevertido, boolean[] visited, int[] componenteDeVertice, int numComponentes) {
		FilaCircular fila = new FilaCircular(n);
		fila.enqueue(v);
		visited[v] = true;
	
		while (!fila.qIsEmpty()) {
			int verticeAtual = fila.dequeue();
			componenteDeVertice[verticeAtual] = numComponentes;
	
			// Visitar vizinhos não visitados
			for (int i = 0; i < n; i++) {
				if (grafoRevertido.adj[verticeAtual][i] == 1 && !visited[i]) {
					fila.enqueue(i);
					visited[i] = true;
				}
			}
		}
	}

	// Construir o grafo reduzido com base nas componentes
	private TGrafoDirecionado construirGrafoReduzido(int[] componenteDeVertice, int numComponentes) {
		TGrafoDirecionado grafoReduzido = new TGrafoDirecionado(numComponentes);
	
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (this.adj[i][j] == 1) {
					int compI = componenteDeVertice[i];
					int compJ = componenteDeVertice[j];
					if (compI != compJ) {
						grafoReduzido.insereA(compI, compJ);
					}
				}
			}
		}
		return grafoReduzido;
	}

	// Retorna o grafo revertido
	private TGrafoDirecionado getGrafoRevertido() {
		TGrafoDirecionado grafoRevertido = new TGrafoDirecionado(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (adj[i][j] == 1) {
					grafoRevertido.insereA(j, i);
				}
			}
		}
		return grafoRevertido;
	}

	public ArrayList<Integer> depthFirstSearch(int src) {
		boolean[] visited = new boolean[this.n];
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		dFSHelper(src, visited, vertices);
		return vertices;
	}

	private void dFSHelper(int src, boolean[] visited, ArrayList<Integer> vertices) {
		if(visited[src]) return;
		visited[src] = true;
		vertices.add(src);

		for(int i = 0; i < this.n; i++) {
			if(this.adj[src][i] == 1) {
				dFSHelper(i, visited, vertices);
			}
		}

		return;
	}

	public String breadthFirstSearch(int src) {
		StringBuilder sb = new StringBuilder();
		FilaCircular queue = new FilaCircular();
		boolean[] visited = new boolean[this.n];

		queue.enqueue(src);
		visited[src] = true;

		while(!queue.qIsEmpty()) {
			src = queue.dequeue();
			sb.append(src).append(" ");

			for(int i = 0; i < this.n; i++) {
				if(this.adj[src][i] == 1 && !visited[i]) {
					queue.enqueue(i);
					visited[i] = true;
				}
			}
		}

		return sb.toString();
	}

	// ORDENAÇÃO TOPOLÓGICA
    public void ordenacaoTopologica(){
        int[] grauEntrada = new int[n];
        FilaCircular fila = new FilaCircular(n);

        // Inicializar os graus de entrada e enfileirar vértices com grau 0
        for (int v = 0; v < n; v ++) {
            grauEntrada[v] = inDegree(v);
            if(grauEntrada[v] == 0) {
                fila.enqueue(v);
                grauEntrada[v] = -1;
            }
        }

        // Processar a fila até que fique vazia
        while (!fila.qIsEmpty()) {
            int v = fila.dequeue();
            System.out.print(v + " ");

            // Atualizar o grau de entrada dos vértices adjacentes
            for(int w = 0; w < n; w++){
                if(this.adj[v][w] == 1){
                    grauEntrada[w]--;
                    if(grauEntrada[w] == 0){
                        fila.enqueue(w);
                        grauEntrada[w] = -1;
                    }
                }
            }
        }
        System.out.println();
    }
}