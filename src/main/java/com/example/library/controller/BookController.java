package com.example.library.controller;

import com.example.library.exception.BaseException;
import com.example.library.object.BookDTO;
import com.example.library.service.BookService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController()
@RequestMapping("books")
@Api(tags = {"Book"},description = "Book APIs")
@SwaggerDefinition(tags = {
        @Tag(name = "Book", description = "Book APIs")
})
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value = "Get books with pagination.", httpMethod = "GET", response = BookDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = BookDTO.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = BaseException.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = BaseException.class),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "Page number (starts from 0).", dataType = "int", paramType = "query", defaultValue = "0", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "size", value = "Page size.", dataType = "int", paramType = "query", defaultValue = "10", dataTypeClass = Integer.class)
    })
    public ResponseEntity<List<BookDTO>> getBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<BookDTO> bookDTO =this.service.getBooks(page, size);
        return new ResponseEntity<>(
                bookDTO,
                HttpStatus.OK
        );
    }

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Get book by id.", httpMethod = "GET", response = BookDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = BookDTO.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = BaseException.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = BaseException.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = BaseException.class),
    })
    @ApiImplicitParam(name = "id", value = "Book id.", dataType = "long", paramType = "path", required = true, dataTypeClass = Long.class)
    public ResponseEntity<BookDTO> getBook(@PathVariable @NotNull long id) {
        BookDTO bookDTO = this.service.getBook(id);
        return new ResponseEntity<>(
                bookDTO,
                HttpStatus.OK
        );
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Delete book by id.", httpMethod = "DELETE", response = BookDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = BookDTO.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = BaseException.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = BaseException.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = BaseException.class),
    })
    @ApiImplicitParam(name = "id", value = "Book id.", dataType = "long", paramType = "path", required = true, dataTypeClass = Long.class)
    public ResponseEntity<BookDTO> deleteBook(@PathVariable long id) {
        BookDTO bookDTO = this.service.deleteBook(id);
        return new ResponseEntity<>(
                bookDTO,
                HttpStatus.OK
        );
    }

    @PostMapping()
    @ApiOperation(value = "Add book.", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED", response = BookDTO.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = BaseException.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = BaseException.class),
            @ApiResponse(code = 409, message = "CONFLICT", response = BaseException.class)
    })
    @ApiImplicitParam(name = "book", value = "Book object.", dataType = "BookDTO", paramType = "body", required = true, dataTypeClass = BookDTO.class)
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO book) {
        BookDTO bookDTO = this.service.addBook(book);
//        log.info("Book added: {}", bookDTO);
        return new ResponseEntity<>(
                bookDTO,
                HttpStatus.CREATED
        );
    }
}
