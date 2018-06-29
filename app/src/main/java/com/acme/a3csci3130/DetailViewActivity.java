package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetailViewActivity extends Activity {

    private EditText nameField, emailField;
    Contact receivedPersonInfo;

    //like CreateContactActivity,just add new stuff :)
    private EditText BusinessNumberField;
    private EditText PrimaryBusinessField;
    private EditText AddressField;
    private EditText ProvinceField;

    //used for erase contact
    private MyApplicationData Data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);

        //new stuff
        BusinessNumberField = (EditText)findViewById(R.id.businessnumber);
        PrimaryBusinessField = (EditText)findViewById(R.id.primarybusiness);
        AddressField = (EditText)findViewById(R.id.address);
        ProvinceField = (EditText)findViewById(R.id.province);

        //used for erase
        Data = (MyApplicationData) getApplicationContext();


        //get Data such as name, email etc from database
        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.Name);
            emailField.setText(receivedPersonInfo.email);
            BusinessNumberField.setText(receivedPersonInfo.BusinessNumber);
            PrimaryBusinessField.setText(receivedPersonInfo.PrimaryBusiness);
            AddressField.setText(receivedPersonInfo.Address);
            ProvinceField.setText(receivedPersonInfo.Province);
        }
    }

    public void updateContact(View v){
        //TODO: Update contact funcionality
        boolean check = true;

        String uid = receivedPersonInfo.uid;
        String Name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String Bnumber = BusinessNumberField.getText().toString();
        String Pbusiness = PrimaryBusinessField.getText().toString();
        String address = AddressField.getText().toString();
        String province = ProvinceField.getText().toString();

        //check if the required fields are not empty
        if (TextUtils.isEmpty(Name)) {
            nameField.setError(getString(R.string.empty));
            check = false;
        }

        if (TextUtils.isEmpty(email)) {
            emailField.setError(getString(R.string.empty));
            check = false;
        }

        if (TextUtils.isEmpty(email)) {
            BusinessNumberField.setError(getString(R.string.empty));
            check = false;
        }

        if (TextUtils.isEmpty(email)) {
            PrimaryBusinessField.setError(getString(R.string.empty));
            check = false;
        }
        if (TextUtils.isEmpty(province)) {
            ProvinceField.setError(getString(R.string.empty));
            check = false;
        }

        //check valid input of Name field
        if(!(Name.length()>2 && Name.length()<48)){
            emailField.setError(getString(R.string.invalid));
            check = false;
        }
        //check BusinessNumber
        if(Bnumber.length()!=9){
            BusinessNumberField.setError(getString(R.string.invalid));
            check = false;
        }

        //check primary business
        if(!(Pbusiness.equals("Fisher")||Pbusiness.equals("Fisher Monger")||Pbusiness.equals("Distributor")||Pbusiness.equals("Processor"))){
            PrimaryBusinessField.setError(getString(R.string.invalid));
            check = false;
        }

        //check province,there is no contains method so...

        if(!(province.equals("AB")||province.equals("BC")||province.equals("MB")||province.equals("NB")||province.equals("NL")||province.equals("NS")||province.equals("NT")||province.equals("NU")||province.equals("ON")||province.equals("PE")||province.equals("QC")||province.equals("SK")||province.equals("YT"))){
            ProvinceField.setError(getString(R.string.invalid));
            check = false;
        }

        //if all check pass show it is update
        if(check) {
            //update the database
            Data.firebaseReference.child(receivedPersonInfo.uid).child("Name").setValue(Name);
            Data.firebaseReference.child(receivedPersonInfo.uid).child("email").setValue(email);
            Data.firebaseReference.child(receivedPersonInfo.uid).child("BusinessNumber").setValue(Bnumber);
            Data.firebaseReference.child(receivedPersonInfo.uid).child("PrimaryBusiness").setValue(Pbusiness);
            Data.firebaseReference.child(receivedPersonInfo.uid).child("Address").setValue(address);
            Data.firebaseReference.child(receivedPersonInfo.uid).child("Province").setValue(province);

            Toast.makeText(DetailViewActivity.this, "The contact " + receivedPersonInfo.Name + " information is update", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
    //else, send error message on toast.
    public void eraseContact(View v)
    {
        //TODO: Erase contact functionality
        Toast.makeText(DetailViewActivity.this, "The contact "+receivedPersonInfo.Name+" has been deleted", Toast.LENGTH_SHORT).show();
        Data.firebaseReference.child( receivedPersonInfo.uid).removeValue();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
