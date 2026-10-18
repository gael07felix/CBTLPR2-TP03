/*
 * CBTLPR2 - Trabalho Prático 03
 * Dupla:
 *   Gael Felix - CB3038912
 *   Guilherme Francisco - CB3037461
 */
package pessoa;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Principal extends JFrame {

    public Principal() {
        super("Cadastro de Pessoa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JLabel lblTitulo = new JLabel("Escolha a versão do formulário:", SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 14f));

        JButton btnVersao01 = new JButton("Versão 01 - Sexo digitado (JTextField)");
        JButton btnVersao02 = new JButton("Versão 02 - Sexo na lista (JComboBox)");
        JButton btnVersao03 = new JButton("Versão 03 - Sexo por opção (JRadioButton)");
        JButton btnSair = new JButton("Sair");

        btnVersao01.addActionListener(e -> new FormPessoaVersao01().setVisible(true));
        btnVersao02.addActionListener(e -> new FormPessoaVersao02().setVisible(true));
        btnVersao03.addActionListener(e -> new FormPessoaVersao03().setVisible(true));
        btnSair.addActionListener(e -> System.exit(0));

        JPanel pnlBotoes = new JPanel(new GridLayout(4, 1, 5, 5));
        pnlBotoes.add(btnVersao01);
        pnlBotoes.add(btnVersao02);
        pnlBotoes.add(btnVersao03);
        pnlBotoes.add(btnSair);

        JPanel pnlPrincipal = new JPanel(new BorderLayout(0, 10));
        pnlPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlPrincipal.add(lblTitulo, BorderLayout.NORTH);
        pnlPrincipal.add(pnlBotoes, BorderLayout.CENTER);
        setContentPane(pnlPrincipal);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Principal().setVisible(true));
    }
}
