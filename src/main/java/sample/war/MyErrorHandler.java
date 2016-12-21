package sample.war;

/**
 * Created by danielfoglio on 12/6/16.
 */
public class MyErrorHandler {

    public void handleError(Exception e) throws Exception {
        throw new Exception("There was an error processing an Event", e);
    }
}
