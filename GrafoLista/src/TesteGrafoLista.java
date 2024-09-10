public class TesteGrafoLista {

	public static void main(String[] args) {
	    TGrafoLista grafo01 = new TGrafoLista(3);
		System.out.println("> Grafo 01: ");
		grafo01.insereA(0, 1);
		grafo01.insereA(0, 2);
		grafo01.insereA(1, 2);
		grafo01.insereA(2, 0);
		grafo01.show();

		TGrafoLista grafo02 = new TGrafoLista(3);
		System.out.println("\n> Grafo 02:");
		grafo02.insereA(0, 1);
		grafo02.insereA(0, 2);
		grafo02.insereA(1, 2);
		grafo02.show();

		System.out.println("\n> Exercício 17: Grafos 01 e 02 são iguais (Esperado: 0)? " + grafo01.isIgual(grafo02));
		System.out.println("> Adicionando vértice (2, 0) ao grafo 02");
		grafo02.insereA(2, 0);
		grafo02.show();
		System.out.println("\n> Exercício 17: Grafos 01 e 02 são iguais (Esperado: 1)? " + grafo01.isIgual(grafo02));

		System.out.println("\n> Grafo 03:");
		TGrafoLista grafo03 = new TGrafoLista(5);
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
		System.out.println("\n> Exercício 19: Invertendo listas de adjacência do grafo 03: ");
		grafo03.show();
		
		System.out.println("\n> Grafo 04: ");
		TGrafoLista grafo04 = new TGrafoLista(3);
		grafo04.insereA(0, 1);
		grafo04.insereA(0, 2);
		grafo04.insereA(1, 2);
		grafo04.insereA(2, 1);
		System.out.println("\n> Grafo 04: Apresenta vértice 0 como fonte.");
		grafo04.show();
		System.out.println("\n> Exercício 20: Vértice 0 do Grafo 04 é fonte (Esperado: 1)? " + grafo04.isFonte(0));
		System.out.println("\n> Exercício 22: Grafo 04 é simétrico (Esperado: 0)? " + grafo04.isSimetric());
		System.out.println("\n> Grafo 03:");
		grafo03.show();
		System.out.println("\n> Exercício 22: Grafo 03 é simétrico (Esperado: 1)? " + grafo03.isSimetric());
		
		System.out.println("\n> Exercício 23: Grafo 05 gerado a partir de leitura do arquivo grafo05.txt:");
		TGrafoLista grafo05 = new TGrafoLista("grafo05.txt");
		grafo05.show();
	}
}
