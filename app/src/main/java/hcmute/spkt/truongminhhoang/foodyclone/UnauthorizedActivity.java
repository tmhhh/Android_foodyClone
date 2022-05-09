package hcmute.spkt.truongminhhoang.foodyclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import hcmute.spkt.truongminhhoang.foodyclone.classes.Database;

public class UnauthorizedActivity extends AppCompatActivity {
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unauthorized);
        //INIT DB
        db=new Database(this,"Foody.sqlite",null,1);
        //CREATE USER TABLE
        String createUserQuery="CREATE TABLE IF NOT EXISTS User(Id INTEGER PRIMARY KEY AUTOINCREMENT, userName VARCHAR(200), fullName VARCHAR(200), password VARCHAR(200), phone INTEGER, address NVARCHAR(200))";
        db.QueryData(createUserQuery);

        // FRAGMENT NAVIGATE
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.authorizeLayout,new LoginFragment());
        fragmentTransaction.commit();



    }
}