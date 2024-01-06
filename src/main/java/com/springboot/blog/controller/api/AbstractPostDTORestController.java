package com.springboot.blog.controller.api;

import com.springboot.blog.dto.GlobalApiErrorResponse;
import com.springboot.blog.dto.api.PostDTOApiResource;
import com.springboot.blog.dto.post.PostDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

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
}
