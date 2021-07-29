package org.kodluyoruz.projetakipsistemi.controller;

import org.kodluyoruz.projetakipsistemi.model.dto.GorevDTO;
import org.kodluyoruz.projetakipsistemi.service.GorevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/gorev/")
public class GorevController {
    @Autowired
    private GorevService gorevService;

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(gorevService.getAll());
    }

    @GetMapping(value = "get")
    public ResponseEntity<?> get(@RequestParam int id){
        return ResponseEntity.ok(gorevService.get(id));
    }

    @PostMapping(value = "add")
    public ResponseEntity<?> add(@RequestBody GorevDTO gorevDTO){
        return ResponseEntity.ok(gorevService.add(gorevDTO));
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<?> delete(@RequestParam int id){
        return ResponseEntity.ok(gorevService.delete(id));
    }

    @PutMapping(value = "update")
    public ResponseEntity<?> update(@RequestBody GorevDTO gorevDTO){
        return ResponseEntity.ok(gorevService.update(gorevDTO));
    }

}
