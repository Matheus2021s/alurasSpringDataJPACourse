package br.com.mariah.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.mariah.spring.data.model.Cargo;
import br.com.mariah.spring.data.repository.CargoRepository;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private final CargoRepository cargoRepository;
	
	public SpringDataApplication( CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cargo cargo = new Cargo();
		cargo.setDescricao("DESENVOLVEDOR JAVA PLENO");
		
		cargoRepository.save(cargo);
	}

}
