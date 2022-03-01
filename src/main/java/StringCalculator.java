public class StringCalculator {


    public int add(String str) {

        if(str.isEmpty()){
            return 0;
        }
        String str2 = str;

        if(!Character.isDigit(str.charAt(0))){
            str2 = str.substring(2).replace(str.substring(0,1), ",");
        }

        String replaced = str2.replace("\n",",");
        String[] array = replaced.split(",");
        int num = 0;
        for (String number: array) {
            num+=Integer.parseInt(number);
        }

        return num;
    }
}
