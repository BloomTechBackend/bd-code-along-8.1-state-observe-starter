package exceptions;

public class MaximumBookCheckedOutException extends RuntimeException {

    @Override
    public String getLocalizedMessage() {
        return "User has reached their limit for how many books they can check out";
    }
}
