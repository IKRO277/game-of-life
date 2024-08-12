package security;

import java.util.Objects;

public class ValidateChessParams {

    public static boolean validateNullParams(String[] params) {
        return (params == null);
    }

    public static boolean validateEmptyParams(String[] params) {
        return (params.length != 6);
    }

    public static boolean validateLettersParams(String[] params) {
        for (int i = 0; i < params.length; i++) {
            String[] actualParam = params[i].split("=");
            String letter = actualParam[0];
            switch (i) {
                case 0: if (!(Objects.equals(letter, "w"))) return true; break;
                case 1: if (!(Objects.equals(letter, "h"))) return true; break;
                case 2: if (!(Objects.equals(letter, "g"))) return true; break;
                case 3: if (!(Objects.equals(letter, "s"))) return true; break;
                case 4: if (!(Objects.equals(letter, "p"))) return true; break;
                case 5: if (!(Objects.equals(letter, "n"))) return true; break;
            }
        }
        return false;
    }

    public static boolean validateNumbersParams(String[] params) {
        for (int i = 0; i < params.length; i++) {
            String[] actualParam = params[i].split("=");
            int integerParam = 0;
            if(i != 4) {
                if (i != 5)  integerParam = Integer.parseInt(actualParam[1]);
                if(i != 5 && integerParam < 0) return true;
                if(i == 0 && integerParam != 10 && integerParam != 20 && integerParam != 40 && integerParam != 80) return true;
                if(i == 1 && integerParam != 10 && integerParam != 20 && integerParam != 40) return true;
                if(i == 3 && (integerParam < 300 || integerParam > 1000)) return true;
            }

            if(i == 4) {
                if(actualParam.length != 1) {
                    String[] string = actualParam[1].substring(1, actualParam[1].length() - 1).split("#");
                    for (String s : string) {
                        String[] binaryString = s.split("");
                        for (String value : binaryString) {
                            if (Integer.parseInt(value) != 0 && Integer.parseInt(value) != 1) return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean validateParams(String[] args) {
        try {
            if (validateNullParams(args)) return false;
            if (validateEmptyParams(args)) return false;
            if(validateLettersParams(args)) return false;
            if(validateNumbersParams(args)) return false;
        } catch(Exception e) {
            return false;
        }
        return true;
    }
}
