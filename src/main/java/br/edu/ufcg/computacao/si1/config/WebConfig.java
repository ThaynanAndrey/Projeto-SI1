package br.edu.ufcg.computacao.si1.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.edu.ufcg.computacao.si1.utils.Paths;
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.FORBIDDEN, Paths.PATH_403);
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, Paths.PATH_404);
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, Paths.PATH_500);

            container.addErrorPages(error401Page, error404Page, error500Page);
        });
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(Paths.PATH_403).setViewName(Paths.PATH_ERRO_403);
        registry.addViewController(Paths.PATH_404).setViewName(Paths.PATH_ERRO_404);
        registry.addViewController(Paths.PATH_500).setViewName(Paths.PATH_ERRO_500);
    }
}
