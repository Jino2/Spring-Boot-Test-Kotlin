package com.havi

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    properties = ["property.value=propertyTest"],
    classes = [SpringBootTestApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
)
class SpringBootTestApplicationTests {


    @Value("\${property.value}") private val propertyValue: String = ""

    @Test
    fun contextLoads() {
        assertEquals(propertyValue, "propertyTest")
    }
}
