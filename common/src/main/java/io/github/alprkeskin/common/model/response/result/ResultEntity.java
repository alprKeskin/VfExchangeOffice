package io.github.alprkeskin.common.model.response.result;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultEntity implements Serializable {
    private ResultType resultType;
    private String message;
}
