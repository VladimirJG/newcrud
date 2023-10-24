package org.example.newcrud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration  // говорит о том что класс является конфигурационным
@ComponentScan("org.example.newcrud") // путь для spring для сканирования и поиска аннотаций
@EnableWebMvc // возможность использования конфигурационного класса в model-view-controller веб-приложения
// класс используется для конфигурации веб приложения(как приложение будет работать с html шаблонами и как они будут отображаться на веб страницах)
// это позволяет создавать динамические веб страницы, вставлять данные в шаблоны и отображать их на сайте с
// использованием spring mvc и thymeleaf
public class NotesConfig implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;

    @Autowired // автоматический поиск и инициализация бина через ApplicationContext
    public NotesConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean // правила нахождения и чтения html шаблонов (файлов с веб страницами)
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();// преобразователь шаблонов
        templateResolver.setApplicationContext(applicationContext); // подаем в преобразователь класс инициализатор
        templateResolver.setPrefix("/WEB-INF/views"); // говорим где искать
        templateResolver.setSuffix(".html"); //и какие файлы ищем
        return templateResolver;
    }

    @Bean // используется непосредственно для обработки html шаблонов
    //самостоятельно передает данные в html и отображает на веб страницах
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine(); // создается обработчик шаблонов
        templateEngine.setTemplateResolver(templateResolver()); // в него передается инфа о путях и файлах из templateResolver
        templateEngine.setEnableSpringELCompiler(true); // установка работоспособности метода
        return templateEngine;
    }

    @Override // механизм перевода шаблонов html в конечные веб страницы
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
}
