package payroll;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
class EmployeeNotFoundAdvice {
  // render in response body
  @ResponseBody
  // only fire when EmplNotFoundExc is thrown
  @ExceptionHandler(EmployeeNotFoundException.class)
  // send http status code 404
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String employeeNotFoundHandler(EmployeeNotFoundException ex) {
    return ex.getMessage();
  }
}
