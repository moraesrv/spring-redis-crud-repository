package br.com.spring.redis_crud_repository.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.spring.redis_crud_repository.entity.Pessoa;

/**
 * Classe responsável pela persistência da entidade Pessoa
 */
@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, String> {

}
