package rizzu82.com.infraserverinventory.exception;

public class ServerNotFoundException extends RuntimeException {

    public ServerNotFoundException(Long id) {

        super(String.format("Server with Id %d not found", id));
    }

    public ServerNotFoundException(String ipAddress) {

        super(String.format("Server with IP Address %d not found", ipAddress));
    }
}
