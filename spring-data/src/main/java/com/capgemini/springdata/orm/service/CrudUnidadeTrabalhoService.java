package com.capgemini.springdata.orm.service;

import com.capgemini.springdata.orm.entity.UnidadeTrabalho;
import com.capgemini.springdata.orm.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

        private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
        private Boolean system = true;

        public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
            this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
        }

        public void inicial(Scanner scan) {

            while (system) {
                System.out.println("Qual ação de UNIDADE DE TRABALHO deseja executar");
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
            System.out.println("Digite o nome da unidade");
            String nomeUnidade = scan.next();

            System.out.println("Digite o endereço da unidade");
            String endereco = scan.next();

            UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
            unidadeTrabalho.setDescricao(nomeUnidade);
            unidadeTrabalho.setEndereco(endereco);

            unidadeTrabalhoRepository.save(unidadeTrabalho);
            System.out.println("Salvo!");
        }

        private void atualizar(Scanner scan) {

            System.out.println("Digite o ID que deseja alterar");
            int id = scan.nextInt();

            Optional<UnidadeTrabalho> unidadeTrabalhoOptional = unidadeTrabalhoRepository.findById(id);
            UnidadeTrabalho unidadeTrabalho = unidadeTrabalhoOptional.get();

            System.out.println("Digite o nome da unidade");
            String nomeUnidade = scan.next();

            System.out.println("Digite o endereço da unidade");
            String endereco = scan.next();

            unidadeTrabalho.setDescricao(nomeUnidade);
            unidadeTrabalho.setEndereco(endereco);

            unidadeTrabalhoRepository.save(unidadeTrabalho);
            System.out.println("Atualizado!");

        }

        private void visualizar() {
            Iterable<UnidadeTrabalho> unidadeTrabalhos = unidadeTrabalhoRepository.findAll();
            unidadeTrabalhos.forEach(unidadeTrabalho -> System.out.println(unidadeTrabalho));

        }

        private void deletar(Scanner scan) {
            System.out.println("Digite o ID que deseja excluir");
            int id = scan.nextInt();
            unidadeTrabalhoRepository.deleteById(id);
            System.out.println("Deletado");


        }

    }







