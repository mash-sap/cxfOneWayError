Endpoint: localhost:9900/cxf/myendpoint
Sending this payload gives following exception:

ava.lang.IllegalStateException: The request object has been recycled and is no longer associated with this facade
at org.apache.catalina.connector.RequestFacade.checkFacade(RequestFacade.java:856) ~[tomcat-embed-core-9.0.83.jar:9.0.83]
at org.apache.catalina.connector.RequestFacade.getUserPrincipal(RequestFacade.java:610) ~[tomcat-embed-core-9.0.83.jar:9.0.83]
at org.apache.cxf.transport.http.AbstractHTTPDestination$2.getUserPrincipal(AbstractHTTPDestination.java:397) ~[cxf-rt-transports-http-3.6.5.jar:3.6.5]
at org.apache.camel.component.cxf.DefaultCxfBinding.populateExchangeFromCxfRequest(DefaultCxfBinding.java:317) ~[camel-cxf-3.17.0.jar:3.17.0]
at org.apache.camel.component.cxf.CxfConsumer$CxfConsumerInvoker.prepareCamelExchange(CxfConsumer.java:297) ~[camel-cxf-3.17.0.jar:3.17.0]
at org.apache.camel.component.cxf.CxfConsumer$CxfConsumerInvoker.syncInvoke(CxfConsumer.java:235) ~[camel-cxf-3.17.0.jar:3.17.0]
at org.apache.camel.component.cxf.CxfConsumer$CxfConsumerInvoker.invoke(CxfConsumer.java:162) ~[camel-cxf-3.17.0.jar:3.17.0]
at org.apache.cxf.interceptor.ServiceInvokerInterceptor$1.run(ServiceInvokerInterceptor.java:59) ~[cxf-core-3.6.5.jar:3.6.5]
at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539) ~[na:na]
at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
at org.apache.cxf.interceptor.ServiceInvokerInterceptor$2.run(ServiceInvokerInterceptor.java:126) ~[cxf-core-3.6.5.jar:3.6.5]
at org.apache.cxf.workqueue.SynchronousExecutor.execute(SynchronousExecutor.java:37) ~[cxf-core-3.6.5.jar:3.6.5]
at org.apache.cxf.interceptor.ServiceInvokerInterceptor.handleMessage(ServiceInvokerInterceptor.java:131) ~[cxf-core-3.6.5.jar:3.6.5]
at org.apache.cxf.phase.PhaseInterceptorChain.doIntercept(PhaseInterceptorChain.java:307) ~[cxf-core-3.6.5.jar:3.6.5]
at org.apache.cxf.phase.PhaseInterceptorChain.resume(PhaseInterceptorChain.java:277) ~[cxf-core-3.6.5.jar:3.6.5]
at org.apache.cxf.interceptor.OneWayProcessorInterceptor$1.run(OneWayProcessorInterceptor.java:139) ~[cxf-core-3.6.5.jar:3.6.5]
at org.apache.cxf.workqueue.AutomaticWorkQueueImpl$3.run(AutomaticWorkQueueImpl.java:413) ~[cxf-core-3.6.5.jar:3.6.5]
at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136) ~[na:na]
at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635) ~[na:na]
at org.apache.cxf.workqueue.AutomaticWorkQueueImpl$AWQThreadFactory$1.run(AutomaticWorkQueueImpl.java:346) ~[cxf-core-3.6.5.jar:3.6.5]
at java.base/java.lang.Thread.run(Thread.java:840) ~[na:na]



Payload:
<soap:Envelope
xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
<soap:Header/>
<soap:Body>
<ns2:invoke
xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
xmlns:ns1="http://test.de/4.0/billing/acc"
xmlns:ns2="http://cxf.component.camel.apache.org/">
<ns1:properties>
<ns1:property name="test" value="123"/>
</ns1:properties>
</ns2:invoke>
</soap:Body>
</soap:Envelope>


This seems to be related to Tomcat. In tomcat 9 the default value of the following setting has changed from false to true
org.apache.catalina.connector.RECYCLE_FACADES

The problem can be fixed by reverting tomcat to the old bahvior via:
-Dorg.apache.catalina.connector.RECYCLE_FACADES=false
