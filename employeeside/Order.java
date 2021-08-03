package com.example.employeeside;

public class Order {
    private String Order_key;
    private int Order_id;
    private int Item_id;
    private String order;
    private String User_note;
    private int Room_no;
    private int User_id;

    public Order() {
    }

    public Order(String order_key, int order_id, int item_id, String order, String user_note, int room_no, int user_id) {
        Order_key = order_key;
        Order_id = order_id;
        Item_id = item_id;
        this.order = order;                   //****************************************
        User_note = user_note;
        Room_no = room_no;
        User_id = user_id;
    }

    public String getOrder_key() {
        return Order_key;
    }

    public void setOrder_key(String order_key) {
        Order_key = order_key;
    }

    public int getOrder_id() {
        return Order_id;
    }

    public void setOrder_id(int order_id) {
        Order_id = order_id;
    }

    public int getItem_id() {
        return Item_id;
    }

    public void setItem_id(int item_id) {
        Item_id = item_id;
    }

    public String getUser_note() {
        return User_note;
    }

    public void setUser_note(String user_note) {
        User_note = user_note;
    }

    public int getRoom_no() {
        return Room_no;
    }

    public void setRoom_no(int room_no) {
        Room_no = room_no;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}