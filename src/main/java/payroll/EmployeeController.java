package payroll;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmployeeController {
  private final EmployeeRepository repository;
  private final EmployeeModelAssembler assembler;

  EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  @GetMapping("/employees")
  CollectionModel<EntityModel<Employee>> all() {
    // get all employees in db
    List<EntityModel<Employee>> employees = repository.findAll().stream()
        // convert them each into entitymodel<employee> with HAL formatted links
        .map(assembler::toModel)
        // stick all elements in stream in a list
        .collect(Collectors.toList());

    // add HAL link to the list of entitymodels as well
    return CollectionModel.of(employees,
        linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
  }

  @GetMapping("/employees/{id}")
  EntityModel<Employee> one(@PathVariable Long id) {
    Employee employee = repository.findById(id)
        .orElseThrow(() -> new EmployeeNotFoundException(id));

    /*
    // use Spring MVC to generate RESTful links to other resources using HAL formatting
    return EntityModel.of(employee,
        // link to self
        linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
        // link to aggregate root
        linkTo(methodOn(EmployeeController.class).all()).withRel("employees")
        );
     */
    return assembler.toModel(employee);
  }

  @PutMapping("/employees/{id}")
  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
    return repository.findById(id)
        .map(employee -> {
          employee.setName(newEmployee.getName());
          employee.setRole(newEmployee.getRole());
          return repository.save(employee);
        })
        .orElseGet(() -> {
          newEmployee.setId(id);
          return repository.save(newEmployee);
        });
  }

  @DeleteMapping("/employees/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
