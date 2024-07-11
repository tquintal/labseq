package org.acme;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

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
    public String labseq(@PathParam("number") int number) {
        if (number < 0) {
            return "Can't be negative";
        }
        
        // Checks if the value exists in cache and returns it instead of repeating the calculations
        if (cache.containsKey(number)) {            
            return String.valueOf(cache.get(number));
        }

        // Calculates the sequence up to the provided number and stores the values in the cache
        // Starts at "i = 4" because there's already 4 values/indexes in the cache
        for (int i = 4; i <= number; i++) {
            BigInteger val = cache.get(i - 4).add(cache.get(i - 3));
            cache.put(i, val);            
        }
        
        return String.valueOf(cache.get(number));
    }
}
