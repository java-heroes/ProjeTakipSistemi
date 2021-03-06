package org.kodluyoruz.projetakipsistemi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonelDTO extends KullaniciDTO{
    private List<GorevDTO> gorevs;
    private int takimId;
}
