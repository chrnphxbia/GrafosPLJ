import MatrizAdj.Direcionado.TGrafoDRotulado;
import MatrizAdj.NaoDirecionado.TGrafoNDRotulado;

public class TesteDijkstra {
    public static void main(String[] args) {
        System.out.println("------------------------------- GRAFOS NÃO DIRECIONADOS -------------------------------");
        TGrafoNDRotulado nDGraph01 = new TGrafoNDRotulado("exemplosGrafos/DijkstraAlg/naodirecionado01.txt");
        System.out.println("> Grafo Não Direcionado 01: ");
        nDGraph01.show();
        System.out.println("\nResultado do algoritmo de Dijkstra aplicado ao Grafo Não Direcionado 01:\n");
        nDGraph01.getDijkstra(3);

        TGrafoNDRotulado nDGraph02 = new TGrafoNDRotulado("exemplosGrafos/DijkstraAlg/naodirecionado02.txt");
        System.out.println("\n> Grafo Não Direcionado 02: ");
        nDGraph02.show();
        System.out.println("\nResultado do algoritmo de Dijkstra aplicado ao Grafo Não Direcionado 02:\n");
        nDGraph02.getDijkstra(0);

        System.out.println("\n------------------------------- GRAFOS DIRECIONADOS -------------------------------");
        TGrafoDRotulado dGraph01 = new TGrafoDRotulado("exemplosGrafos/DijkstraAlg/direcionado01.txt");
        System.out.println("> Grafo Direcionado 01: ");
        dGraph01.show();
        System.out.println("\nResultado do algoritmo de Dijkstra aplicado ao Grafo Direcionado 01:\n");
        dGraph01.getDijkstra(0);

        TGrafoDRotulado dGraph02 = new TGrafoDRotulado("exemplosGrafos/DijkstraAlg/direcionado02.txt");
        System.out.println("\n> Grafo Direcionado 02: ");
        dGraph02.show();
        System.out.println("\nResultado do algoritmo de Dijkstra aplicado ao Grafo Direcionado 02:\n");
        dGraph02.getDijkstra(0);
    }
}
