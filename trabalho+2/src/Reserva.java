public class Reserva {
    private Voo voo;
    private Passageiro passageiro;

    public Reserva(Voo voo, Passageiro passageiro) {
        this.voo = voo;
        this.passageiro = passageiro;
        voo.adicionarPassageiro(passageiro);
    }

    public Voo getVoo() {
        return voo;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }
}
