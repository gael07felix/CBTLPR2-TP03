/*
 * CBTLPR2 - Trabalho Prático 03
 * Dupla:
 *   Gael Felix - CB3038912
 *   Guilherme Francisco - CB3037461
 */
package pessoa;

public class Pessoa {

    protected static int kp;
    protected String nome;
    protected char sexo;
    protected int idade;

    public Pessoa() {
    }

    public Pessoa(String nome, char sexo, int idade) {
        setNome(nome);
        setSexo(sexo);
        setIdade(idade);
        setKp();
    }

    public void setKp() {
        kp++;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getKp() {
        return kp;
    }

    public String getNome() {
        return nome;
    }

    public char getSexo() {
        return sexo;
    }

    public int getIdade() {
        return idade;
    }
}
