package com.javaPro.applicationProperty;

import static java.util.Arrays.stream;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

@Configuration
public class PropertiesConfig {

	private static final Logger log = LoggerFactory.getLogger(PropertiesConfig.class);

	private final static String PROPERTIES_FILENAME = "application-dev.yml";

	@Bean
	Properties userProperties() throws IOException {
		final Resource[] possiblePropertiesResources = { new ClassPathResource("config/" + PROPERTIES_FILENAME),
				new PathResource("config/" + PROPERTIES_FILENAME) };
		final Resource propertiesResource = stream(possiblePropertiesResources).filter(Resource::exists)
				.reduce((previous, current) -> current).get();
		final Properties userProperties = new Properties();
		userProperties.load(propertiesResource.getInputStream());
		log.info("Using {} as user resource", propertiesResource);
		return userProperties;
	}

}