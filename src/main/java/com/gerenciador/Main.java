package com.gerenciador;

import com.gerenciador.dao.ProdutoDAO;
import com.gerenciador.modelo.Produto;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String senha = System.getenv("DB_PASSWORD");
        System.setProperty("DB_PASSWORD", senha);

        Scanner scanner = new Scanner(System.in);
        ProdutoDAO produtoDAO = new ProdutoDAO();

        int opcao;

        do {
            System.out.println("\n===== GERENCIADOR DE PRODUTOS =====");
            System.out.println("[1] Adicionar Produto");
            System.out.println("[2] Listar Produtos");
            System.out.println("[3] Atualizar Produto");
            System.out.println("[4] Remover Produto");
            System.out.println("[5] Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Preço: ");
                    Double preco = scanner.nextDouble();

                    System.out.print("Quantidade: ");
                    Integer quantidade = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Categoria: ");
                    String categoria = scanner.nextLine();

                    Produto novoProduto = new Produto(
                            nome,
                            descricao,
                            preco,
                            quantidade,
                            categoria
                    );

                    boolean cadastrado = produtoDAO.salvar(novoProduto);

                    if (cadastrado) {
                        System.out.println("Produto cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar produto.");
                    }

                    break;
                case 2:

                    List<Produto> produtos = produtoDAO.listar();

                    if (produtos.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado.");
                    } else {

                        for (Produto produto : produtos) {

                            System.out.println("------------------------------");
                            System.out.println("ID: " + produto.getId());
                            System.out.println("Nome: " + produto.getNome());
                            System.out.println("Descrição: " + produto.getDescricao());
                            System.out.println("Preço: R$ " + produto.getPreco());
                            System.out.println("Quantidade: " + produto.getQuantidade());
                            System.out.println("Categoria: " + produto.getCategoria());
                        }
                    }

                    break;

                case 3:

                    System.out.print("Digite o ID do produto: ");
                    Long idAtualizar = scanner.nextLong();
                    scanner.nextLine();

                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();

                    System.out.print("Nova descrição: ");
                    String novaDescricao = scanner.nextLine();

                    System.out.print("Novo preço: ");
                    Double novoPreco = scanner.nextDouble();

                    System.out.print("Nova quantidade: ");
                    Integer novaQuantidade = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nova categoria: ");
                    String novaCategoria = scanner.nextLine();

                    Produto produtoAtualizado = new Produto(
                            novoNome,
                            novaDescricao,
                            novoPreco,
                            novaQuantidade,
                            novaCategoria
                    );

                    produtoAtualizado.setId(idAtualizar);

                    boolean atualizado = produtoDAO.atualizar(produtoAtualizado);

                    if (atualizado) {
                        System.out.println("Produto atualizado com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar produto.");
                    }

                    break;

                case 4:

                    System.out.print("Digite o ID do produto: ");
                    Long idRemover = scanner.nextLong();

                    boolean removido = produtoDAO.remover(idRemover);

                    if (removido) {
                        System.out.println("Produto removido com sucesso!");
                    } else {
                        System.out.println("Produto não encontrado ou erro ao remover.");
                    }

                    break;

                case 5:

                    System.out.println("Encerrando programa...");

                    break;

                default:

                    System.out.println("Opção inválida.");
            }

        } while (opcao != 5);

        scanner.close();
    }
}