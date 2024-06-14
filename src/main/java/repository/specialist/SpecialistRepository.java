package repository.specialist;

import base.repository.BaseRepository;
import enums.SpecialistStatus;
import model.*;

import java.util.List;
import java.util.Set;

public interface SpecialistRepository extends BaseRepository<Specialist, Long> {
    Specialist findByUsernameAndPassword(String username, String password);

    List<Specialist> findByStatusIn(List<SpecialistStatus> specialistStatusList);

    Specialist update(Specialist specialist, Long specialistId, Set<SubService> subServices);

    List<Specialist> findByStatus(SpecialistStatus status);

    Specialist updateSpecialistWithSubServices(Long specialistId, Set<SubService> subServices);

    void removeByUsername(String username);

    List<Specialist> findAll();

    void removeById(Long specialistId);

    List<Specialist> findBySubService(SubService subService);
    Specialist findByIdWithSubServices(Long id);

//    void addSpecialty(Long specialistId, Long specialtyId);
//
//
//    Set<Specialty> getSpecialties(Long specialistId);

}
