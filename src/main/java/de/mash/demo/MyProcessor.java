package de.mash.demo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.support.DefaultMessage;

public class MyProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("######################################## processor called ####################################");
        org.apache.camel.support.DefaultMessage msg = (DefaultMessage) exchange.getMessage();
        System.out.println(msg.getBody());
        System.out.println(msg.getBody(String.class));
        System.out.println(msg.getBody().getClass());
    }
}
