/*  
    INTEGRANTES DO PROJETO ARAGRAPH
    Jônatas Garcia de Oliveira      10396490
    Livia Alabarse dos Santos       10403046
    Pedro Henrique Araujo Farias    10265432

    Este arquivo apresenta a classe de testes utilizada durante desenvolvimento
    para avaliação da corretude dos métodos implementados na classe WUGraph.

    DATA            AUTOR       ATUALIZAÇÃO       
    23/09/2024;     Pedro       Adicionada classe de Teste
*/

import graph.WUGraph;

public class Test {
    public static void main(String[] args) throws Exception {
        WUGraph grafo = new WUGraph("assets/grafo.txt");
        grafo.show();
        grafo.writeToFile("grafoteste.txt");
        grafo.show();
        System.out.println();
        String[] rotulos = grafo.getRotulos();
        
        for (String rotulo : rotulos) {
            System.out.println(rotulo);
        }

        grafo.insereV("Batata frita");
        String[] novosRotulos = grafo.getRotulos();
        grafo.show();
        for (String novoRotulo : novosRotulos) {
            System.out.println(novoRotulo);
        }

        grafo.insereV("Bolo de morango");
        novosRotulos = grafo.getRotulos();
        grafo.show();
        for (String novoRotulo : novosRotulos) {
            System.out.println(novoRotulo);
        }

        grafo.removeV(5);
        novosRotulos = grafo.getRotulos();
        grafo.show();
        for (String novoRotulo : novosRotulos) {
            System.out.println(novoRotulo);
        }
        
        grafo.removeV(4);
        grafo.insereA(3, 3, 5);
        grafo.show();

        System.out.println(grafo.getConexidade());
    }
}
