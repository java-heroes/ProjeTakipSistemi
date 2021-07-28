package org.kodluyoruz.projetakipsistemi.controller;

import org.kodluyoruz.projetakipsistemi.model.dto.TakimDTO;
import org.kodluyoruz.projetakipsistemi.service.TakimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/takim/")
public class TakimController {
    @Autowired
    private TakimService takimService;

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(takimService.getAll());
    }

    @GetMapping(value = "get")
    public ResponseEntity<?> get(@RequestParam int id){
        return ResponseEntity.ok(takimService.get(id));
    }

    @PostMapping(value = "add")
    public ResponseEntity<?> add(@RequestBody TakimDTO takimDTO){
        return ResponseEntity.ok(takimService.add(takimDTO));
    }

    @PutMapping(value = "update")
    public ResponseEntity<?> update(@RequestBody TakimDTO takimDTO){
        return ResponseEntity.ok(takimService.update(takimDTO));
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<?> delete(@RequestParam int id){
        return ResponseEntity.ok(takimService.delete(id));
    }
}
