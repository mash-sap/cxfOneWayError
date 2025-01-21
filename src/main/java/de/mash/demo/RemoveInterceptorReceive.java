package de.mash.demo;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.InterceptorChain;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class RemoveInterceptorReceive extends AbstractPhaseInterceptor {

    public RemoveInterceptorReceive() {
        super(Phase.RECEIVE);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
//        remove(message, "HttpsTokenInInterceptor");
//        remove(message, "PolicyBasedWSS4JStaxInInterceptor");
//        remove(message, "PolicyBasedWSS4JInInterceptor");
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
