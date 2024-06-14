package service.admin;

import base.service.BaseService;
import model.Admin;

public interface AdminService extends BaseService<Admin, Long> {

    Admin findByUsernameAndPassword(String username, String password);

   // void approveSpecialties(Long adminId, Long specialistId, String requiredPermission);
    //boolean adminHasPermission(Admin admin, String requiredPermission);

}
