package com.example.exceptions;

import jakarta.ws.rs.NotFoundException;

public class ProdutoNaoLocalizadoException extends NotFoundException {
    public ProdutoNaoLocalizadoException(Long id) {
        super("Produto não encontrado com esse ID: " + id);
    }
}
