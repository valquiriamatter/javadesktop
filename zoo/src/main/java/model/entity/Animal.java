package model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Animal {

    @Id
    @GeneratedValue
    protected int id;
    protected String nome;
    protected String raca;
    protected String cor;
    protected String sexo;

    //forte
    @ManyToOne
    protected Dono dono;

    protected int idadeEmMeses;
    protected LocalDate dataNascimento;
    protected LocalDate dataCadastro;


    public Animal() {
    }

    public Animal(String nome, String raca, String cor, String sexo, Dono dono) {
        this.nome = nome;
        this.raca = raca;
        this.cor = cor;
        this.sexo = sexo;
        this.dono = dono;
        this.dataCadastro = LocalDate.now();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Dono getDono() {
        return dono;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }

    public int getIdadeEmMeses() {
        return idadeEmMeses;
    }

    public void setIdadeEmMeses(int idadeEmMeses) {
        this.idadeEmMeses = idadeEmMeses;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String emitirSom() {
        return "Emitindo som";
    }

    public boolean isAdulto() {
        return idadeEmMeses > 0;
    }

    //todo
    public long calcularIdade(LocalDate dataCadastro, LocalDate dataNascimento) {
        return ChronoUnit.YEARS.between(dataCadastro, dataNascimento);
    }

    public static LocalDate stringToLocalDate(String data) {

        //cria a padrão que virá a String
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //convert String to LocalDate já no formato
        LocalDate localDate = LocalDate.parse(data, formatter);

        System.out.println("Date recebido: " + data);
        System.out.println("LocalDate depois de formatar: " + localDate);

        return localDate;

    }

    public static String localDateToString(LocalDate data) {

        //System.out.println("LocalDateTime antes de formatar: " + data);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String agoraFormatado = data.format(formatter);

        //System.out.println("LocalDateTime depois de formatar: " + agoraFormatado);

        return agoraFormatado;
    }


}
