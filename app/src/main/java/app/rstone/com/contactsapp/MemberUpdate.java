package app.rstone.com.contactsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MemberUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_update);

        findViewById(R.id.update_detail_btn).setOnClickListener(
                (View v)->{
                    startActivity(new Intent(this,MemberDetail.class));
                }
        );

        findViewById(R.id.update_update_btn).setOnClickListener(
                (View v)->{

                }
        );
    }
}
