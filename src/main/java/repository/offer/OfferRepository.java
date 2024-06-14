package repository.offer;

import base.repository.BaseRepository;
import model.Offer;
import model.Specialist;

import java.util.Optional;

public interface OfferRepository extends BaseRepository<Offer, Long> {

    Optional<Offer> findBySpecialist(Specialist specialist);
    void update(Offer selectedOffer, Specialist specialist);

    void deleteOffer(Offer selectedOffer);


}
