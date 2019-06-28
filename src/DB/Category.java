package DB;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Categories", schema = "dbo", catalog = "Jobs")
public class Category {
    private int id;
    private String name;
    private List<Offer> offersList = new ArrayList<>();

    public Category(){}
    public Category(String name){
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
    @JoinColumn(name = "categoryID")
    //@LazyCollection(LazyCollectionOption.FALSE)
    public List<Offer> getOffersList() {
        return offersList;
    }
    public void setOffersList(List<Offer> offersList) {
        this.offersList = offersList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category that = (Category) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

//    @Override
//    public String toString() {
//        return "Category{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", offersList=" + offersList +
//                '}';
//    }
}
