package hello.typeconverter;

import hello.typeconverter.converter.IntegerToStringConverter;
import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringtoIntegerConverter;
import hello.typeconverter.converter.StringtoIpPortConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringtoIntegerConverter());
        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringtoIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());
    }
}
