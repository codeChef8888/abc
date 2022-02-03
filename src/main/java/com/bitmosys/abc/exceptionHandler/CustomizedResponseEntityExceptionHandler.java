package com.bitmosys.abc.exceptionHandler;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.servlet.NoHandlerFoundException;






@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler  {


	  @ExceptionHandler(Exception.class)
	    public String handleAllExceptions(Exception ex) {
		  System.out.println("I am the Exception");
	        return "exceptionPage";
	    }
	  

	  @ExceptionHandler(NoHandlerFoundException.class)
	  @ResponseStatus(value = HttpStatus.NOT_FOUND)
	    public String handleNotFoundResourceException(NoHandlerFoundException ex) {
		   System.out.println("I am the Exception");
	        return "exceptionPage";
	    }
	  
	  @Order(Ordered.HIGHEST_PRECEDENCE)
	  @ExceptionHandler(NotFound.class)
	  @ResponseStatus(value = HttpStatus.NOT_FOUND)
	    public String handleNotFound(NotFound ex) {
		   System.out.println("I am the Exception");
	        return "exceptionPage";
	    }
}
