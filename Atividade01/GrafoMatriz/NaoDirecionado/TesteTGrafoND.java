public class TesteTGrafoND {
    public static void main(String args[]) {
        TGrafoND grafo01 = new TGrafoND(4);
        grafo01.insereA(0, 1, 2.4);
        grafo01.insereA(0, 2, 3.4);
        grafo01.insereA(1, 3, 5.6);
        grafo01.insereA(2, 1, 3.9);
        grafo01.insereA(2, 3, 1.5);
        grafo01.insereA(3, 3, 99.4);
        grafo01.removeA(3, 3);
        grafo01.show();

        TGrafoND grafo02 = new TGrafoND("exemplo.txt");
        grafo02.show();

        grafo01.removeV(0);
        grafo01.show();
    }
}
