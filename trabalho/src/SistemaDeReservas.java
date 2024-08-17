import java.util.ArrayList;
import java.util.List;

public class SistemaDeReservas {
    private List<Voo> voos;
    private List<Reserva> reservas;
    private List<Passageiro> passageiros;

    public SistemaDeReservas() {
        this.voos = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.passageiros = new ArrayList<>();
    }

    public void adicionarVoo(Voo voo) {
        voos.add(voo);
    }

    public void adicionarPassageiro(Passageiro passageiro) {
        passageiros.add(passageiro);
    }

    public Passageiro buscarPassageiro(String nome, String cpf) {
        for (Passageiro p : passageiros) {
            if (p.getNome().equals(nome) && p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null;
    }

    public void reservarPassagem(Voo voo, Passageiro passageiro) {
        Reserva reserva = new Reserva(voo, passageiro);
        reservas.add(reserva);
    }

    public List<Reserva> listarReservas() {
        return reservas;
    }

    public List<Voo> listarVoos() {
        return voos;
    }
}
