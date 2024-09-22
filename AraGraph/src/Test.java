import graph.WUGraph;

public class Test {
    public static void main(String[] args) throws Exception {
        WUGraph grafo = new WUGraph("assets/temp.txt");
        // grafo.show();
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
