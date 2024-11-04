package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Universite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idUniversite;

    String nomUniversite;

    String adresse;

    @OneToOne(cascade = CascadeType.ALL)
    Foyer foyer;

    // You can remove these if they aren't needed
    // public Universite(long idUniversite, String nomUniversite) {
    //     this.idUniversite = idUniversite;
    //     this.nomUniversite = nomUniversite;
    // }

    // Optionally remove these as well if not needed
    // public void setId(long idUniversite) {
    //     this.idUniversite = idUniversite;
    // }

    // public void setNom(String nomUniversite) {
    //     this.nomUniversite = nomUniversite;
    // }
}
