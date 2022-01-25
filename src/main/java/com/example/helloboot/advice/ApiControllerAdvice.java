package com.example.helloboot.advice;

import com.example.helloboot.controller.ExceptionApiController;
import com.example.helloboot.vo.ErrorResponseVO;
import com.example.helloboot.vo.ErrorVO;
import org.hibernate.validator.internal.properties.Field;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

// (basePackages = "com.example.helloBoot.controller") <-- 이걸 붙여줌으로써, 해당 에러 처리에 대한 컨트롤러를 지정해 줄 수 있다.
@RestControllerAdvice(basePackageClasses = ExceptionApiController.class)
public class ApiControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e) {
        System.out.println(e.getClass().getName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {

        List<ErrorVO> errorVOList = new ArrayList<>();


        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors().forEach(error -> {

            FieldError field = (FieldError) error;

            String fieldName = field.getField();
            String message = field.getDefaultMessage();
            String value = field.getRejectedValue().toString();

            ErrorVO errorVO = new ErrorVO();
            errorVO.setField(fieldName);
            errorVO.setMessage(message);
            errorVO.setInvalidValue(value);

            errorVOList.add(errorVO);
        });

        ErrorResponseVO errorResponseVO = new ErrorResponseVO();
        errorResponseVO.setErrorList(errorVOList);
        errorResponseVO.setMessage("");
        errorResponseVO.setReuqestUrl(httpServletRequest.getRequestURI());
        errorResponseVO.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponseVO.setResultCode("FAIL");


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseVO);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(ConstraintViolationException e, HttpServletRequest httpServletRequest) {

        List<ErrorVO> errorVOList = new ArrayList<>();

        e.getConstraintViolations().forEach(error -> {

            Stream<Path.Node> stream = StreamSupport.stream(error.getPropertyPath().spliterator(), false);
            List<Path.Node> list = stream.collect(Collectors.toList());

//            String field = error.getLeafBean().toString();
            String field = list.get(list.size() -1).getName();
            String message = error.getMessage();
            String invalidValue = error.getInvalidValue().toString();

            ErrorVO errorVO = new ErrorVO();
            errorVO.setField(field);
            errorVO.setMessage(message);
            errorVO.setInvalidValue(invalidValue);

            errorVOList.add(errorVO);

        });

        ErrorResponseVO errorResponseVO = new ErrorResponseVO();
        errorResponseVO.setErrorList(errorVOList);
        errorResponseVO.setMessage("");
        errorResponseVO.setReuqestUrl(httpServletRequest.getRequestURI());
        errorResponseVO.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponseVO.setResultCode("FAIL");


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseVO);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity missingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest httpServletRequest) {

        List<ErrorVO> errorVOList = new ArrayList<>();

        String fieldName = e.getParameterName();
        String invalidValue = e.getMessage();

        ErrorVO errorVO = new ErrorVO();
        errorVO.setField(fieldName);
        errorVO.setMessage(e.getMessage());

        errorVOList.add(errorVO);

        ErrorResponseVO errorResponseVO = new ErrorResponseVO();
        errorResponseVO.setErrorList(errorVOList);
        errorResponseVO.setMessage("");
        errorResponseVO.setReuqestUrl(httpServletRequest.getRequestURI());
        errorResponseVO.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponseVO.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseVO);
    }

}
