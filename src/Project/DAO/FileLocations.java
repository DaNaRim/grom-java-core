package Project.DAO;

public class FileLocations {
    private static final String HotelFileLocation = "testPath1";
    private static final String OrderFileLocation = "testPath2";
    private static final String RoomFileLocation = "testPath3";
    private static final String UserFileLocation = "testPath4";

    public static String getHotelFileLocation() {
        return HotelFileLocation;
    }

    public static String getOrderFileLocation() {
        return OrderFileLocation;
    }

    public static String getRoomFileLocation() {
        return RoomFileLocation;
    }

    public static String getUserFileLocation() {
        return UserFileLocation;
    }
}