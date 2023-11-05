package io.github.vshnv.nortviz.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.github.vshnv.nortviz.interceptor.ImgurAuthClientHttpRequestInterceptor;
import io.github.vshnv.nortviz.service.ImgurService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BeanConfig {
    @Bean(name = "imgur_authenticated")
    public RestTemplate restTemplate(ImgurAuthClientHttpRequestInterceptor interceptor) {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (interceptors.isEmpty()) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(interceptor);
        restTemplate.setInterceptors(interceptors);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new ObjectMapper().registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES)));
        restTemplate.getMessageConverters().add(converter);
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        return restTemplate;
    }
    @Bean(name = "plain")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new ObjectMapper().registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES)));
        restTemplate.getMessageConverters().add(converter);
        return restTemplate;
    }
}
