package org.kodluyoruz.projetakipsistemi.service;

import com.github.dozermapper.core.Mapper;
import org.kodluyoruz.projetakipsistemi.dao.IMesajDao;
import org.kodluyoruz.projetakipsistemi.model.Mesaj;
import org.kodluyoruz.projetakipsistemi.model.dto.MesajDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MesajService {
    @Autowired
    private IMesajDao mesajDao;
    @Autowired
    private Mapper dozerMapper;
    public List<MesajDto> getAll () {
        List<MesajDto> mesajList= new ArrayList<>();
        mesajDao.findAll().forEach(mesaj -> {
            mesajList.add(dozerMapper.map(mesaj,MesajDto.class));
        });
        return mesajList ;
    }
    public MesajDto add (MesajDto mesajDto) {
        Mesaj mesaj = dozerMapper.map(mesajDto,Mesaj.class);
        mesajDao.save(mesaj);
        return mesajDto ;

    }



}
