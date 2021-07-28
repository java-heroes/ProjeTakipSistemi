package org.kodluyoruz.projetakipsistemi.service;

import com.github.dozermapper.core.Mapper;
import org.kodluyoruz.projetakipsistemi.core.exception.*;
import org.kodluyoruz.projetakipsistemi.core.utility.EmailServiceImpl;
import org.kodluyoruz.projetakipsistemi.dao.IPersonelDAO;
import org.kodluyoruz.projetakipsistemi.dao.ITakimDAO;
import org.kodluyoruz.projetakipsistemi.dao.IYoneticiDAO;
import org.kodluyoruz.projetakipsistemi.model.Personel;
import org.kodluyoruz.projetakipsistemi.model.Takim;
import org.kodluyoruz.projetakipsistemi.model.Yonetici;
import org.kodluyoruz.projetakipsistemi.model.dto.PersonelDTO;
import org.kodluyoruz.projetakipsistemi.model.dto.TakimDTO;
import org.kodluyoruz.projetakipsistemi.model.dto.YoneticiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonelService {
    @Autowired
    private IPersonelDAO personelDAO;
    @Autowired
    private Mapper dozerMapper;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private ITakimDAO takimDAO;

    public boolean kayit(Personel personel) throws UserAlreadyException {
        if (userExist(personel.getKullaniciAdi(),personel.getMail())){
            throw new UserAlreadyException(personel.getKullaniciAdi(),personel.getMail());
        }
        try{
            personelDAO.save(personel);
        }catch (Exception e){
            throw new ValidationException();
        }
        return true;
    }

    public List<PersonelDTO> getAll(){
        List<PersonelDTO> personelDTOS = new ArrayList<>();
        personelDAO.findAll().forEach(personel -> {
            PersonelDTO personelDTO = dozerMapper.map(personel,PersonelDTO.class);
            personelDTO.setTakimId(personel.getTakim().getId());
            personelDTOS.add(personelDTO);
        });
        return personelDTOS;
    }

    public PersonelDTO get(int id){
        Optional<Personel> personel = personelDAO.findById(id);
        if (!personel.isPresent()){
            throw new PersonelNotFoundException(id);
        }
        return dozerMapper.map(personel.get(),PersonelDTO.class);
    }


    public boolean update(Personel personel){
        Optional<Personel> getPersonel = personelDAO.findById(personel.getId());
        if (!getPersonel.isPresent()){
            throw new PersonelNotFoundException(personel.getId());
        }
        try{
            personelDAO.save(personel);
            return true;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public boolean delete(int id){
        Optional<Personel> personel = personelDAO.findById(id);
        if (!personel.isPresent()){
            throw new PersonelNotFoundException(id);
        }
        try {
            personelDAO.deleteById(id);
            return true;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public PersonelDTO login(String kullaniciAdi, String sifre) {
        Optional<Personel> personel = Optional.ofNullable(personelDAO.findByKullaniciAdiAndSifre(kullaniciAdi, sifre));
        if (!personel.isPresent()){
            throw new PersonelNotFoundException();
        }
        return dozerMapper.map(personel.get(),PersonelDTO.class);

    }

    public boolean forgotPassword(String mail){
        Optional<Personel> personel = Optional.ofNullable(personelDAO.findByMail(mail));
        if (!personel.isPresent()){
            throw new PersonelNotFoundException(mail);
        }
        return emailService.sendSimpleMessage(mail,"Şifremi Unuttum","Şifreniz: " + personel.get().getSifre());
    }

    public TakimDTO getTakim(PersonelDTO personelDTO){
        Optional<Personel> personel = personelDAO.findById(personelDTO.getId());
        if (!personel.isPresent()){
            throw new PersonelNotFoundException(personelDTO.getId());
        }
        Optional<Takim> takim = takimDAO.findById(personelDTO.getTakimId());
        if (!takim.isPresent()){
            throw new TakimNotFoundException(personelDTO.getTakimId());
        }
        try {
            return dozerMapper.map(takim.get(),TakimDTO.class);
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public boolean userExist(String kullaniciAdi, String mail){
        return personelDAO.findByKullaniciAdiOrMail(kullaniciAdi, mail) != null;
    }
}
