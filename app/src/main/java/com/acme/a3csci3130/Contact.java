package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */
public class Contact implements Serializable {

    public String uid;
    public String email;
    public String BusinessNumber;
    public String Name;
    public String PrimaryBusiness;
    public String Address;
    public String Province;


    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * Instantiates a new Contact.
     *
     * @param uid            the uid
     * @param Name           the name
     * @param email          the email
     * @param BusinessNumber the business number
     * @param PrimaryBusines the primary busines
     * @param Address        the address
     * @param Province       the province
     */
    public Contact(String uid, String Name,String email,String BusinessNumber, String PrimaryBusines, String Address, String Province){
        this.uid = uid;
        this.email = email;
        this.BusinessNumber = BusinessNumber;
        this.Name = Name;
        this.PrimaryBusiness = PrimaryBusines;
        this.Address = Address;
        this.Province = Province;
    }

    /**
     * To map map.
     *
     * @return the map
     */
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", Name);
        result.put("email",BusinessNumber );
        result.put("email",PrimaryBusiness );
        result.put("email",Address );
        result.put("email",Province );

        return result;
    }
}
