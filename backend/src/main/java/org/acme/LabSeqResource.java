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
        
        if (cache.containsKey(number)) {
            return "cache: " + cache.get(number);
        }

        int startIndex = 4;
        if (cache.size() > 4) startIndex = cache.size();

        for (int i = startIndex; i <= number; i++) {
            BigInteger val = cache.get(i-4).add(cache.get(i-3));
            cache.put(i, val);
        };
        
        return "not cache: " + String.valueOf(cache.get(number));
    }
}
