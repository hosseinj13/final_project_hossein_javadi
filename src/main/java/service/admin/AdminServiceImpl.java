package service.admin;

import base.service.BaseServiceImpl;
import model.Admin;
import org.hibernate.SessionFactory;
import repository.admin.AdminRepository;

public class AdminServiceImpl  extends BaseServiceImpl<Admin, Long, AdminRepository>
        implements AdminService {
    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }

    @Override
    public Admin findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }

   /* @Override
    public void approveSpecialties(Long adminId, Long specialistId, String requiredPermission) {
        repository.approveSpecialties(adminId, specialistId, requiredPermission);
    }

    @Override
    public boolean adminHasPermission(Admin admin, String requiredPermission) {
        return repository.adminHasPermission(admin, requiredPermission);
    }*/
}
