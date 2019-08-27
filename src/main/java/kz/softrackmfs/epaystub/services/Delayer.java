package kz.softrackmfs.epaystub.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class Delayer {

    private static final Logger logger = Logger.getLogger(Delayer.class.getName());

    private final Map<String, Long> delays = new HashMap<>();

    public void waitForDelay(String requestURI) {
        if (delays.containsKey(requestURI)) {
            Long amountMs = delays.get(requestURI);
            logger.info("waiting method " + requestURI + " for " + amountMs + " ms");
            try {
                Thread.sleep(amountMs);
                logger.info("waiting method " + requestURI + " done");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void setDelay(String requestURI, long delay) {
        logger.info("set a new delay = " + delay + " for method " + requestURI);
        delays.put(requestURI, delay);
    }
}
