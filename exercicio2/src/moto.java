public class moto {
    String marca;
    String modelo;
    String cor;
    int ano;
    int cilindradas;

    public moto(String marca, String modelo, String cor, int ano, int cilindradas) {
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.cilindradas = cilindradas;

    }
    public void descricaomoto() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Cor: " + cor);
        System.out.println("Ano: " + ano);
        System.out.println("Cilindradas: " + cilindradas);
    }


}
