package rizzu82.com.infraserverinventory.exception;


public class PersonException  extends RuntimeException {
    private Exception exception;
    private String errorCode;
    private String errorMessage;
    public PersonException(String errorCode,String errorMessage,Exception exception) {
        super();
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
        this.exception =exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public void setErrorCode(String errorCode) { this.errorCode = errorCode;}

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public PersonException(){

    }
}
