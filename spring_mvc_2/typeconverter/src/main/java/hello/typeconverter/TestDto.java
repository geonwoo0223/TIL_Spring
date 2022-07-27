package hello.typeconverter;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TestDto {

    private Integer priority;
    private String containerId;
    private String searchType;

    @Builder
    public TestDto(Integer priority, String containerId, String searchType, String data) {
        this.priority = priority;
        this.containerId = containerId;
        this.searchType = searchType;
    }
}
