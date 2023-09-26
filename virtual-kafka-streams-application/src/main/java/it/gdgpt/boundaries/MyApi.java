package it.gdgpt.boundaries;

import io.smallrye.common.annotation.RunOnVirtualThread;
import it.gdgpt.dto.MyRequest;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/myapi")
public interface MyApi {

    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Description of success",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @APIResponse(
                    responseCode = "500",
                    description = "Internal service error"
            )
    })
    @Operation(
            summary = "My brand new api summary",
            operationId = "myBrandApi"
    )
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    default Response run(
            @RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(
                                    implementation = MyRequest.class
                            )
                    )
            )
            MyRequest request
    ){
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }

    @GET
    @Path("/virtual")
    @Produces(MediaType.APPLICATION_JSON)
    Response virtual();
}
