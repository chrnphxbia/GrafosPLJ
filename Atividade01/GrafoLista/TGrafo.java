package Lista;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


//definicao da classe de nós da lista
class TNo{ // define uma struct (registro)
	public	int w;  // vértice que é adjacente ao elemento da lista
	public TNo prox;
}

//definição de uma classe para armezanar um grafo
public class TGrafo{
	// atributos privados
	private	int n; // quantidade de vértices
	private	int m; // quantidade de arestas
	private	TNo adj[]; // um vetor onde cada entrada guarda o inicio de uma lista
	// métodos públicos
	// Construtor do grafo com a lista de
	// adjacência
	public TGrafo( int n ) {
	    // aloca a estrutura TGrafo
	    this.n = n;
	    this.m = 0;
	    // aloca m vetor para guardar lista de adjacencias
	    TNo adjac[] = new TNo[n];
	    // Inicia o vetor com nullL
		for(int i = 0; i< n; i++)
			adjac[i]=null;	
	    this.adj = adjac;
	};

	/*
	(Exercício 23)
	Construtor para entrada de arquivo Onde na primeira linha
	contém um inteiro V (vértice), na segunda contém um inteiro
	A (arestas) e nas demais linha contém dois inteiros pertencentes
	ao intervalo 0..V-1. 
	*/
	public TGrafo(String arquivo){
		try{
			Scanner scanner = new Scanner(new File(arquivo));

			 // Ler o número de vértices e arestas
			this.n = scanner.nextInt(); // Primeira linha: número de vértices
			int arestas = scanner.nextInt(); // Segunda linha: número de arestas

			// Inicializar o vetor de listas de adjacência
			adj = new TNo[n]; 
        	for (int i = 0; i < n; i++) {
            	adj[i] = null;
        	}

			// Ler as arestas
			for (int i = 0; i < arestas; i++) {
				int origem = scanner.nextInt();  // Vértice de origem
				int destino = scanner.nextInt();  // Vértice de destino
				insereA(origem, destino);  // Insere a aresta origem -> destino
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado.");
		}
	}


	/*
	Método que cria uma aresta v-w no grafo. O método supõe que
	v e w são distintos, positivos e menores que V.
	Se o grafo já tem a aresta v-w, o método não faz nada.
	O método também atualiza a quantidade de arestas no grafo.
	*/
	public void insereA( int v, int w) {
		
	    TNo novoNo;
	    // anda na lista para chegar ao final
	    TNo no = adj[v];
	    TNo ant = null;
	    // anda na lista enquanto no != NULL E w  > no->w
	    while( no != null && w >= no.w ){
	        if( w == no.w)
	            return;
	        ant = no;
	        no = no.prox;
	    };
	    // cria o novo No para guardar w
	    novoNo = new TNo();
	    novoNo.w = w;
	    novoNo.prox = no;
	    // atualiza a lista
	    if( ant == null){
	        // insere no inicio
	        adj[v] = novoNo;
	    } else
	        // insere no final
	        ant.prox = novoNo;
	    m++;	
	}
	
	/*
	Método que remove do grafo a aresta que tem ponta inicial v
	e ponta final w. O método supõe que v e w são distintos,
	positivos e menores que V. Se não existe a aresta v-w,
	o método não faz nada. O método também atualiza a
	quantidade de arestas no grafo.
	*/
	public void removeA( int v, int w) {
	    // Obtém o início da lista do vértice v
	    TNo no = adj[v];
	    TNo ant = null;
	    // Percorre a lista do vértice v
	    // procurando w (se adjacente)
	    while( no != null && no.w != w ){
	    		ant = no;
	    		no = no.prox;
	    }
	    // Se w é adjacente, remove da lista de v
	    if (no != null){
	    	ant.prox = no.prox;
	    	no = null;
	    	m--;
		}	
	}
	/*
	Para cada vértice v do grafo, este método imprime, em
	uma linha, todos os vértices adjacentes ao vértice v
	(vizinhos ao vértice v).
	*/
	public void show() {
	    System.out.print("n: " + n);
	    System.out.print("\nm: " + m + "\n");
	    for( int i=0; i < n; i++){
	    	System.out.print("\n" + i + ": ");
	        // Percorre a lista na posição i do vetor
	        TNo no = adj[i];
	        while( no != null ){
	        	System.out.print(no.w + " ");
	            no = no.prox;
	        }
	    }
	    System.out.print("\n\nfim da impressao do grafo.\n");
	}

	/*
	(Exercício 17)
	Método que decide se dois grafos direcionados são iguais
	*/
	

	/*
	(Exercício 20)
	Método que recebe um grafo e um vértice e retorna 1
	se o vértice for uma fonte (grau de saída maior que 
	zero e grau de entrada igual a 0) ou 0 caso contrário
	*/
	public int isFonte(int v){
		
		// Verificar se o vértice v tem algum adjacente (grau de saída)
		if (adj[v] == null){
			return 0; // Se não tem adjacente, não é uma fonte
		}

		// Verificar se algum outro vértice tem v como adjacente (grau de entrada)
		for (int i = 0; i < n; i++){
			if (i != v){
				TNo no = adj[i];
				while (no != null) {
					if (no.w == v){
						return 0; // Se encontrarmos v como adjacente, não é uma fonte
					}
					no = no.prox;
				}
			}
		}

		// Se não foi encontrado como adjacente em nenhum outro vértice
		return 1;
	}

}
