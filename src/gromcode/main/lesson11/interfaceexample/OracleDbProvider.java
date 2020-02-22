package gromcode.main.lesson11.interfaceexample;

public class OracleDbProvider implements DbProvider {

    @Override
    public void connectToDb() {
        //some logic for oracle
    }

    @Override
    public void disconnectFromDb() {
        //some logic for oracle
    }

    @Override
    public void encryptData() {
        //some logic for oracle
    }
}
