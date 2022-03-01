public class StringCalculator {


    public int add(String str) {

        if(str.isEmpty()){
            return 0;
        }
        String replaced = str.replace("\n",",");
        String[] array = replaced.split(",");
        int num = 0;
        for (String number: array) {
            num+=Integer.parseInt(number);
        }

        return num;
    }
}
