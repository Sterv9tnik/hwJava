package entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="organizations")
public class Organization {
    @Id
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "image", nullable = false)
    private String image;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private String contact_person_id;
    @Column(name = "director", nullable = false)
    private String director;
    @Column(name = "inn", nullable = false)
    private String inn;
    @Column(name = "ogrn", nullable = false)
    private String ogrn;
    @Column(name = "kpp", nullable = false)
    private String kpp;
    @Column(name = "okved", nullable = false)
    private String okved;
    @Column(name = "okpo", nullable = false)
    private String okpo;
    @Column(name = "bank", nullable = false)
    private String bank;
    @Column(name = "bik", nullable = false)
    private String bik;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "employees_count", nullable = false)
    private Integer employees_count;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "creation_date", nullable = false)
    private Timestamp creation_date;
    @Column(name = "update_date", nullable = false)
    private Timestamp update_date;
    @Column(name = "description", nullable = false)
    private String description;

    public Organization(){}

}
