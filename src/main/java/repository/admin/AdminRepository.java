package repository.admin;

import base.repository.BaseRepository;
import model.Admin;

import java.util.Optional;

public interface AdminRepository extends BaseRepository<Admin, Long> {

    Admin findByUsernameAndPassword(String username, String password);
   // void approveSpecialties(Long adminId, Long specialistId, String requiredPermission);
  //  boolean adminHasPermission(Admin admin, String requiredPermission);
}
