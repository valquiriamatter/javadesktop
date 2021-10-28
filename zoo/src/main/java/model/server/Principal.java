package model.server;

import model.dao.CachorroDao;
import model.dao.DonoDao;
import model.dao.GatoDao;
import model.entity.Animal;
import model.entity.Cachorro;
import model.entity.Dono;
import model.entity.Gato;
import model.factory.JPAUtil;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        EntityManager entityManager = JPAUtil.getEntityManager();
        Scanner scanner = new Scanner(System.in);

        Locale.setDefault(Locale.US);
        boolean flag = true;

        while (flag) {

            System.out.println("Qual ação deseja executar? ");
            System.out.println("1-MENU ANIMAL");
            System.out.println("2-MENU DONO");
            System.out.println("0-SAIR");

            int option = scanner.nextInt();

            if (option == 1) {
                menuAnimal(scanner, entityManager);
            } else if (option == 2) {
                menuDono(scanner, entityManager);
            } else if (option == 0) {
                flag = false;
            }

        }

    }

    private static void menuAnimal(Scanner scanner, EntityManager entityManager) throws SQLException, ClassNotFoundException {

        boolean flag = true;

        while (flag) {

            System.out.println("Qual animal deseja?");
            System.out.println("1-CACHORRO");
            System.out.println("2-GATO");
            System.out.println("0-VOLTAR");

            int option = scanner.nextInt();

            if (option == 1) {
                menuCachorro(scanner, entityManager);
            } else if (option == 2) {
                menuGato(scanner, entityManager);
            } else if (option == 0) {
                flag = false;
            }
        }
    }


    private static void menuDono(Scanner scanner, EntityManager entityManager) {

        boolean flag = true;

        System.out.println("Qual ação deseja executar?");
        System.out.println("1-CADASTRAR");
        System.out.println("2-ALTERAR");
        System.out.println("3-DELETAR");
        System.out.println("4-PESQUISAR POR ID");
        System.out.println("5-PESQUISAR TODOS");
        System.out.println("0-VOLTAR");

        int option = scanner.nextInt();

        if (option == 1) {

            System.out.println("Digite o nome");
            String nome = scanner.next();

            System.out.println("Digite o telefone");
            String telefone = scanner.next();

            Dono dono = new Dono(nome, telefone);
            DonoDao donoDao = new DonoDao(entityManager);

            //inicia a transaçao com o BD
            entityManager.getTransaction().begin();
            donoDao.save(dono);

            //comita
            entityManager.getTransaction().commit();

            System.out.println(dono);
            System.out.println("Dono Cadastrado!");


        } else if (option == 2) {

            DonoDao donoDao = new DonoDao(entityManager);

            //testando o update
            System.out.println("Digite o id do dono que deseja alterar");
            int id = scanner.nextInt();

            Dono dono = donoDao.findById(id);

            System.out.println("Digite o nome");
            String nome = scanner.next();

            System.out.println("Digite o telefone");
            String telefone = scanner.next();

            dono.setNome(nome);
            dono.setTelefone(telefone);

            //inicia a transaçao com o BD
            entityManager.getTransaction().begin();

            donoDao.update(dono);

            //comita
            entityManager.getTransaction().commit();

            System.out.println("Dono Alterado!");
            System.out.println(dono);

        } else if (option == 3) {

            //testando o delete
            System.out.println("Digite o id do dono que deseja excluir");
            int id = scanner.nextInt();

            DonoDao donoDao = new DonoDao(entityManager);
            Dono dono = donoDao.findById(id);

            //inicia a transaçao com o BD
            entityManager.getTransaction().begin();
           donoDao.delete(dono);

            //comita
            entityManager.getTransaction().commit();

            System.out.println("Dono Excluído!");

        } else if (option == 4) {

            DonoDao donoDao = new DonoDao(entityManager);

            System.out.println("Digite o id do dono que deseja pesquisar");
            int id = scanner.nextInt();

            Dono dono = donoDao.findById(id);
            System.out.println(dono);

        } else if (option == 5) {

            DonoDao donoDao = new DonoDao(entityManager);

            //testando o findAll
            System.out.println("Lista de Donos");
            List<Dono> listaDonos = donoDao.findAll();
            listaDonos.forEach(dono -> System.out.println(dono));


        } else if (option == 0) {
            flag = false;
        }
    }


    private static void menuCachorro(Scanner scanner, EntityManager entityManager) {

        boolean flag = true;

        System.out.println("Qual ação deseja executar?");
        System.out.println("1-CADASTRAR");
        System.out.println("2-ALTERAR");
        System.out.println("3-DELETAR");
        System.out.println("4-PESQUISAR POR ID");
        System.out.println("5-PESQUISAR POR ID DONO");
        System.out.println("6-PESQUISAR TODOS");
        System.out.println("0-VOLTAR");

        int option = scanner.nextInt();

        if (option == 1) {

            System.out.println("Digite o nome");
            String nome = scanner.next();

            System.out.println("Digite a raça");
            String raca = scanner.next();

            System.out.println("Digite a cor");
            String cor = scanner.next();

            System.out.println("Digite o sexo");
            String sexo = scanner.next();

            System.out.println("Digite o id do dono");
            int idDono = scanner.nextInt();

            System.out.println("Digite a data de Nascimento: DD/MM/YYYY");
            String data = scanner.next();

            System.out.println("Digite o porte");
            String porte = scanner.next();

            DonoDao donoDao = new DonoDao(entityManager);
            Dono dono = donoDao.findById(idDono);

            Cachorro cachorro = new Cachorro(nome, raca, cor, sexo, porte, dono);

            LocalDate dataConvertida = cachorro.stringToLocalDate(data);
            cachorro.setDataNascimento(dataConvertida);

            //cachorro.setDataNascimento(LocalDate.of(2010,10,2));


            CachorroDao cachorroDao = new CachorroDao(entityManager);

            //inicia a transaçao com o BD
            entityManager.getTransaction().begin();

            cachorroDao.save(cachorro);

            //comita
            entityManager.getTransaction().commit();

            System.out.println(cachorro);

            System.out.println("Animal Cadastrado!");


        } else if (option == 2) {

            CachorroDao cachorroDao = new CachorroDao(entityManager);

            //testando o update
            System.out.println("Digite o id do cachorro que deseja alterar");
            int id = scanner.nextInt();

            Cachorro cachorro = cachorroDao.findById(id);

            System.out.println("Digite o nome");
            String nome = scanner.next();

            System.out.println("Digite a raça");
            String raca = scanner.next();

            System.out.println("Digite a cor");
            String cor = scanner.next();

            System.out.println("Digite o sexo");
            String sexo = scanner.next();

            System.out.println("Digite o id do dono");
            int idDono = scanner.nextInt();

            System.out.println("Digite a data de Nascimento: DD/MM/YYYY");
            String data = scanner.next();

            System.out.println("Digite o porte");
            String porte = scanner.next();

            LocalDate dataConvertida = cachorro.stringToLocalDate(data);

            cachorro.setDataNascimento(dataConvertida);
            cachorro.setNome(nome);
            cachorro.setRaca(raca);
            cachorro.setCor(cor);
            cachorro.setSexo(sexo);

            DonoDao donoDao = new DonoDao(entityManager);
            Dono dono = donoDao.findById(idDono);

            cachorro.setDono(dono);
            cachorro.setPorte(porte);

            //inicia a transaçao com o BD
            entityManager.getTransaction().begin();

            cachorroDao.update(cachorro);

            //comita
            entityManager.getTransaction().commit();

            System.out.println("Cachorro Alterado!");
            System.out.println(cachorro);

        } else if (option == 3) {

            //testando o delete
            System.out.println("Digite o id do cachorro que deseja excluir");
            int id = scanner.nextInt();

            CachorroDao cachorroDao = new CachorroDao(entityManager);
            Cachorro cachorro = cachorroDao.findById(id);

            //inicia a transaçao com o BD
            entityManager.getTransaction().begin();
            cachorroDao.delete(cachorro);

            //comita
            entityManager.getTransaction().commit();

            System.out.println("Animal Excluído!");

        } else if (option == 4) {

            CachorroDao cachorroDao = new CachorroDao(entityManager);

            System.out.println("Digite o id do cachorro que deseja pesquisar");
            int id = scanner.nextInt();

            //testando o findById
            Cachorro cachorro = cachorroDao.findById(id);
            System.out.println(cachorro);

        } else if (option == 5) {

            //testando o findByDono
            System.out.println("Digite o id do dono que deseja pesquisar");
            int id = scanner.nextInt();

            DonoDao donoDao = new DonoDao(entityManager);
            Dono dono = donoDao.findById(id);

            System.out.println("Lista de Animais");
            List<Animal> listaAnimais = dono.getListaAnimais();
            System.out.println(listaAnimais);
            //listaAnimais.forEach( a ->System.out.println(listaAnimais));


        } else if (option == 6) {

            CachorroDao cachorroDao = new CachorroDao(entityManager);

            //testando o findAll
            System.out.println("Lista de Cachorros");
            List<Cachorro> listaCachorros = cachorroDao.findAll();
            listaCachorros.forEach(cachorro -> System.out.println(cachorro));

        } else if (option == 0) {
            flag = false;
        }
    }

    private static void menuGato(Scanner scanner, EntityManager entityManager) {

        boolean flag = true;

        System.out.println("Qual ação deseja executar?");
        System.out.println("1-CADASTRAR");
        System.out.println("2-ALTERAR");
        System.out.println("3-DELETAR");
        System.out.println("4-PESQUISAR POR ID");
        System.out.println("5-PESQUISAR POR ID DO DONO");
        System.out.println("6-PESQUISAR TODOS");
        System.out.println("0-VOLTAR");

        int option = scanner.nextInt();

        if (option == 1) {

            System.out.println("Digite o nome");
            String nome = scanner.next();

            System.out.println("Digite a raça");
            String raca = scanner.next();

            System.out.println("Digite a cor");
            String cor = scanner.next();

            System.out.println("Digite o sexo");
            String sexo = scanner.next();

            System.out.println("Digite o id do dono");
            int idDono = scanner.nextInt();

            System.out.println("Digite a data de Nascimento: DD/MM/YYYY");
            String data = scanner.next();

            DonoDao donoDao = new DonoDao(entityManager);
            Dono dono = donoDao.findById(idDono);

            Gato gato = new Gato(nome, raca, cor, sexo, dono);

            LocalDate dataConvertida = gato.stringToLocalDate(data);
            gato.setDataNascimento(dataConvertida);

            GatoDao gatoDao = new GatoDao(entityManager);

            //inicia a transaçao com o BD
            entityManager.getTransaction().begin();

            gatoDao.save(gato);

            //comita
            entityManager.getTransaction().commit();

            System.out.println(gato);

            System.out.println("Animal Cadastrado!");


        } else if (option == 2) {

            GatoDao gatoDao = new GatoDao(entityManager);

            //testando o update
            System.out.println("Digite o id do gato que deseja alterar");
            int id = scanner.nextInt();

            Gato gato = gatoDao.findById(id);

            System.out.println("Digite o nome");
            String nome = scanner.next();

            System.out.println("Digite a raça");
            String raca = scanner.next();

            System.out.println("Digite a cor");
            String cor = scanner.next();

            System.out.println("Digite o sexo");
            String sexo = scanner.next();

            System.out.println("Digite o id do dono");
            int idDono = scanner.nextInt();

            System.out.println("Digite a data de Nascimento: DD/MM/YYYY");
            String data = scanner.next();

            LocalDate dataConvertida = gato.stringToLocalDate(data);

            gato.setDataNascimento(dataConvertida);
            gato.setNome(nome);
            gato.setRaca(raca);
            gato.setCor(cor);
            gato.setSexo(sexo);

            DonoDao donoDao = new DonoDao(entityManager);
            Dono dono = donoDao.findById(idDono);

            gato.setDono(dono);

            //inicia a transaçao com o BD
            entityManager.getTransaction().begin();

            gatoDao.update(gato);

            //comita
            entityManager.getTransaction().commit();

            System.out.println("Gato Alterado!");
            System.out.println(gato);

        } else if (option == 3) {

            //testando o delete
            System.out.println("Digite o id do gato que deseja excluir");
            int id = scanner.nextInt();

            GatoDao gatoDao = new GatoDao(entityManager);
            Gato gato = gatoDao.findById(id);

            //inicia a transaçao com o BD
            entityManager.getTransaction().begin();
            gatoDao.delete(gato);

            //comita
            entityManager.getTransaction().commit();

            System.out.println("Animal Excluído!");

        } else if (option == 4) {

            GatoDao gatoDao = new GatoDao(entityManager);

            System.out.println("Digite o id do gato que deseja pesquisar");
            int id = scanner.nextInt();

            //testando o findById
            Gato gato = gatoDao.findById(id);
            System.out.println(gato);

        } else if (option == 5) {

            //testando o findByDono
            System.out.println("Digite o id do dono que deseja pesquisar");
            int id = scanner.nextInt();

            DonoDao donoDao = new DonoDao(entityManager);
            Dono dono = donoDao.findById(id);

            System.out.println("Lista de Animais");
            List<Animal> listaAnimais = dono.getListaAnimais();
            System.out.println(listaAnimais);
            //listaAnimais.forEach( a ->System.out.println(listaAnimais));

        } else if (option == 6) {

            GatoDao gatoDao = new GatoDao(entityManager);

            //testando o findAll
            System.out.println("Lista de Gatos");
            List<Gato> listaGato = gatoDao.findAll();
            listaGato.forEach(gato -> System.out.println(gato));


        } else if (option == 0) {
            flag = false;
        }
    }

}

