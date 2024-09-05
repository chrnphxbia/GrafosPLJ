public class TesteGrafoMatriz {
    public static void main(String args[]) {
        // TGrafo grafo01 = new TGrafo(4);
        // grafo01.insereA(0, 1);
        // grafo01.insereA(0, 2);
        // grafo01.insereA(1, 3);
        // grafo01.insereA(2, 1);
        // grafo01.insereA(2, 3);
        // grafo01.show();
        // System.out.println("Grafo é simétrico? " + grafo01.isSimetrico());

        TGrafo grafo02 = new TGrafo("exemplo.txt");
        grafo02.show();
        grafo02.removeV(0);
        grafo02.show();
    }
}