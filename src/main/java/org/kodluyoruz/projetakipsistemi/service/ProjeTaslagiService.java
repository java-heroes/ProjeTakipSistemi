package org.kodluyoruz.projetakipsistemi.service;

import com.github.dozermapper.core.Mapper;
import org.kodluyoruz.projetakipsistemi.core.exception.MusteriNotFoundException;
import org.kodluyoruz.projetakipsistemi.core.exception.ProjeTaslagiNotFoundException;
import org.kodluyoruz.projetakipsistemi.core.exception.ValidationException;
import org.kodluyoruz.projetakipsistemi.dao.IMusteriDAO;
import org.kodluyoruz.projetakipsistemi.dao.IProjeTaslagiDAO;
import org.kodluyoruz.projetakipsistemi.model.Musteri;
import org.kodluyoruz.projetakipsistemi.model.ProjeTaslagi;
import org.kodluyoruz.projetakipsistemi.model.dto.MusteriDTO;
import org.kodluyoruz.projetakipsistemi.model.dto.ProjeTaslagiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjeTaslagiService {
    @Autowired
    private IProjeTaslagiDAO projeTaslagiDAO;
    @Autowired
    private Mapper dozerMapper;
    @Autowired
    private IMusteriDAO musteriDAO;

    public ProjeTaslagiDTO add(ProjeTaslagiDTO projeTaslagiDTO){
        ProjeTaslagi projeTaslagi = dozerMapper.map(projeTaslagiDTO,ProjeTaslagi.class);
        try{
            Optional<Musteri> musteri = musteriDAO.findById(projeTaslagiDTO.getMusteriId());
            if (!musteri.isPresent()){
                throw new MusteriNotFoundException(projeTaslagiDTO.getMusteriId());
            }
            projeTaslagi.setMusteri(musteri.get());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            projeTaslagiDAO.save(projeTaslagi);
            return projeTaslagiDTO;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public List<ProjeTaslagiDTO> getAll(){
       try {
           List<ProjeTaslagi> projeTaslagiList = projeTaslagiDAO.findAll();
           List<ProjeTaslagiDTO> projeTaslagiDTOList = new ArrayList<>();
           projeTaslagiList.forEach(projeTaslagi -> {
               ProjeTaslagiDTO projeTaslagiDTO = dozerMapper.map(projeTaslagi,ProjeTaslagiDTO.class);
               projeTaslagiDTO.setMusteriId(projeTaslagi.getMusteri().getId());
               projeTaslagiDTOList.add(projeTaslagiDTO);
           });
           return projeTaslagiDTOList;
       }catch (Exception e){
           throw new ValidationException();
       }
    }

    public ProjeTaslagiDTO get(int id){
        Optional<ProjeTaslagi> projeTaslagi = projeTaslagiDAO.findById(id);
        if (!projeTaslagi.isPresent()){
            throw new ProjeTaslagiNotFoundException();
        }
        ProjeTaslagiDTO projeTaslagiDTO =  dozerMapper.map(projeTaslagi.get(),ProjeTaslagiDTO.class);
        projeTaslagiDTO.setMusteriId(projeTaslagi.get().getMusteri().getId());
        return projeTaslagiDTO;
    }

    public ProjeTaslagiDTO update(ProjeTaslagiDTO projeTaslagiDTO){
        Optional<ProjeTaslagi> projeTaslagi = projeTaslagiDAO.findById(projeTaslagiDTO.getId());
        if (!projeTaslagi.isPresent()){
            throw new ProjeTaslagiNotFoundException(projeTaslagiDTO.getId());
        }
        Optional<Musteri> musteri = musteriDAO.findById(projeTaslagiDTO.getMusteriId());
        if (!musteri.isPresent()){
            throw new MusteriNotFoundException(projeTaslagiDTO.getMusteriId());
        }
        try {
            projeTaslagi.get().setTaslak(projeTaslagiDTO.getTaslak());
            projeTaslagi.get().setAsama(projeTaslagiDTO.getAsama());
            projeTaslagi.get().setMusteri(musteri.get());
            projeTaslagiDAO.save(projeTaslagi.get());
            return projeTaslagiDTO;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public ProjeTaslagiDTO delete(int id){
        Optional<ProjeTaslagi> projeTaslagi = projeTaslagiDAO.findById(id);
        if (!projeTaslagi.isPresent()){
            throw new ProjeTaslagiNotFoundException();
        }
        try {
            projeTaslagiDAO.delete(projeTaslagi.get());
            ProjeTaslagiDTO projeTaslagiDTO =  dozerMapper.map(projeTaslagi.get(),ProjeTaslagiDTO.class);
            projeTaslagiDTO.setMusteriId(projeTaslagi.get().getMusteri().getId());
            return projeTaslagiDTO;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public MusteriDTO getByMusteri(int id){
        ProjeTaslagiDTO projeTaslagiDTO = get(id);
        Optional<Musteri> musteri = musteriDAO.findById(projeTaslagiDTO.getMusteriId());
        if (!musteri.isPresent()){
            throw new MusteriNotFoundException(projeTaslagiDTO.getMusteriId());
        }
        return dozerMapper.map(musteri.get(),MusteriDTO.class);
    }
}
