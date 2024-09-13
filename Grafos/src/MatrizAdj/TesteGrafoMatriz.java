package MatrizAdj;

import MatrizAdj.Direcionado.*;
import MatrizAdj.NaoDirecionado.*;

public class TesteGrafoMatriz {
	public static void main(String args[]) {
		System.out.println("|| -------------------------- MATRIZ DE ADJACÊNCIA -------------------------- ||");
		// Testando Grafo Direcionado de arestas {(0,1),(0,2),(2,1),(2,3),(1,3)}
		TGrafoDirecionado grafo01 = new TGrafoDirecionado(4);
		grafo01.insereA(0 , 1);
		grafo01.insereA(0,2);
		grafo01.insereA(2,1);
		grafo01.insereA(2,3);
		grafo01.insereA(1,3);
        System.out.println("> Grafo 01 - Grafo Direcionado com arestas {(0,1),(0,2),(2,1),(2,3),(1,3)}");
		grafo01.show();
        System.out.println("\n> Grafo 01 após remoção da aresta (0, 2)");
		grafo01.removeA(0, 2);
		grafo01.show();
		System.out.println("\n> Exercício 01: Grau de entrada do vértice 1 " +
		"(Esperado: 2): " + grafo01.inDegree(1));
		System.out.println("> Exercício 02: Grau de saída do vértice 1 " +
		"(Esperado: 1): " + grafo01.outDegree(1));
		System.out.println("> Exercício 03: Vértice 0 é fonte (Esperado: 1)? " + grafo01.isFonte(0));
		System.out.println("> Exercício 04: Vértice 3 é sorvedouro (Esperado: 1)? " + grafo01.isSorvedouro(3));
		System.out.println("> Exercício 05: O grafo é simétrico (Esperado: 0)? " + grafo01.isSimetrico());
        
        // Testando construtor por arquivo
        System.out.println("\n> Exercício 06: Grafo 02 - Grafo Direcionado construído a partir do arquivo leitura.txt");
        TGrafoDirecionado grafo02 = new TGrafoDirecionado("exemplosGrafos/MatrizAdj/leitura.txt");
        grafo02.show();
		
		// Testando Grafo Nao Direcionado
        System.out.println("\n> Exercício 07: Grafo 03 - Grafo Não Direcionado");
		TGrafoNaoDirecionado grafo03 = new TGrafoNaoDirecionado(4);
		grafo03.insereA(0, 1);
		grafo03.insereA(0, 2);
		grafo03.insereA(0, 3);
		grafo03.insereA(1, 2);
		grafo03.show();

		// Testando Grafo Não Direcionado Rotulado
		System.out.println("\n> Exercício 08: Grafo 04 - Grafo Não Direcionado Rotulado");
		TGrafoNDRotulado grafo04 = new TGrafoNDRotulado(3);
		grafo04.insereA(0, 1, 5.4f);
		grafo04.insereA(0, 2, 3.27f);
		grafo04.show();

		// Testando remoção de vértice no Grafo Direcionado
		System.out.println("\n> Exercício 09: Removendo vértice 3 do Grafo 02 (Direcionado)");
		grafo02.removeV(3);
		grafo02.show();

		// Testando remoção de vértice no Grafo Não Direcionado
		System.out.println("\n> Exercício 09: Removendo vértice 1 do Grafo 03 (Não Direcionado)");
		grafo03.removeV(1);
		grafo03.show();

		System.out.println("\n> Grafo 05 - Grafo Não Direcionado Completo");
		TGrafoNaoDirecionado grafo05 = new TGrafoNaoDirecionado(4);
		grafo05.insereA(0, 1);
		grafo05.insereA(0, 2);
		grafo05.insereA(0, 3);
		grafo05.insereA(1, 0);
		grafo05.insereA(1, 2);
		grafo05.insereA(1, 3);
		grafo05.insereA(2, 0);
		grafo05.insereA(2, 1);
		grafo05.insereA(2, 3);
		grafo05.insereA(3, 0);
		grafo05.insereA(3, 1);
		grafo05.insereA(3, 2);
		grafo05.show();
		System.out.println("\n> Exercício 10: Grafo 05 é Completo (Esperado: 1)? " + grafo05.verificarGrafoCompleto());
		
		System.out.println("\n> Grafo 06 - Grafo Direcionado Completo");
		TGrafoDirecionado grafo06 = new TGrafoDirecionado(3);
		grafo06.insereA(0, 1);
		grafo06.insereA(0, 2);
		grafo06.insereA(1, 0);
		grafo06.insereA(1, 2);
		grafo06.insereA(2, 0);
		grafo06.insereA(2, 1);
		grafo06.show();
		System.out.println("\n> Exercício 11: Grafo 06 é Completo (Esperado: 1)? " + grafo06.isCompleto());

		System.out.println("\n> Exercício 12: Obtendo matriz do grafo complementar ao Grafo 02 (Direcionado):");
		int[][] matrizCompDir = grafo02.getCompMatriz();
		for(int i=0; i < grafo02.getVertices(); i++) {
			System.out.println();
	        for(int w = 0; w < grafo02.getVertices(); w++) {
				if(matrizCompDir[i][w] == 1) {
					System.out.print("Adj[" + i + "," + w + "] = 1" + " ");
                } else {
					System.out.print("Adj[" + i + "," + w + "] = 0" + " ");
                }
            }
	    }
        System.out.println();
		
		System.out.println("\n> Exercício 12: Obtendo matriz do grafo complementar ao Grafo 03 (Não Direcionado):");
		int[][] matrizCompND = grafo03.getCompMatriz();
		for(int i=0; i < grafo03.getVertices(); i++) {
			System.out.println();
	        for(int w = 0; w < grafo03.getVertices(); w++) {
				if(matrizCompND[i][w] == 1) {
					System.out.print("Adj[" + i + "," + w + "] = 1" + " ");
                } else {
					System.out.print("Adj[" + i + "," + w + "] = 0" + " ");
                }
            }
	    }
        System.out.println();

		// Testando conexidade Grafo ND
		TGrafoNaoDirecionado grafo07 = new TGrafoNaoDirecionado("exemplosGrafos/MatrizAdj/ndconexo.txt");
		System.out.println("\n> Grafo 07 - Grafo Não Direcionado Conexo");
		grafo07.show();
		System.out.println("\n> Exercício 13: Grafo 07 (Conexo = 0, Desconexo = 1): " + grafo07.getConexidade());

		TGrafoNaoDirecionado grafo08 = new TGrafoNaoDirecionado("exemplosGrafos/MatrizAdj/nddesconexo.txt");
		System.out.println("\n> Grafo 08 - Grafo Não Direcionado Desconexo");
		grafo08.show();
		System.out.println("\n> Exercício 13: Grafo 08 (Conexo = 0, Desconexo = 1): " + grafo08.getConexidade());

		// Testando Grafo Direcionado Não Rotulado
		TGrafoDRotulado grafo09 = new TGrafoDRotulado("exemplosGrafos/MatrizAdj/GDRotulado.txt");
		System.out.println("\n> Exercício 16: Grafo 09 (Direcionado Rotulado) lido do arquivo GDRotulado.txt");
		grafo09.show();

		System.out.println("|| -------------------------- FIM DOS TESTES -------------------------- ||\n\n");
	}
}
