package DB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CandidatesOffersPK implements Serializable {
    private int candidateId;
    private int offerId;

    @Column(name = "CandidateID")
    @Id
    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    @Column(name = "OfferID")
    @Id
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
        CandidatesOffersPK that = (CandidatesOffersPK) o;
        return candidateId == that.candidateId &&
                offerId == that.offerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateId, offerId);
    }
}
