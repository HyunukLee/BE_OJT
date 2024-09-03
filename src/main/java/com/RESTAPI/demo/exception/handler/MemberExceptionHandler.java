package com.RESTAPI.demo.exception.handler;

import com.RESTAPI.demo.exception.CharacterLimitException;
import com.RESTAPI.demo.exception.IllegalCharacterInUsernameException;
import com.RESTAPI.demo.exception.MemberAlreadyExistsException;
import com.RESTAPI.demo.exception.MemberWrongPasswordException;
import com.RESTAPI.demo.exception.entity.ApiExceptionEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class MemberExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({MemberAlreadyExistsException.class, MemberWrongPasswordException.class, CharacterLimitException.class})
    public final ResponseEntity<Object> handleApiRequestException(Exception ex) {
        ApiExceptionEntity apiException = new ApiExceptionEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }



//
//    @ExceptionHandler(MemberWrongPasswordException.class)
//    public final ResponseEntity<Object> handleWrongPasswordExceptions(MemberWrongPasswordException ex) {
//        ApiExceptionEntity errorDetails = new ApiExceptionEntity("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(CharacterLimitException.class)
//    public final ResponseEntity<Object> handleCharacterLimitExceptions(CharacterLimitException ex) {
//        ApiExceptionEntity errorDetails = new ApiExceptionEntity("유저명 또는 비밀번호가 255자를 초과합니다.", HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(IllegalCharacterInUsernameException.class)
//    public final ResponseEntity<Object> handleIllegalCharacterInUsernameExceptions(IllegalCharacterInUsernameException ex) {
//        ApiExceptionEntity errorDetails = new ApiExceptionEntity("유저명은 영어 또는 숫자로만 구성되어야 합니다.", HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append(fieldError.getDefaultMessage());
        }

        ApiExceptionEntity apiException = new ApiExceptionEntity(
                builder.toString(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(
                apiException,
                HttpStatus.BAD_REQUEST
        );
    }
}