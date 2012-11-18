package edu.integration.patterns.filter;

import org.apache.log4j.Logger;
import org.springframework.integration.Message;
import org.springframework.integration.core.MessageSelector;

import edu.integration.patterns.OrderBean;
import edu.integration.patterns.producer.ProducerImpl;

public class Authenticator implements MessageSelector {

    private static final String EXPECTED_USERNAME = "dido";
    private static final String EXPECTED_PASSWORD = "pass";

    private static final Logger logger = Logger.getLogger(ProducerImpl.class);

    @Override
    public boolean accept(Message<?> message) {
        logger.info("Start authenticating..");
        try {
            OrderBean payload = (OrderBean) message.getPayload();
            return authenticate(payload.getUsername(), payload.getPassword());
        } catch (ClassCastException e) {
            return false;
        }

    }

    private boolean authenticate(String username, String password) {
        boolean isAuthenticated = EXPECTED_USERNAME.equals(username)
                && EXPECTED_PASSWORD.equals(password);
        if (isAuthenticated) {
            logger.info("Authention successful");
        } else {
            logger.info("Authention failure");
        }
        return isAuthenticated;
    }

}
