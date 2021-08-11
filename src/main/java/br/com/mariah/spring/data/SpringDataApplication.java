package br.com.mariah.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.mariah.spring.data.service.CrudCargoService;
import br.com.mariah.spring.data.service.CrudFuncionarioService;
import br.com.mariah.spring.data.service.CrudUnidadeDeTrabalhoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private Boolean system = true;
	private final CrudCargoService crudCargoService;
	private final CrudFuncionarioService crudFuncionarioService;
	private final CrudUnidadeDeTrabalhoService crudUnidadeDeTrabalhoService;
	
	public SpringDataApplication(CrudCargoService crudCargoService , CrudFuncionarioService crudFuncionarioService,  CrudUnidadeDeTrabalhoService crudUnidadeDeTrabalhoService) {
		this.crudCargoService = crudCargoService;
		this.crudFuncionarioService = crudFuncionarioService;
		this.crudUnidadeDeTrabalhoService = crudUnidadeDeTrabalhoService;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		while (system) {
			System.out.println("Qual comando você quer executar?");
			System.out.println();
			System.out.println("0 - SAIR");
			System.out.println("1 - CARGO");
			System.out.println("2 - UNIDADE DE TRABALHO");
			System.out.println("3 - FUNCIONARIO ");
			
			int action = scanner.nextInt();
			
			if (action == 1) {
				crudCargoService.inicial(scanner);
			}else if(action == 2){
				crudUnidadeDeTrabalhoService.inicial(scanner);	
			}else if(action == 3){
				crudFuncionarioService.inicial(scanner);
			} else {
				system = false;
			}
		}
		
	}

}
