/*  
    INTEGRANTES DO PROJETO ARAGRAPH
    Jônatas Garcia de Oliveira      10396490
    Livia Alabarse dos Santos       10403046
    Pedro Henrique Araujo Farias    10265432

    Este arquivo apresenta a classe de implementação de um Grafo Não Direcionado
	Rotulado (Weighted Undirected Graph), a partir da qual serão realizadas 
	chamadas para processamento dos métodos da estrutura de dados.

    DATA            AUTOR       ATUALIZAÇÃO       
    23/09/2024     	Pedro       Corrigido DFS
	30/10/2024		Pedro		Atualizando estrutura e adicionando funções
	06/11/2024		Pedro		Finalizando parte 2 do projeto
	17/11/2024		Pedro		Adicionando comentarios
*/

package graph;

import aves.Ave;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class WUGraph {
    private	int n; // Número de vértices
	private	int m; // Número de arestas
	private	Integer adj[][]; // Matriz de adjacência
	private Ave aves[]; // Aves adicionadas ao grafo

	// Método Construtor Vazio
	public WUGraph() {} 

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
			this.aves = new Ave[this.n]; // Cria vetor de aves

			for(int i = 0; i < this.n; i++) {
				for(int j = 0; j < this.n; j++) { // Inicializa matriz com INF
					this.adj[i][j] = Integer.MAX_VALUE;
				}
			}

			for(int i = 0; i < this.n; i++) {
				vertice = scanner.nextInt(); // Lê vértice
				rotuloAtual = scanner.nextLine().stripLeading(); // Lê rótulo
				Ave aveAtual = new Ave(rotuloAtual); // Cria objeto Ave com base no taxon (rotulo) lido
				this.aves[vertice] = aveAtual; // Atribui ave na posição do vértice
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
	public Ave[] getAves() { return this.aves; }

	// Método que insere vértice no grafo
	// Argumentos: Rótulo do vértice a ser adicionado
	public void insereV(Ave novaAve) {
		Ave[] novoVetorAves = new Ave[this.n + 1];
		Integer[][] novaMatriz = new Integer[this.n + 1][this.n + 1];

		for(int i = 0; i < this.n; i++) {
			for(int j = 0; j < this.n; j++) {
				novaMatriz[i][j] = this.adj[i][j];
				novaMatriz[j][this.n] = Integer.MAX_VALUE;
			}

			novaMatriz[this.n][i] = Integer.MAX_VALUE;
			novoVetorAves[i] = this.aves[i];
		}

		novaMatriz[this.n][this.n] = Integer.MAX_VALUE;
		this.adj = novaMatriz;

		int numAvesMesmoTaxon = numOfAvesSameTaxon(novaAve);
		if(numAvesMesmoTaxon != 0) {
			novaAve.setIDGraph(novaAve.getTaxon() + " (" + numAvesMesmoTaxon + ")");
		}

		novoVetorAves[this.n] = novaAve;
		this.aves = novoVetorAves;
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
		Ave[] novoVetor = new Ave[this.n - 1];
		Integer novaMatriz[][] = new Integer[this.n-1][this.n-1];

		for(int i = 0; i < this.n - 1; i++) {
			row = i;
			if(i >= vertice) row++; 

			for(int j = 0; j < this.n - 1; j++) {
				column = j;
				if(j >= vertice) column++;
				novaMatriz[i][j] = this.adj[row][column];
			}

			novoVetor[i] = this.aves[row];
		}
	
		this.m = this.m - getDegree(vertice);
		this.n--;
		this.adj = novaMatriz;
		this.aves = novoVetor;
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
		ArrayList<Integer> verticesVisitados = depthFirstSearch(0);
		int numVisitados = verticesVisitados.size();
		if(numVisitados == this.n) return 0;
		return 1;
	}

	// Método que realiza busca em profundidade no grafo
	// Argumentos: Vértice de origem
	public ArrayList<Integer> depthFirstSearch(int src) {
		boolean[] visited = new boolean[this.n];
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		dFSHelper(src, visited, vertices);
		return vertices;
	}

	// Método auxiliar para busca em profundidade no grafo 
	// Argumentos: Vértice de origem, vetor de vértices visitados, vetor de vértices do percurso
	private void dFSHelper(int src, boolean[] visited, ArrayList<Integer> vertices) {
		if(visited[src]) return;
		visited[src] = true;
		vertices.add(src);

		for(int i = 0; i < this.n; i++) {
			if(this.adj[src][i] != Integer.MAX_VALUE) {
				dFSHelper(i, visited, vertices);
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
		int origem, destino, peso;

		try {
			FileWriter writer = new FileWriter(fileName);

			writer.write("2\n"); // Escrevendo tipo do grafo
			writer.write(this.n + "\n"); // Escrevendo número de vértices

			for(int i = 0; i < this.n; i++) {
				writer.write(i + " "); // Escrevendo número do vértice
				writer.write(this.aves[i].getTaxon() + "\n"); // Escrevendo taxon da ave
			}

			writer.write(this.m + "\n"); // Escrevendo número de arestas

			int aux[][] = new int[this.n][this.n]; // Criando matriz auxiliar

			for(int i = 0; i < this.n; i++) { // Inicializando matriz auxiliar
				for(int j = 0; j < this.n; j++) {
					if(i != j) aux[i][j] = 0; 
					else aux[i][j] = 1; // Ignora-se laços
					// Ignora arestas que não existem (inf)
					if(this.adj[i][j] == Integer.MAX_VALUE) aux[i][j] = 1;
				}
			}

			// Como em grafos não direcionados, se [v][w] existe então [w][v]
			// também, é ideal evitar a duplicação de arestas no arquivo.
			// Utiliza-se a matriz auxiliar para verificar se a aresta [v][w]
			// já foi gravada no arquivo quando tentar gravar a aresta [w][v]

			for(int i = 0; i < this.n; i++) {
				for(int j = 0; j < this.n; j++) {
					// Se aresta ainda não foi gravada
					if(aux[i][j] != 1) { 
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showAves() { // Metodo que exibe aves representadas pelos vertices
		for(int i = 0; i < this.n; i++) {
			System.out.println("Vértice " + i + ": " + this.aves[i].getIDGraph());
		}
	}

	// Metodo que exibe todas as informacoes de uma ave representada por um vertice
	// Argumentos: numero do vertice que representa a ave no grafo
	public void showInfoAve(int vertice) {
		if(vertice < 0 || vertice >= this.n) {
			System.out.println("Vértice não encontrado no grafo");
			return;
		}

		System.out.println("ID no Grafo: " + this.aves[vertice].getIDGraph());
		System.out.println("Taxon: " + this.aves[vertice].getTaxon());
		System.out.println("Ordem: " + this.aves[vertice].getOrdem());
		System.out.println("Familia: " + this.aves[vertice].getFamilia());
		System.out.println("Genero: " + this.aves[vertice].getGenero());
		System.out.println("Especie: " + this.aves[vertice].getEspecie());
	}

	// Metodo que exibe as relacoes de uma ave representada por um vertice com
	// outras aves
	// Argumentos: numero do vertice que representa a ave no grafo 
	public void showRelacoesDoVertice(int vertice) {
		if(vertice < 0 || vertice >= this.n) {
			System.out.println("Vértice não encontrado no grafo");
			return;
		}

		String relacoes[] = {"Classe", "Ordem", "Família", "Gênero", "Espécie"};

		for(int i = 0; i < this.n; i++) {
			if(vertice != i && this.adj[vertice][i] != Integer.MAX_VALUE) {
				System.out.print(this.aves[vertice].getIDGraph() + " --- ");
				System.out.print(relacoes[this.adj[vertice][i] - 1]);
				System.out.println(" --- " + this.aves[i].getIDGraph());
			}
		}
	}

	// Metodo que retorna todas as Ordens taxonomicas das aves no grafo
	private ArrayList<String> getAllOrdens() {
		String ordem;
		ArrayList<String> ordens = new ArrayList<String>();

		for(int i = 0; i < this.n; i++) {
			ordem = this.aves[i].getOrdem();

			if(!ordens.contains(ordem)) {
				ordens.add(ordem);
			}
		}

		return ordens;
	}

	// Metodo que retorna todas as Familias taxonomicas das aves no grafo
	private ArrayList<String> getAllFamilias() {
		String familia;
		ArrayList<String> familias = new ArrayList<String>();

		for(int i = 0; i < this.n; i++) {
			familia = this.aves[i].getFamilia();

			if(!familias.contains(familia)) {
				familias.add(familia);
			}
		}

		return familias;
	}

	// Metodo que retorna todos os Generos taxonomicos das aves no grafo
	private ArrayList<String> getAllGeneros() {
		String genero;
		ArrayList<String> generos = new ArrayList<String>();

		for(int i = 0; i < this.n; i++) {
			genero = this.aves[i].getGenero();

			if(!generos.contains(genero)) {
				generos.add(genero);
			}
		}

		return generos;
	}

	// Metodo que exibe todas as organizacoes taxonomicas (ordem, genero, familia e especie)
	// A especie e exibida por taxon (genero + especie), pois nao e comum um individuo ser
	// referenciado apenas por sua especie, mas sim por seu taxon
	public void showOrganizacoesTaxonomicas() {
		ArrayList<String> ordens = getAllOrdens();
		ArrayList<String> generos = getAllGeneros();
		ArrayList<String> familias = getAllFamilias();

		System.out.println("CLASSES:\nAves");

		System.out.println("\nORDENS:");
		for (String ordem : ordens) {
			System.out.println(ordem);
		}

		System.out.println("\nFAMÍLIAS:");
		for (String familia : familias) {
			System.out.println(familia);
		}

		System.out.println("\nGÊNEROS:");
		for (String genero : generos) {
			System.out.println(genero);
		}

		System.out.println("\nESPÉCIES (TAXON):");
		for(int i = 0; i < this.n; i++) {
			System.out.println(this.aves[i].getTaxon());
		}
	}

	// Metodo que escreve a um arquivo o relatorio completo com todas as organizacoes
	// taxonomicas presentes no grafo, bem como as relacoes das aves que o compoem
	public void writeRelatorio() {
		ArrayList<String> ordens = getAllOrdens();
		ArrayList<String> generos = getAllGeneros();
		ArrayList<String> familias = getAllFamilias();
		String relacoes[] = {"Classe", "Ordem", "Família", "Gênero", "Espécie"};

		try {
			FileWriter writer = new FileWriter("assets/RelatorioTaxonomico.txt");
			writer.write("CLASSES:\nAves\n");

			writer.write("\nORDENS:\n");
			for (String ordem : ordens) {
				writer.write(ordem + "\n");
			}

			writer.write("\nFAMÍLIAS:\n");
			for (String familia : familias) {
				writer.write(familia + "\n");
			}
			
			writer.write("\nGÊNEROS:\n");
			for (String genero : generos) {
				writer.write(genero + "\n");
			}

			writer.write("\nESPÉCIES (TAXON):\n");
			for(int i = 0; i < this.n; i++) {
				writer.write(this.aves[i].getTaxon() + "\n");
			}

			writer.write("\nINFORMAÇÕES COMPLETAS DAS ESPÉCIES NO GRAFO:\n");
			for(int i = 0; i < this.n; i++) {
				writer.write("ID: " + this.aves[i].getIDGraph() + "\n");
				writer.write("TAXON: " + this.aves[i].getTaxon() + "\n");
				writer.write("ORDEM: " + this.aves[i].getOrdem() + "\n");
				writer.write("FAMÍLIA: " + this.aves[i].getFamilia() + "\n");
				writer.write("GÊNERO: " + this.aves[i].getGenero() + "\n");
				writer.write("ESPÉCIE: " + this.aves[i].getEspecie() + "\n\n");
			}

			writer.write("RELAÇÕES TAXONÔMICAS NO GRAFO:\n");
			for(int i = 0; i < this.n; i++) {
				for(int j = 0; j < this.n; j++) {
					if(i != j && this.adj[i][j] != Integer.MAX_VALUE) {
						writer.write(this.aves[i].getIDGraph() + " --- ");
						writer.write(relacoes[this.adj[i][j] - 1]);
						writer.write(" --- " + this.aves[j].getIDGraph() + "\n");
					}
				}
				writer.write("\n");
			}

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Metodo que exibe o grau de todos os vertices do grafo
	public void showAllDegrees() {
		for(int i = 0; i < this.n; i++) {
			System.out.println("Grau do Vértice " + i + ": " + getDegree(i));
		}
	}

	// Metodo que retorna se o grafo possui um caminho euleriano
	public boolean hasEulerianPath() {
		int numGrausImpares = 0, i = 0;

		while(i < this.n) {
			if(getDegree(i) % 2 == 1) {
				numGrausImpares++;
			}
			i++;
		}

		if(numGrausImpares >= 2) return false;
		return true;
	}

	// Metodo que retorna se o grafo é euleriano
	public boolean isAnEulerianGraph() {
		if(getConexidade() != 0) { 
			return false; 
		}

		for(int i = 0; i < this.n; i++) {
			if(getDegree(i) % 2 == 1) {
				return false;
			}
		}

		return true;
	}

	// Metodo que garante a unicidade de uma ave a partir da numeracao de aves
	// em seu ID caso aves de mesmo taxon coexistam no grafo
	// Argumentos: ave a ter o numero de individuos de mesmo taxon verificado
	private int numOfAvesSameTaxon(Ave ave) {
		int num = 0;

		for(Ave aveNoGrafo : this.aves) {
			if(aveNoGrafo.getTaxon().equals(ave.getTaxon())) {
				num++;
			}
		}

		return num;
	}
}
