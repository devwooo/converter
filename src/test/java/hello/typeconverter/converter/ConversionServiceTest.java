package hello.typeconverter.converter;

import hello.typeconverter.converter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;

public class ConversionServiceTest {
    @Test
    void conversionService() {
        // 등록
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringtoIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        conversionService.addConverter(new StringtoIpPortConverter());

        //사용
        Integer convert = conversionService.convert("10", Integer.class);
        assertThat(convert).isEqualTo(10);

        String convert1 = conversionService.convert(10, String.class);
        assertThat(convert1).isEqualTo("10");

        assertThat(conversionService.convert(new IpPort("127.1.1.100", 8080), String.class)).isEqualTo("127.1.1.100:8080");
        assertThat(conversionService.convert("127.1.1.100:8080", IpPort.class)).isEqualTo(new IpPort("127.1.1.100",8080));
    }
}
