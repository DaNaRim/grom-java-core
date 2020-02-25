package Project.DAO;

public class FileLocations {
    private static final String HotelFileLocation = "E:/Project/HotelDb.txt";
    private static final String OrderFileLocation = "E:/Project/OrderDb.txt";
    private static final String RoomFileLocation = "E:/Project/RoomDb.txt";
    private static final String UserFileLocation = "E:/Project/UserDb.txt";

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