package com.example.employeeside;

public class Employee {
        private String Employee_key;
        private int Employee_id;
        private int Employee_password;
        private String Employee_name;

        public Employee() {
        }

        public Employee(String employee_key, int employee_id, int employee_password, String employee_name) {
            Employee_key = employee_key;
            Employee_id = employee_id;
            Employee_password = employee_password;
            Employee_name = employee_name;
        }

        public String getEmployee_key() {
            return Employee_key;
        }

        public void setEmployee_key(String employee_key) {
            Employee_key = employee_key;
        }

        public int getEmployee_id() {
            return Employee_id;
        }

        public void setEmployee_id(int employee_id) {
            Employee_id = employee_id;
        }

        public int getEmployee_password() {
            return Employee_password;
        }

        public void setEmployee_password(int employee_password) {
            Employee_password = employee_password;
        }

        public String getEmployee_name() {
            return Employee_name;
        }

        public void setEmployee_name(String employee_name) {
            Employee_name = employee_name;
        }
    }

