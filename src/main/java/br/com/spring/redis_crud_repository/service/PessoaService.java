package br.com.spring.redis_crud_repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.redis_crud_repository.entity.Pessoa;
import br.com.spring.redis_crud_repository.repository.PessoaRepository;

/**
 * Classe de serviço para a entidade Pessoa
 */
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    /**
     * Método responsável por inserir uma pessoa no Redis
     * @param pessoa dados da pessoa a serem persistidos
     * @return dados da pessoa persistidos
     */
    public Pessoa inserir(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    /**
     * Método responsável por pesquisar uma pessoa pelo seu id no Redis
     * @param id identificação da pessoa
     * @return dados da pessoa pesquisada
     */
    public Pessoa pesquisarPeloId(String id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    /**
     * Método responsável por atualizar os dados de uma pessoa no Redis
     * @param Pessoa dados a serem atualizados
     * @return dados da pessoa persistidos
     */
    public Pessoa atualizar(Pessoa Pessoa) {
        if (pessoaRepository.existsById(Pessoa.getId())) {
            return pessoaRepository.save(Pessoa);
        }
        return null;
    }

    /**
     * Método responsável por excluir uma pessoa do Redis
     * @param id identificação da pessoa
     */
    public void deletar(String id) {
        pessoaRepository.deleteById(id);
    }

    /**
     * Método responsável por excluir uma pessoa do Redis
     * @param id entidade pessoa
     */
    public void deletar(Pessoa pessoa) {
        pessoaRepository.delete(pessoa);
    }

    /**
     * Método responsável por pesquisar todas as pessoas cadastradas do Redis
     * @param id identificação da pessoa
     */
    public Iterable<Pessoa> pesquisarTodos() {
        return pessoaRepository.findAll();
    }

}

