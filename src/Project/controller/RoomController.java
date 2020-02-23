package Project.controller;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.NoAccessException;
import Project.exception.NotRegisteredException;
import Project.model.Filter;
import Project.model.Room;
import Project.service.RoomService;

import java.io.IOException;
import java.util.ArrayList;

public class RoomController {
    private RoomService roomService = new RoomService();

    public ArrayList<Room> findRooms(Filter filter) {
        ArrayList<Room> arrayList = null;
        try {
            arrayList = roomService.findRooms(filter);
            System.out.println("Rooms find successfully");
        } catch (BadRequestException | IOException | BrokenFileException e) {
            System.err.println("Can`t find rooms: " + e.getMessage());
        }
        return arrayList;
    }

    public Room addRoom(Room room) {
        Room room1 = null;
        try {
            room1 = roomService.addRoom(room);
            System.out.println("Room added successfully");
        } catch (NotRegisteredException | NoAccessException | IOException | BrokenFileException e) {
            System.err.println("Can`t add room: " + e.getMessage());
        }
        return room1;
    }

    public void deleteRoom(long roomId) {
        try {
            roomService.deleteRoom(roomId);
        } catch (NotRegisteredException | NoAccessException | IOException | BrokenFileException e) {
            System.err.println("Can`t delete room: " + roomId + " : " + e.getMessage());
        }
    }
}