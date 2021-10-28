package model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Dono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String nome;
    public String telefone;

    @OneToMany(mappedBy = "dono", cascade = CascadeType.PERSIST)
    public List<Animal> listaAnimais;

    public Dono(){
    }

    public Dono(String nome) {
        this.nome = nome;
    }

    public Dono(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Animal> getListaAnimais() {
        return listaAnimais;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setListaAnimais(List<Animal> listaAnimais) {
        this.listaAnimais = listaAnimais;
    }

    @Override
    public String toString() {
        return "Dono{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", listaAnimais=" + listaAnimais +
                '}';
    }
}
