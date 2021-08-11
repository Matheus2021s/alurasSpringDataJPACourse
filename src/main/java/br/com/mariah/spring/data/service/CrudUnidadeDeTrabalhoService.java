package br.com.mariah.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.mariah.spring.data.model.Funcionario;
import br.com.mariah.spring.data.model.UnidadeDeTrabalho;
import br.com.mariah.spring.data.repository.FuncionarioRepository;
import br.com.mariah.spring.data.repository.UnidadeDeTrabalhoRepository;

@Service
public class CrudUnidadeDeTrabalhoService {

	private Boolean system = true;
	private Boolean systemUnideTrabalho = true;
	private final UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;
	private final FuncionarioRepository funcionarioRepository;
	
	public CrudUnidadeDeTrabalhoService(UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository, FuncionarioRepository funcionarioRepository) {
		this.unidadeDeTrabalhoRepository = unidadeDeTrabalhoRepository;
		this.funcionarioRepository = funcionarioRepository;
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
		Iterable<UnidadeDeTrabalho> unidadeDeTrabalhos = unidadeDeTrabalhoRepository.findAll();
		unidadeDeTrabalhos.forEach(unidadeDeTrabalho -> System.out.println(unidadeDeTrabalho));
	}

	private void salvar(Scanner scanner) {
		UnidadeDeTrabalho unidadeDeTrabalho = new UnidadeDeTrabalho();
		System.out.println("DESCRICAO:");
		String descricao = scanner.next();
		unidadeDeTrabalho.setDescricao(descricao);
		
		System.out.println("ENDERECO:");
		String endereco = scanner.next();
		unidadeDeTrabalho.setEndereco(endereco);
	   

	    while( systemUnideTrabalho ) {
	    	System.out.println("DESEJA INSERIR FUNCIONARIOS?");
	    	System.out.println("0 - NÃO");
	    	System.out.println("1 - SIM");
	    	int unidade = scanner.nextInt();
	    	if (unidade == 1) {
	    		System.out.println("DIGITE A CHAVE DO FUNCIONARIO QUE DESEJA ADICIONAR");
	    		this.funcionarioRepository.findAll().forEach(item -> System.out.println(item));
	    		Funcionario funcionario = this.funcionarioRepository.findById( scanner.nextInt() ).get();
	    		unidadeDeTrabalho.getFuncionarios().add(funcionario);
	    	} else {
	    		systemUnideTrabalho = false;
	    	}
	    	
	    }
	    
	    unidadeDeTrabalhoRepository.save( unidadeDeTrabalho );	    	
		System.out.println("UNIDADE DE TRABALHO SALVA COM SUCESSO!");
	}

	private void atualizar(Scanner scanner) {
		UnidadeDeTrabalho unidadeDeTrabalho = new UnidadeDeTrabalho();
		System.out.println("INFORME O ID");
		int id = scanner.nextInt();
		unidadeDeTrabalho.setId(id);
		
		System.out.println("DESCRICAO:");
		String descricao = scanner.next();
		unidadeDeTrabalho.setDescricao(descricao);
		
		System.out.println("ENDERECO:");
		String endereco = scanner.next();
		unidadeDeTrabalho.setEndereco(endereco);
	   

	    while( systemUnideTrabalho ) {
	    	System.out.println("DESEJA INSERIR FUNCIONARIOS?");
	    	System.out.println("0 - NÃO");
	    	System.out.println("1 - SIM");
	    	int unidade = scanner.nextInt();
	    	if (unidade == 1) {
	    		System.out.println("DIGITE A CHAVE DO FUNCIONARIO QUE DESEJA ADICIONAR");
	    		this.funcionarioRepository.findAll().forEach(item -> System.out.println(item));
	    		Funcionario funcionario = this.funcionarioRepository.findById( scanner.nextInt() ).get();
	    		unidadeDeTrabalho.getFuncionarios().add(funcionario);
	    	} else {
	    		systemUnideTrabalho = false;
	    	}
	    	
	    }
	    
	    
		unidadeDeTrabalhoRepository.save(unidadeDeTrabalho);
		System.out.println("DADOS ATUALIZADOS COM SUCESSO!");
	}

	private void deletar(Scanner scanner) {
		System.out.println("INFORME O ID DA UNIDADE QUE DESEJA DELETAR");
		listar();
		int id = scanner.nextInt();
		unidadeDeTrabalhoRepository.deleteById(id);
		System.out.println("UNIDADE DELETADA COM SUCESSO!");
	}
}
