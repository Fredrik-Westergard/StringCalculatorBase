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
                if(str.startsWith("scalc '") && str.endsWith("'")){
                    String[] strArr = str.split("'");
                    int num = strCalc.add(strArr[1]);
                    if(num > 0){
                        System.out.println("The result is " + num);
                    }
                }
            }catch(Exception e){
                System.out.println("No negative numbers");
            }
            str = scanner.nextLine();
        }

    }
}
