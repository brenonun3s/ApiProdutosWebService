package com.example.exceptions;

import jakarta.ws.rs.NotFoundException;

public class ListaDeProdutosVaziaException extends NotFoundException {

    public ListaDeProdutosVaziaException() {
        super("Lista de Produtos está vazia.");
    }
}
