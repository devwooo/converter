package hello.typeconverter.converter;

import hello.typeconverter.converter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.assertj.core.api.Assertions.*;

public class ConverterTest {

    @Test
    void stringToInteger() {
        StringtoIntegerConverter converter = new StringtoIntegerConverter();

        Integer convert = converter.convert("10");
        assertThat(convert).isEqualTo(10);
    }

    @Test
    void integerToString() {
        IntegerToStringConverter converter = new IntegerToStringConverter();

        String convert = converter.convert(10);
        assertThat(convert).isEqualTo("10");
    }

    @Test
    void StringToIpPort() {
        StringtoIpPortConverter converter = new StringtoIpPortConverter();
        IpPort convert = converter.convert("127.0.0.1:8080");
        assertThat(convert).isEqualTo(new IpPort("127.0.0.1", 8080));
    }

    @Test
    void IpPortToString() {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        String convert = converter.convert(new IpPort("127.1.1.100", 8080));
        assertThat(convert).isEqualTo("127.1.1.100:8080");
    }

}
