package br.com.lourenco.domain.produto;

import lombok.Data;

@Data
public class Produto {
    public String id;
    public String nome;
    public Integer estoque;
}
