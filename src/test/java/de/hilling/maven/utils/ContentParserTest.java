package de.hilling.maven.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ContentParserTest {

    private MavenVersion mavenVersion;

    @Test
    public void testIllegalContent() throws Exception {
        parse("whatever");
        assertNull(mavenVersion);
    }

    @Test
    public void testNoNumbers() throws Exception {
        parse("version=");
        assertNull(mavenVersion);
    }

    @Test
    public void testSimpleContent() throws Exception {
        parse("version=1.2.4");
        assertEquals(1, mavenVersion.major);
        assertEquals(2, mavenVersion.minor);
    }

    @Test
    public void testExtraContent() throws Exception {
        parse(" version=2.5.7.CR1.WHATEVER");
        assertEquals(2, mavenVersion.major);
        assertEquals(5, mavenVersion.minor);
    }

    private void parse(String content) {
        mavenVersion = new ContentParser(content).toMavenVersion();
    }
}