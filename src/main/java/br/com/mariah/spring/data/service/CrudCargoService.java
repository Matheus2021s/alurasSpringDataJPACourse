package br.com.mariah.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.mariah.spring.data.model.Cargo;
import br.com.mariah.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {

	private Boolean system = true;
	private final CargoRepository cargoRepository;

	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
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
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}

	private void salvar(Scanner scanner) {
		System.out.println("DESCRICAO DO CARGO");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("CARGO SALVO COM SUCESSO!");
	}

	private void atualizar(Scanner scanner) {
		System.out.println("INFORME O ID");
		int id = scanner.nextInt();
		System.out.println("INFORME A NOVA DESCRICAO");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("DADOS ATUALIZADOS COM SUCESSO!");
	}

	private void deletar(Scanner scanner) {
		System.out.println("INFORME O ID DO CARGO QUE DESEJA DELETAR");
		listar();
		int id = scanner.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("CARGO DELETADO COM SUCESSO!");
	}
}
