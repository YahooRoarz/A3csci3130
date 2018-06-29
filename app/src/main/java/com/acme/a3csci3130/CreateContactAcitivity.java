package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, emailField;
    private MyApplicationData appState;

    //new stuff
    private EditText BusinessNumberField;
    private EditText PrimaryBusinessField;
    private EditText AddressField;
    private EditText ProvinceField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);

        // add new stuff like PrimaryBusiness etc
        BusinessNumberField = (EditText)findViewById(R.id.businessnumber);
        PrimaryBusinessField = (EditText)findViewById(R.id.primarybusiness);
        AddressField = (EditText)findViewById(R.id.address);
        ProvinceField = (EditText)findViewById(R.id.province);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        boolean check = true;
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();

        //new stuff Add
        String BusinessNumber = BusinessNumberField.getText().toString();
        String PrimaryBusiness = PrimaryBusinessField.getText().toString();
        String Address = AddressField.getText().toString();
        String Province = ProvinceField.getText().toString();

        //check if the required fields are not empty
        if (TextUtils.isEmpty(name)) {
            nameField.setError(getString(R.string.empty));
            check = false;
        }

        if (TextUtils.isEmpty(email)) {
            emailField.setError(getString(R.string.empty));
            check = false;
        }

        if (TextUtils.isEmpty(BusinessNumber)) {
            BusinessNumberField.setError(getString(R.string.empty));
            check = false;
        }

        if (TextUtils.isEmpty(PrimaryBusiness)) {
            PrimaryBusinessField.setError(getString(R.string.empty));
            check = false;
        }
        if (TextUtils.isEmpty(Province)) {
            ProvinceField.setError(getString(R.string.empty));
            check = false;
        }

        //check valid input of Name field
        if(!(name.length()>2 && name.length()<48)){
            emailField.setError(getString(R.string.invalid));
            check = false;
        }
        //check BusinessNumber
        if(BusinessNumber.length()!=9){
            BusinessNumberField.setError(getString(R.string.invalid));
            check = false;
        }

        //check primary business
        if(!(PrimaryBusiness.equals("Fisher")||PrimaryBusiness.equals("Fisher Monger")||PrimaryBusiness.equals("Distributor")||PrimaryBusiness.equals("Processor"))){
            PrimaryBusinessField.setError(getString(R.string.invalid));
            check = false;
        }

        //check province,there is no contains method so...

        if(!(Province.equals("AB")||Province.equals("BC")||Province.equals("MB")||Province.equals("NB")||Province.equals("NL")||Province.equals("NS")||Province.equals("NT")||Province.equals("NU")||Province.equals("ON")||Province.equals("PE")||Province.equals("QC")||Province.equals("SK")||Province.equals("YT"))){
            ProvinceField.setError(getString(R.string.invalid));
            check = false;
        }

        //if all check pass show it is update
        if(check) {
            Contact person = new Contact(personID, name, email,BusinessNumber,PrimaryBusiness,Address,Province);
            appState.firebaseReference.child(personID).setValue(person);
            finish();
        }

        //else, send an error message
        if(!check){
            Toast.makeText(CreateContactAcitivity.this, "New contact build failed", Toast.LENGTH_SHORT).show();
        }


    }
}
