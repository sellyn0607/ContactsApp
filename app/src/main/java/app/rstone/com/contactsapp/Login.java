package app.rstone.com.contactsapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static app.rstone.com.contactsapp.Main.MEMPW;
import static app.rstone.com.contactsapp.Main.MEMSEQ;
import static app.rstone.com.contactsapp.Main.MEMTAB;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Context _this = Login.this;


        findViewById(R.id.login_login_btn).setOnClickListener(
                (View v)->{
                    EditText x = findViewById(R.id.userid);
                    EditText y = findViewById(R.id.password);
                    ItemExist query = new ItemExist(_this);
                    query.id=x.getText().toString();
                    query.pw=y.getText().toString();
                    new Main.StatusSerivice(){

                        @Override
                        public void perform() {
                            if(query.execute()){
                                Toast.makeText(_this,"로그인성공",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(_this,MemberList.class));
                            }else{
                                Toast.makeText(_this,"로그인실패",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(_this,Login.class));
                            }
                        }
                    }.perform();


                }
        );

    }
    private class LoginQuery extends Main.QueryFactory {
        SQLiteOpenHelper helper;

        public LoginQuery(Context _this) {
            super(_this);
            helper= new Main.SQLiteHelper(_this);

        }

        @Override
        public SQLiteDatabase getDatabase() {
            return helper.getReadableDatabase();
        }
    }
    private class ItemExist extends LoginQuery{
        String id,pw;
        public ItemExist(Context _this) {
            super(_this);
        }

      public boolean execute(){
            return super.getDatabase().
                    rawQuery(String.format(
                            "SELECT * FROM %s where %s = '%s' and %s like '%s' ",
                            MEMTAB,MEMSEQ,id,MEMPW,pw),null).moveToNext();
      }
    }
}
