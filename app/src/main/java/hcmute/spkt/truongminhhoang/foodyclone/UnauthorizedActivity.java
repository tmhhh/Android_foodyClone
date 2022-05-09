package hcmute.spkt.truongminhhoang.foodyclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import hcmute.spkt.truongminhhoang.foodyclone.classes.Database;
import hcmute.spkt.truongminhhoang.foodyclone.classes.User;

public class UnauthorizedActivity extends AppCompatActivity {
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unauthorized);
        //INIT DB
        db = new Database(this, "Foody.sqlite", null, 1);
        //CREATE USER TABLE
        String createUserQuery = "CREATE TABLE IF NOT EXISTS User(Id INTEGER PRIMARY KEY AUTOINCREMENT, userName VARCHAR(200), fullName NVARCHAR(200), password VARCHAR(200), phone VARCHAR(200), address NVARCHAR(200))";
        db.QueryData(createUserQuery);
        // PERSIST LOGIN CHECK
        SharedPreferences prefs = this.getSharedPreferences("authentication", MODE_PRIVATE);
        String username = prefs.getString("username", "");
        String password = prefs.getString("password", "");
        if (!username.isEmpty() || !password.isEmpty()) {
            String checkAccountExistQuery = "SELECT * FROM User WHERE userName='" + username + "' AND password='" + password + "'";
            Cursor cursor = db.GetData(checkAccountExistQuery);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    User loginUser = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                    Intent myIntent = new Intent(this, MainActivity.class);
                    myIntent.putExtra("userInfo", loginUser);
                    this.startActivity(myIntent);
                }

            }
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.authorizeLayout, new LoginFragment());
            fragmentTransaction.commit();
        }


    }
}