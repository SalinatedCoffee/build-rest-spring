package payroll;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
class Employee {
  private @Id @GeneratedValue Long id;
  private String name;
  private String role;

  protected Employee() {}

  Employee(String name, String role) {
    this.name = name;
    this.role = role;
  }
}
