package de.bas.deploymentmanager.logic.domain.project.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TagTest {

    @Test
    void testFuerTag_ToString() {
        //GIVEN
        Tag tag = new Tag(1, 2, 3, 4);

        //WHEN
        String s = tag.toString();

        //THEN
        assertEquals("1.2.3-4", s);
    }
}