package de.mash.demo;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.bind.annotation.XmlAnyElement;

@SOAPBinding(parameterStyle = ParameterStyle.BARE)
@WebService(targetNamespace = "http://camel.apache.org/cxf/jaxws/dispatch", name = "OneWay")
public interface OneWay {
    @Oneway
    @WebMethod(operationName = "InvokeOneWay")
    @XmlAnyElement(lax = true)
    public void oneWay(@WebParam(partName = "InvokeOneWayRequest", mode = WebParam.Mode.IN) Object in);
}