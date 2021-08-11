package br.com.mariah.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.mariah.spring.data.model.Cargo;
import br.com.mariah.spring.data.model.Funcionario;
import br.com.mariah.spring.data.model.UnidadeDeTrabalho;
import br.com.mariah.spring.data.repository.CargoRepository;
import br.com.mariah.spring.data.repository.FuncionarioRepository;
import br.com.mariah.spring.data.repository.UnidadeDeTrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private Boolean system = true;
	private Boolean systemUnideTrabalho = true;
	private final FuncionarioRepository funcionarioRepository;
	private final CargoRepository cargoRepository;
	private final UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;
	
	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
		this.unidadeDeTrabalhoRepository = unidadeDeTrabalhoRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("SELECIONE A ACAO A EXECUTAR");
			System.out.println("0 - SAIR");
			System.out.println("1 - CADASTRAR ");
			System.out.println("2 - ALTERAR ");
			System.out.println("3 - LISTAR ");
			System.out.println("4 - DELETAR ");

			int action = scanner.nextInt();

			switch (action) {
			case 1: {
				salvar(scanner);
				break;
			}
			case 2: {
				atualizar(scanner);
				break;
			}
			case 3: {
				listar();
				break;
			}
			case 4: {
				deletar(scanner);
				break;
			}
			default:
				system = false;
				break;
			}
		}

	}

	private void listar() {
		Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}

	private void salvar(Scanner scanner) {
		Funcionario funcionario = new Funcionario();
		System.out.println("NOME:");
		String nome = scanner.next();
		funcionario.setNome(nome);
		
		System.out.println("CPF:");
		String cpf = scanner.next();
		funcionario.setCpf(cpf);
		
		System.out.println("SALARIO:");
		double salario = scanner.nextDouble();
		funcionario.setSalario(salario);
		
		System.out.println("CARGO CHAVE");
		this.cargoRepository.findAll().forEach(cargo-> System.out.println(cargo));
	    funcionario.setCargo( this.cargoRepository.findById(scanner.nextInt()).get());
	    
	    while( systemUnideTrabalho ) {
	    	System.out.println("DESEJA INSERIR UNIDADE DE TRABALHO?");
	    	System.out.println("0 - NÃO");
	    	System.out.println("1 - SIM");
	    	int unidade = scanner.nextInt();
	    	if (unidade == 1) {
	    		System.out.println("DIGITE A CHAVE DA UNIDADE QUE DESEJA ADICIONAR");
	    		this.unidadeDeTrabalhoRepository.findAll().forEach(item -> System.out.println(item));
	    		 int chaveUnidade = scanner.nextInt();
	    		 UnidadeDeTrabalho unidadeDeTrabalho = this.unidadeDeTrabalhoRepository.findById(chaveUnidade).get();
	    		 if(  unidadeDeTrabalho.getId() != null ) {
	    			 funcionario.getUnidadesDeTrabalho().add(unidadeDeTrabalho);
	    		 }
	    	} else {
	    		systemUnideTrabalho = false;
	    	}
	    	
	    }
	    
	    funcionarioRepository.save( funcionario );	    	
		System.out.println("CARGO SALVO COM SUCESSO!");
	}

	private void atualizar(Scanner scanner) {
		Funcionario funcionario = new Funcionario();
		System.out.println("INFORME O ID");
		int id = scanner.nextInt();
		funcionario.setId(id);
		
		System.out.println("NOME:");
		String nome = scanner.next();
		funcionario.setNome(nome);
		
		System.out.println("CPF:");
		String cpf = scanner.next();
		funcionario.setCpf(cpf);
		
		System.out.println("SALARIO:");
		double salario = scanner.nextDouble();
		funcionario.setSalario(salario);
		
		System.out.println("CARGO CHAVE");
		this.cargoRepository.findAll().forEach(cargo-> System.out.println(cargo));
	    Cargo cargo = this.cargoRepository.findById(scanner.nextInt()).get(); 
	    funcionario.setCargo(cargo);
	    while( systemUnideTrabalho ) {
	    	System.out.println("DESEJA INSERIR UNIDADE DE TRABALHO?");
	    	System.out.println("0 - NÃO");
	    	System.out.println("1 - SIM");
	    	int unidade = scanner.nextInt();
	    	if (unidade == 1) {
	    		System.out.println("DIGITE A CHAVE DA UNIDADE QUE DESEJA ADICIONAR");
	    		this.unidadeDeTrabalhoRepository.findAll().forEach(item -> System.out.println(item));
	    		UnidadeDeTrabalho unidadeDeTrabalho = this.unidadeDeTrabalhoRepository.findById( scanner.nextInt() ).get();
	    		funcionario.getUnidadesDeTrabalho().add(unidadeDeTrabalho);
	    	} else {
	    		systemUnideTrabalho = false;
	    	}	    
	    }
	    
		funcionarioRepository.save(funcionario);
		System.out.println("DADOS ATUALIZADOS COM SUCESSO!");
	}

	private void deletar(Scanner scanner) {
		System.out.println("INFORME O ID DO FUNCIONARIO QUE DESEJA DELETAR");
		listar();
		int id = scanner.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("FUNCIONARIO DELETADO COM SUCESSO!");
	}
}
