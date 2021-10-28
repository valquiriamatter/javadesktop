package model.entity;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Gato extends Animal {

    public Gato(){
    }

    public Gato(String nome, String raca, String cor, String sexo, Dono dono) {
        this.nome = nome;
        this.raca = raca;
        this.cor = cor;
        this.sexo = sexo;
        this.dono = dono;
        this.dataCadastro = LocalDate.now();
    }

    @Override
    public String emitirSom() {
        return "miau miau";
    }

    @Override
    public boolean isAdulto() {
        return idadeEmMeses > 12;
    }

    @Override
    public String toString() {
        return "Gato {" +
                ", nome='" + nome + '\'' +
                ", raca='" + raca + '\'' +
                ", cor='" + cor + '\'' +
                ", sexo='" + sexo + '\'' +
                " nasceu em " + this.localDateToString(dataNascimento) +
                " e tem " + calcularIdade(this.getDataNascimento(), this.getDataCadastro()) + " anos" +
                '}';
    }




}
