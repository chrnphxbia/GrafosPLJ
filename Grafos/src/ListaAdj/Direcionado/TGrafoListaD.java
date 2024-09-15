package ListaAdj.Direcionado;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import MatrizAdj.Direcionado.TGrafoDirecionado;

class TNo {
	public int w; // Vértice adjacente na lista
	public TNo prox; // Próximo nó da lista
}

public class TGrafoListaD {
	private	int n; // quantidade de vértices
	private	int m; // quantidade de arestas
	private	TNo adj[]; // um vetor onde cada entrada guarda o inicio de uma lista
	
	public TGrafoListaD(int n) {
	    this.n = n;
	    this.m = 0;
	    TNo adjac[] = new TNo[n];
		for(int i = 0; i< n; i++) adjac[i] = null;	
	    this.adj = adjac;
	};

	/* Método que cria uma aresta v-w no grafo. O método supõe que
	v e w são distintos, positivos e menores que V.
	Se o grafo já tem a aresta v-w, o método não faz nada.
	O método também atualiza a quantidade de arestas no grafo. */
	public void insereA(int v, int w) {
	    TNo novoNo;
	    // anda na lista para chegar ao final
	    TNo no = adj[v];
	    TNo ant = null;
	    // anda na lista enquanto no != NULL E w  > no->w
	    while(no != null && w >= no.w) {
	        if(w == no.w) return;
	        ant = no;
	        no = no.prox;
	    };
	    // cria o novo No para guardar w
	    novoNo = new TNo();
	    novoNo.w = w;
	    novoNo.prox = no;
	    // atualiza a lista
	    if(ant == null) adj[v] = novoNo;// insere no inicio
		else ant.prox = novoNo; // insere no final
	    this.m++;	
	}
	
