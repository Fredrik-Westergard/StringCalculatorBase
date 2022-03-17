import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to String Calculator\nExample usage: scalc '1,2,3'");
        Scanner scanner = new Scanner(System.in);
        Logger logger = new ActualLogger();
        StringCalculator strCalc = new StringCalculator(logger);
        String str = scanner.nextLine();
        while(!str.isEmpty()){
            try {
                if(str.startsWith("scalc '[") && str.endsWith("]")){
                    String delimiters = str.split("scalc '")[1].split("\\[[.]{1,10}]")[0];
                    String nextStr = scanner.nextLine();
                    if(nextStr.endsWith("'")){
                        nextStr = nextStr.split("'")[0];
                        String[] delimitersSplit = delimiters.split("]\\[");
                        delimitersSplit[0] = delimitersSplit[0].split("\\[")[1];
                        delimitersSplit[delimitersSplit.length-1] = delimitersSplit[delimitersSplit.length-1].split("]")[0];
                        for (String del : delimitersSplit) {
                            nextStr = nextStr.replace(del, ",");
                        }
                        int num = strCalc.add(nextStr);
                        if(num > 0){
                            System.out.println("The result is " + num);
                        }
                    }
                }
                else if(str.startsWith("scalc '") && str.endsWith("'")){
                    String[] strArr = str.split("'");
                    int num = strCalc.add(strArr[1]);
                    if(num > 0){
                        System.out.println("The result is " + num);
                    }
                }
            }catch(Exception e){
                System.out.println("No negative numbers");
                e.printStackTrace();
            }
            str = scanner.nextLine();
        }

    }
}
