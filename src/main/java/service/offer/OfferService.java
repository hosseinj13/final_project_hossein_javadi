package service.offer;

import base.service.BaseService;
import model.Offer;
import model.Specialist;

import java.util.Optional;

public interface OfferService extends BaseService<Offer, Long> {
    Optional<Offer> findBySpecialist(Specialist specialist);

    void update(Offer selectedOffer, Specialist specialist);

    void deleteOffer(Offer selectedOffer);
}
