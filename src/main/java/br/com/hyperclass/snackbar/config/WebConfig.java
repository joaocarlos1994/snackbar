package br.com.hyperclass.snackbar.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"br.com.hyperclass.snackbar.restapi", "br.com.hyperclass.snackbar.restapi.deserializer",
		"br.com.hyperclass.snackbar.restapi.serializer"})
//@Import(EventosSerializersConfig.class)
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
		configurer.mediaTypes(getMediaTypes());
	}

	@Bean
	public ViewResolver viewResolver(final ContentNegotiationManager manager) {
		final ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		return resolver;
	}

	@Bean
	public ContentNegotiatingViewResolver contentViewResolver() throws Exception {
		final ContentNegotiationManagerFactoryBean contentNegotiationManager = new ContentNegotiationManagerFactoryBean();
		contentNegotiationManager.addMediaTypes(getMediaTypes());
		contentNegotiationManager.setDefaultContentType(MediaType.APPLICATION_JSON);

		final MappingJackson2JsonView defaultJsonView = new MappingJackson2JsonView();
		defaultJsonView.setExtractValueFromSingleKeyModel(true);

		final ContentNegotiatingViewResolver contentViewResolver = new ContentNegotiatingViewResolver();
		contentViewResolver.setContentNegotiationManager(contentNegotiationManager.getObject());
		contentViewResolver.setDefaultViews(Arrays.<View>asList(defaultJsonView, new MappingJackson2JsonView()));

		return contentViewResolver;
	}

	private Map<String, MediaType> getMediaTypes() {
		final Map<String, MediaType> mediaTypes = new HashMap<>();
		mediaTypes.put("json", MediaType.APPLICATION_JSON);
		mediaTypes.put("xml", MediaType.APPLICATION_XML);
		return mediaTypes;
	}
}