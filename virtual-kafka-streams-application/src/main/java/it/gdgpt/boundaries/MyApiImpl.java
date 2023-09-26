package it.gdgpt.boundaries;

import io.smallrye.common.annotation.RunOnVirtualThread;
import it.gdgpt.dto.MyRequest;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.Response;

import java.util.Map;
import java.util.logging.Logger;

@RequestScoped
public class MyApiImpl implements MyApi{

    Logger log = Logger.getLogger(MyApiImpl.class.getName());

    @Override
    @RunOnVirtualThread
    public Response run(MyRequest request) {
        //log.info("operationName=runMethodExecution");
        return Response.ok().build();
    }

    @Override
    @RunOnVirtualThread
    public Response virtual() {
        log.info("operationName=virtual");
        // Runs on the event loop
        return Response.ok(Map.of("msg", Thread.currentThread())).build();
    }
}
