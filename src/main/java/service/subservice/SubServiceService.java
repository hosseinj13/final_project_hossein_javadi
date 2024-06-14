package service.subservice;

import base.service.BaseService;
import model.MainService;
import model.SubService;

import java.util.List;

public interface SubServiceService extends BaseService<SubService, Long> {

    List<SubService> findAll();
    List<SubService> findByMainService(MainService mainService);
    void removeById(Long subserviceId);


    SubService findBySubserviceNameAndService(String subServiceName, MainService service);

}
