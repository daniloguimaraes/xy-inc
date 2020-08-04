package com.xyinc.poiservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.View
import org.springframework.web.servlet.view.RedirectView
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * Swagger2 configuration.
 *
 * @author Danilo Guimaraes
 */
@EnableSwagger2
@Configuration
@RestController
@RequestMapping("/")
class SwaggerConfig {

    @GetMapping(path = ["/doc", "/docs"])
    fun docsRedirect(): View {
        return RedirectView("swagger-ui.html");
    }

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.xyinc.poiservice"))
                .build()
                .apiInfo(apiInfo())
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfo(
                "PoI Service",
                null,
                null,
                null,
                null,
                null,
                null,
                ArrayList()
        )
    }

}