package com.angerbytes.workshopmongo.resources.exception;

import com.sun.jdi.ObjectCollectedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    /* I´ve changed ObjectNotFoundException by ObjectCollected because I was
       continuously getting a value 500 error instead of 404 as I set on the method  */
    @ExceptionHandler(ObjectCollectedException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectCollectedException e,
                                                        HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(),
                "Não encontrado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
