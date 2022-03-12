public class StringCalculator {

    private Logger logger;

    public StringCalculator(Logger logger) {
        this.logger = logger;
    }

    public int add(String str) throws Exception {

        if(str.isEmpty()){
            return 0;
        }
        String str2 = str;

        if(str.charAt(0) == '/'){
            str2 = str.substring(4).replace(str.substring(2,3), ",");
        }

        String replaced = str2.replace("\n",",");
        String[] array = replaced.split(",");
        int num = 0;
        for (String number: array) {
            int n;
            if((n = Integer.parseInt(number)) < 0){
                throw new Exception("Negatives not allowed " + n);
            }
            if(n > 1000){
                logger.log(n);
            }
            num+=n;
        }

        return num;
    }
}
