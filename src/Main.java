import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        FileWriter arq = new FileWriter("d:\\loja.txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        Controller controller = new Controller();
        boolean flag = true;
        gravarArq.println("LOJA SANTANDER CODERS\n" +
                "SEJA BEM VIND@\n");
        String menu = "Selecione uma das opções abaixo\n" +
                " (1)Cadastrar Produto\n (2)Editar Produto\n (3)Excluir Produto\n (4)Pesquisar Produto\n (5)Comprar Produtos\n (6)Sair da Loja";

        while(flag) {
            gravarArq.println(menu);
            Integer opcao = Integer.parseInt(in.nextLine());
            switch (opcao){
                case 1: {
                    System.out.println("Digite o nome do Produto: ");
                    String nome = in.nextLine();
                    System.out.println("Informe a quantidade do produto disponível: ");
                    Integer quant = Integer.parseInt(in.nextLine());
                    System.out.println("Insira o preço unitário do produto: ");
                    Double preco = Double.parseDouble(in.nextLine());
                    if(controller.adicionaProduto(nome, quant, preco)>=0) System.out.println("Produto adicionado com sucesso\n");
                    else System.out.println("Informações inválidas, tente novamente\n");
                    break;
                }
                case 2: {
                    if (controller.getTamanho()<1) {
                        System.out.println("Não há produtos no sistema da loja. Tente novamente");
                        break;
                    }
                    System.out.println("Qual produto você deseja editar: ");
                    System.out.println(controller.listaProdutos());
                    Integer idProduto = Integer.parseInt(in.nextLine());
                    if (!controller.contemProduto(idProduto)){
                        System.out.println("ID não encontrado no sistema\n");
                        break;
                    }
                    System.out.println("Digite o novo nome do Produto: ");
                    String nome = in.nextLine();
                    System.out.println("Informe a quantidade do produto dísponível: ");
                    Integer quant = Integer.parseInt(in.nextLine());
                    System.out.println("Insira o preço unitário do produto: ");
                    Double preco = Double.parseDouble(in.nextLine());
                    if (controller.editaProduto(idProduto, nome, quant, preco)) System.out.println("Produto editado com sucesso\n");
                    else System.out.println("Informações inválidas, tente novamente\n");
                    break;
                }
                case 3: {
                    if (controller.getTamanho()<1) {
                        System.out.println("Não há produtos no sistema da loja. Tente novamente");
                        break;
                    }
                    System.out.println("Qual produto você deseja excluir: ");
                    System.out.println(controller.listaProdutos());
                    Integer idProduto = Integer.parseInt(in.nextLine());
                    if (!controller.contemProduto(idProduto)){
                        System.out.println("ID não encontrado no sistema\n");
                        break;
                    }
                    controller.excluirProduto(idProduto);
                    System.out.println("Produto excluido com sucesso");
                    break;
                }
                case 4: {
                    if (controller.getTamanho()<1) {
                        System.out.println("Não há produtos no sistema da loja. Tente novamente");
                        break;
                    }
                    System.out.println("Digite o nome, completamente ou parcialmente, do produto que deseja buscar: ");
                    String nomeProduto = in.nextLine();
                    if (nomeProduto.trim().isEmpty()) {
                        System.out.println("Nome inválido. Tente novamente");
                        break;
                    }
                    System.out.println(controller.buscaProduto(nomeProduto));
                    break;
                }
                case 5: {
                    if (controller.getTamanho()<1) {
                        System.out.println("Não há produtos no sistema da loja. Tente novamente");
                        break;
                    }
                    ArrayList<Integer> idProdutos = new ArrayList<>();
                    ArrayList<Integer> quantidades = new ArrayList<>();
                    while(true) {
                        System.out.println(controller.listaProdutos());
                        System.out.println("Qual produto você deseja comprar(digite o seu número de codificação):");
                        Integer idProduto = Integer.parseInt(in.nextLine());
                        System.out.println("Qual a quantidade desse item que você deseja comprar?");
                        Integer quantidade = Integer.parseInt(in.nextLine());
                        if (!controller.verificaQuantidade(idProduto, quantidade)) System.out.println("Erro com a sua compra. Não há estoque suficiente para a sua compra");
                        else {
                            idProdutos.add(idProduto);
                            quantidades.add(quantidade);
                        }
                        System.out.println("Você deseja finalizar as suas compras?");
                        String finalizar = in.nextLine().trim().toLowerCase();
                        if (finalizar.equals("sim") || finalizar.equals("s")) {
                            System.out.println("O valor total da sua compra é: " + controller.calculaPrecoTotal(idProdutos, quantidades) + "\n");
                            controller.compraProduto(idProdutos, quantidades);
                            break;
                        }
                    }
                }
                case 6: {
                    System.out.println("Obrigado por utilizar o sistema da nossa loja, espero que volte em breve ;)");
                    flag = false;
                    break;
                }
                default:
                    System.out.println("Opção inválide. Tente novamente\n");
                    break;
            }
        }
    }
}
