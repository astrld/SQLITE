package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText firstNameEDT,lastNameEDT,phoneNumberEDT;
    private Button addUser, displayUsers;

    private DBHandler DBHandler;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNameEDT = findViewById(R.id.firstName);
        lastNameEDT = findViewById(R.id.lastName);
        phoneNumberEDT = findViewById(R.id.phoneNumber);

        addUser = findViewById(R.id.addUser);
        displayUsers = findViewById(R.id.displayUsers);

        listView = findViewById(R.id.listView);

        DBHandler = new DBHandler(MainActivity.this);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameEDT.getText().toString();
                String lastName = lastNameEDT.getText().toString();
                String phoneNumber = phoneNumberEDT.getText().toString();

                if(firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHandler.addNewUser(firstName,lastName,phoneNumber);

                Toast.makeText(MainActivity.this, "User has been created.", Toast.LENGTH_SHORT).show();
                firstNameEDT.setText("");
                lastNameEDT.setText("");
                phoneNumberEDT.setText("");
            }
        });

        displayUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<userInfo> users = DBHandler.returnUsers();
                if(users.isEmpty()){
                    Toast.makeText(MainActivity.this, "No users to display.", Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<String> usersString = new ArrayList<>();
                for(userInfo user : users){
                    usersString.add(user.getFirstName() + " " + user.getLastName() + "\n" + user.getPhoneNumber());
                }
                // for each user display FirstName, LastName, PhoneNumber in listView
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, usersString);
                listView.setAdapter(adapter);
            }
        });
    }
}