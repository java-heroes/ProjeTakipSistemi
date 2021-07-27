package org.kodluyoruz.projetakipsistemi.controller;

import org.kodluyoruz.projetakipsistemi.model.dto.ProjeTaslagiDTO;
import org.kodluyoruz.projetakipsistemi.service.ProjeTaslagiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/proje_taslagi/")
public class ProjeTaslagiController {
    @Autowired
    private ProjeTaslagiService projeTaslagiService;

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody ProjeTaslagiDTO projeTaslagiDTO){
        return ResponseEntity.ok(projeTaslagiService.add(projeTaslagiDTO));
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(projeTaslagiService.getAll());
    }

    @GetMapping("get")
    public ResponseEntity<?> get(@RequestParam int id){
        return ResponseEntity.ok(projeTaslagiService.get(id));
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody ProjeTaslagiDTO projeTaslagiDTO){
        return ResponseEntity.ok(projeTaslagiService.update(projeTaslagiDTO));
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam int id){
        return ResponseEntity.ok(projeTaslagiService.delete(id));
    }

    @GetMapping("getByMusteri")
    public ResponseEntity<?> getByMusteri(@RequestParam int id){
        return ResponseEntity.ok(projeTaslagiService.getByMusteri(id));
    }
}
