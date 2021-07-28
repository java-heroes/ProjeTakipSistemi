package org.kodluyoruz.projetakipsistemi.controller;

import org.kodluyoruz.projetakipsistemi.core.exception.UserAlreadyException;
import org.kodluyoruz.projetakipsistemi.model.Personel;
import org.kodluyoruz.projetakipsistemi.model.Yonetici;
import org.kodluyoruz.projetakipsistemi.model.dto.PersonelDTO;
import org.kodluyoruz.projetakipsistemi.service.PersonelService;
import org.kodluyoruz.projetakipsistemi.service.YoneticiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/personel/")
public class PersonelController {

    @Autowired
    PersonelService personelService;

    @PostMapping(value = "kayit")
    public ResponseEntity<?> kayit(@RequestBody Personel personel) throws UserAlreadyException {
        return ResponseEntity.ok(personelService.kayit(personel));
    }

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(personelService.getAll());
    }

    @GetMapping(value = "get")
    public ResponseEntity<?> get(@RequestParam int id){
        return ResponseEntity.ok(personelService.get(id));
    }

    @PutMapping(value = "update")
    public ResponseEntity<?> update(@RequestBody Personel personel){
        return ResponseEntity.ok(personelService.update(personel));
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<?> delete(@RequestParam int id){
        return ResponseEntity.ok(personelService.delete(id));
    }

    @GetMapping(value = "login")
    public ResponseEntity<?> login(@RequestParam("kullaniciAdi") String kullaniciAdi, @RequestParam("sifre") String sifre){
        return  ResponseEntity.ok(personelService.login(kullaniciAdi, sifre));
    }

    @GetMapping(value = "forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestParam String mail){
        return ResponseEntity.ok(personelService.forgotPassword(mail));
    }

    @GetMapping(value = "getTakim")
    public ResponseEntity<?> getTakim(@RequestBody PersonelDTO personelDTO){
        return ResponseEntity.ok(personelService.getTakim(personelDTO));
    }


}