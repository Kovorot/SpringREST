package ru.andrey.vasilev.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.andrey.vasilev.spring.rest.entity.Employee;
import ru.andrey.vasilev.spring.rest.exception.handling.NoSuchEmployeeException;
import ru.andrey.vasilev.spring.rest.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with id = " + id + " in Database");
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with id = " + id + " in Database");
        }
        employeeService.deleteEmployee(id);
        return "Employee with id = " + id + " was deleted";
    }

    @Autowired
    private void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
