package org.example.newcrud.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//класс нужен для инициализации DispatcherServlet, который с помощью методов будет обрабатывать классы-атрибуты методов
public class NotesMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    // возвращает классы содержащие бизнес логику/использующие подключаемые сервисы
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    // возвращает конфигурационный класс содержащий конфигурацию(и) для веб приложения
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{NotesConfig.class};
    }

    //класс обработчик url адресов
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

// использование фильтра
    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException {
        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }
//фильтр для работы со скрытыми полямина веб странице
    public void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null, true, "/*");
    }
}
