package DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Candidates_Offers", schema = "dbo", catalog = "Jobs")
@IdClass(CandidatesOffersPK.class)
public class CandidatesOffers {
    private int candidateId;
    private int offerId;

    @Id
    @Column(name = "CandidateID")
    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    @Id
    @Column(name = "OfferID")
    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidatesOffers that = (CandidatesOffers) o;
        return candidateId == that.candidateId &&
                offerId == that.offerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateId, offerId);
    }
}
