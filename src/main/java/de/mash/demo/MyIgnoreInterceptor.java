package de.mash.demo;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.policy.AssertionInfoMap;

import javax.xml.namespace.QName;
import java.util.HashSet;
import java.util.Set;

public class MyIgnoreInterceptor extends AbstractPhaseInterceptor {
    Set<QName> set;
    public MyIgnoreInterceptor(){
        super(Phase.POST_LOGICAL);

        String NAMESPACE = "http://docs.oasis-open.org/ws-sx/ws-securitypolicy/200702";
//        PolicyInterceptorProviderRegistry reg = bus.getExtension(PolicyInterceptorProviderRegistry.class);
        set = new HashSet<QName>();
        set.add(new QName(NAMESPACE, "TransportBinding"));
        set.add(new QName(NAMESPACE, "TransportToken"));
        set.add(new QName(NAMESPACE, "HttpsToken"));
        set.add(new QName(NAMESPACE, "HttpBasicAuthentication"));
        set.add(new QName(NAMESPACE, "RequireClientCertificate"));
        set.add(new QName(NAMESPACE, "AlgorithmSuite"));
        set.add(new QName(NAMESPACE, "Basic256"));
        set.add(new QName(NAMESPACE, "Basic192"));
        set.add(new QName(NAMESPACE, "Basic128"));
        set.add(new QName(NAMESPACE, "TripleDes"));
        set.add(new QName(NAMESPACE, "Basic256Rsa15"));
        set.add(new QName(NAMESPACE, "Basic192Rsa15"));
        set.add(new QName(NAMESPACE, "Basic128Rsa15"));
        set.add(new QName(NAMESPACE, "TripleDesRsa15"));
        set.add(new QName(NAMESPACE, "Basic256Sha256"));
        set.add(new QName(NAMESPACE, "Basic192Sha256"));
        set.add(new QName(NAMESPACE, "Basic128Sha256"));
        set.add(new QName(NAMESPACE, "TripleDesSha256"));
        set.add(new QName(NAMESPACE, "Basic256Sha256Rsa15"));
        set.add(new QName(NAMESPACE, "Basic192Sha256Rsa15"));
        set.add(new QName(NAMESPACE, "Basic128Sha256Rsa15"));
        set.add(new QName(NAMESPACE, "TripleDesSha256Rsa15"));
        set.add(new QName(NAMESPACE, "Layout"));
        set.add(new QName(NAMESPACE, "Strict"));
//        reg.register(new IgnorablePolicyInterceptorProvider(set));
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        AssertionInfoMap aim = message.get(AssertionInfoMap.class);
        if(aim != null){
            aim.forEach((k,v) -> v.forEach((v2) -> v2.setAsserted(true)));
        }

//        aim.forEach(x,y -> y.forEach);
//        for (QName an : set) {
//            Collection<AssertionInfo> ais = aim.getAssertionInfo(an);
//            if (null != ais) {
//                for (AssertionInfo ai : ais) {
//                    ai.setAsserted(true);
//                }
//            }
//        }
    }

    @Override
    public void handleFault(Message message) {

    }
}
