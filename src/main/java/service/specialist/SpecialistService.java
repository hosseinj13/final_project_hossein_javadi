package service.specialist;

import base.service.BaseService;
import enums.SpecialistStatus;
import model.Specialist;
import model.SubService;

import java.util.List;
import java.util.Set;

public interface SpecialistService extends BaseService<Specialist, Long> {

    Specialist findByUsernameAndPassword(String username, String password);

    List<Specialist> findByStatusIn(List<SpecialistStatus> specialistStatusList);

    Specialist update(Specialist specialist, Long id, Set<SubService> newSubServices);

    List<Specialist> findByStatus(SpecialistStatus status);

    Specialist updateSpecialistWithSubServices(Long specialistId, Set<SubService> subServices);

    void removeByUsername(String username);

    List<Specialist> findAll();


    void removeById(Long specialistId);

    List<Specialist> findBySubService(SubService subService);

    Specialist findByIdWithSubServices(Long id);

//    void addSpecialty(Long specialistId, Long specialtyId);
//
//    Set<Specialty> getSpecialties(Long specialistId);

}
