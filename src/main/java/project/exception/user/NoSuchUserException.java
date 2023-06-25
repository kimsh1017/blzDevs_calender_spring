package project.exception.user;

import org.springframework.http.HttpStatus;

public class NoSuchUserException extends RuntimeException {
    private HttpStatus status;
    
    public NoSuchUserException() {
        super();
    }
    
    public NoSuchUserException(String message){
        super(message);
    }
    
    public NoSuchUserException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
    
    public NoSuchUserException(HttpStatus status){
        this.status = status;
    }
}  