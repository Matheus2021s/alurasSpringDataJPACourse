package br.com.mariah.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.mariah.spring.data.model.Funcionario;
import br.com.mariah.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final FuncionarioRepository funcionarioRepository;

	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("SELECIONE A ACAO A EXECUTAR");
			System.out.println("0 - SAIR");
			System.out.println("1 - BUSCAR FUNCIONARIO POR NOME ");
			System.out.println("2 - BUSCAR FUNCIONARIO POR NOME, MAIOR SALARIO E DATA");
			System.out.println("3 - BUSCAR FUNCIONARIO CONTRATADO A PARTIR DE DATA");

			int action = scanner.nextInt();

			switch (action) {
			case 1: {
				buscaFuncionarioPorNome(scanner);
				break;
			}
			case 2 :
				buscaFuncionarioPorNomeSalarioMaiorData(scanner);
				break;
			case 3:
				findDataContratacaoMaior(scanner);
			default:
				system = false;
				break;
			}
		}

	}

	private void buscaFuncionarioPorNome(Scanner scanner) {
		System.out.println("DIGITE O NOME DO FUNICIONARIO:");
		List<Funcionario> funcionariosEncontrados = this.funcionarioRepository.findByNome(scanner.next());
		funcionariosEncontrados.forEach(funcionario-> System.out.println(funcionario));
	}
	
	private void buscaFuncionarioPorNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("DIGITE O NOME QUE DESEJA PESQUISAR");
		String nome = scanner.next();
	
		System.out.println("QUAL DATA CONTRATACAO DESEJA PESQUISAR");
		String data = scanner.next();
		LocalDate dataFormatada =  LocalDate.parse(data, formatter);
		
		System.out.println("QUAL SALARIO DESEJA PESQUISAR");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> list = this.funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, dataFormatada);
		list.forEach(valor -> System.out.println(valor));
		
	}	

	private void findDataContratacaoMaior(Scanner scanner) {
		System.out.println("QUAL DATA CONTRATACAO DESEJA PESQUISAR");
		String data = scanner.next();
		LocalDate dataFormatada =  LocalDate.parse(data, formatter);
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(dataFormatada);
		list.forEach(item -> System.out.println(item));
	}
}
