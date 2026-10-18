/*
 * CBTLPR2 - Trabalho Prático 03
 * Dupla:
 *   Gael Felix - CB3038912
 *   Guilherme Francisco - CB3037461
 */
package pessoa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class FormPessoaVersao01 extends JFrame {

    private final Pessoa umaPessoa = new Pessoa();

    private final JTextField txtNumero = new JTextField(15);
    private final JTextField txtNome = new JTextField(15);
    private final JTextField txtSexo = new JTextField(15);
    private final JTextField txtIdade = new JTextField(15);

    public FormPessoaVersao01() {
        super("Semana 06 - Exercicio 01");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        txtNumero.setEditable(false);
        txtNumero.setFocusable(false);
        configurarCampoSexo();

        JPanel pnlCampos = new JPanel(new GridLayout(4, 2, 2, 2));
        pnlCampos.setBackground(Color.LIGHT_GRAY);
        pnlCampos.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        adicionarCampo(pnlCampos, "Numero:", txtNumero);
        adicionarCampo(pnlCampos, "Nome:", txtNome);
        adicionarCampo(pnlCampos, "Sexo:", txtSexo);
        adicionarCampo(pnlCampos, "Idade:", txtIdade);

        JButton btnOk = new JButton("OK");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnMostrar = new JButton("Mostrar");
        JButton btnSair = new JButton("Sair");

        btnOk.addActionListener(e -> confirmar());
        btnLimpar.addActionListener(e -> limpar());
        btnMostrar.addActionListener(e -> mostrar());
        btnSair.addActionListener(e -> dispose());

        JPanel pnlBotoes = new JPanel(new GridLayout(1, 4, 2, 2));
        pnlBotoes.setBackground(Color.LIGHT_GRAY);
        pnlBotoes.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        pnlBotoes.add(btnOk);
        pnlBotoes.add(btnLimpar);
        pnlBotoes.add(btnMostrar);
        pnlBotoes.add(btnSair);

        add(pnlCampos, BorderLayout.CENTER);
        add(pnlBotoes, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void adicionarCampo(JPanel painel, String rotulo, JTextField campo) {
        JLabel lbl = new JLabel(rotulo);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD));
        painel.add(lbl);
        painel.add(campo);
    }

    private void configurarCampoSexo() {
        txtSexo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = Character.toUpperCase(e.getKeyChar());
                if (Character.isISOControl(c)) {
                    return;
                }
                String selecionado = txtSexo.getSelectedText();
                int tamanhoFinal = txtSexo.getText().length()
                        - (selecionado == null ? 0 : selecionado.length());
                if ((c != 'M' && c != 'F') || tamanhoFinal >= 1) {
                    e.consume();
                    return;
                }
                e.setKeyChar(c);
            }
        });
    }

    private void confirmar() {
        String nome = txtNome.getText().trim();
        String sexo = txtSexo.getText().trim().toUpperCase();
        String idadeTexto = txtIdade.getText().trim();

        if (nome.isEmpty()) {
            avisar("O campo Nome é obrigatório.", txtNome);
            return;
        }
        if (!sexo.equals("M") && !sexo.equals("F")) {
            avisar("O campo Sexo é obrigatório e só aceita M ou F.", txtSexo);
            return;
        }

        int idade;
        try {
            idade = Integer.parseInt(idadeTexto);
        } catch (NumberFormatException ex) {
            avisar("O campo Idade é obrigatório e deve ser um número inteiro.", txtIdade);
            return;
        }
        if (idade < 0 || idade > 150) {
            avisar("Informe uma idade entre 0 e 150.", txtIdade);
            return;
        }

        umaPessoa.setNome(nome);
        umaPessoa.setSexo(sexo.charAt(0));
        umaPessoa.setIdade(idade);
        umaPessoa.setKp();

        txtNumero.setText(String.valueOf(umaPessoa.getKp()));
        JOptionPane.showMessageDialog(this, "Dados transferidos para UmaPessoa com sucesso!",
                "OK", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpar() {
        txtNome.setText("");
        txtSexo.setText("");
        txtIdade.setText("");
        txtNome.requestFocus();
    }

    private void mostrar() {
        if (umaPessoa.getKp() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Nenhuma pessoa foi cadastrada ainda.\nPreencha os dados e clique em OK.",
                    "Mostrar", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String dados = "Dados de UmaPessoa:\n\n"
                + "Nome: " + umaPessoa.getNome() + "\n"
                + "Sexo: " + umaPessoa.getSexo() + "\n"
                + "Idade: " + umaPessoa.getIdade() + "\n\n"
                + "Contador kp (pessoas setadas): " + umaPessoa.getKp();
        JOptionPane.showMessageDialog(this, dados, "Mostrar", JOptionPane.INFORMATION_MESSAGE);
    }

    private void avisar(String mensagem, JComponent campo) {
        JOptionPane.showMessageDialog(this, mensagem, "Atenção", JOptionPane.WARNING_MESSAGE);
        campo.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPessoaVersao01().setVisible(true));
    }
}
