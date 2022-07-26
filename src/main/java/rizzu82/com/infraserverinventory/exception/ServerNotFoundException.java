package rizzu82.com.infraserverinventory.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class ServerNotFoundException  extends EmptyResultDataAccessException {
    public ServerNotFoundException(String msg, int expectedSize) {
        super(msg, expectedSize);
    }
}
