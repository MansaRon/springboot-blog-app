package com.springboot.blog.controller.api;

import com.springboot.blog.dto.GlobalApiErrorResponse;
import com.springboot.blog.dto.api.PostDTOApiResource;
import com.springboot.blog.dto.api.PostDTOListApiResource;
import com.springboot.blog.dto.api.PostPageApiResource;
import com.springboot.blog.dto.post.PostDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Thendo
 * @date 2024/01/06
 */
public interface AbstractPostDTORestController {

    @Operation(tags = "Create Post", summary = "Creation of a post")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Post created successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = PostDTOApiResource.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Request failed, incorrect payload",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Not authorised to access resource",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Authorisation invalid",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Requested resource does not exist",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "409",
                    description = "Request could not be completed",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    })
    })
    ResponseEntity<PostDTOApiResource> createPost(@RequestBody PostDTO postDTO);

    @Operation(tags = "Get Posts", summary = "Retrieval of post")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Post retrieved successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = PostPageApiResource.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Request failed, incorrect payload",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Not authorised to access resource",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Authorisation invalid",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Requested resource does not exist",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "409",
                    description = "Request could not be completed",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    })
    })
    ResponseEntity<PostPageApiResource> getPosts(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                    @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize);

    @Operation(tags = "Get Post By ID", summary = "Retrieval of a single post")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Single Post retrieved successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = PostDTOApiResource.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Request failed, incorrect payload",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Not authorised to access resource",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Authorisation invalid",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Requested resource does not exist",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "409",
                    description = "Request could not be completed",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    })
    })
    ResponseEntity<PostDTOApiResource> getPostById(@PathVariable(name = "id") long id);

    @Operation(tags = "Edit post", summary = "Edit existing post")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Post edited successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = PostDTOApiResource.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Request failed, incorrect payload",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Not authorised to access resource",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Authorisation invalid",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Requested resource does not exist",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "409",
                    description = "Request could not be completed",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    })
    })
    ResponseEntity<PostDTOApiResource> editPost(@PathVariable(name = "id") long id, @RequestBody PostDTO postDTO);

    @Operation(tags = "Delete post", summary = "Delete post")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Post deleted successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = PostDTOApiResource.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Request failed, incorrect payload",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Not authorised to access resource",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Authorisation invalid",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Requested resource does not exist",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "409",
                    description = "Request could not be completed",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    })
    })
    ResponseEntity<String> deletePost(@PathVariable(name = "id") long id);

    @Operation(tags = "Delete all posts", summary = "Delete all posts")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "All Posts deleted successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = PostDTOApiResource.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Request failed, incorrect payload",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Not authorised to access resource",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Authorisation invalid",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Requested resource does not exist",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "409",
                    description = "Request could not be completed",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =
                                    @Schema(implementation = GlobalApiErrorResponse.class))
                    })
    })
    ResponseEntity<String> deleteAllPosts();
}
