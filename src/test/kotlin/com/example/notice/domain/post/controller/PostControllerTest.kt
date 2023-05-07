package com.example.notice.domain.post.controller

import com.example.notice.domain.post.controller.dto.response.PostResponses
import com.example.notice.domain.post.facade.PostFacade
import com.example.notice.stub.*
import com.example.notice.util.RestControllerTest
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import kotlin.text.Charsets.UTF_8

@WebMvcTest(PostController::class)
@DisplayName("PostController")
class PostControllerTest @Autowired constructor(
    @MockkBean private val postFacade: PostFacade,
) : RestControllerTest() {

    @Test
    fun `게시글 목록 조회`() {
        val rootPath = "/api/posts"
        val want = PostResponses(listOf(getPostSimpleResponse()))

        every { postFacade.findAll() } returns want
        val result = mockMvc.perform(get(rootPath))

        result.andExpect(status().isOk).andDo(
            document(
                "post/get-posts", responseFields(
                    fieldWithPath("status").type(JsonFieldType.NUMBER).description("상태 코드"),
                    fieldWithPath("message").type(JsonFieldType.STRING).description("API 결과 메시지"),
                    fieldWithPath("body.posts[].id").type(JsonFieldType.NUMBER)
                        .description("게시글 이름"),
                    fieldWithPath("body.posts[].title").type(JsonFieldType.STRING)
                        .description("게시글 이름"),
                    fieldWithPath("body.posts[].member.id").type(JsonFieldType.NUMBER)
                        .description("유저 ID"),
                    fieldWithPath("body.posts[].member.nickName").type(JsonFieldType.STRING)
                        .description("유저 닉네임"),
                    fieldWithPath("body.posts[].createdAt").type(JsonFieldType.STRING)
                        .description("게시글 생성 시간"),
                    fieldWithPath("body.posts[].updatedAt").type(JsonFieldType.STRING)
                        .description("게시글 수정 시간")
                )
            )
        )
        verify { postFacade.findAll() }
    }

    @Test
    fun `게시글 단일 조회`() {
        val want = getPostResponse()
        val rootPath = "/api/posts/{id}"
        every { postFacade.findById(want.id) } returns want
        val result = mockMvc.perform(get(rootPath, want.id))

        result.andExpect(status().isOk).andDo(
            document(
                "post/get-post-by-id", pathParameters(
                    parameterWithName("id").description("게시글 ID")
                ), responseFields(
                    fieldWithPath("status").type(JsonFieldType.NUMBER).description("상태 코드"),
                    fieldWithPath("message").type(JsonFieldType.STRING).description("API 결과 메시지"),
                    fieldWithPath("body.id").type(JsonFieldType.NUMBER).description("게시글 ID"),
                    fieldWithPath("body.title").type(JsonFieldType.STRING).description("게시글 이름"),
                    fieldWithPath("body.content").type(JsonFieldType.STRING).description("게시글 본문"),
                    fieldWithPath("body.content").type(JsonFieldType.STRING).description("게시글 본문"),
                    fieldWithPath("body.member.id").type(JsonFieldType.NUMBER).description("유저 ID"),
                    fieldWithPath("body.member.nickName").type(JsonFieldType.STRING)
                        .description("유저 닉네임"),
                    fieldWithPath("body.createdAt").type(JsonFieldType.STRING)
                        .description("게시글 생성 시간"),
                    fieldWithPath("body.updatedAt").type(JsonFieldType.STRING)
                        .description("게시글 수정 시간")
                )
            )
        )
        verify { postFacade.findById(want.id) }
    }

    @Test
    fun `게시글 생성`() {
        val request = getPostSaveRequest()
        val rootPath = "/api/posts"
        val want = getIdResponse()
        every { postFacade.save(request) } returns want
        val result = mockMvc.perform(
            post(rootPath).characterEncoding(UTF_8).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request))
        )

        result.andExpect(status().isCreated).andDo(
            document(
                "post/save", requestFields(
                    fieldWithPath("memberName").type(JsonFieldType.STRING).description("유저 이름"),
                    fieldWithPath("password").type(JsonFieldType.STRING).description("게시글 패스워드"),
                    fieldWithPath("title").type(JsonFieldType.STRING).description("게시글 제목"),
                    fieldWithPath("content").type(JsonFieldType.STRING).description("게시글 본문")
                ), responseFields(
                    fieldWithPath("status").type(JsonFieldType.NUMBER).description("상태 코드"),
                    fieldWithPath("message").type(JsonFieldType.STRING).description("API 결과 메시지"),
                    fieldWithPath("body.id").type(JsonFieldType.NUMBER).description("게시글 ID")
                )
            )
        )
        verify { postFacade.save(request) }
    }

    @Test
    fun `게시글 업데이트`() {
        val request = getPostUpdateRequest()
        val id = getId()
        val rootPath = "/api/posts/{id}"
        val want = getPostResponse()

        every { postFacade.update(request, id) } returns want
        val result = mockMvc.perform(
            patch(rootPath, id).characterEncoding(UTF_8).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request))
        )

        result.andExpect(status().isOk).andDo(
            document(
                "post/update", pathParameters(
                    parameterWithName("id").description("게시글 ID")
                ), requestFields(
                    fieldWithPath("password").type(JsonFieldType.STRING).description("게시글 패스워드"),
                    fieldWithPath("title").type(JsonFieldType.STRING).description("게시글 제목"),
                    fieldWithPath("content").type(JsonFieldType.STRING).description("게시글 본문")
                ), responseFields(
                    fieldWithPath("status").type(JsonFieldType.NUMBER).description("상태 코드"),
                    fieldWithPath("message").type(JsonFieldType.STRING).description("API 결과 메시지"),
                    fieldWithPath("body.id").type(JsonFieldType.NUMBER).description("게시글 ID"),
                    fieldWithPath("body.title").type(JsonFieldType.STRING).description("게시글 이름"),
                    fieldWithPath("body.content").type(JsonFieldType.STRING).description("게시글 본문"),
                    fieldWithPath("body.content").type(JsonFieldType.STRING).description("게시글 본문"),
                    fieldWithPath("body.member.id").type(JsonFieldType.NUMBER).description("유저 ID"),
                    fieldWithPath("body.member.nickName").type(JsonFieldType.STRING)
                        .description("유저 닉네임"),
                    fieldWithPath("body.createdAt").type(JsonFieldType.STRING)
                        .description("게시글 생성 시간"),
                    fieldWithPath("body.updatedAt").type(JsonFieldType.STRING)
                        .description("게시글 수정 시간")
                )
            )
        )
        verify { postFacade.update(request, id) }
    }

    @Test
    fun `게시글 삭제`() {
        val rootPath = "/api/posts/{id}"
        val request = getPostDeleteRequest()
        val id = getId()

        every { postFacade.deleteById(request, id) } just runs
        val result = mockMvc.perform(
            delete(rootPath, id).characterEncoding(UTF_8).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request))
        )

        result.andExpect(status().isOk).andDo(
            document(
                "post/delete", pathParameters(
                    parameterWithName("id").description("게시글 ID")
                ), requestFields(
                    fieldWithPath("password").type(JsonFieldType.STRING).description("게시글 패스워드"),
                ), responseFields(
                    fieldWithPath("status").type(JsonFieldType.NUMBER).description("상태 코드"),
                    fieldWithPath("message").type(JsonFieldType.STRING).description("API 결과 메시지"),
                )
            )
        )

        verify { postFacade.deleteById(request, id) }
    }
}