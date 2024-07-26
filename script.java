import java.util.ArrayList;
import java.util.Scanner;

// Classe Moeda pra definir a estrutura básica das moedas
abstract class Moeda {
    protected double valor; // Valor da moeda

    public Moeda(double valor) {
        this.valor = valor;
    }

    public abstract void info(); // Método abstrato pra informações da moeda serem visíveis

    public double converter() {
        // Método de converter os valores pra Real
        return this.valor;
    }
}

// Classes concretas pra moedas diferentes
class Dolar extends Moeda {
    public Dolar(double valor) {
        super(valor);
    }

    @Override
    public void info() {
        System.out.println("Moeda: Dólar");
        System.out.println("Valor: $" + this.valor);
    }
}

class Euro extends Moeda {
    public Euro(double valor) {
        super(valor);
    }

    @Override
    public void info() {
        System.out.println("Moeda: Euro");
        System.out.println("Valor: €" + this.valor);
    }
}

class Real extends Moeda {
    public Real(double valor) {
        super(valor);
    }

    @Override
    public void info() {
        System.out.println("Moeda: Real");
        System.out.println("Valor: R$" + this.valor);
    }
}

// Classe "Cofrinho" pra gerenciar as moedas
class Cofrinho {
    private ArrayList<Moeda> colecaoMoedas; // ArrayList para armazenar as moedas

    public Cofrinho() {
        this.colecaoMoedas = new ArrayList<>();
    }

    public void adicionarMoeda(Moeda moeda) {
        this.colecaoMoedas.add(moeda);
        System.out.println("Ka-ching! Moeda adicionada ao BatCofrinho, o cofrinho do Batman!");
    }

    public void removerMoeda(Moeda moeda) {
        if (this.colecaoMoedas.remove(moeda)) {
            System.out.println("Moeda removida do BatCofrinho! Ser o Batman é incrível, mas não de graça.");
        } else {
            System.out.println("Essa moeda não está no BatCofrinho! Talvez o Batman a tenha usado para alguma caridade ou para comprar capas novas.");
        }
    }

    public void listarMoedas() {
        if (this.colecaoMoedas.isEmpty()) {
            System.out.println("O BatCofrinho tá vazio! O Batman deve estar em uma missão, o dinheiro já foi transferido pra outro lugar.");
        } else {
            System.out.println("Moedas no BatCofrinho:");
            for (Moeda moeda : colecaoMoedas) {
                moeda.info(); // Mostra informações da moeda específica
                System.out.println(); // Pula linha pra separar as moedas na lista
            }
        }
    }

    public double calcularTotalConvertido() {
        double total = 0.0;
        for (Moeda moeda : colecaoMoedas) {
            total += moeda.converter(); // Soma o valor convertido de cada moeda
        }
        return total;
    }
}

// Classe Principal para interação com o usuário
public class BatCofrinho {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cofrinho batCofrinho = new Cofrinho();

        System.out.println("Bem-vindo ao BatCofrinho, o cofrinho do Batman, onde ele guarda a fortuna da família Wayne!");
        System.out.println("Aproveite para administrar as moedas do Batman com sabedoria.");

        while (true) {
            System.out.println("\n=== Menu do BatCofrinho ===");
            System.out.println("1. Adicionar moeda para o Batman");
            System.out.println("2. Remover moedas do Batman");
            System.out.println("3. Consultar moedas do Batman");
            System.out.println("4. Calcular total das moedas do Batman convertido para Reais");
            System.out.println("5. Sair do BatCofrinho");

            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.print("Que tipo de moeda você quer adicionar para o Batman? (Dolar, Euro, Real): ");
                    String tipoMoeda = scanner.next().toLowerCase();

                    System.out.print("Qual é o valor da moeda do Batman? ");
                    double valorMoeda = scanner.nextDouble();

                    Moeda novaMoeda = null;
                    switch (tipoMoeda) {
                        case "dolar":
                            novaMoeda = new Dolar(valorMoeda);
                            break;
                        case "euro":
                            novaMoeda = new Euro(valorMoeda);
                            break;
                        case "real":
                            novaMoeda = new Real(valorMoeda);
                            break;
                        default:
                            System.out.println("Tipo de moeda desconhecido! O BatCofrinho não aceita moedas do Coringa.");
                            continue; // Volta ao início do loop
                    }

                    batCofrinho.adicionarMoeda(novaMoeda);
                    break;
                case 2:
                    System.out.print("Qual tipo de moeda você quer sacar? (Dolar, Euro, Real): ");
                    tipoMoeda = scanner.next().toLowerCase();

                    Moeda moedaRemover = null;
                    for (Moeda moeda : batCofrinho.colecaoMoedas) {
                        if (moeda.getClass().getSimpleName().toLowerCase().equals(tipoMoeda)) {
                            moedaRemover = moeda;
                            break;
                        }
                    }

                    if (moedaRemover != null) {
                        batCofrinho.removerMoeda(moedaRemover);
                    } else {
                        System.out.println("Essa moeda não foi encontrada no BatCofrinho! O Robin deve ter bagunçado as coisas... Crianças, são terríveis.");
                    }
                    break;
                case 3:
                    batCofrinho.listarMoedas();
                    break;
                case 4:
                    double totalConvertido = batCofrinho.calcularTotalConvertido();
                    System.out.println("Total no BatCofrinho convertido para Reais: R$" + totalConvertido);
                    break;
                case 5:
                    System.out.println("Você está saindo do BatCofrinho... Quando precisar da gente de novo, mande um batsinal!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida, cidadão! Escolha um número do menu do BatCofrinho.");
            }
        }
    }
}
