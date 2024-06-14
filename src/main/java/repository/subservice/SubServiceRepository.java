package repository.subservice;

import base.repository.BaseRepository;
import model.MainService;
import model.SubService;

import java.util.List;

public interface SubServiceRepository extends BaseRepository<SubService, Long> {

    List<SubService> findAll();

    List<SubService> findByMainService(MainService mainService);
    void removeById(Long mainServiceId);
    SubService findBySubserviceNameAndService(String subServiceName, MainService service);


}
