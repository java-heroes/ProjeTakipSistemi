package org.kodluyoruz.projetakipsistemi.service;

import com.github.dozermapper.core.Mapper;
import org.hibernate.sql.Update;
import org.kodluyoruz.projetakipsistemi.core.exception.TeklifNotFoundException;
import org.kodluyoruz.projetakipsistemi.core.exception.ValidationException;
import org.kodluyoruz.projetakipsistemi.dao.IMusteriDAO;
import org.kodluyoruz.projetakipsistemi.dao.ITeklifDAO;
import org.kodluyoruz.projetakipsistemi.model.Musteri;
import org.kodluyoruz.projetakipsistemi.model.Teklif;
import org.kodluyoruz.projetakipsistemi.model.dto.MusteriDTO;
import org.kodluyoruz.projetakipsistemi.model.dto.TeklifDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeklifService {
    @Autowired
    private ITeklifDAO teklifDAO;
    @Autowired
    private IMusteriDAO musteriDAO;
    @Autowired
    private Mapper dozerMapper;

    public TeklifDTO add(Teklif teklif){
        try{
            teklifDAO.save(teklif);
            return dozerMapper.map(teklif,TeklifDTO.class);
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public List<TeklifDTO> getAll(){
        List<TeklifDTO> teklifDTOS = new ArrayList<>();
        try {
            teklifDAO.findAll().forEach(teklif -> {
                teklifDTOS.add(dozerMapper.map(teklif,TeklifDTO.class));
            });
            return teklifDTOS;
        }catch (Exception e){
            throw new TeklifNotFoundException();
        }
    }

    public TeklifDTO get(int id){
        Optional<Teklif> teklif = teklifDAO.findById(id);
        if (!teklif.isPresent()){
            throw new TeklifNotFoundException(id);
        }
        return dozerMapper.map(teklif.get(),TeklifDTO.class);
    }

    public TeklifDTO update(TeklifDTO teklifDTO){
        Optional<Teklif> teklif = teklifDAO.findById(teklifDTO.getId());
        if (!teklif.isPresent()){
            throw new TeklifNotFoundException(teklifDTO.getId());
        }
        try {
            teklif.get().setBaslik(teklifDTO.getBaslik());
            teklif.get().setAciklama(teklifDTO.getAciklama());
            teklif.get().setTeklifDegeri(teklifDTO.getTeklifDegeri());
            teklifDAO.save(teklif.get());
            return dozerMapper.map(teklif.get(),TeklifDTO.class);
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public TeklifDTO delete(TeklifDTO teklifDTO){
        Optional<Teklif> teklif = teklifDAO.findById(teklifDTO.getId());
        if (!teklif.isPresent()){
            throw new TeklifNotFoundException(teklifDTO.getId());
        }
        try {
            teklifDAO.delete(teklif.get());
            return teklifDTO;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public MusteriDTO teklifMusteri(TeklifDTO teklifDTO){
        Musteri musteri = musteriDAO.findByTeklifs(dozerMapper.map(teklifDTO,Teklif.class));
        return dozerMapper.map(musteri,MusteriDTO.class);
    }

}
