package pl.coderslab.memorize.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

public class WebConfiguration implements WebMvcConfigurer {

    // Fakultatywne przekieranie ścieżki "/login" na "login.jsp" (zastępuje w całości LoginController.java, bo jest tam
    // tylko 1 metoda przekierowująca przez GET na formularz; natomiast POST z formularza jest obsługiwana przez
    // Security zgodnie z SecurityConfiguration.java metodą void configure(HttpSecurity http) i
    // http.authorizeRequests().....formLogin())
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("login");
    }


    // Implementuje możliwość zmiany jezyka (jednak tylko przez metodę Get tj. np. local:/8080?lang=pl)
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
    //Zapisuje? do cookie język
    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(new Locale("en"));
        return cookieLocaleResolver;
    }
}

