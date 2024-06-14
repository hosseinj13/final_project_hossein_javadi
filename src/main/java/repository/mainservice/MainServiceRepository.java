package repository.mainservice;

import base.repository.BaseRepository;
import model.MainService;
import model.SubService;

import java.util.List;

public interface MainServiceRepository extends BaseRepository<MainService, Long > {

    List<MainService> findAll();
    void removeById(Long mainServiceId);

    MainService findByServiceName(String serviceName);



}
