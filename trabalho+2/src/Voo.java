import java.util.ArrayList;
import java.util.List;

public class Voo {
    private String codigo;
    private String destino;
    private List<Passageiro> passageiros;

    public Voo(String codigo, String destino) {
        this.codigo = codigo;
        this.destino = destino;
        this.passageiros = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDestino() {
        return destino;
    }

    public List<Passageiro> getPassageiros() {
        return passageiros;
    }

    public void adicionarPassageiro(Passageiro passageiro) {
        passageiros.add(passageiro);
    }

    @Override
    public String toString() {
        return codigo + " - " + destino;
    }
}
