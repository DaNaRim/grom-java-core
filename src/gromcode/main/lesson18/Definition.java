package gromcode.main.lesson18;

public class Definition {

    private void notifyUser(String[] userEmails) {
        for (String email : userEmails) {

            //обработка ошыбки
            try {
                //send email to user - error
                System.out.println("Email " + email + " was sent");
                //
                //
                //
            } catch (Exception e) {
                //how should handle exception
                System.err.println("Email " + email + " was not sent");
            } finally {
                //updateStatus
            }
//            } catch (type2 e) {
//
//            }

            //1
            //2
            //3
        }
    }
}
