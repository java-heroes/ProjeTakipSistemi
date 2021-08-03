package org.kodluyoruz.projetakipsistemi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "mesaj")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mesaj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(name = "baslik")
    private String baslik;
    @Column(name = "mesajin_icerigi")
    private String mesajinIcerigi;
    @OneToOne
    @JoinColumn(name = "alıcıId")
    private Kullanici alici;
    @OneToOne
    @JoinColumn(name = "gönderenId")
    private Kullanici gonderen;






}
