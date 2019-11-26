package app;

import org.jooq.conf.RenderNameStyle;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// BeanPostProcessor used to preserve JooqAutoConfiguration's work, instead of
		// completely redefining DefaultConfiguration as explained here:
		//
		//     https://www.baeldung.com/jooq-with-spring
		
		// System.out.println("Bean '" + beanName + "' created : " + bean.toString());
		
		if (bean instanceof DefaultConfiguration)
		{
			DefaultConfiguration configuration = (DefaultConfiguration) bean;
			Settings settings = configuration.settings();
			settings.setRenderFormatted(true);
			settings.setRenderNameStyle(RenderNameStyle.AS_IS);
			settings.setRenderSchema(false);
		}
		
		return bean;
	}
}