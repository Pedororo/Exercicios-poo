import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Retangulo ret1 = new Retangulo(5, 10);
        Retangulo ret2 = new Retangulo(3, 6);

        ret1.exibirInformacoes();
        System.out.println();
        ret2.exibirInformacoes();
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        Retangulo[] retangulos = new Retangulo[5];

        for (int i = 0; i < retangulos.length; i++) {
            System.out.println("Digite a largura do retângulo " + (i + 1) + ": ");
            double largura = scanner.nextDouble();
            System.out.println("Digite a altura do retângulo " + (i + 1) + ": ");
            double altura = scanner.nextDouble();
            retangulos[i] = new Retangulo(largura, altura);
        }

        for (Retangulo retangulo : retangulos) {
            retangulo.exibirInformacoes();
            System.out.println();
        }

        Retangulo maiorArea = retangulos[0];
        for (Retangulo retangulo : retangulos) {
            if (retangulo.calcularArea() > maiorArea.calcularArea()) {
                maiorArea = retangulo;
            }
        }
        System.out.println("Retângulo com a maior área:");
        maiorArea.exibirInformacoes();
        System.out.println();

        Retangulo menorPerimetro = retangulos[0];
        for (Retangulo retangulo : retangulos) {
            if (retangulo.calcularPerimetro() < menorPerimetro.calcularPerimetro()) {
                menorPerimetro = retangulo;
            }
        }
        System.out.println("Retângulo com o menor perímetro:");
        menorPerimetro.exibirInformacoes();
        System.out.println();

        Arrays.sort(retangulos, Comparator.comparingDouble(Retangulo::calcularArea));

        System.out.println("Retângulos ordenados por área:");
        for (Retangulo retangulo : retangulos) {
            retangulo.exibirInformacoes();
            System.out.println();
        }

        retangulos = Arrays.copyOf(retangulos, 10);

        for (int i = 5; i < retangulos.length; i++) {
            System.out.println("Digite a largura do retângulo " + (i + 1) + ": ");
            double largura = scanner.nextDouble();
            System.out.println("Digite a altura do retângulo " + (i + 1) + ": ");
            double altura = scanner.nextDouble();
            retangulos[i] = new Retangulo(largura, altura);
        }

        System.out.println("Informações de todos os retângulos após a expansão:");
        for (Retangulo retangulo : retangulos) {
            retangulo.exibirInformacoes();
            System.out.println();
        }
    }
}