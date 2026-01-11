package Exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployNotFoundException extends RuntimeException {
  public  EmployNotFoundException(Long id) {
	  super("Employ not found"+ id);
  }
  public EmployNotFoundException(String message) {
      super(message);
  }
}
