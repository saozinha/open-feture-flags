package br.com.lourenco.entryPoint;

import br.com.lourenco.application.service.FeatureService;
import br.com.lourenco.domain.produto.Produto;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    FeatureService featureService;

    @GET
    public Produto get(@QueryParam("id") String id,
                       @HeaderParam("userId") String userId) {

        Produto p = new Produto();
        p.id = id;
        p.nome = "Notebook";

        if (featureService.isInventoryEnabled(userId)) {
            p.estoque = 15;
        }

        return p;
    }
}
