package Jasper.SideProject.SpringSecurity.Config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@ComponentScan
@EnableAutoConfiguration
@Configuration
@EnableSwagger2
public class SwaggerUi {
	   @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	            .apiInfo(apiInfo())
	            .ignoredParameterTypes(Errors.class)
	            .select()
	            .apis(RequestHandlerSelectors.basePackage("Jasper.SideProject.SpringSecurity"))
	            .paths(PathSelectors.any())
	            .build();
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	            .title("TEST")
	            .description("TEST API")
	            .version("v1")
	            .build();
	    }
}
