package br.com.mariah.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.mariah.spring.data.model.Funcionario;
import br.com.mariah.spring.data.repository.FuncionarioRepository;
import br.com.mariah.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamicoService {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public final FuncionarioRepository funcionarioRepository;

	public RelatorioFuncionarioDinamicoService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		System.out.println("DIGIGE UM NOME");
		String nome = scanner.next();
		
		if (nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}
		
		System.out.println("DIGIGE UM CPF");
		String cpf = scanner.next();
		
		if (cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}
		
		System.out.println("DIGIGE UM SALARIO");
		Double salario = scanner.nextDouble();
		
		if (salario == 0) {
			salario = null;
		}
		
		
		System.out.println("DIGIGE UMA DATA DE CONTRATACAO");
		String data = scanner.next();
		
		LocalDate dataContratacao;
		if (data.equalsIgnoreCase("NULL")) {
			dataContratacao = null;
		} else {
			dataContratacao = LocalDate.parse(data, formatter);
		}
		
		List<Funcionario> lista = this.funcionarioRepository.findAll(
				Specification
				.where(SpecificationFuncionario.nome(nome))
				.or(SpecificationFuncionario.cpf(cpf))
				.or(SpecificationFuncionario.salario(salario))
				.or(SpecificationFuncionario.dataContratacao(dataContratacao))
				);
		lista.forEach(System.out::println);
	
		
	}
}
