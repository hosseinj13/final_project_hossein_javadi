package service.subservice;

import base.service.BaseServiceImpl;
import model.MainService;
import model.SubService;
import org.hibernate.SessionFactory;
import repository.subservice.SubServiceRepository;

import java.util.List;

public class SubServiceServiceImpl extends BaseServiceImpl<SubService, Long, SubServiceRepository>
        implements SubServiceService {
    public SubServiceServiceImpl(SubServiceRepository repository) {
        super(repository);
    }

    @Override
    public List<SubService> findAll() {
        return repository.findAll();
    }

    @Override
    public List<SubService> findByMainService(MainService mainService) {
        return repository.findByMainService(mainService);
    }

    @Override
    public void removeById(Long subserviceId) {
        repository.removeById(subserviceId);
    }

    @Override
    public SubService findBySubserviceNameAndService(String subServiceName, MainService service) {
        return repository.findBySubserviceNameAndService(subServiceName, service);
    }
}
