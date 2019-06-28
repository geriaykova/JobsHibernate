package DB;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Offers", schema = "dbo", catalog = "Jobs")
public class Offer {
    private int id;
    private String name;
    private String description;
    private Boolean isActive;
    private Category category;
    private Employer employer;
    private List<Candidate> candidatesList = new ArrayList<>();

    public Offer(){}
    public Offer(Employer employer,
                 String name,
                 String description,
                 Category category){
            this.name = name;
            this.description = description;
            this.employer = employer;
            this.isActive = true;
            this.category = category;
            employer.getOffersList().add(this);
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "isActive")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @ManyToOne()
    @JoinColumn(name = "categoryID")
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne()
    @JoinColumn(name = "employerID")
    public Employer getEmployer() {
        return employer;
    }
    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Candidates_Offers",
            joinColumns = {@JoinColumn(name = "offerID")},
            inverseJoinColumns = {@JoinColumn(name = "candidateID")}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Candidate> getCandidatesList() {
        return candidatesList;
    }
    public void setCandidatesList(List<Candidate> candidatesList) {
        this.candidatesList = candidatesList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return id == offer.id &&
                Objects.equals(name, offer.name) &&
                Objects.equals(isActive, offer.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isActive);
    }

//    @Override
//    public String toString() {
//        return "Offer{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", isActive=" + isActive +
//                ", category=" + category +
//                ", employersList=" + employersList +
//                ", candidatesList=" + candidatesList +
//                '}';
//    }
}
