package service.mainservice;

import base.service.BaseService;
import model.MainService;
import model.SubService;

import java.util.List;

public interface MainServiceService extends BaseService<MainService, Long> {

    List<MainService> findAll();

    void removeById(Long mainServiceId);


    MainService findByServiceName(String serviceName);
}
