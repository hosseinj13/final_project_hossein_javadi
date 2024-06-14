package service.offer;

import base.service.BaseServiceImpl;
import model.Offer;
import model.Specialist;
import org.hibernate.SessionFactory;
import repository.offer.OfferRepository;

import java.util.Optional;

public class OfferServiceImpl extends BaseServiceImpl<Offer, Long, OfferRepository>
        implements OfferService {
    public OfferServiceImpl(OfferRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Offer> findBySpecialist(Specialist specialist) {
        return repository.findBySpecialist(specialist);
    }

    @Override
    public void update(Offer selectedOffer, Specialist specialist) {
        repository.update(selectedOffer, specialist);
    }

    @Override
    public void deleteOffer(Offer selectedOffer) {
        repository.deleteOffer(selectedOffer);
    }
}
