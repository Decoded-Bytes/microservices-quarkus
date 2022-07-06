package quarkus.projects.exception;

import lombok.Data;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.function.Supplier;

@Data
public class ResourceNotFoundException implements Supplier<WebApplicationException> {
    //implements ExceptionMapper<Exception>

//        @Override
//        public Response toResponse(WebApplicationException e) {

    @Override
    public WebApplicationException get(){
        return null;
    }
}

//            int httpCode = 404;
//
//            if (e instanceof WebApplicationException) {
//                httpCode = ((WebApplicationException) e).getResponse().getStatus();
//            }
//
//            JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
//                    .add("exceptionObject", e.getClass().getName())
//                    .add("errorCode", httpCode);
//
//            return Response.status(httpCode)
//                    .entity(responseBuilder.build())
//                    .build();
//        }


//        return null;
//    }

