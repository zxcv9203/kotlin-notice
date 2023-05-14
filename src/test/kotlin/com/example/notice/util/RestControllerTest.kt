package com.example.notice.util

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext

@AutoConfigureRestDocs
abstract class RestControllerTest {

    val objectMapper: ObjectMapper = ObjectMapper()

    lateinit var mockMvc: MockMvc

    @BeforeEach
    internal fun setup(
        webContext: WebApplicationContext,
        @Autowired restDocs: RestDocumentationContextProvider
    ) {
        mockMvc = buildRestDocsMockMvc(webContext, restDocs)
    }
}