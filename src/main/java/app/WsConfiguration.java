package app;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
public class WsConfiguration {
	public static final String NAMESPACE = "http://www.rogueflashspjo.foo/soap";
	
	// All configured by WebServicesAutoConfiguration except DefaultWsdl11Definition.
	//
	// To support SOAP 1.1 and 1.2 together a custom SoapMessageFactory is necessary, see:
	//
	//     https://stackoverflow.com/questions/53567967/support-both-soap-1-1-and-soap-1-2-messages-in-spring-ws
	//     https://panbhatt.blogspot.com/2011/04/spring-web-service-part-iii-creating.html
	
	@Bean(name = "decksWs")
	public DefaultWsdl11Definition defaultWsdl11Definition(@Qualifier("decksSchema") XsdSchema schema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("Decks");
		wsdl11Definition.setLocationUri("/decks/");
		wsdl11Definition.setTargetNamespace(NAMESPACE);
		wsdl11Definition.setSchema(schema);
		return wsdl11Definition;
	}
}
