package ua.goals.exception;

import lombok.extern.slf4j.Slf4j;
import ua.goals.error.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO>  handleUnHandled(Exception e){
        String message = e.getMessage();
        ErrorDTO error = new ErrorDTO();
        error.setMessage(message);
        log.error("Error: {}", message);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
