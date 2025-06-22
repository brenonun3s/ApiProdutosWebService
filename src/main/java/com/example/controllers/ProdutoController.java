package com.example.controllers;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.example.dto.ProdutoDTO;
import com.example.entities.Produto;
import com.example.exceptions.ProdutoNaoLocalizadoException;
import com.example.repositories.ProdutoRepository;
import com.example.service.ProdutoService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

@Path("/api/produtos")
@Produces("application/json")
@Consumes("application/json")
@Tag(name = "Produtos", description = "Operações de gerenciamento de produtos")
public class ProdutoController {

    @Inject
    ProdutoRepository repository;

    @Inject
    ProdutoService service;

    
    @GET
    @Operation(summary = "Listar todos os produtos")
    @APIResponse(responseCode = "200", description = "Lista de produtos")
    @APIResponse(responseCode = "204", description = "Nenhum produto encontrado")
    public Response listarProdutos() {
        List<Produto> listaDeProdutos =  service.listar();

        if (listaDeProdutos.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(listaDeProdutos).build();

    }

    
    @POST
    @Transactional
    @Operation(summary = "Cadastrar um novo produto")
    @APIResponse(responseCode = "201", description = "Produto criado no sistema")
    @APIResponse(responseCode = "400", description = "Dados do produto inválidos")
    public Response create(@Valid ProdutoDTO dto, @Context UriInfo uriInfo) {
        Produto produto = service.salvar(dto);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder()
                .path(Long.toString(produto.getId()));
        return Response.created(builder.build()).entity(produto).build();
    }

    
    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar produto por ID")
    @APIResponse(responseCode = "200", description = "Produto encontrado")
    @APIResponse(responseCode = "404", description = "Produto não encontrado")
    public Response buscarProdutoPorId(@PathParam("id") Long id) {
        Produto produto = service.buscarPorId(id);
        if (produto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(produto).build();
    }
    

    
    @PUT
    @Path("/{id}")
    @Transactional
    @Operation(summary = "Atualizar produto por ID")
    @APIResponse(responseCode = "200", description = "Produto atualizado")
    @APIResponse(responseCode = "404", description = "Produto não encontrado")
    public Response atualizar(@PathParam("id") Long id, @Valid ProdutoDTO dto) {
        Produto atualizado = service.atualizar(id, dto);
        return Response.ok(new ProdutoDTO(atualizado)).build();
    }

    
    @DELETE
    @Path("/{id}")
    @Transactional
    @Operation(summary = "Deletar produto por ID")
    @APIResponse(responseCode = "200", description = "Produto atualizado")
    @APIResponse(responseCode = "404", description = "Produto não encontrado")
    public Response delete(@PathParam("id") Long id) {
        Produto produto = repository
                .findByIdOptional(id)
                .orElseThrow(() -> new ProdutoNaoLocalizadoException(id));
        repository.delete(produto);
        return Response.noContent().build();
    }

}

