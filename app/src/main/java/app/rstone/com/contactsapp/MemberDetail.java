package app.rstone.com.contactsapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static app.rstone.com.contactsapp.Main.MEMADDR;
import static app.rstone.com.contactsapp.Main.MEMEMAIL;
import static app.rstone.com.contactsapp.Main.MEMNAME;
import static app.rstone.com.contactsapp.Main.MEMPHONE;
import static app.rstone.com.contactsapp.Main.MEMPHOTO;
import static app.rstone.com.contactsapp.Main.MEMPW;
import static app.rstone.com.contactsapp.Main.MEMSEQ;

public class MemberDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_detail);
        Intent intent = this.getIntent();
        Context _this = MemberDetail.this;
        String seq = intent.getExtras().getString("seq");
        String seq2 = intent.getStringExtra("seq");
        ItemExist query = new ItemExist(_this);
        query.seq = seq;
        TextView name = findViewById(R.id.de_name);
        TextView phone = findViewById(R.id.de_phone);
        TextView email = findViewById(R.id.de_email);
        ImageView profile = findViewById(R.id.de_profile);
        Main.Member mem = query.execute();
        int[] photos= {
                0,
                R.drawable.profile_1,
                R.drawable.profile_2,
                R.drawable.profile_3,
                R.drawable.profile_4,
                R.drawable.profile_5,

        };
        name.setText(mem.name);
        phone.setText(mem.phone);
        email.setText(mem.email);
        profile.setImageResource(photos[mem.seq]);



        new Main.DetailSerivice(){

            @Override
            public Object perform() {
                return query.execute();
            }
        }.perform();
        Log.d("이름",query.execute().name);


    }

    private  class DetailQuery extends Main.QueryFactory{
        SQLiteOpenHelper helper;
        public DetailQuery(Context _this) {
            super(_this);
            helper=new Main.SQLiteHelper(_this);
        }

        @Override
        public SQLiteDatabase getDatabase() {
            return helper.getReadableDatabase();
        }
    }
    private class ItemExist extends DetailQuery{
        String seq;
        public ItemExist(Context _this) { super(_this); }

        public Main.Member execute(){
            Main.Member m = null;
            Cursor cursor = this.getDatabase().rawQuery(String.format(
                    "select * from member where %s "+
                            "like '%s' ",MEMSEQ,seq),null);
            if(cursor!=null){
                while(cursor.moveToNext()){
                    m=new Main.Member();
                    m.seq = cursor.getInt(cursor.getColumnIndex(MEMSEQ));
                    m.name=cursor.getString(cursor.getColumnIndex(MEMNAME));
                    m.addr=cursor.getString(cursor.getColumnIndex(MEMADDR));
                    m.email=cursor.getString(cursor.getColumnIndex(MEMEMAIL));
                    m.phone=cursor.getString(cursor.getColumnIndex(MEMPHONE));
                    m.photo=cursor.getString(cursor.getColumnIndex(MEMPHOTO));
                    m.pw=cursor.getString(cursor.getColumnIndex(MEMPW));

                }
            }
            return m;
        }
    }
}

