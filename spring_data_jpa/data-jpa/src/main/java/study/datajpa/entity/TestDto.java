package study.datajpa.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TestDto {

    private Integer priority;
    private String containerId;
    private String searchType;

    @Builder
    public TestDto(Integer priority, String containerId,String searchType) {
        this.priority = priority;
        this.containerId = containerId;
        this.searchType = searchType;
    }
}
