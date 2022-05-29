package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.config;

import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http.data.response.PdError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.NoResultException;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ResponseBody
    @ExceptionHandler(NoResultException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public PdError notFoundException(NoResultException e) {
        return new PdError("404", "Not found", e.getMessage());
    }
}
