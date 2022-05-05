package connect.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="organizations")
public class Organization {
    @Id
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "image", nullable = false)
    private String image;
    @Column(name = "contact_person_id", nullable = false)
    private String contactPersonId;
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
    private Integer employeesCount;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "creation_date", nullable = false)
    private Timestamp creationDate;
    @Column(name = "update_date", nullable = false)
    private Timestamp updateDate;
    @Column(name = "description", nullable = false)
    private String description;

    public Organization(){}

    public Organization(String id, String name, String image, String contactPersonId, String director, String inn, String ogrn, String kpp, String okved, String okpo, String bank, String bik, String phone, String email, Integer employeesCount, String address, Timestamp creationDate, Timestamp updateDate, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.contactPersonId = contactPersonId;
        this.director = director;
        this.inn = inn;
        this.ogrn = ogrn;
        this.kpp = kpp;
        this.okved = okved;
        this.okpo = okpo;
        this.bank = bank;
        this.bik = bik;
        this.phone = phone;
        this.email = email;
        this.employeesCount = employeesCount;
        this.address = address;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(image, that.image) && Objects.equals(contactPersonId, that.contactPersonId) && Objects.equals(director, that.director) && Objects.equals(inn, that.inn) && Objects.equals(ogrn, that.ogrn) && Objects.equals(kpp, that.kpp) && Objects.equals(okved, that.okved) && Objects.equals(okpo, that.okpo) && Objects.equals(bank, that.bank) && Objects.equals(bik, that.bik) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(employeesCount, that.employeesCount) && Objects.equals(address, that.address) && Objects.equals(creationDate, that.creationDate) && Objects.equals(updateDate, that.updateDate) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image, contactPersonId, director, inn, ogrn, kpp, okved, okpo, bank, bik, phone, email, employeesCount, address, creationDate, updateDate, description);
    }
}
