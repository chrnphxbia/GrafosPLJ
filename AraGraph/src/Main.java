/*  
    INTEGRANTES DO PROJETO ARAGRAPH
    Jônatas Garcia de Oliveira      10396490
    Livia Alabarse dos Santos       10403046
    Pedro Henrique Araujo Farias    10265432

    Este arquivo apresenta a classe principal da aplicação, por onde será
    executado o método main.

    DATA            AUTOR       ATUALIZAÇÃO       
    23/09/2024;     Pedro       Adicionado Main completo
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import graph.WUGraph;

public class Main {

    private static void printMenu(String filePath) { // Imprime o menu da aplicação
        System.out.println("\\\\--------------------\\\\ AraGraph //--------------------//");
        System.out.println("a) Ler dados do arquivo " + filePath);
        System.out.println("b) Gravar dados no arquivo " + filePath);
        System.out.println("c) Inserir vértice");
        System.out.println("d) Inserir aresta");
        System.out.println("e) Remover vértice");
        System.out.println("f) Remover aresta");
        System.out.println("g) Mostrar conteúdo do arquivo " + filePath);
        System.out.println("h) Mostrar grafo");
        System.out.println("i) Apresentar conexidade do grafo");
        System.out.println("j) Encerrar a aplicação");
        System.out.println("!) Mudar caminho de arquivo\n");
    }

    private static void graphNotLoadedYet() { // Adverte ao tentar processar grafo sem dados
        System.out.println("Grafo ainda não carregado!");
        System.out.println("Selecione a opção <a> para carregar dados ao grafo.\n");
    }

    public static void main(String[] args) {
        int origem, destino, peso; // Recebem input do usuário
        WUGraph graph = new WUGraph(); // Inicializa-se grafo vazio
        boolean flagControl = true; // Controla laço do menu
        String optionSelected = ""; // Recebe input do usuário
        boolean isGraphLoaded = false; // Controle de grafo com ou sem dados
        final String standardPath = "assets/grafo.txt"; // Caminho padrão da aplicação
        String filePath = "assets/grafo.txt"; // Variável de caminho do arquivo
        Scanner scanner = new Scanner(System.in);

        printMenu(filePath);
        while(flagControl) {
            System.out.print("Selecione uma opção (x para exibir menu de opções): ");
            optionSelected = scanner.nextLine();
            System.out.println();

            switch(optionSelected) {
                case "a": // Ler dados de grafo.txt
                    System.out.println("Lendo dados de grafo.txt...");
                    graph = new WUGraph(filePath);
                    // Quando dados carregados, libera opções de processamento em grafo
                    isGraphLoaded = true; 
                    System.out.println("Leitura de dados concluída.\n");
                    break;

                case "b": // Gravar dados no arquivo grafo.txt
                    if(!isGraphLoaded) {
                        graphNotLoadedYet();
                        continue;
                    }

                    graph.writeToFile(filePath);
                    System.out.println("Dados gravados em " + filePath + "!\n");
                    break;
                
                case "c": // Inserir vértice
                    if(!isGraphLoaded) {
                        graphNotLoadedYet();
                        continue;
                    }

                    System.out.print("Insira o rótulo do novo vértice: ");
                    String novoRotulo = scanner.nextLine();
                    graph.insereV(novoRotulo);

                    System.out.println("Vértice inserido!\n");
                    break;
                
                case "d": // Inserir aresta
                    if(!isGraphLoaded) {
                        graphNotLoadedYet();
                        continue;
                    }

                    System.out.print("Vértice de origem: ");
                    origem = Integer.parseInt(scanner.nextLine());

                    System.out.print("Vértice de destino: ");
                    destino = Integer.parseInt(scanner.nextLine());

                    System.out.print("Peso da aresta: ");
                    peso = Integer.parseInt(scanner.nextLine());

                    graph.insereA(origem, destino, peso);

                    System.out.println("Aresta inserida!\n");
                    break;
                
                case "e": // Remover vértice
                    if(!isGraphLoaded) {
                        graphNotLoadedYet();
                        continue;
                    }

                    System.out.print("Vértice a ser removido: ");
                    int vertice = Integer.parseInt(scanner.nextLine());

                    graph.removeV(vertice);

                    System.out.println("Vértice removido!\n");
                    break;

                case "f": // Remover aresta
                    if(!isGraphLoaded) {
                        graphNotLoadedYet();
                        continue;
                    }

                    System.out.print("Vértice de origem da aresta: ");
                    origem = Integer.parseInt(scanner.nextLine());
                    
                    System.out.print("Vértice de destino da aresta: ");
                    destino = Integer.parseInt(scanner.nextLine());

                    graph.removeA(origem, destino);
                    
                    System.out.println("Aresta removida!\n");
                    break;
                
                case "g": // Mostrar conteúdo do arquivo com caminho em filePath
                    System.out.println("Exibindo conteúdo em " + filePath + ":\n");
                    try {
                        String line;
                        BufferedReader reader = new BufferedReader(new FileReader(filePath));
                        while((line = reader.readLine()) != null) System.out.println(line);
                        reader.close();
                        System.out.println();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;

                case "h": // Mostrar grafo
                    if(!isGraphLoaded) {
                        graphNotLoadedYet();
                        continue;
                    }

                    System.out.println("Exibindo dados armazenados no grafo: \n");
                    graph.show();
                    System.out.println();

                    break;

                case "i": // Apresentar conexidade do grafo
                    if(!isGraphLoaded) {
                        graphNotLoadedYet();
                        continue;
                    }

                    System.out.println("0: Conexo; 1: Desconexo;");
                    System.out.println("Conexidade do grafo: " + graph.getConexidade() + "\n");

                    break;

                case "j": // Encerrar aplicação
                    System.out.println("Encerrando a aplicação...");
                    flagControl = false;
                    break;
                
                case "!": // Alterar caminho para arquivo
                    System.out.println("Caminho do arquivo atual: " + filePath);
                    System.out.print("Insira o novo caminho para arquivo (! para padrão | ? para cancelar): ");
                    String temp = scanner.nextLine();
                    
                    if(temp.equals("?")) { 
                        System.out.println("Alteração cancelada.\n");
                        continue;
                    };

                    filePath = temp;
                    if(filePath.equals("!")) filePath = standardPath;
                    System.out.println("Novo caminho para arquivo: " + filePath + "\n");
                    break;
                
                case "x": // Imprimir menu de opções
                    System.out.println();
                    printMenu(filePath);
                    break;
            
                default: // Tratar entrada inválida
                    System.out.println("Opção inválida. Tente novamente.\n");
                    break;
            }
        }

        scanner.close();
    }
}
