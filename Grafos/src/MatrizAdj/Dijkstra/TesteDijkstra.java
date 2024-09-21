package MatrizAdj.Dijkstra;

import MatrizAdj.Direcionado.TGrafoDRotulado;
import MatrizAdj.NaoDirecionado.TGrafoNDRotulado;

public class TesteDijkstra {
    public static void main(String[] args) {
        TGrafoNDRotulado naodirecionado = new TGrafoNDRotulado("exemplosGrafos/MatrizAdj/dijkstrand.txt");
        naodirecionado.show();
        System.out.println();
        naodirecionado.getDijkstra(3);

        System.out.println();

        TGrafoDRotulado direcionado = new TGrafoDRotulado("exemplosGrafos/MatrizAdj/dijkstrad.txt");
        direcionado.show();
        System.out.println();
        direcionado.getDijkstra(0);
    }
}
