package org.kodluyoruz.projetakipsistemi.service;

import com.github.dozermapper.core.Mapper;
import org.kodluyoruz.projetakipsistemi.exception.MusteriNotFoundException;
import org.kodluyoruz.projetakipsistemi.core.exception.UserAlreadyException;
import org.kodluyoruz.projetakipsistemi.core.exception.ValidationException;
import org.kodluyoruz.projetakipsistemi.core.utility.EmailServiceImpl;
import org.kodluyoruz.projetakipsistemi.dao.IMusteriDAO;
import org.kodluyoruz.projetakipsistemi.dao.IProjeTaslagiDAO;
import org.kodluyoruz.projetakipsistemi.dao.ITeklifDAO;
import org.kodluyoruz.projetakipsistemi.model.Musteri;
import org.kodluyoruz.projetakipsistemi.model.dto.MusteriDTO;
import org.kodluyoruz.projetakipsistemi.model.dto.ProjeTaslagiDTO;
import org.kodluyoruz.projetakipsistemi.model.dto.TeklifDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MusteriService {
    private IMusteriDAO musteriDAO;
    private ITeklifDAO teklifDAO;
    private IProjeTaslagiDAO projeTaslagiDAO;
    @Autowired
    private Mapper dozerMapper;
    @Autowired
    private EmailServiceImpl emailService;


    @Autowired
    public MusteriService(IMusteriDAO musteriDAO, ITeklifDAO teklifDAO, IProjeTaslagiDAO projeTaslagiDAO) {
        this.musteriDAO = musteriDAO;
        this.teklifDAO = teklifDAO;
        this.projeTaslagiDAO = projeTaslagiDAO;
    }

    public boolean kayit(Musteri musteri) throws UserAlreadyException {
        if (userExist(musteri.getKullaniciAdi(),musteri.getMail())){
            throw new UserAlreadyException(musteri.getKullaniciAdi(),musteri.getMail());
        }
        try{
            musteriDAO.save(musteri);
        }catch (Exception e){
            throw new ValidationException();
        }
        return true;
    }

    public List<MusteriDTO> getAll(){
        List<MusteriDTO> musteriDTOS = new ArrayList<>();
        musteriDAO.findAll().forEach(musteri -> {
            musteriDTOS.add(dozerMapper.map(musteri,MusteriDTO.class));
        });
        return musteriDTOS;
    }

    public MusteriDTO get(int id){
        Optional<Musteri> getMusteri = musteriDAO.findById(id);
        if (!getMusteri.isPresent()){
            throw new MusteriNotFoundException(id);
        }
        return dozerMapper.map(getMusteri.get(),MusteriDTO.class);
    }


    public boolean update(Musteri musteri){
        Optional<Musteri> getMusteri = musteriDAO.findById(musteri.getId());
        if (!getMusteri.isPresent()){
            throw new MusteriNotFoundException(musteri.getId());
        }
        try{
            musteriDAO.save(musteri);
            return true;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public boolean delete(int id){
        Optional<Musteri> getMusteri = musteriDAO.findById(id);
        if (!getMusteri.isPresent()){
            throw new MusteriNotFoundException(id);
        }
        try {
            musteriDAO.deleteById(id);
            return true;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public MusteriDTO login(String kullaniciAdi, String sifre) {
        Optional<Musteri> getMusteri = Optional.ofNullable(musteriDAO.findByKullaniciAdiAndSifre(kullaniciAdi, sifre));
        if (!getMusteri.isPresent()){
            throw new MusteriNotFoundException();
        }
        return dozerMapper.map(getMusteri.get(),MusteriDTO.class);

    }

    public boolean forgotPassword(String mail){
        Optional<Musteri> getMusteri = Optional.ofNullable(musteriDAO.findByMail(mail));
        if (!getMusteri.isPresent()){
            throw new MusteriNotFoundException(mail);
        }
        return emailService.sendSimpleMessage(mail,"Şifremi Unuttum","Şifreniz: " + getMusteri.get().getSifre());
    }

    public List<TeklifDTO> musteriTeklifs(int id){
        List<TeklifDTO> teklifDTOS = new ArrayList<>();
        teklifDAO.findAllByMusteri_Id(id).forEach(teklif -> {
            teklifDTOS.add(dozerMapper.map(teklif,TeklifDTO.class));
        });
        return teklifDTOS;
    }

    public List<ProjeTaslagiDTO> musteriProjeTaslaks(int id){
        List<ProjeTaslagiDTO> projeTaslagiDTOS = new ArrayList<>();
        projeTaslagiDAO.findAllByMusteri_Id(id).forEach(projeTaslagi -> {
            projeTaslagiDTOS.add(dozerMapper.map(projeTaslagi,ProjeTaslagiDTO.class));
        });
        return projeTaslagiDTOS;
    }

    public boolean userExist(String kullaniciAdi, String mail){
        return musteriDAO.findByKullaniciAdiOrMail(kullaniciAdi, mail) != null;
    }

}
