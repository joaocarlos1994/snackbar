package br.com.hyperclass.snackbar.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SnackBarWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?> [] {RootConfig.class, SecurityConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?> [] {WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String [] {"/*"};
	}

}
