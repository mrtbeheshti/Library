package com.example.library.controller;

import com.example.library.exception.BaseException;
import com.example.library.object.BookDTO;
import com.example.library.service.BookService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;


@RestController()
@RequestMapping("books")
@Api(tags = {"Book"})
@SwaggerDefinition(tags = {
        @Tag(name = "Book", description = "Book APIs")
})
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value = "Get books with pagination.",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = BookDTO.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = BaseException.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = BaseException.class),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "Page number (starts from 0).", dataType = "int", paramType = "query", defaultValue = "0", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "size", value = "Page size.", dataType = "int", paramType = "query", defaultValue = "10", dataTypeClass = Integer.class)
    })
    public List<BookDTO> getBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return  this.service.getBooks(page,size);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Get book by id.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = BookDTO.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = BaseException.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = BaseException.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = BaseException.class),
    })
    @ApiImplicitParam(name = "id", value = "Book id.", dataType = "long", paramType = "path", required = true, dataTypeClass = Long.class)
    public BookDTO getBook(@PathVariable @NotNull long id) {
        return this.service.getBook(id);
    }

    @PostMapping()
    @ApiOperation(value = "Add book.",httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = BookDTO.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = BaseException.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = BaseException.class),
            @ApiResponse(code = 409, message = "CONFLICT", response = BaseException.class)
    })
    @ApiImplicitParam(name = "book", value = "Book object.", dataType = "BookDTO", paramType = "body", required = true, dataTypeClass = BookDTO.class)
    public BookDTO addBook(@RequestBody BookDTO book) {
        return this.service.addBook(book);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete book by id.",httpMethod = "DELETE")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = BookDTO.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = BaseException.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = BaseException.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = BaseException.class),
    })
    @ApiImplicitParam(name = "id", value = "Book id.", dataType = "long", paramType = "path", required = true, dataTypeClass = Long.class)
    public BookDTO deleteBook(@PathVariable long id) {
        return this.service.deleteBook(id);
    }
}
