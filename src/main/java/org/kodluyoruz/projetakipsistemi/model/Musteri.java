package org.kodluyoruz.projetakipsistemi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "musteri")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Musteri extends Kullanici{

    @OneToOne(mappedBy = "musteri")
    private ProjeTaslagi projeTaslagi;

    @OneToMany(targetEntity=Teklif.class, mappedBy="musteri",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Teklif> teklifs = new ArrayList<>();

}
