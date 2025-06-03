package com.example.dto;

import com.example.entities.Produto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    @NotBlank(message = "Nome do produto é obrigatório")
    @Size(min = 3, max = 100)
    public String nomeProduto;

    @NotBlank(message = "Descrição do produto é obrigatória")
    @Size(min = 10, max = 500)
    public String descricaoProduto;

    @NotNull(message = "Preço do produto é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    public Double precoProduto;

    @NotBlank(message = "Categoria do produto é obrigatória")
    @Size(min = 3, max = 50)
    public String categoriaProduto;

    //Construtor para converter um Produto em ProdutoDTO na atualização
    public ProdutoDTO(Produto produto) {
    }



}
