import MatrizAdj.NaoDirecionado.TGrafoNaoDirecionado;

public class TesteColoracaoClasse {
    public static void main(String[] args) throws Exception {
        TGrafoNaoDirecionado grafo01 = new TGrafoNaoDirecionado("exemplosGrafos/ColoracaoAlg/grafoSlides.txt");
        System.out.println("\nGrafo de exemplo nos Slides");
        grafo01.show();
        System.out.println();
        grafo01.showClassesColoracao();

        System.out.println();

        TGrafoNaoDirecionado grafo02 = new TGrafoNaoDirecionado("exemplosGrafos/ColoracaoAlg/grafoExercicio.txt");
        System.out.println("Grafo do Exercício de Coloração de Vértices");
        grafo02.show();
        System.out.println();
        grafo02.showClassesColoracao();
    }
}
