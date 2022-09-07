package io.github.alprkeskin.common.model.response.result;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResultEntitiesTest {
    @Test
    public void testEntities() {
        assertNotNull(ResultEntities.SUCCESS.getResultEntity());
        assertNotNull(ResultEntities.FAIL.getResultEntity());
    }
}
