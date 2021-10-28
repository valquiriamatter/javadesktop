package com.capgemini.springdata;

import java.util.Scanner;

import com.capgemini.springdata.orm.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private Boolean system = true;

	private final CrudCargoService cargoService;
	private final RelatoriosService relatoriosService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

	public SpringDataApplication(CrudCargoService cargoService,
								 RelatoriosService relatoriosService,
								 CrudFuncionarioService funcionarioService,
								 CrudUnidadeTrabalhoService unidadeTrabalhoService,
								 RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
		this.cargoService = cargoService;
		this.relatoriosService = relatoriosService;
		this.funcionarioService = funcionarioService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}


	public static void main(String[] args) {

		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Qual função deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade");
			System.out.println("4 - Relatorios");
			System.out.println("5 - Relatório Funcionário Dinâmico");


			Integer function = scanner.nextInt();

			switch (function) {
				case 1:
					cargoService.inicial(scanner);
					break;
				case 2:
					funcionarioService.inicial(scanner);
					break;
				case 3:
					unidadeTrabalhoService.inicial(scanner);
					break;
				case 4:
					relatoriosService.inicial(scanner);
					break;
				case 5:
					relatorioFuncionarioDinamico.inicial(scanner);
					break;
				default:
					System.out.println("Finalizando");
					system = false;
					break;
			}
		}
	}
}



