/*
 * CBTLPR2 - Trabalho Prático 03
 * Dupla:
 *   Gael Felix - CB3038912
 *   Guilherme Francisco - CB3037461
 */
package calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Calculadora extends JFrame {

    private final JTextField txtResultado = new JTextField("0");

    private double memoria = 0;
    private String operacao = "";
    private boolean novoNumero = true;

    public Calculadora() {
        super("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        txtResultado.setEditable(false);
        txtResultado.setHorizontalAlignment(JTextField.RIGHT);
        txtResultado.setFont(new Font("SansSerif", Font.PLAIN, 18));
        txtResultado.setBackground(Color.WHITE);

        JPanel pnlBotoes = new JPanel(new GridLayout(5, 4, 3, 3));
        String[] rotulos = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };
        for (String rotulo : rotulos) {
            JButton botao = new JButton(rotulo);
            botao.setFont(new Font("SansSerif", Font.BOLD, 14));
            botao.addActionListener(this::botaoClicado);
            pnlBotoes.add(botao);
        }

        JPanel pnlPrincipal = new JPanel(new BorderLayout(0, 5));
        pnlPrincipal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlPrincipal.add(txtResultado, BorderLayout.NORTH);
        pnlPrincipal.add(pnlBotoes, BorderLayout.CENTER);
        setContentPane(pnlPrincipal);

        setSize(260, 280);
        setLocationRelativeTo(null);
    }

    private void botaoClicado(ActionEvent e) {
        String tecla = e.getActionCommand();
        switch (tecla) {
            case "C":
                limpar();
                break;
            case "=":
                calcularResultado();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                definirOperacao(tecla);
                break;
            case ".":
                adicionarPonto();
                break;
            default:
                adicionarDigito(tecla);
        }
    }

    private void adicionarDigito(String digito) {
        if (novoNumero || txtResultado.getText().equals("0")) {
            txtResultado.setText(digito);
        } else {
            txtResultado.setText(txtResultado.getText() + digito);
        }
        novoNumero = false;
    }

    private void adicionarPonto() {
        if (novoNumero) {
            txtResultado.setText("0.");
            novoNumero = false;
        } else if (!txtResultado.getText().contains(".")) {
            txtResultado.setText(txtResultado.getText() + ".");
        }
    }

    private void definirOperacao(String novaOperacao) {
        try {
            double valor = lerVisor();
            if (!operacao.isEmpty() && !novoNumero) {
                memoria = calcular(memoria, valor, operacao);
                mostrar(memoria);
            } else if (operacao.isEmpty()) {
                memoria = valor;
            }
            operacao = novaOperacao;
        } catch (ArithmeticException ex) {
            mostrarErro(ex.getMessage());
        } catch (NumberFormatException ex) {
            mostrarErro("Número inválido.");
        } finally {
            novoNumero = true;
        }
    }

    private void calcularResultado() {
        if (operacao.isEmpty()) {
            return;
        }
        try {
            double resultado = calcular(memoria, lerVisor(), operacao);
            mostrar(resultado);
            memoria = resultado;
        } catch (ArithmeticException ex) {
            mostrarErro(ex.getMessage());
        } catch (NumberFormatException ex) {
            mostrarErro("Número inválido.");
        } finally {
            operacao = "";
            novoNumero = true;
        }
    }

    private double calcular(double a, double b, String op) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Não é possível dividir por zero.");
                }
                return a / b;
            default:
                throw new IllegalStateException("Operação desconhecida: " + op);
        }
    }

    private double lerVisor() {
        return Double.parseDouble(txtResultado.getText());
    }

    private void mostrar(double valor) {
        if (Double.isInfinite(valor) || Double.isNaN(valor)) {
            throw new ArithmeticException("Resultado fora do limite da calculadora.");
        }
        if (valor == Math.rint(valor) && Math.abs(valor) < 1e15) {
            txtResultado.setText(String.valueOf((long) valor));
        } else {
            txtResultado.setText(String.valueOf(valor));
        }
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
        limpar();
    }

    private void limpar() {
        memoria = 0;
        operacao = "";
        novoNumero = true;
        txtResultado.setText("0");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculadora().setVisible(true));
    }
}
