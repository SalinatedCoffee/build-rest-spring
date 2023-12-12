package payroll;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jdk.jshell.Snippet;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.lang.model.element.NestingKind;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
// 'ORDER' is not a valid table name, so specify using @Table annotation
@Table(name = "CUSTOMER_ORDER")
class Order {
  private @Id @GeneratedValue Long id;

  private String description;
  private Status status;

  protected Order() {}

  Order(String description, Status status) {
    this.description = description;
    this.status = status;
  }
}
