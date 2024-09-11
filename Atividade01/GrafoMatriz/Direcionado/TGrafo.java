import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//definição de uma estrutura Matriz de Adjacência para armezanar um grafo
public class TGrafo {
    private int vertices;
    private int arestas;
    private double adj[][];
    
   
    
    
    public TGrafo(String arquivo){
        try{
            //int origem,destino;
            Scanner teclado = new Scanner(new File(arquivo));
            //System.out.println("Arquivo aberto");
            this.vertices = teclado.nextInt();
            this.arestas = teclado.nextInt();
          
            this.adj =  new double[vertices][vertices];
            
            for(int i = 0;i < vertices;i++){
                for(int j =0;j < vertices;j++){
                        adj[i][j] = Double.POSITIVE_INFINITY;
                    }
                }
            
            for(int i = 0 ;i < arestas;i++){
                int w = teclado.nextInt();
                int x = teclado.nextInt();
                int peso = teclado.nextInt();
                adj[w][x] = peso;
            }
        }
        catch(FileNotFoundException e){
            System.err.println("Arquivo não encontrado");
        }
    }
    
    
    
    
    public TGrafo(int vertices){
        this.vertices = vertices;
        this.arestas = 0;
        
        this.adj =  new double[vertices][vertices];//Alocação em java
        for(int i = 0;i < vertices;i++){
            for(int j =0;j < vertices;j++){
                this.adj[i][j] = Double.POSITIVE_INFINITY;
            }
        }
    }


    //Inserir aresta com v adjacente a w
    public void insereAresta(int v,int w,double peso){
        if(v > vertices || w > vertices){
            System.out.println("Excessão lista fora dos limites - Não foi possivel inserir");
        }else{
            if(this.adj[v][w] == Double.POSITIVE_INFINITY ){
                this.adj[v][w] = peso;
                arestas++;
              }else{
                System.out.println("Aresta :" + v + w  + " já existe ");
            }
        }
    }
	
	public void removeAresta(int v,int w){
        
        if(v > vertices || w > vertices){
            System.out.println("Excessão lista fora dos limites - Não foi possivel encontrar");
        }
        if(adj[v][w] != Double.POSITIVE_INFINITY ){
	        adj[v][w] = Double.POSITIVE_INFINITY;
	        arestas--; // atualiza qtd arestas
	    }else{
                System.out.println("Aresta já removida");
            }
	}


	// Apresenta o Grafo contendo
	// número de vértices, arestas
	// e a matriz de adjacência obtida	
	public void show(){
            System.out.println("n: " + vertices);
            System.out.println("m: " + arestas);
            for(int i = 0;i < vertices;i++){
                System.out.println("");
                for(int j=0;j < vertices;j++){
                     System.out.print("Adj [" + i + "," + j + "] = " + adj[i][j] + " ");
            	}
            }
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

	/* (Exercício 9)
	Método que remove vértice do grafo */
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

	/* (Exercício 12)
	 * Método para obter complemento do grafo
	 */
	public TGrafo getComplemento() {
		TGrafo complemento = new TGrafo(this.n);

		for(int i = 0; i < this.n; i++) {
			for(int j = 0; j < this.n; j++) {
				if(this.adj[i][j] == 0 & i != j) complemento.insereA(i, j);
			}
		}

		return complemento;
	}

	/*Exercício 14
	 Método que retorne a categoria de conexidade
	 para um grafo direcionado (3 – C3, 2 – C2, 1 – C1 ou 0 – c0).
	 */

	public int getCategoriaConexidade() {
		// Verifica se é fortemente conexo (C3)
		if (f_conexo()) { return 3;}

		// Verifica se o grafo é fracamente conexo (C2)
		if (sf_conexo()) { return 2;}

 		// Verifica se o grafo é desconexo (C0)
		if (desconexo()){ return 0;}

		// Caso contrário, é unilateralmente conexo (C1)
		return 1;
	 }

	// Método auxiliar
	private boolean f_conexo(){
		for (int v = 0; v < n; v++){
			String percurso = depthFirstSearch(v); // Realiza o percurso em profundidade
			String[] verticesVisitados = percurso.split(" ");
			if (verticesVisitados.length != n) {
				return false; // Se nem todos os vértices foram visitados, não é fortemente conexo
			}
		}
		return true; // Se todos os vértices foram visitados
	}

	private boolean sf_conexo(){
		for (int v = 0; v < n; v++){
			for (int w = 0; w < n; w++){
				if (v != w){
					String percursoV = depthFirstSearch(v); // Percurso a partir de v
					String percursoW = depthFirstSearch(w); // Percurso a partir de w
					if (!percursoV.contains(String.valueOf(w)) && !percursoW.contains(String.valueOf(v))){
						return false; // Se não há conexão entre v e w, o grafo não é fracamente conexo
					}
				}
			}
		}
		return true;
	}

	private boolean desconexo(){
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
       		String percurso = simetrico.depthFirstSearch(0);
        	return percurso.replaceAll("\\s","").length() != n; // Se não visitou todos os vértices, o grafo é desconexo
	}
}
