import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TGrafoND {
    private	int n; // quantidade de vértices
	private	int m; // quantidade de arestas
	private	Double adj[][]; //matriz de adjacência

    public TGrafoND(int n) {  // construtor
	    this.n = n;
	    // No início dos tempos não há arestas
	    this.m = 0; 
	    // alocação da matriz do TGrafo
	    this.adj = new Double [n][n];

	    // Inicia a matriz com zeros
		for(int i = 0; i< n; i++)
			for(int j = 0; j< n; j++)
				this.adj[i][j] = Double.POSITIVE_INFINITY;	
	}

    public TGrafoND(String arquivo) {
		try {
			int origem, destino;
			Double peso;

			Scanner scanner = new Scanner(new File(arquivo));
			this.n = scanner.nextInt();
			int linhas = scanner.nextInt();

			this.adj = new Double[this.n][this.n];
			
			for(int i = 0; i< n; i++) {
				for(int j = 0; j< n; j++) {
					this.adj[i][j] = Double.POSITIVE_INFINITY;
				}
			}

			for(int k = 0; k < linhas; k++) {
				origem = scanner.nextInt();
				destino = scanner.nextInt();
				peso = Double.parseDouble(scanner.nextLine());
				insereA(origem, destino, peso);
			}

			scanner.close();

		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado.");
		}
	}

    public void insereA(int v, int w, double peso) {
	    // testa se nao temos a aresta
	    if(adj[v][w] == Double.POSITIVE_INFINITY){
	        adj[v][w] = peso;
            adj[w][v] = peso;
	        m++; // atualiza qtd arestas
	    }
	}

    public void removeA(int v, int w) {
	    // testa se temos a aresta
	    if(adj[v][w] != Double.POSITIVE_INFINITY ){
	        adj[v][w] = Double.POSITIVE_INFINITY;
            adj[w][v] = Double.POSITIVE_INFINITY;
	        m--; // atualiza qtd arestas
	    }
	}

	public int getDegree(int v) {
		int degree = 0;

		for(int j = 0; j < n; j++) {
			if(adj[v][j] != Double.POSITIVE_INFINITY) {
				degree++;
			}
		}

		return degree;
	}

    public void show() {
	    System.out.println("n: " + n );
	    System.out.println("m: " + m );
	    for(int i=0; i < n; i++) {
	    	System.out.print("\n");
	        for(int w=0; w < n; w++)
	            if(adj[i][w] != Double.POSITIVE_INFINITY)
	            	System.out.print("Adj[" + i + "," + w + "]= " + adj[i][w] + " ");
	            else System.out.print("Adj[" + i + "," + w + "]= " + Double.POSITIVE_INFINITY + " ");
	    }
	    System.out.println("\n\nfim da impressao do grafo." );
	}

	public void removeV(int v) {
		if(v >= this.n) {
			System.err.println("Não há vértice " + v + " no grafo.\nNão é " +
				"possível realizar remoção.");
			return;
		}

		int row, column;
		Double novaMatriz[][] = new Double[this.n-1][this.n-1];

		for(int i = 0; i < this.n - 1; i++) {
			row = i;
			if(i >= v) { row++; }
			for(int j = 0; j < this.n - 1; j++) {
				column = j;
				if(j >= v) { column++; }
				novaMatriz[i][j] = this.adj[row][column];
			}
		}
	
		this.m = this.m - getDegree(v);
		this.n--;
		this.adj = novaMatriz;
	}
}
