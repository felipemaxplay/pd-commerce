package br.com.felipemaxplay.pdcommerce.pdproductsservice.config;

import br.com.felipemaxplay.pdcommerce.pdproductsservice.http.data.response.PdError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.NoResultException;
import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseBody
    @ExceptionHandler(NoResultException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public PdError notFoundException(NoResultException e) {
        return new PdError(new Date(), "404", "Not found", e.getMessage());
    }
}
