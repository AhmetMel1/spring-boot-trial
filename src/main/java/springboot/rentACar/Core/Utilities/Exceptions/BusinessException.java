package springboot.rentACar.Core.Utilities.Exceptions;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}
