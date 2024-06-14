package service.specialist;

import base.service.BaseServiceImpl;
import enums.SpecialistStatus;
import exception.NotFoundException;
import model.Specialist;
import model.SubService;
import org.hibernate.SessionFactory;
import repository.specialist.SpecialistRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SpecialistServiceImpl extends BaseServiceImpl<Specialist, Long, SpecialistRepository>
        implements SpecialistService {
    public SpecialistServiceImpl(SpecialistRepository repository) {
        super(repository);
    }

    @Override
    public Specialist findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<Specialist> findByStatusIn(List<SpecialistStatus> specialistStatusList) {
        return repository.findByStatusIn(specialistStatusList);
    }

    public Specialist update(Specialist specialist, Long id, Set<SubService> newSubServices) {
        Specialist foundedSpecialist = repository.findById(id).orElseThrow(
                () -> new NotFoundException("specialist with id " + id + " not found")
        );
        Optional.ofNullable(specialist.getUsername()).ifPresent(foundedSpecialist::setUsername);
        Optional.ofNullable(specialist.getFirstName()).ifPresent(foundedSpecialist::setFirstName);
        Optional.ofNullable(specialist.getLastName()).ifPresent(foundedSpecialist::setLastName);
        Optional.ofNullable(specialist.getEmail()).ifPresent(foundedSpecialist::setEmail);
        Optional.ofNullable(specialist.getPhoneNumber()).ifPresent(foundedSpecialist::setPhoneNumber);
        Optional.of(specialist.getYearsOfExperience()).ifPresent(foundedSpecialist::setYearsOfExperience);
        Optional.of(specialist.getProfilePicture()).ifPresent(foundedSpecialist::setProfilePicture);
        Optional.of(specialist.getStatus()).ifPresent(foundedSpecialist::setStatus);
        Optional.ofNullable(newSubServices).ifPresent(specialist::setSubServices);
        Optional.ofNullable(specialist.getPassword()).ifPresent(foundedSpecialist::setPassword);
        return repository.saveOrUpdate(foundedSpecialist);
    }

    @Override
    public List<Specialist> findByStatus(SpecialistStatus status) {
        return repository.findByStatus(status);
    }

    @Override
    public Specialist updateSpecialistWithSubServices(Long specialistId, Set<SubService> subServices) {
        Specialist specialist = repository.findById(specialistId)
                .orElseThrow(() -> new NotFoundException("Specialist not found"));
        specialist.setSubServices(subServices);
        return repository.saveOrUpdate(specialist);
    }

    @Override
    public void removeByUsername(String username) {
        repository.removeByUsername(username);
    }

    @Override
    public List<Specialist> findAll() {
        return repository.findAll();
    }

    @Override
    public void removeById(Long specialistId) {
        repository.removeById(specialistId);
    }

    @Override
    public List<Specialist> findBySubService(SubService subService) {
        return repository.findBySubService(subService);
    }

    @Override
    public Specialist findByIdWithSubServices(Long id) {
        return repository.findByIdWithSubServices(id);
    }

//    @Override
//    public void addSpecialty(Long specialistId, Long specialtyId) {
//        repository.addSpecialty(specialistId, specialtyId);
//    }
//
//    @Override
//    public Set<Specialty> getSpecialties(Long specialistId) {
//        return repository.getSpecialties(specialistId);
//    }
//

}
