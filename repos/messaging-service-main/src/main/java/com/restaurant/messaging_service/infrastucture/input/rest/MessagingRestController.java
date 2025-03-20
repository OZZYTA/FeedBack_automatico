package com.restaurant.messaging_service.infrastucture.input.rest;

import com.restaurant.messaging_service.application.dto.NotifyClientRequestDto;
import com.restaurant.messaging_service.application.handler.IMessagingHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
@Validated
public class MessagingRestController {

    private  final IMessagingHandler messagingHandler;

    @Operation(
            summary = "Notify a client",
            description = "Sends a notification to the client regarding an order status."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Notification sent successfully"),
    })
    @PostMapping("/notify")
    public ResponseEntity<Void> notifyClient(@RequestBody NotifyClientRequestDto notifyClientRequestDto) {
        messagingHandler.notifyClient(notifyClientRequestDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Verify an order code",
            description = "Checks if the provided order ID and code are valid."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Code verified successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "409", description = "Invalid code for the given order")
    })
    @GetMapping("/verify-code")
    public ResponseEntity<Void> verifyCode(
            @RequestParam Long orderId,
            @RequestParam String code) {
        messagingHandler.verifyCode(orderId, code);
        return ResponseEntity.ok().build();
    }
}
