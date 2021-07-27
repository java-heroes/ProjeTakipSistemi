package org.kodluyoruz.projetakipsistemi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "musteri")
@SQLDelete(sql = "UPDATE kullanici SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Musteri extends Kullanici{

    @OneToMany(targetEntity=ProjeTaslagi.class, mappedBy="musteri",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProjeTaslagi> projeTaslagis = new ArrayList<>();

    @OneToMany(targetEntity=Teklif.class, mappedBy="musteri",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Teklif> teklifs = new ArrayList<>();

}
