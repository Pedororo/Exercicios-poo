import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Swing {
    public static void main(String[] args) {
        SistemaDeReservas sistema = new SistemaDeReservas();

        Voo voo1 = new Voo("AB123", "São Paulo");
        Voo voo2 = new Voo("CD456", "Rio de Janeiro");
        Voo voo3 = new Voo("EF789", "Belo Horizonte");

        sistema.adicionarVoo(voo1);
        sistema.adicionarVoo(voo2);
        sistema.adicionarVoo(voo3);

        SwingUtilities.invokeLater(() -> {
            JanelaLogin janelaLogin = new JanelaLogin(sistema);
            janelaLogin.setVisible(true);
        });
    }
}

class JanelaLogin extends JFrame {
    private SistemaDeReservas sistema;

    public JanelaLogin(SistemaDeReservas sistema) {
        this.sistema = sistema;

        setTitle("Login de Passageiro");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblCpf = new JLabel("CPF:");
        JTextField txtCpf = new JTextField();

        JButton btnLogin = new JButton("Login");

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String cpf = txtCpf.getText();

                Passageiro passageiro = sistema.buscarPassageiro(nome, cpf);
                if (passageiro == null) {
                    passageiro = new Passageiro(nome, cpf);
                    sistema.adicionarPassageiro(passageiro);
                }

                JanelaPrincipal janelaPrincipal = new JanelaPrincipal(sistema, passageiro);
                janelaPrincipal.setVisible(true);
                dispose();
            }
        });

        add(lblNome);
        add(txtNome);
        add(lblCpf);
        add(txtCpf);
        add(new JLabel());
        add(btnLogin);
    }
}

class JanelaPrincipal extends JFrame {
    private SistemaDeReservas sistema;
    private Passageiro passageiro;

    public JanelaPrincipal(SistemaDeReservas sistema, Passageiro passageiro) {
        this.sistema = sistema;
        this.passageiro = passageiro;

        setTitle("Sistema de Reservas - Bem-vindo, " + passageiro.getNome());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton btnReservar = new JButton("Reservar Passagem");
        JButton btnListarReservas = new JButton("Minhas Reservas");

        btnReservar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new JanelaReservar(sistema, passageiro).setVisible(true);
            }
        });

        btnListarReservas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new JanelaListarReservas(sistema, passageiro).setVisible(true);
            }
        });

        add(btnReservar);
        add(btnListarReservas);
    }
}

class JanelaReservar extends JFrame {
    private SistemaDeReservas sistema;
    private Passageiro passageiro;
    private JComboBox<Voo> comboVoos;

    public JanelaReservar(SistemaDeReservas sistema, Passageiro passageiro) {
        this.sistema = sistema;
        this.passageiro = passageiro;

        setTitle("Reservar Passagem");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));

        JLabel lblVoo = new JLabel("Voo:");
        comboVoos = new JComboBox<>(sistema.listarVoos().toArray(new Voo[0]));

        JButton btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Voo vooSelecionado = (Voo) comboVoos.getSelectedItem();
                sistema.reservarPassagem(vooSelecionado, passageiro);
                JOptionPane.showMessageDialog(JanelaReservar.this, "Passagem reservada com sucesso!");
                dispose();
            }
        });

        add(lblVoo);
        add(comboVoos);
        add(new JLabel());
        add(btnReservar);
    }
}

class JanelaListarReservas extends JFrame {
    private SistemaDeReservas sistema;
    private Passageiro passageiro;

    public JanelaListarReservas(SistemaDeReservas sistema, Passageiro passageiro) {
        this.sistema = sistema;
        this.passageiro = passageiro;

        setTitle("Minhas Reservas");
        setSize(400, 300);
        setLayout(new BorderLayout());

        JTextArea txtReservas = new JTextArea();
        txtReservas.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtReservas);

        List<Reserva> reservas = sistema.listarReservasPorPassageiro(passageiro);
        for (Reserva reserva : reservas) {
            txtReservas.append("Voo: " + reserva.getVoo().getCodigo() +
                    ", Destino: " + reserva.getVoo().getDestino() + "\n");
        }

        add(scrollPane, BorderLayout.CENTER);
    }
}
