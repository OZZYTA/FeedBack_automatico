package com.restaurant.traceability_service.domain.model;

public class EmployeeEfficiency {

    private Long employeeId;
    private String employeeEmail;
    private double averageTimeInMinutes;
    private Integer totalOrders;

    public EmployeeEfficiency(Long employeeId, String employeeEmail, double averageTimeInMinutes, Integer totalOrders) {
        this.employeeId = employeeId;
        this.employeeEmail = employeeEmail;
        this.averageTimeInMinutes = averageTimeInMinutes;
        this.totalOrders = totalOrders;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public double getAverageTimeInMinutes() {
        return averageTimeInMinutes;
    }

    public void setAverageTimeInMinutes(double averageTimeInMinutes) {
        this.averageTimeInMinutes = averageTimeInMinutes;
    }

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }
}