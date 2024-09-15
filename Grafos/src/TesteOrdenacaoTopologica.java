import MatrizAdj.Direcionado.TGrafoDirecionado;

public class TesteOrdenacaoTopologica {
    public static void main(String[] args) throws Exception {
        System.out.println("|| -------------------------- ORDENAÇÃO TOPOLÓGICA -------------------------- ||");

        TGrafoDirecionado grafo1 = new TGrafoDirecionado("exemplosGrafos/OrdTop/grafo1.txt");
        TGrafoDirecionado grafo2 = new TGrafoDirecionado("exemplosGrafos/OrdTop/grafo2.txt");
        TGrafoDirecionado grafo3 = new TGrafoDirecionado("exemplosGrafos/OrdTop/grafo3.txt");


        System.out.println("**************************** TESTE 1 ****************************");

        System.out.println("Matriz de Adjacência:");
        grafo1.show();

        System.out.println("Ordenação Topológica (Esperado: 0 1 2 3 5 4 6 7):");
        grafo1.ordenacaoTopologica();


        System.out.println("\n\n**************************** TESTE 2 ****************************");
        System.out.println("Matriz de Adjacência:");
        grafo2.show();

        System.out.println("Ordenação Topológica (Esperado: 0 1 2 3 4 5):");
        grafo2.ordenacaoTopologica();


        System.out.println("\n\n**************************** TESTE 3 ****************************");
        System.out.println("Matriz de Adjacência:");
        grafo3.show();

        System.out.println("Ordenação Topológica (Esperado: 0 1 2 3 4):");
        grafo3.ordenacaoTopologica();
    }
}
