package study.datajpa.entity;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecommendContentsJsonbDto {

    private String contentId;
    private String searchType;

}
