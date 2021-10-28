package model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
public class Cachorro extends Animal {

    private String porte;

    public Cachorro() {
    }

    public Cachorro(String nome, String raca, String cor, String sexo, String porte, Dono dono) {
        this.nome = nome;
        this.raca = raca;
        this.cor = cor;
        this.sexo = sexo;
        this.porte = porte;
        this.dono = dono;
        this.dataCadastro = LocalDate.now();
    }


    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }


    @Override
    public String emitirSom() {
        return "au au";
    }

    @Override
    public boolean isAdulto() {
        return idadeEmMeses > 24;
    }




    @Override
    public String toString() {
        return "Cachorro {" +
                ", nome='" + nome + '\'' +
                ", raca='" + raca + '\'' +
                ", cor='" + cor + '\'' +
                ", sexo='" + sexo + '\'' +
                " nasceu em " + this.localDateToString(dataNascimento) +
                " e tem " + calcularIdade(this.getDataNascimento(), this.getDataCadastro()) + " anos" +
                ", porte='" + porte + '\'' +
                '}';
    }
}