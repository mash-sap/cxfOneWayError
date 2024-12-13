Endpoint: localhost:9900/cxf/oneway
Sending this payload gives following exception (its different based on the versions):

Caused by: com.ctc.wstx.exc.WstxEOFException: Unexpected EOF; was expecting a close tag for element <soap-env:Body>
at [row,col {unknown-source}]: [25,15]

OR

Caused by: java.lang.IllegalStateException: Current event not START_ELEMENT or END_ELEMENT
at com.ctc.wstx.sr.BasicStreamReader.getNamespaceCount(BasicStreamReader.java:805) ~[woodstox-core-6.2.7.jar:6.2.7]
at org.apache.cxf.staxutils.DepthXMLStreamReader.getNamespaceCount(DepthXMLStreamReader.java:122) ~[cxf-core-3.5.2.jar:3.5.2]
at org.apache.cxf.staxutils.DepthXMLStreamReader.getNamespaceCount(DepthXMLStreamReader.java:122) ~[cxf-core-3.5.2.jar:3.5.2]
at org.apache.camel.component.cxf.converter.DelegatingXMLStreamReader.<init>(DelegatingXMLStreamReader.java:40) ~[camel-cxf-3.17.0.jar:3.17.0]
at org.apache.camel.component.cxf.converter.CxfPayloadConverter.convertTo(CxfPayloadConverter.java:225) ~[camel-cxf-3.17.0.jar:3.17.0]
at org.apache.camel.component.cxf.converter.CxfPayloadConverterLoader.lambda$registerFallbackConverters$8(CxfPayloadConverterLoader.java:68) ~[camel-cxf-3.17.0.jar:3.17.0]
at org.apache.camel.support.SimpleTypeConverter.convertTo(SimpleTypeConverter.java:101) ~[camel-support-3.17.0.jar:3.17.0]
... 30 common frames omitted



Payload:
<soap-env:Envelope xmlns:soap-env="http://schemas.xmlsoap.org/soap/envelope/">
<soap-env:Header>
<wsa:To soap-env:mustUnderstand="1" xmlns:wsa="http://schemas.xmlsoap.org/ws/2004/08/addressing">http://localhost/test</wsa:To>
<wsa:From xmlns:wsa="http://schemas.xmlsoap.org/ws/2004/08/addressing">
<wsa:Address>http://schemas.xmlsoap.org/ws/2004/08/addressing/role/anonymous</wsa:Address>
</wsa:From>
<wsa:ReplyTo xmlns:wsa="http://schemas.xmlsoap.org/ws/2004/08/addressing">
<wsa:Address>http://schemas.xmlsoap.org/ws/2004/08/addressing/role/anonymous</wsa:Address>
</wsa:ReplyTo>
<wsa:FaultTo xmlns:wsa="http://schemas.xmlsoap.org/ws/2004/08/addressing">
<wsa:Address>http://schemas.xmlsoap.org/ws/2004/08/addressing/role/anonymous</wsa:Address>
</wsa:FaultTo>
<wsa:Action soap-env:mustUnderstand="1" xmlns:wsa="http://schemas.xmlsoap.org/ws/2004/08/addressing"/>
<wsa:MessageID xmlns:wsa="http://schemas.xmlsoap.org/ws/2004/08/addressing">uuid:123</wsa:MessageID>
</soap-env:Header>
<soap-env:Body>
<data>
Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.

            Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.
            Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.
					
            Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.
            Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et aaa
        </data>
    </soap-env:Body>
</soap-env:Envelope>