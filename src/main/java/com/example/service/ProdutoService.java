package com.example.service;

import com.example.dto.ProdutoDTO;
import com.example.entities.Produto;
import com.example.exceptions.ProdutoNaoLocalizadoException;
import com.example.repositories.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ProdutoService {

    @Inject
    ProdutoRepository repository;

    public List<Produto> listar() {
        return repository.findAll().list();
    }

    public Produto buscarPorId(Long id) {
        return repository.findByIdOptional(id)
                .orElseThrow
                (() -> new ProdutoNaoLocalizadoException(id));
    }

    public Produto atualizar(Long id, ProdutoDTO dto) {
        Produto produtoExistente = buscarPorId(id);

        produtoExistente.setNomeProduto(dto.nomeProduto);
        produtoExistente.setCategoriaProduto(dto.categoriaProduto);
        produtoExistente.setDescricaoProduto(dto.descricaoProduto);
        produtoExistente.setPrecoProduto(dto.precoProduto);

        return produtoExistente;
    }

    public Produto salvar(ProdutoDTO dto) {
        Produto novoProduto = new Produto();

        novoProduto.setNomeProduto(dto.nomeProduto);
        novoProduto.setCategoriaProduto(dto.categoriaProduto);
        novoProduto.setDescricaoProduto(dto.descricaoProduto);
        novoProduto.setPrecoProduto(dto.precoProduto);

        repository.persist(novoProduto);
        return novoProduto;
    }


}
