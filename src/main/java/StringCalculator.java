public class StringCalculator {


    public int add(String s) {

        if(s.isEmpty()){
            return 0;
        }
        String[] array = s.split(",");
        int num = 0;
        for (String number: array) {
            num+=Integer.parseInt(number);
        }

        return num;
    }
}
