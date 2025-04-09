package com.workintech.zoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ZooGlobalExceptionHandler {

    @ExceptionHandler(ZooException.class)
    public ResponseEntity<ZooErrorResponse> handleException(ZooException zooException)
    {
        ZooErrorResponse errorResponse = new ZooErrorResponse(zooException.getHttpStatus().value(),
                zooException.getMessage(),System.currentTimeMillis());
        return  new ResponseEntity<ZooErrorResponse>(errorResponse,zooException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(Exception exception)
    {
        ZooErrorResponse errorResponse = new ZooErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<ZooErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
    }

}
