package com.example.library.controller;

import com.example.library.exception.BaseException;
import com.example.library.object.ReservationDTO;
import com.example.library.service.ReservationService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reserves")
@Api(tags = {"Reservation"})
@SwaggerDefinition(tags = {
        @Tag(name = "Reservation", description = "Reservation APIs")
})
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;


    @PostMapping()
    @ApiOperation(value = "Reserve book.", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ReservationDTO.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = BaseException.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = BaseException.class),
            @ApiResponse(code = 403, message = "ACCESS DENIED", response = BaseException.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = BaseException.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "User id.", dataType = "long", paramType = "query", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(name = "book_id", value = "Book id.", dataType = "long", paramType = "query", required = true, dataTypeClass = Long.class)
    })
    public ReservationDTO reserve(
            @RequestParam(value = "user_id") long userId,
            @RequestParam(value = "book_id") long bookId) {
        return this.service.reserve(bookId,userId);
    }

    @DeleteMapping(value = "{bookId}")
    @ApiOperation(value = "End reservation.", httpMethod = "DELETE")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ReservationDTO.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = BaseException.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = BaseException.class),
            @ApiResponse(code = 403, message = "ACCESS DENIED", response = BaseException.class),
            @ApiResponse(code = 404, message = "'firstName' 'lastName' has reached maximum reserves", response = BaseException.class),
            @ApiResponse(code = 404, message = "'book title' is reserved right now.", response = BaseException.class)
    })
    public ReservationDTO endReservation(@PathVariable long bookId) {
        return this.service.endReservation(bookId);
    }
}
