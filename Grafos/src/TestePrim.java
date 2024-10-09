/* INTEGRANTES DO GRUPO
 * Jônatas Garcia de Oliveira		10396490
 * Livia Alabarse dos Santos		10403046
 * Pedro Henrique Araujo Farias		10265432 
*/

import MatrizAdj.NaoDirecionado.TGrafoNDRotulado;

public class TestePrim {
    public static void main(String[] args) throws Exception {
        TGrafoNDRotulado grafo01 = new TGrafoNDRotulado("exemplosGrafos/PrimAlg/grafo01.txt");
        System.out.println("Grafo 01: ");
        grafo01.show();
        System.out.println();
        grafo01.getMinimalSpanningTree(0);
        System.out.println();

        TGrafoNDRotulado grafo02 = new TGrafoNDRotulado("exemplosGrafos/PrimAlg/grafo02.txt");
        System.out.println("Grafo 02: ");
        grafo02.show();
        System.out.println();
        grafo02.getMinimalSpanningTree(0);
        System.out.println();

        TGrafoNDRotulado grafo03 = new TGrafoNDRotulado("exemplosGrafos/PrimAlg/grafo03.txt");
        System.out.println("Grafo 03: ");
        grafo03.show();
        System.out.println();
        grafo03.getMinimalSpanningTree(0);
        System.out.println();
    }
}
