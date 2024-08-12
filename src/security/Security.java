package security;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Security {
    public static boolean validateParams(String[] args) {
        if(args == null) {
            return false;
        }

        if (args.length != 6) {
            return false;
        }

        try {
            for (int i = 0; i < args.length; i++) {
                String[] actualParam = args[i].split("=");
                String letter = actualParam[0];

                switch (i) {
                    case 0: if (!(Objects.equals(letter, "w"))) return false; break;
                    case 1: if (!(Objects.equals(letter, "h"))) return false; break;
                    case 2: if (!(Objects.equals(letter, "g"))) return false; break;
                    case 3: if (!(Objects.equals(letter, "s"))) return false; break;
                    case 4: if (!(Objects.equals(letter, "p"))) return false; break;
                    case 5: if (!(Objects.equals(letter, "n"))) return false; break;
                    default: return false;
                } // verifica se as letras presentes são as esperadas

            }

            for (int i = 0; i < args.length; i++) {
                String[] actualParam = args[i].split("=");
                if(i != 4) {
                    int integerParam = Integer.parseInt(actualParam[1]);
                    if(i != 5 && integerParam < 0) return false;
                    if (i == 5 && (integerParam < 1 || integerParam > 5)) return false;
                    if(i == 0 && integerParam != 10 && integerParam != 20 && integerParam != 40 && integerParam != 80) return false;
                    if(i == 1 && integerParam != 10 && integerParam != 20 && integerParam != 40) return false;
                    if(i == 3 && (integerParam < 300 || integerParam > 1000)) return false;
                }
                if(i == 4) {
                    String[] string = actualParam[1].substring(1, actualParam[1].length() - 1).split("#");
                    for (String s : string) {
                        String[] binaryString = s.split("");
                        for (String value : binaryString) {
                            if (Integer.parseInt(value) != 0 && Integer.parseInt(value) != 1) return false;
                        }
                    }
                }
            }

        } catch(Exception e) {
            return false;
        }

        return true;
    }
}
