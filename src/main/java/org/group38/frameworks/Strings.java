package org.group38.frameworks;

public final class Strings {
    private Strings(){
        throw new AssertionError("Can't assert strings");
    }

    /** requiresNullAndNotEmpty makes sure no labels creates null-values*/
    public static String requireNonNullAndNotEmpty(String value) {
        if(value == null){
            throw new NullPointerException();
        }

        if("".equals(value)){
            throw new IllegalArgumentException();
        }

        return value;
    }

}
