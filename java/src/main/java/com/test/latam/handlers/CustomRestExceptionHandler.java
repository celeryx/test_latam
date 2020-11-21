package com.test.latam.handlers;

import com.test.latam.components.Messages;
import com.test.latam.constants.Constants;
import com.test.latam.domain.entities.ApiError;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;


@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private Messages messages;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        ApiError apiError = completarObjetoError(request);

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            apiError.setMessage(error.getDefaultMessage());
        }

        return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status,
                                                                          WebRequest request) {

        ApiError apiError = completarObjetoError(request);
        apiError.setMessage(String.format(messages.get(Constants.PARAMETRO_VACIO), ex.getParameterName()));

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e, WebRequest request) {

        ApiError apiError = completarObjetoError(request);

        for (ConstraintViolation error : e.getConstraintViolations()) {
            if(StringUtils.isNotEmpty(error.getInvalidValue().toString().trim())
            || StringUtils.isNotBlank(error.getInvalidValue().toString().trim())){
                Iterator iterator = error.getPropertyPath().iterator();
                while (iterator.hasNext()){
                    String paramError = iterator.hasNext() ? iterator.next().toString() : StringUtils.EMPTY;
                    if (StringUtils.compare(paramError,Constants.FECHA_NACIMIENTO) == 0) {
                        apiError.setMessage(String.format(messages.get(Constants.PARAMETRO_FORMATO_INVALIDO_FECHA),paramError));
                    } else {
                        apiError.setMessage(String.format(messages.get(Constants.PARAMETRO_FORMATO_INVALIDO_NOMBRE), paramError,paramError));
                    }
                }
            } else {
                Iterator iterator = error.getPropertyPath().iterator();
                while (iterator.hasNext()) {
                    String paramError = iterator.hasNext() ? iterator.next().toString() : StringUtils.EMPTY;
                    apiError.setMessage(String.format(messages.get(Constants.PARAMETRO_VACIO),paramError));
                }
            }
        }
        return handleExceptionInternal(e, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    private ApiError completarObjetoError(WebRequest request) {

        String path = ((ServletWebRequest)request).getRequest().getRequestURI();
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.BAD_REQUEST.value());
        apiError.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        apiError.setPath(path);

        return apiError;
    }
}
