package DB;

import net.bytebuddy.build.Plugin;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.lang.reflect.AnnotatedWildcardType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Employers", schema = "dbo", catalog = "Jobs")
public class Employer {
    private int id;
    private String name;
    private List<Offer> offersList = new ArrayList<>();

    public Employer(){}
    public Employer(String name){
        this.name = name;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @OneToMany()
    @JoinColumn(name = "employerID")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Offer> getOffersList() {
        return offersList;
    }
    public void setOffersList(List<Offer> offersList) {
        this.offersList = offersList;
    }

    public boolean checkOffersLimit(List<Offer> list){
        int counter = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getActive().equals(true)) {
                counter++;
            }
        }
        if(counter<10){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer employer = (Employer) o;
        return id == employer.id &&
                Objects.equals(name, employer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

//    @Override
//    public String toString() {
//        return "Employer{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", offersList=" + offersList +
//                '}';
//    }
}
