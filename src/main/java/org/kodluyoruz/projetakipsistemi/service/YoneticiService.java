package org.kodluyoruz.projetakipsistemi.service;

import com.github.dozermapper.core.Mapper;
import org.kodluyoruz.projetakipsistemi.core.exception.UserAlreadyException;
import org.kodluyoruz.projetakipsistemi.core.exception.ValidationException;
import org.kodluyoruz.projetakipsistemi.core.exception.YoneticiNotFoundException;
import org.kodluyoruz.projetakipsistemi.core.utility.EmailServiceImpl;
import org.kodluyoruz.projetakipsistemi.dao.IYoneticiDAO;
import org.kodluyoruz.projetakipsistemi.model.Yonetici;
import org.kodluyoruz.projetakipsistemi.model.dto.YoneticiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class YoneticiService {
    @Autowired
    private IYoneticiDAO yoneticiDAO;
    @Autowired
    private Mapper dozerMapper;
    @Autowired
    private EmailServiceImpl emailService;

    public boolean kayit(Yonetici yonetici) throws UserAlreadyException {
        if (userExist(yonetici.getKullaniciAdi(),yonetici.getMail())){
            throw new UserAlreadyException(yonetici.getKullaniciAdi(),yonetici.getMail());
        }
        try{
            yoneticiDAO.save(yonetici);
        }catch (Exception e){
            throw new ValidationException();
        }
        return true;
    }

    public List<YoneticiDTO> getAll(){
        List<YoneticiDTO> yoneticiDTOS = new ArrayList<>();
        yoneticiDAO.findAll().forEach(yonetici -> {
            yoneticiDTOS.add(dozerMapper.map(yonetici,YoneticiDTO.class));
        });
        return yoneticiDTOS;
    }

    public YoneticiDTO get(int id){
        Optional<Yonetici> yonetici = yoneticiDAO.findById(id);
        if (!yonetici.isPresent()){
            throw new YoneticiNotFoundException(id);
        }
        return dozerMapper.map(yonetici.get(),YoneticiDTO.class);
    }


    public boolean update(Yonetici yonetici){
        Optional<Yonetici> getYonetici = yoneticiDAO.findById(yonetici.getId());
        if (!getYonetici.isPresent()){
            throw new YoneticiNotFoundException(yonetici.getId());
        }
        try{
            yoneticiDAO.save(yonetici);
            return true;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public boolean delete(int id){
        Optional<Yonetici> yonetici = yoneticiDAO.findById(id);
        if (!yonetici.isPresent()){
            throw new YoneticiNotFoundException(id);
        }
        try {
            yoneticiDAO.deleteById(id);
            return true;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public YoneticiDTO login(String kullaniciAdi, String sifre) {
        Optional<Yonetici> yonetici = Optional.ofNullable(yoneticiDAO.findByKullaniciAdiAndSifre(kullaniciAdi, sifre));
        if (!yonetici.isPresent()){
            throw new YoneticiNotFoundException();
        }
        return dozerMapper.map(yonetici.get(),YoneticiDTO.class);

    }

    public boolean forgotPassword(String mail){
        Optional<Yonetici> yonetici = Optional.ofNullable(yoneticiDAO.findByMail(mail));
        if (!yonetici.isPresent()){
            throw new YoneticiNotFoundException(mail);
        }
        return emailService.sendSimpleMessage(mail,"Şifremi Unuttum","Şifreniz: " + yonetici.get().getSifre());
    }

    public boolean userExist(String kullaniciAdi, String mail){
        return yoneticiDAO.findByKullaniciAdiOrMail(kullaniciAdi, mail) != null;
    }
}