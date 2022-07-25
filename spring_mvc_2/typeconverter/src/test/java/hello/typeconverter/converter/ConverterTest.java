package hello.typeconverter.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.typeconverter.DataToJson;
import hello.typeconverter.TestDto;
import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.minBy;
import static org.assertj.core.api.Assertions.*;

public class ConverterTest {

    @Test
    void stringToInteger() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");
        assertThat(result).isEqualTo(10);
    }

    @Test
    void integerToString() {
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String result = converter.convert(10);
        assertThat(result).isEqualTo("10");
    }

    @Test
    void stringToIpPort() {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort source = new IpPort("127.0.0.1", 8080);
        String result = converter.convert(source);
        assertThat(result).isEqualTo("127.0.0.1:8080");
    }

    @Test
    void ipPortToString() {
        StringToIpPortConverter converter = new StringToIpPortConverter();
        String source = "127.0.0.1:8080";
        IpPort result = converter.convert(source);
        assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));
    }

    @Test
    public void colltest() {

        List<TestDto> result = new ArrayList<>();
        result.add(TestDto.builder().priority(1).containerId("123").searchType("T").build());
        result.add(TestDto.builder().priority(1).containerId("126").searchType("C").build());
        result.add(TestDto.builder().priority(2).containerId("123").searchType("C").build());
        result.add(TestDto.builder().priority(2).containerId("124").searchType("C").build());
        result.add(TestDto.builder().priority(3).containerId("123").searchType("V").build());
        result.add(TestDto.builder().priority(3).containerId("124").searchType("C").build());
        result.add(TestDto.builder().priority(3).containerId("125").searchType("V").build());
        result.add(TestDto.builder().priority(3).containerId("126").searchType("V").build());

        Map<String, Optional<TestDto>> map = result.stream()
                .collect(groupingBy(TestDto::getContainerId, minBy(comparingInt(TestDto::getPriority))));

        for (Map.Entry<String, Optional<TestDto>> entry : map.entrySet()) {
            System.out.println("entry = " + entry);
        }

    }

}
