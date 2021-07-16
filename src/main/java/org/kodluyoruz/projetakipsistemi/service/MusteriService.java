package org.kodluyoruz.projetakipsistemi.service;

import com.github.dozermapper.core.Mapper;
import org.kodluyoruz.projetakipsistemi.core.exception.MusteriNotFoundException;
import org.kodluyoruz.projetakipsistemi.core.exception.ValidationException;
import org.kodluyoruz.projetakipsistemi.dao.IMusteriDAO;
import org.kodluyoruz.projetakipsistemi.model.Musteri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusteriService {
    private IMusteriDAO musteriDAO;

    @Autowired
    private Mapper dozerMapper;

    @Autowired
    public MusteriService(IMusteriDAO musteriDAO) {
        this.musteriDAO = musteriDAO;
    }

    public boolean kayit(Musteri musteri){
        try{
            musteriDAO.save(musteri);
        }catch (Exception e){
            throw new ValidationException();
        }
        return true;
    }

    public List<Musteri> getAll(){
        return musteriDAO.findAll();
    }

    public Musteri get(int id){
        Optional<Musteri> getMusteri = musteriDAO.findById(id);
        if (!getMusteri.isPresent()){
            throw new MusteriNotFoundException(id);
        }
        return getMusteri.get();
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

}
