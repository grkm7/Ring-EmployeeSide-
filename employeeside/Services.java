package com.example.employeeside;

public class Services {
    private int user_id;
    private String timeSpace;
    private String deletebox_name;
    private int roomService_id;
    private String roomService_key;

    public Services() {
    }

    public Services(int user_id, String timeSpace, String deletebox_name, int roomService_id, String roomService_key) {
        this.user_id = user_id;
        this.timeSpace = timeSpace;
        this.deletebox_name = deletebox_name;
        this.roomService_id = roomService_id;
        this.roomService_key = roomService_key;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTimeSpace() {
        return timeSpace;
    }

    public void setTimeSpace(String timeSpace) {
        this.timeSpace = timeSpace;
    }

    public String getDeletebox_name() {
        return deletebox_name;
    }

    public void setDeletebox_name(String deletebox_name) {
        this.deletebox_name = deletebox_name;
    }

    public int getRoomService_id() {
        return roomService_id;
    }

    public void setRoomService_id(int roomService_id) {
        this.roomService_id = roomService_id;
    }

    public String getRoomService_key() {
        return roomService_key;
    }

    public void setRoomService_key(String roomService_key) {
        this.roomService_key = roomService_key;
    }
}
