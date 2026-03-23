package br.com.lourenco.entryPoint;

import br.com.lourenco.domain.feature.FeatureFlag;
import br.com.lourenco.infrastructure.persistence.repository.FeatureRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/admin/features")
public class FeatureAdminResource {

    @Inject
    FeatureRepository repo;

    @POST
    public void create(FeatureFlag flag){
        repo.persist(flag);
    }

    @PUT
    public void update(FeatureFlag flag){
        repo.update(flag);
    }

    @GET
    public List<FeatureFlag> list(){
        return repo.listAll();
    }
}