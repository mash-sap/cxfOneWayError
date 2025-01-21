package de.mash.demo;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.InterceptorChain;
import org.apache.cxf.interceptor.StaxOutEndingInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.addressing.MAPAggregator;

public class RemoveInterceptorPreStream extends AbstractPhaseInterceptor {

    public RemoveInterceptorPreStream(){
        super(Phase.WRITE_ENDING);
        this.addBefore(StaxOutEndingInterceptor.class.getName());
    }

    @Override
    public void handleMessage(Message message) throws Fault {
//        remove(message, "SoapOutEndingInterceptor");
        remove(message, "StaxOutEndingInterceptor");
        remove(message, "MessageSenderEndingInterceptor");
        System.out.println(message.getInterceptorChain());
    }

    private void remove(Message message, String clazz) {
        InterceptorChain chain = message.getInterceptorChain();
        for (Interceptor<? extends Message> interceptor : message.getInterceptorChain()) {
            if (interceptor.getClass().getName().contains(clazz)) {
                System.out.println("Removing interceptor: " +clazz);
                chain.remove(interceptor);
            }
        }
    }
}
