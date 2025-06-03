package com.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_produto", nullable = false)
    private String nomeProduto;

    @Column(name = "descricao_produto", nullable = false)
    private String descricaoProduto;

    @Column(name = "preco_produto", nullable = false)
    private Double precoProduto;

    @Column(name = "categoria_produto", nullable = false)
    private String categoriaProduto;


}
