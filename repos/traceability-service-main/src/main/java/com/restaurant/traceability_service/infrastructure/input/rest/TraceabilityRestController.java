package com.restaurant.traceability_service.infrastructure.input.rest;

import com.restaurant.traceability_service.application.dto.*;
import com.restaurant.traceability_service.application.handler.IAuthenticationHandler;
import com.restaurant.traceability_service.application.handler.ITraceabilityHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("traceability")
@RequiredArgsConstructor
@Validated
public class TraceabilityRestController {

    private final ITraceabilityHandler traceabilityHandler;
    private final IAuthenticationHandler authenticationHandler;

    @Operation(
            summary = "Create order traceability",
            description = "Registers the traceability of an order in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Traceability created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping
    public ResponseEntity<Void> createOrderTraceability(@RequestBody TraceabilityDtoRequest traceabilityRequestDto) {
        traceabilityHandler.creteOrderTraceability(traceabilityRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Update order state",
            description = "Updates the state of an existing order. If the order is canceled or delivered, additional operations may be triggered."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order state updated successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "409", description = "Order cannot be updated due to business rules")
    })
    @PostMapping("/update-state")
    public ResponseEntity<Void> updateOrderState(@RequestBody StateUpdateDto stateUpdateDto) {
        traceabilityHandler.updateOrderState(stateUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Get client traceability log",
            description = "Retrieves the traceability log of the authenticated client, providing a history of order status changes."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved traceability log"),
    })
    @GetMapping
    public ResponseEntity<List<TraceabilityDtoResponse>> getClientLog() {
        Long clientId = authenticationHandler.getAuthenticatedUserId();
        return ResponseEntity.ok(traceabilityHandler.getClientLog(clientId));
    }

    @Operation(
            summary = "Get order efficiency log",
            description = "Retrieves the efficiency log of orders, showing the time taken for each order to be processed."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved order efficiency log")
    })
    @GetMapping("/efficiency")
    public ResponseEntity<List<OrderEfficiencyDtoResponse>> getOrderEfficiency(){
        return ResponseEntity.ok(traceabilityHandler.getOrderEfficiency());
    }

    @Operation(
            summary = "Get employee efficiency",
            description = "Calculates and retrieves the average order processing time for each employee."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved employee efficiency data")
    })
    @GetMapping("/employee/efficiency")
    public ResponseEntity<List<EmployeeEfficiencyDtoResponse>> getEmployeeEfficiency() {
        List<EmployeeEfficiencyDtoResponse> employeeEfficiencyList = traceabilityHandler.getEmployeeEfficiency();
        return ResponseEntity.ok(employeeEfficiencyList);
    }
}
