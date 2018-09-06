package app.rstone.com.contactsapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;


public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Context _this = Main.this;
      findViewById(R.id.main_login_btn).setOnClickListener(
              (View v)->{
                  SQLiteHelper helper = new SQLiteHelper(_this);
                  startActivity(new Intent(_this,Login.class));
              }
      );

    }
    static class Member{int seq; String name,pw,email,phone,addr,photo;}
    static interface StatusSerivice{public void perform();}
    static interface ListSerivice{public List<?> perform();}
    static interface DetailSerivice{public Object perform();}
 /*   static interface UpdateSerivice{public void perform();}
    static interface DeleteSerivice{public void perform();}*/
    static String DBNAME="KJ.db";
    static String MEMTAB="MEMBER";
    static String MEMSEQ="SEQ";
    static String MEMNAME="NAME";
    static String MEMPW="PW";
    static String MEMEMAIL="EMAIL";
    static String MEMPHONE="PHONE";
    static String MEMADDR="ADDR";
    static String MEMPHOTO="PHOTO";
    static abstract class QueryFactory{
        Context _this;

        public QueryFactory(Context _this) {
            this._this = _this;
        }
        public abstract SQLiteDatabase getDatabase();

    }
    static class SQLiteHelper extends SQLiteOpenHelper{// 데이터베이스 오픈

        public SQLiteHelper(Context context) {
            super(context, DBNAME, null, 1);
            this.getWritableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = String.format("CREATE TABLE IF NOT EXISTS %s "+
                    " (%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s TEXT) "
                    ,MEMTAB,MEMSEQ,MEMNAME,MEMPW,MEMADDR,MEMEMAIL,MEMPHONE,MEMPHOTO);
            Log.d( "실행할 쿼리",sql);
            db.execSQL(sql);
            Log.d("==================","create 완료");
            String names[]={"유재석","휴잭맨","로다주","앤해서웨이","박명수"};
            String emails[]={"sellyn0607@gmail.com","jack@gmail.com","daju@gmail.com","ann@gmail.com","park@gmail.com"};
            String phones[]={"010-3485-7767","010-2147-7511","010-8741-2546","010-7365-1542","010-6421-1134"};
            for(int i=1;i<=5;i++){
                db.execSQL(String.format("INSERT INTO %s "+
                                " (%s ,%s ,%s ,%s ,%s ,%s ) VALUES('%s' ,'%s' ,'%s' ,'%s' ,'%s' ,'%s' )",
                        MEMTAB,MEMNAME,MEMPW,MEMADDR,MEMEMAIL,MEMPHONE,MEMPHOTO,
                        names[i-1],"1","신촌 "+i,emails[i-1],phones[i-1],"profile_"+i));
            }
            Log.d("==================","insert 완료");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+MEMTAB);
            onCreate(db);
        }
    }





}
