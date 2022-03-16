import org.apache.logging.log4j.LogManager;

public class ActualLogger implements Logger {

    @Override
    public void log(int number) {
        org.apache.logging.log4j.Logger logger = LogManager.getLogger(this.getClass().getName());
        logger.info(number);
    }
}
