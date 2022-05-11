package connect.jpa.entity.exception;

public class RequiredFieldMissedException extends RuntimeException{
    public RequiredFieldMissedException(){
        super("Field is not filled");
    }
    public RequiredFieldMissedException(String message){
        super(message);
    }
}
