package io.github.alprkeskin.common.model.response.result;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResultEntityTest {
    @Test
    public void testConstructors() {
        ResultEntity entity = new ResultEntity();
        ResultEntity entity2 = new ResultEntity(ResultType.FAIL, "fail");

        assertNotNull(entity);
        assertNotNull(entity2);
    }

    @Test
    public void testBuilder() {
        ResultEntity entity = ResultEntity.builder().resultType(ResultType.SUCCESS).message("message").build();

        assertEquals(ResultType.SUCCESS, entity.getResultType());
        assertEquals("message", entity.getMessage());
    }

    @Test
    public void testMutator() {
        ResultEntity entity = ResultEntity.builder().resultType(ResultType.SUCCESS).message("message").build();

        entity.setResultType(ResultType.FAIL);
        entity.setMessage("test");

        assertEquals(ResultType.FAIL, entity.getResultType());
        assertEquals("test", entity.getMessage());
    }
}
