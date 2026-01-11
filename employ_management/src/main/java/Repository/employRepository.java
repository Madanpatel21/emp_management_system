package Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import Entity.Employ;

public interface employRepository extends JpaRepository<Employ, Long> {
	boolean existsByEmpId(String empId);
    boolean existsByEmail(String email);
}
