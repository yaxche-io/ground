package edu.berkeley.ground.resources;

import com.codahale.metrics.annotation.Timed;
import edu.berkeley.ground.api.models.Structure;
import edu.berkeley.ground.api.models.StructureFactory;
import edu.berkeley.ground.api.models.StructureVersion;
import edu.berkeley.ground.api.models.StructureVersionFactory;
import edu.berkeley.ground.exceptions.GroundException;
import io.dropwizard.jersey.params.NonEmptyStringParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/structures")
@Produces(MediaType.APPLICATION_JSON)
public class StructuresResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(StructuresResource.class);

    private StructureFactory structureFactory;
    private StructureVersionFactory structureVersionFactory;

    public StructuresResource(StructureFactory structureFactory, StructureVersionFactory structureVersionFactory) {
        this.structureFactory = structureFactory;
        this.structureVersionFactory = structureVersionFactory;
    }

    @GET
    @Timed
    @Path("/{name}")
    public Structure getStructure(@PathParam("name") String name) throws GroundException {
        LOGGER.info("Retrieving structure " + name + ".");
        return this.structureFactory.retrieveFromDatabase(name);
    }

    @GET
    @Timed
    @Path("/versions/{id}")
    public StructureVersion getStructureVersion(@PathParam("id") String id) throws GroundException {
        LOGGER.info("Retrieving structure version " + id + ".");
        return this.structureVersionFactory.retrieveFromDatabase(id);
    }

    @POST
    @Timed
    @Path("/{name}")
    public Structure createStructure(@PathParam("name") String name) throws GroundException {
        LOGGER.info("Creating structure " + name + ".");
        return this.structureFactory.create(name);
    }

    @POST
    @Timed
    @Path("/versions")
    public StructureVersion createStructureVersion(@Valid StructureVersion structureVersion, @QueryParam("parent") NonEmptyStringParam parentId) throws GroundException {
        LOGGER.info("Creating structure version in structure " + structureVersion.getStructureId() + ".");
        return this.structureVersionFactory.create(structureVersion.getStructureId(),
                                                   structureVersion.getAttributes(),
                                                   parentId.get());
    }
}
