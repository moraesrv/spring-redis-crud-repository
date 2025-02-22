package br.com.spring.redis_crud_repository;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import br.com.spring.redis_crud_repository.entity.Pessoa;
import br.com.spring.redis_crud_repository.service.PessoaService;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RedisCrudRepositoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisCrudRepositoryApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(PessoaService pessoaService) {
		return args -> {

			Pessoa pessoa1 = new Pessoa("1", "Rafael", 35);
			Pessoa pessoa2 = new Pessoa("2", "Ana", 30);
			Pessoa pessoa3 = new Pessoa("3", "Carolinaaaaaa", 25);
			Pessoa pessoa4 = new Pessoa("4", "Eduardo", 20);

			// Insere os registros no Redis
			pessoaService.inserir(pessoa1);
			pessoaService.inserir(pessoa2);
			pessoaService.inserir(pessoa3);
			pessoaService.inserir(pessoa4);

			// Lista todas as pessoas inseridas no Redis
			Iterable<Pessoa> pessoas = pessoaService.pesquisarTodos();
			if (pessoas != null) {
				System.out.println("Listando os registros encontrados:");
				pessoas.forEach(p -> {
					p.imprimir();
				});
			} else {
				System.out.println("Nenhum registro encontrado");
			}

			// Atualiza o nome da pessoa de id = 3
			pessoa3 = new Pessoa("3", "Carolina", 25);
			pessoaService.atualizar(pessoa3);
			// Pesquisa a pessoa de id = 3
			pessoa3 = pessoaService.pesquisarPeloId("3");
			// Imprime os dados atualizados da pessoa com id = 3
			pessoa3.imprimir();

			// Remove a pessoa de id = 2
			pessoaService.deletar(pessoa2);
			pessoaService.deletar(pessoa1);
			//pessoaService.deletar("pessoa:2");

			// Lista todas as pessoas cadastradas no Redis
			pessoas = pessoaService.pesquisarTodos();
			if (pessoas != null) {
				System.out.println("Listando os registros encontrados:");
				pessoas.forEach(p -> {
					p.imprimir();
				});
			} else {
				System.out.println("Nenhum registro encontrado");
			}

		};
	}
}
