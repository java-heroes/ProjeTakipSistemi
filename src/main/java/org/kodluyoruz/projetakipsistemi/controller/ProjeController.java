package org.kodluyoruz.projetakipsistemi.controller;

import org.kodluyoruz.projetakipsistemi.model.dto.ProjeDTO;
import org.kodluyoruz.projetakipsistemi.service.ProjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/proje/")
public class ProjeController {
    @Autowired
    private ProjeService projeService;

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(projeService.getAll());
    }

    @GetMapping(value = "get")
    public ResponseEntity<?> get(@RequestParam int id){
        return ResponseEntity.ok(projeService.get(id));
    }

    @PostMapping(value = "add")
    public ResponseEntity<?> add(@RequestBody ProjeDTO projeDTO){
        return ResponseEntity.ok(projeService.add(projeDTO));
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<?> delete(@RequestParam int id){
        return ResponseEntity.ok(projeService.delete(id));
    }

    @PutMapping(value = "update")
    public ResponseEntity<?> update(@RequestBody ProjeDTO projeDTO){
        return ResponseEntity.ok(projeService.update(projeDTO));
    }

}
