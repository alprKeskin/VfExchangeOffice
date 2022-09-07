package io.github.alprkeskin.common.model.response.result;

import lombok.Getter;

@Getter
public enum ResultEntities {
    FAIL(ResultType.FAIL, "Operation is fail"), SUCCESS(ResultType.SUCCESS, "Operation is successful");

    private final ResultEntity resultEntity;

    ResultEntities(ResultType resultType, String message) {
        resultEntity = ResultEntity.builder().resultType(resultType).message(message).build();
    }
}
