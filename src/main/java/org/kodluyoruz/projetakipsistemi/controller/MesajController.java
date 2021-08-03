package org.kodluyoruz.projetakipsistemi.controller;

import org.kodluyoruz.projetakipsistemi.model.dto.MesajDTO;
import org.kodluyoruz.projetakipsistemi.service.MesajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/mesaj/")
public class MesajController {
    @Autowired
    private MesajService mesajService ;
    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll (){
        return ResponseEntity.ok(mesajService.getAll());
    }
    @PostMapping(value = "add")
    public ResponseEntity<?> add(@RequestBody MesajDTO mesajDto) {
        return ResponseEntity.ok(mesajService.add(mesajDto));
    }
    @PutMapping(value = "update")
    public ResponseEntity<?> update(@RequestBody MesajDTO mesajDto){
        return ResponseEntity.ok(mesajService.update(mesajDto));
    }
    @DeleteMapping(value = "delete")
    public ResponseEntity<?> delete(@RequestBody MesajDTO mesajDto){
        return ResponseEntity.ok(mesajService.delete(mesajDto));
    }
    @GetMapping(value = "get")
    public ResponseEntity<?> get(@RequestParam int id) {
        return ResponseEntity.ok(mesajService.get(id));

    }
}