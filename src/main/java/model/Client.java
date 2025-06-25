package model;

public class Client {

    private int id;
    private String name;
    private String address;
    private String phone;
    private String requisites;
    private String contactPerson;

    public Client(int id, String name, String address, String phone, String requisites, String contactPerson){
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.requisites = requisites;
        this.contactPerson = contactPerson;
    }

    public int getId() {return id;}

    public String getName() {return name;}

    public String getAddress() {return address;}

    public String getPhone() {return phone;}

    public String getRequisites() {return requisites;}

    public String getContactPerson() {return contactPerson;}
}

