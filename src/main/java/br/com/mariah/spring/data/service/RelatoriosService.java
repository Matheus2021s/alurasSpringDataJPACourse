package br.com.mariah.spring.data.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.mariah.spring.data.model.Funcionario;
import br.com.mariah.spring.data.repository.CargoRepository;
import br.com.mariah.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	private Boolean system = true;
	private final FuncionarioRepository funcionarioRepository;

	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("SELECIONE A ACAO A EXECUTAR");
			System.out.println("0 - SAIR");
			System.out.println("1 - BUSCAR FUNCIONARIO POR NOME ");


			int action = scanner.nextInt();

			switch (action) {
			case 1: {
				buscaFuncionarioPorNome(scanner);
				break;
			}
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

}
