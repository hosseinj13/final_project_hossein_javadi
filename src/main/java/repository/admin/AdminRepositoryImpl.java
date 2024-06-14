package repository.admin;

import base.repository.BaseRepositoryImpl;
import model.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Objects;

public class AdminRepositoryImpl extends BaseRepositoryImpl<Admin, Long>
        implements AdminRepository {
    public AdminRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Admin> getEntityClass() {
        return Admin.class;
    }

    public static List<Admin> ADMINS;

    //database
    static {
        ADMINS = List.of(
                new Admin(1L, "hosseinj13", "h1374308N@")
        );
    }

    public void update(Admin admin) {
        List<Admin> collect = new java.util.ArrayList<>(ADMINS.stream()
                .filter(a -> !Objects.equals(a.getId(), admin.getId()))
                .toList());
        collect.add(admin);
        ADMINS = collect;
    }

    @Override
    public Admin findByUsernameAndPassword(String username, String password) {
        return ADMINS.stream()
                .filter(a -> a.getUsername().equals(username) && a.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

 /*   @Override
    public void approveSpecialties(Long adminId, Long specialistId, String requiredPermission) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Admin admin = session.get(Admin.class, adminId);
            Specialist specialist = session.get(Specialist.class, specialistId);

            if (admin == null || specialist == null) {
                throw new NotFoundException("Admin or Specialist not found!");
            }

            // Check if the admin has the necessary permissions (this logic can be expanded)
            if (!adminHasPermission(admin, requiredPermission)) {
                throw new IllegalAccessException("Admin does not have the necessary permissions to approve specialties.");
            }

            // Approve specialties logic here
            // Example: Validate that the specialist's specialties match with the available services and subservices

            // Assuming all specialties are approved for simplicity
            Set<Specialty> approvedSpecialties = specialist.getSpecialties();
            for (Specialty specialty : approvedSpecialties) {
                // Perform any necessary actions on the approved specialties
                System.out.println("Specialty approved: " + specialty.getName());
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean adminHasPermission(Admin admin, String requiredPermission) {
        for (Role role : admin.getRoles()) {
            if (role.getPermissions().contains(requiredPermission)) {
                return true;
            }
        }
        return false;
    }*/



}
