package org.group38.frameworks;

public final class Strings {
    private Strings(){
        throw new AssertionError("Can't assert strings");
    }

    public static final String EMPTY = "";

    public static String requireNonNullAndNotEmpty(String value) {
        if(value == null){
            throw new NullPointerException();
        }

        if("".equals(value)){
            throw new IllegalArgumentException();
        }

        return value;
    }


    public static boolean isNullOrEmpty(String value){
        return value == null || "".equals(value);
    }
}
