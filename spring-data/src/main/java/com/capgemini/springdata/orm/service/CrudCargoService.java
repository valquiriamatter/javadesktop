package com.capgemini.springdata.orm.service;

import com.capgemini.springdata.orm.entity.Cargo;
import com.capgemini.springdata.orm.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private final CargoRepository cargoRepository;
    private Boolean system = true;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scan) {

        while (system) {
            System.out.println("Qual ação de CARGO deseja executar");
            System.out.println("0-SAIR");
            System.out.println("1-CADASTRAR");
            System.out.println("2-ATUALIZAR");
            System.out.println("3-LISTAR");
            System.out.println("4-DELETAR");

            int action = scan.nextInt();

            switch (action) {
                case 1:
                    salvar(scan);
                    break;
                case 2:
                    atualizar(scan);
                    break;
                case 3:
                    visualizar();
                    break;
                case 4:
                    deletar(scan);
                    break;
                default:
                    system = false;
                    break;
            }

        }
    }


    private void salvar(Scanner scan) {
        System.out.println("Descrição do Cargo");
        String descricao = scan.next();

        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);

        cargoRepository.save(cargo);
        System.out.println("Cargo Salvo! ");
    }

    private void atualizar(Scanner scan) {
        System.out.println("Digite o ID que deseja alterar");
        int id = scan.nextInt();

        System.out.println("Descrição do Cargo");
        String descricao = scan.next();

        Cargo cargo = new Cargo();

        cargo.setId(id);
        cargo.setDescricao(descricao);

        cargoRepository.save(cargo);
        System.out.println("Cargo Atualizado!");
    }

    private void visualizar() {
        Iterable<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(cargo -> System.out.println(cargo));

    }

    private void deletar(Scanner scan) {
        System.out.println("Digite o ID que deseja alterar");
        int id = scan.nextInt();
        cargoRepository.deleteById(id);
        System.out.println("Deletado");


    }

}



