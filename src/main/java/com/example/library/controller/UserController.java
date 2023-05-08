package com.example.library.controller;

import com.example.library.exception.BaseException;
import com.example.library.object.UserDTO;
import com.example.library.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@Api(tags = {"User"})
@SwaggerDefinition(tags = {
        @Tag(name = "User", description = "User APIs")
})
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping()
    @ApiOperation(value = "Create user.", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = UserDTO.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = BaseException.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = BaseException.class),
            @ApiResponse(code = 403, message = "ACCESS DENIED", response = BaseException.class),
            @ApiResponse(code = 409, message = "CONFLICT", response = BaseException.class)
    })
    @ApiImplicitParam(name = "user", value = "User object.", dataType = "UserDTO", paramType = "body", required = true, dataTypeClass = UserDTO.class)
    public UserDTO addUser(@RequestBody UserDTO user) {
        return this.service.addUser(user);

    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_BOOKER')")
    @ApiOperation(value = "Get user by id.", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = UserDTO.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = BaseException.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = BaseException.class),
            @ApiResponse(code = 403, message = "ACCESS DENIED", response = BaseException.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = BaseException.class)
    })
    @ApiImplicitParam(name = "id", value = "User id.", dataType = "long", paramType = "path", required = true, dataTypeClass = Long.class)
    public UserDTO getUser(@PathVariable long id) {
        return this.service.getUser(id);
    }

    @PostMapping("authorize/{id}")
    @ApiOperation(value = "Authorize user.", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = String.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = BaseException.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = BaseException.class),
            @ApiResponse(code = 403, message = "ACCESS DENIED", response = BaseException.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = BaseException.class)
    })
    @ApiImplicitParam(name = "id", value = "User id.", dataType = "long", paramType = "path", required = true, dataTypeClass = Long.class)
    public String authorizeUser(@PathVariable long id) {
        return this.service.authorizeUser(id);
    }
}
