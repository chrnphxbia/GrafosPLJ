package ListaAdj;

import ListaAdj.Direcionado.*;
import ListaAdj.NaoDirecionado.*;
import MatrizAdj.Direcionado.TGrafoDirecionado;

public class TesteGrafoLista {

	public static void main(String[] args) {
		System.out.println("|| -------------------------- LISTA DE ADJACÊNCIA -------------------------- ||");
	    TGrafoListaD grafo01 = new TGrafoListaD(3);
		System.out.println("> Grafo 01: ");
		grafo01.insereA(0, 1);
		grafo01.insereA(0, 2);
		grafo01.insereA(1, 2);
		grafo01.insereA(2, 0);
		grafo01.show();

		TGrafoListaD grafo02 = new TGrafoListaD(3);
		System.out.println("\n> Grafo 02:");
		grafo02.insereA(0, 1);
		grafo02.insereA(0, 2);
		grafo02.insereA(1, 2);
		grafo02.show();

		System.out.println("\n> Exercício 17: Grafos 01 e 02 são iguais (Esperado: 0)? " + grafo01.isIgual(grafo02));
		System.out.println("\n> Adicionando vértice (2, 0) ao grafo 02");
		grafo02.insereA(2, 0);
		grafo02.show();
		System.out.println("\n> Exercício 17: Grafos 01 e 02 são iguais (Esperado: 1)? " + grafo01.isIgual(grafo02));

		TGrafoListaD grafoLista = new TGrafoListaD("exemplosGrafos/ListaAdj/conversao.txt");
		System.out.println("\n> Grafo em Lista: ");
		grafoLista.show();
		TGrafoDirecionado grafoMatriz = grafoLista.toMatriz();
		System.out.println("\n> Exercício 18: Convertendo grafo em lista para grafo em matriz: ");
		System.out.println("\n> Grafo em Matriz: ");
		grafoMatriz.show();

		System.out.println("\n> Grafo 03:");
		TGrafoListaD grafo03 = new TGrafoListaD(5);
		grafo03.insereA(0, 1);
		grafo03.insereA(0, 2);
		grafo03.insereA(0, 3);
		grafo03.insereA(0, 4);
		grafo03.insereA(1, 0);
		grafo03.insereA(1, 2);
		grafo03.insereA(1, 3);
		grafo03.insereA(1, 4);
		grafo03.insereA(2, 0);
		grafo03.insereA(2, 1);
		grafo03.insereA(2, 3);
		grafo03.insereA(2, 4);
		grafo03.insereA(3, 0);
		grafo03.insereA(3, 1);
		grafo03.insereA(3, 2);
		grafo03.insereA(3, 4);
		grafo03.insereA(4, 0);
		grafo03.insereA(4, 1);
		grafo03.insereA(4, 2);
		grafo03.insereA(4, 3);
		grafo03.show();
		grafo03.inverteLista();
		System.out.println("\n> Exercício 19: Invertendo " + 
		"listas de adjacência do grafo 03: ");
		grafo03.show();
		
		TGrafoListaD grafo04 = new TGrafoListaD(3);
		grafo04.insereA(0, 1);
		grafo04.insereA(0, 2);
		grafo04.insereA(1, 2);
		grafo04.insereA(2, 1);
		System.out.println("\n> Grafo 04: Apresenta vértice 0 como fonte.");
		grafo04.show();
		System.out.println("\n> Exercício 20: Vértice 0 do Grafo 04 é fonte (Esperado: 1)? " + grafo04.isFonte(0));
		
		System.out.println("\n> Grafo 06 (Vértice 2 é Sorvedouro): ");
		TGrafoListaD grafo06 = new TGrafoListaD("exemplosGrafos/ListaAdj/grafo06.txt");
		grafo06.show();
		System.out.println("\n> Exercício 21: Vértice 2 é Sorvedouro (Esperado: 1)? " + grafo06.isSorvedouro(2));
		System.out.println("\n> Exercício 21: Vértice 0 é Sorvedouro (Esperado: 0)? " + grafo06.isSorvedouro(0));
		
		System.out.println("\n> Grafo 04:");
		grafo04.show();
		System.out.println("\n> Exercício 22: Grafo 04 é simétrico (Esperado: 0)? " + grafo04.isSimetric());
		System.out.println("\n> Grafo 03:");
		grafo03.show();
		System.out.println("\n> Exercício 22: Grafo 03 é simétrico (Esperado: 1)? " + grafo03.isSimetric());
		
		System.out.println("\n> Exercício 23: Grafo 05 gerado a partir de leitura do arquivo grafo05.txt:");
		TGrafoListaD grafo05 = new TGrafoListaD("exemplosGrafos/ListaAdj/grafo05.txt");
		grafo05.show();
		
		// Testando remoção em grafo não direcionado
		System.out.println("\n> Grafo 07 Não Direcionado: ");
		TGrafoListaND grafo07 = new TGrafoListaND("exemplosGrafos/ListaAdj/exemplo.txt");
		grafo07.show();
		System.out.println("\n> Exercício 24: Removendo vértice 4 do Grafo 07: \n");
		grafo07.removeV(4);
		grafo07.show();

		// Testando remoção em grafo direcionado
		System.out.println("\n> Grafo 08 Direcionado: ");
		TGrafoListaD grafo08 = new TGrafoListaD("exemplosGrafos/ListaAdj/grafo08.txt");
		grafo08.show();
		System.out.println("\n> Exercício 25: Removendo vértice 3 do Grafo 08: \n");
		grafo08.removeV(3);
		grafo08.show();

		System.out.println("\n> Grafo 05: ");
		grafo05.show();
		System.out.println("\n> Exercício 26: Grafo 05 é completo (Esperado: 0)? " + grafo05.isCompleto());
		System.out.println("\n> Grafo 03:");
		grafo03.show();
		System.out.println("\n> Exercício 26: Grafo 03 é completo (Esperado: 1)? " + grafo03.isCompleto());

		System.out.println("\n|| -------------------------- FIM DOS TESTES -------------------------- ||");
	}
}
