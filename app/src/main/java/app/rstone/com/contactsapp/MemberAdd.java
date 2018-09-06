package app.rstone.com.contactsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MemberAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_add);

        findViewById(R.id.add_add_btn).setOnClickListener(
                (View v)->{

                }
        );

        findViewById(R.id.add_list_btn).setOnClickListener(
                (View v)->{
                    startActivity(new Intent(MemberAdd.this,MemberList.class));
        }
        );
    }
}
