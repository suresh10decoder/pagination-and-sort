package com.sort.page.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sort.page.entity.DataEntity;
import com.sort.page.exception.RecordNotFoundException;
import com.sort.page.service.DataService;

 
@RestController
@RequestMapping("/employees")
public class DataController
{
    @Autowired
    DataService service;
 
    @GetMapping("page")

    public ResponseEntity<List<DataEntity>> getAllEmployees(
                        @RequestParam(defaultValue = "0") Integer pageNo,
                        @RequestParam(defaultValue = "5") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy)
    {
        List<DataEntity> list = service.getAllEmployees(pageNo, pageSize, sortBy);
 
        return new ResponseEntity<List<DataEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<DataEntity> getEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        DataEntity entity = service.getEmployeeById(id);
 
        return new ResponseEntity<DataEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping("/{id}")
    public DataEntity createOrUpdateEmployee(@RequestBody DataEntity employee)
     {
  
        return service.createOrUpdateEmployee(employee);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteEmployeeById(id);
        return HttpStatus.FORBIDDEN;
    }
 
}