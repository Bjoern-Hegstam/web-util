package com.bhegstam.webutil;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class CustomCollectorsTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void onlyOptionalElement_emptyStream() {
        // when
        Optional<Object> result = Stream.of().collect(CustomCollectors.onlyOptionalElement());

        // then
        assertFalse(result.isPresent());
    }

    @Test
    public void onlyOptionalElement_singleElementStream() {
        // when
        Optional<String> result = Stream.of("foo").collect(CustomCollectors.onlyOptionalElement());

        // then
        assertTrue(result.isPresent());
        assertEquals("foo", result.get());
    }

    @Test
    public void onlyOptionalElement_twoElementStream() {
        // then
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("More than one element found");

        // when
        Optional<String> result = Stream.of("foo", "bar").collect(CustomCollectors.onlyOptionalElement());
    }
}