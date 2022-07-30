package rizzu82.com.infraserverinventory.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", String.format(ex.getValue()+" is not valid parameter for "+((MethodArgumentTypeMismatchException) ex).getName()));
        body.put("status", status.value());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ServerNotFoundException.class)
    protected ResponseEntity<Object> handleServerNotFound(ServerNotFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        if(ex.getException() instanceof EmptyResultDataAccessException || ex.getException() instanceof NullPointerException)
        {
            body.put("timestamp", LocalDateTime.now());
            body.put("message", String.format("Server is not found for id "+ex.getErrorCode()));
            body.put("status", HttpStatus.NOT_FOUND.value());
        }
        else {
            body.put("timestamp", LocalDateTime.now());
            body.put("message", String.format("Something went wrong"));
            body.put("status", HttpStatus.BAD_REQUEST);
        }



        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersonException.class)
    protected ResponseEntity<Object> handlePersonException(PersonException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        if(ex.getException() instanceof EmptyResultDataAccessException || ex.getException() instanceof NullPointerException)
        {
            body.put("timestamp", LocalDateTime.now());
            body.put("message", String.format("Person is not found for id "+ex.getErrorCode()));
            body.put("status", HttpStatus.NOT_FOUND.value());
        }
        else if(ex.getException() instanceof DataIntegrityViolationException || ex.getException() instanceof SQLIntegrityConstraintViolationException
                || ex.getException() instanceof ConstraintViolationException)
        {
            body.put("timestamp", LocalDateTime.now());
            body.put("message", String.format("Person with email address "+ex.getErrorCode() + " already exists"));
            body.put("status", HttpStatus.BAD_REQUEST.value());
        }
        else {
            body.put("timestamp", LocalDateTime.now());
            body.put("message", String.format("Something went wrong"));
            body.put("status", HttpStatus.BAD_REQUEST);
        }



        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRunTimeException(RuntimeException e) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", e.getMessage());
        body.put("status", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}