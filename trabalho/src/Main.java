import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaDeReservas sistema = new SistemaDeReservas();

        // Adicionando alguns voos fixos
        Voo voo1 = new Voo("AB123", "São Paulo");
        Voo voo2 = new Voo("CD456", "Rio de Janeiro");
        Voo voo3 = new Voo("EF789", "Belo Horizonte");

        sistema.adicionarVoo(voo1);
        sistema.adicionarVoo(voo2);
        sistema.adicionarVoo(voo3);

        // Adicionando alguns passageiros fixos
        Passageiro passageiro1 = new Passageiro("João Silva", "123.456.789-00");
        Passageiro passageiro2 = new Passageiro("Maria Oliveira", "987.654.321-00");

        sistema.adicionarPassageiro(passageiro1);
        sistema.adicionarPassageiro(passageiro2);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Reservar Passagem");
            System.out.println("2. Listar Reservas");
            System.out.println("3. Associar Passageiro a Voo");
            System.out.println("4. Listar Voos");
            System.out.println("5. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha

            switch (opcao) {
                case 1:
                    reservarPassagem(scanner, sistema);
                    break;
                case 2:
                    listarReservas(sistema);
                    break;
                case 3:
                    associarPassageiro(scanner, sistema);
                    break;
                case 4:
                    listarVoos(sistema);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void reservarPassagem(Scanner scanner, SistemaDeReservas sistema) {
        System.out.println("Digite o código do voo:");
        String codigoVoo = scanner.nextLine();
        Voo voo = sistema.listarVoos().stream()
                .filter(v -> v.getCodigo().equals(codigoVoo))
                .findFirst()
                .orElse(null);

        if (voo == null) {
            System.out.println("Voo não encontrado.");
            return;
        }

        System.out.println("Digite o nome do passageiro:");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do passageiro:");
        String cpf = scanner.nextLine();
        Passageiro passageiro = sistema.buscarPassageiro(nome, cpf);

        if (passageiro == null) {
            System.out.println("Passageiro não encontrado. Deseja adicioná-lo? (s/n)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("s")) {
                passageiro = new Passageiro(nome, cpf);
                sistema.adicionarPassageiro(passageiro);
            } else {
                return;
            }
        }

        sistema.reservarPassagem(voo, passageiro);
        System.out.println("Passagem reservada com sucesso!");
    }

    private static void listarReservas(SistemaDeReservas sistema) {
        if (sistema.listarReservas().isEmpty()) {
            System.out.println("Nenhuma reserva encontrada.");
        } else {
            for (Reserva reserva : sistema.listarReservas()) {
                System.out.println("Passageiro: " + reserva.getPassageiro().getNome() +
                        ", Voo: " + reserva.getVoo().getCodigo() +
                        ", Destino: " + reserva.getVoo().getDestino());
            }
        }
    }

    private static void associarPassageiro(Scanner scanner, SistemaDeReservas sistema) {
        System.out.println("Digite o nome do passageiro:");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do passageiro:");
        String cpf = scanner.nextLine();
        Passageiro passageiro = new Passageiro(nome, cpf);
        sistema.adicionarPassageiro(passageiro);
        System.out.println("Passageiro associado com sucesso!");
    }

    private static void listarVoos(SistemaDeReservas sistema) {
        if (sistema.listarVoos().isEmpty()) {
            System.out.println("Nenhum voo encontrado.");
        } else {
            for (Voo voo : sistema.listarVoos()) {
                System.out.println(voo);
            }
        }
    }
}
