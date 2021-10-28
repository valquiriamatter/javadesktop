package com.capgemini.springdata.orm.service;

import com.capgemini.springdata.orm.entity.Funcionario;
import com.capgemini.springdata.orm.repository.FuncionarioRepository;
import com.capgemini.springdata.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {

    private final FuncionarioRepository funcionarioRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scan){

        System.out.println("Digite um nome");
        String nome = scan.next();

        if(nome.equalsIgnoreCase("NULL")){
            nome = null;
        }

        System.out.println("Digite um cpf");
        String cpf = scan.next();

        if(cpf.equalsIgnoreCase("NULL")){
            cpf = null;
        }

        System.out.println("Digite um salário");
        Double salario = scan.nextDouble();

        if(salario == 0){
            salario = null;
        }

        System.out.println("Digite a data de contratação");
        String data = scan.next();

        LocalDate dataContratacao = null;
        if(data.equalsIgnoreCase("NULL")){
            data = null;
        }else{
            dataContratacao = LocalDate.parse(data, formatter);
        }

        List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification.where(
                SpecificationFuncionario.nome(nome))
                .or(SpecificationFuncionario.cpf(cpf))
                .or(SpecificationFuncionario.dataContratacao(dataContratacao))
                .or(SpecificationFuncionario.salario(salario)));

        funcionarios.forEach(System.out::println);

    }

}
