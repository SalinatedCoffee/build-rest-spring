package payroll;

import org.springframework.data.jpa.repository.JpaRepository;

// In JpaRepository<T,V>, Domain type is T and id type is V
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
