package Project.DAO;

public class FileLocations {
    private static final String HotelFileLocation = "HotelDb.txt";
    private static final String OrderFileLocation = "OrderDb.txt";
    private static final String RoomFileLocation = "RoomDb.txt";
    private static final String UserFileLocation = "UserDb.txt";

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