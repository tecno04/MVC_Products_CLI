package CLI_Products_Optional.utils;

import CLI_Products_Optional.excpetions.InvalidProductException;

public class Validates {

    public static <T extends Number> void validateNumber(T value, String message) throws InvalidProductException {
        if(value == null){
            throw new InvalidProductException(message);
        }
    }

    public static <T> void validateObject(T obj, String message) throws InvalidProductException {
        if(obj == null){
            throw new InvalidProductException(message);
        }
    }

    public static void validateText(String txt, String message) throws InvalidProductException {
        if(txt == null | txt.isEmpty()){
            throw new InvalidProductException(message);
        }
    }

}
