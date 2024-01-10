package org.mcudzik.backend.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CodeGenerationUtilTest {

    @Test
    void testGenerateRandomCode() {
        // Test case 1: Check if the generated code has the expected length
        int length = 10;
        String code = CodeGenerationUtil.generateRandomCode(length);
        assertEquals(length, code.length());

        // Test case 2: Check if the generated code contains only valid characters
        String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (char c : code.toCharArray()) {
            assertTrue(validChars.contains(String.valueOf(c)));
        }

        // Test case 3: Check if two generated codes are not the same
        String code1 = CodeGenerationUtil.generateRandomCode(length);
        String code2 = CodeGenerationUtil.generateRandomCode(length);
        assertNotEquals(code1, code2);
    }
}
