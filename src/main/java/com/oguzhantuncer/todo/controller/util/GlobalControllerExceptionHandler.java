package com.oguzhantuncer.todo.controller.util;

import com.oguzhantuncer.todo.error.ErrorDTO;
import com.oguzhantuncer.todo.error.ErrorDetailDTO;
import com.oguzhantuncer.todo.exception.BaseException;
import com.oguzhantuncer.todo.exception.BusinessException;
import com.oguzhantuncer.todo.exception.DomainNotFoundException;
import com.oguzhantuncer.todo.exception.SystemException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@Log4j2
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    private final MessageSource messageSource;

    public GlobalControllerExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(DomainNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleDomainNotFoundException(DomainNotFoundException exception) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setException("DomainNotFoundException");
        errorDTO.addError(getErrorDetailDTO(exception, HttpStatus.NOT_FOUND));
        log.error("DomainNotFoundException occurred : {}", errorDTO);
        return errorDTO;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleBusinessException(BusinessException exception) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setException("BusinessException");
        errorDTO.addError(getErrorDetailDTO(exception, HttpStatus.BAD_REQUEST));
        log.error("BusinessException occurred : {}", errorDTO);
        return errorDTO;
    }

    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleSystemException(BusinessException exception) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setException("SystemException");
        errorDTO.addError(getErrorDetailDTO(exception, HttpStatus.INTERNAL_SERVER_ERROR));
        log.error("SystemException occurred : {}", errorDTO);
        return errorDTO;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDTO handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setException("DataIntegrityViolationException");
        log.error("DataIntegrityViolationException occurred : {}", errorDTO);
        return errorDTO;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setException("MethodArgumentNotValidException");
        exception.getBindingResult().getAllErrors().forEach(each -> {
            ErrorDetailDTO fieldError = new ErrorDetailDTO();
            fieldError.setMessage(getMessage(each.getDefaultMessage(), each.getArguments(), StringUtils.EMPTY));
            fieldError.setKey(each.getDefaultMessage());
            fieldError.setErrorCode(HttpStatus.BAD_REQUEST.getReasonPhrase());
            errorDTO.addError(fieldError);
        });
        log.error("MethodArgumentNotValidException occurred : {}", errorDTO);
        return errorDTO;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleConstraintViolationException(ConstraintViolationException exception) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setException("ConstraintViolationException");

        exception.getConstraintViolations().forEach(constraintViolation -> {
            ErrorDetailDTO fieldError = new ErrorDetailDTO();
            fieldError.setMessage(getMessage(constraintViolation.getMessage(), StringUtils.EMPTY, constraintViolation.getInvalidValue()));
            fieldError.setKey(constraintViolation.getMessageTemplate());
            fieldError.setErrorCode(HttpStatus.BAD_REQUEST.getReasonPhrase());
            errorDTO.addError(fieldError);
        });
        log.error("ConstraintViolationException occurred : {}", errorDTO);
        return errorDTO;
    }

    private ErrorDetailDTO getErrorDetailDTO(BaseException exception, HttpStatus errorCode) {
        ErrorDetailDTO errorDetailDTO = new ErrorDetailDTO();
        errorDetailDTO.setKey(exception.getKey());
        errorDetailDTO.setMessage(getMessage(exception.getKey(), exception.getArgs(), exception.getMessage()));
        errorDetailDTO.setArgs(exception.getArgs());
        errorDetailDTO.setErrorCode(errorCode.getReasonPhrase());
        return errorDetailDTO;
    }

    private String getMessage(String key, Object[] args, String defaultMessage) {
        return Optional.of(getMessage(() -> messageSource.getMessage(key, args, LocaleContextHolder.getLocale()), defaultMessage))
                .filter(StringUtils::isNotBlank)
                .orElse(defaultMessage);
    }

    private String getMessage(String key, Object... args) {
        return Optional.of(getMessage(() -> messageSource.getMessage(key, args, LocaleContextHolder.getLocale()), StringUtils.EMPTY))
                .filter(StringUtils::isNotBlank)
                .orElse(StringUtils.EMPTY);
    }

    private String getMessage(Supplier<String> supplier, String defaultMessage) {
        String message = StringUtils.EMPTY;
        try {
            message = supplier.get();
        } catch (Exception exception) {
            if (Objects.isNull(defaultMessage)) {
                log.error("MessageResource Not found ex: ", exception);
            }
        }
        return message;
    }
}