	/*
	Método que remove do grafo a aresta que tem ponta inicial v
	e ponta final w. O método supõe que v e w são distintos,
	positivos e menores que V. Se não existe a aresta v-w,
	o método não faz nada. O método também atualiza a
	quantidade de arestas no grafo.
	*/
	public void removeA(int v, int w) {
	    // Obtém o início da lista do vértice v
	    TNo no = adj[v];
	    TNo ant = null;
	    // Percorre a lista do vértice v
	    // procurando w (se adjacente)
	    while(no != null && no.w != w) {
	    		ant = no;
	    		no = no.prox;
	    }
	    // Se w é adjacente, remove da lista de v
	    if(no != null) {
			if(no != adj[v]) ant.prox = no.prox;
			else adj[v] = no.prox;
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
	    System.out.println();
	}

    // Exercício 17
    // Método que verifica se dois grafos direcionados são iguais
	public int isIgual(TGrafoListaD grafo2) {
		// Verifica se o número de vértices e arestas são iguais
		if (this.n != grafo2.n || this.m != grafo2.m) {
			return 0;
		}

		for (int i = 0; i < this.n; i++){
			TNo no1 = this.adj[i];
			TNo no2 = grafo2.adj[i];

			// Verifica se as listas de adjacência de ambos os grafos são iguais
			while (no1 != null && no2 != null){
				if (no1.w != no2.w){ // Se encontrar um vértice adjacente diferente
					return 0;
				}
				no1 = no1.prox;
				no2 = no2.prox;
			}

			// Se uma das listas tiver terminado antes da outra
            		if (no1 != null || no2 != null) {
                		return 0;
            		}
		}
		return 1;
	}

	// Exercício 18
	// Método que converte grafo direcionado em lista para matriz 
	public TGrafoDirecionado toMatriz() {
		TNo aux;
		TGrafoDirecionado grafoMatriz = new TGrafoDirecionado(this.n);

		for(int i = 0; i < this.n; i++) {
			aux = this.adj[i];
			while(aux != null) {
				grafoMatriz.insereA(i, aux.w);
				aux = aux.prox;
			}
		}

		return grafoMatriz;
	}

    // Exercício 19
    // Método que inverte lista de adjacência
    public void inverteLista(){
        for(int i = 0;i < n;i++){
            TNo atual = adj[i]; //Vamos usar pra percorrer a lista
            TNo anterior = null; //Faz um backup do nó
            TNo novaLista = null; //Vamos criar a estrutura aqui
    
            while(atual != null){//Percorre a lista inteira
                anterior = atual;
             
                atual = atual.prox;
         
                anterior.prox = novaLista;
                novaLista = anterior; 
            }
        this.adj[i] = anterior; //Ligando a lista que foi criada
        }
    }

    // Exercício 20
	// Método que verifica se vértice informado é fonte
	public int isFonte(int v){
		
		// Verificar se o vértice v tem algum adjacente (grau de saída)
		if (adj[v] == null){
			return 0; // Se não tem adjacente, não é uma fonte
		}

		// Verificar se algum outro vértice tem v como adjacente (grau de entrada)
		for (int i = 0; i < n; i++) {
			if (i != v) {
				TNo no = adj[i];
				while (no != null) {
					if (no.w == v) {
						return 0; // Se encontrarmos v como adjacente, não é uma fonte
					}
					no = no.prox;
				}
			}
		}

		// Se não foi encontrado como adjacente em nenhum outro vértice
		return 1;
	}

	// Exercício 21
	// Método que retorna 1 se grau de entrada > 0 e grau de saída == 0 de um vértice v
	public int isSorvedouro(int v) {
		if(this.adj[v] != null) return 0; // Se v tiver adjacentes, grau de saída != 0

		TNo aux = new TNo();
		for(int i = 0; i < this.n; i++) { // Percorrendo vetor de listas
			aux = this.adj[i];
			while(aux != null) { // Percorrendo nós da lista atual
				if(aux.w == v) return 1; // Se v for encontrado, grau de entrada > 0
				aux = aux.prox;
			}
		}

		return 0; // Caso grau de saída == 0 porém grau de entrada = 0
	}

    // Exercício 22 
    // Método que verifica se o gráfico é simétrico ou não
    public int isSimetric() {
        boolean encontrei_par = false;
        for(int i = 0; i < n; i++) { //Percorre os indices do vetor
            TNo no = adj[i];
            while(no != null){//Percorre os elementos da lista;
                int elem = no.w;
                TNo aux = adj[elem];
                while(aux != null) {//Percorre os elementos da outra lista
                    if(aux.w == i) {
                        encontrei_par = true;
                    }
                    aux = aux.prox;
                }
                if(!encontrei_par) {
                    return 0;
                }
                no = no.prox;
            }
            
        }
        return 1;
    }

	// Exercício 23
	// Método construtor a partir de leitura de arquivo
	public TGrafoListaD(String arquivo) {
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


	//Exericio 25 
	//Remover os vértices de um grafo dirigido
	public void removeV(int v) {
		while(this.adj[v] != null) removeA(v, adj[v].w);
		for(int i = 0; i < this.n; i++) removeA(i, v);

		TNo aux;
		int proxIndice;
		TNo novoVetor[] = new TNo[this.n - 1];
		
		for(int i = 0; i < this.n - 1; i++) {
			proxIndice = i;
			if(i >= v) proxIndice++;
			aux = this.adj[proxIndice];
			while(aux != null) {
				if(aux.w > v) aux.w--;
				aux = aux.prox;
			}
			novoVetor[i] = this.adj[proxIndice];
		}

		this.adj = novoVetor;
		this.n--;
	}

	// Exercício 26
	// Verifica se o grafo é completo
	public int isCompleto() {
		for (int v = 0; v < n; v++) {
			for (int w = 0; w < n; w++) {
				if (v != w) {
					TNo no = adj[v];
					// Verifica se existe a aresta v -> w
					while (no != null && no.w != w) {
						no = no.prox;
					}
					// Se não encontrar a aresta v -> w
					if (no == null) {
						return 0;
					}
				}
			}
		}
		return 1;
	}
}