package service.mainservice;

import base.service.BaseServiceImpl;
import model.MainService;
import model.SubService;
import org.hibernate.SessionFactory;
import repository.mainservice.MainServiceRepository;

import java.util.List;

public class MainServiceServiceImpl extends BaseServiceImpl<MainService, Long, MainServiceRepository>
        implements MainServiceService {
    public MainServiceServiceImpl(MainServiceRepository repository) {
        super(repository);
    }

    @Override
    public List<MainService> findAll() {
        return repository.findAll();
    }

    @Override
    public void removeById(Long mainServiceId) {
        repository.removeById(mainServiceId);
    }

    @Override
    public MainService findByServiceName(String serviceName) {
        return repository.findByServiceName(serviceName);
    }
}
