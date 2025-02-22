package br.com.spring.redis_crud_repository.entity;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

/**
 * Classe de dados para ser persistida no Redis
 */
@RedisHash("pessoa")
public class Pessoa implements Serializable {

    @Id
    private String id;
    private String nome;
    private int idade; 

    public void imprimir() {
        System.out.println(String.format("[ Pessoa #%s, Nome: %s, Idade %d]", this.id, this.nome, this.idade));
    }

}
