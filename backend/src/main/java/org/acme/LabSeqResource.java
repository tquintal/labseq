package org.acme;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/labseq")
public class LabSeqResource {

    // Creates the cache object and loads it's initial values
    private Map<Integer, BigInteger> cache = new HashMap<>();

    {
        cache.put(0, BigInteger.ZERO);
        cache.put(1, BigInteger.ONE);
        cache.put(2, BigInteger.ZERO);
        cache.put(3, BigInteger.ONE);
    }
    
    @GET
    @Path("/{number}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response labseq(@PathParam("number") String numberStr) {
        int number;
        
        // Tries to parse the input string to an integer and returns a 400 error if the format is invalid
        try {
            number = Integer.parseInt(numberStr);
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid number format").build();
        }
        
        if (number < 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Can't be negative").build();
        }
        
        // Checks if the value exists in cache and returns it instead of repeating the calculations
        if (cache.containsKey(number)) {            
            return Response.ok(cache.get(number).toString()).build();
        }

        // Calculates the sequence up to the provided number and stores the values in the cache
        // Starts at "i = cache.size()" to avoid recalculating values that are already stored in the cache
        for (int i = cache.size(); i <= number; i++) {
            BigInteger val = cache.get(i - 4).add(cache.get(i - 3));
            cache.put(i, val);            
        }
        
        return Response.ok(cache.get(number).toString()).build();
    }
}
