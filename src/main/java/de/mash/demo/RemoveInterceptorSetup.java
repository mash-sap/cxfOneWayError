package de.mash.demo;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.InterceptorChain;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.addressing.MAPAggregator;

public class RemoveInterceptorSetup extends AbstractPhaseInterceptor {

    public RemoveInterceptorSetup(){
        super(Phase.PRE_LOGICAL);
        this.addBefore(MAPAggregator.class.getName());
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        remove(message, "MAPAggregatorImpl");
        remove(message, "SecurityVerificationOutInterceptor");
        remove(message, "PolicyBasedWSS4JStaxOutInterceptor");
        remove(message, "HttpsTokenOutInterceptor");
        remove(message, "MAPCodec");
        remove(message, "PolicyBasedWSS4JOutInterceptor");
        remove(message, "PolicyBasedWSS4JOutInterceptorInternal");
        remove(message, "PolicyVerificationOutInterceptor");
        remove(message, "SoapOutEndingInterceptor");
        remove(message, "SAAJOutEndingInterceptor");
        remove(message, "StaxOutEndingInterceptor");
        remove(message, "MessageSenderEndingInterceptor");
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
