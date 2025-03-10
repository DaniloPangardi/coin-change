package br.com.desafio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
@ComponentScan(basePackageClasses = SwaggerConfig.class)
public class SwaggerConfig {
	
	@Bean
	public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.desafio"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
	
	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	    		.title("Operações para troco de moedas.")
	    		.description("Api para cálculo de troco utilizando as menor quantidade de moedas.")
	    		.version("1.0.0")
	    		.build();
	}

}
