package ir.MohammadReza.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import ir.MohammadReza.applications.app;
import ir.MohammadReza.Objects.City_Objects;


public class CityDbHelper extends SQLiteOpenHelper {

    private static final int DBVERSION = 2;
    public static final String TABLE_CITY = "tb_city";
    public static final String DBNAME = "db_city";
    public static final String[] ALL_COLUMNS = {"id", "name", "lat", "lon", "countryCode"};


    private static final String CMD_CREATE_CITY_TABLE = "CREATE TABLE IF NOT EXISTS '" + TABLE_CITY + "' (" +
            "'id' INTEGER PRIMARY KEY NOT NULL, " +
            "'name' TEXT , " +
            "'lat' DOUBLE, " +
            "'lon' DOUBLE, " +
            "'countryCode' TEXT, " +
            "'selected' INTEGER" +
            ")";
    private Context context;

    public CityDbHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
        this.context = context;


        //dar halati ke etelat toye jadval pak
        // shode bud ama khode jadvaal na
        // aval ye test anjam midim to
        // haminja ke yek object azash
        // sakhte mishe
//        if (getCities(null, null).isEmpty()) {
//            try {
//                initContents();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //  context=getc;
        db.execSQL(CMD_CREATE_CITY_TABLE);
        app.L_i("table created");
        //migim zamani ke omde to on create pas ghaedatan hichi tush nist pas init mikonim

//        try {
//            initContents();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//delet table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITY);
        onCreate(db);


    }


    //this method for config raw file .splits with \t
//    public void initContents() {
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//
//                    InputStream stream = context.getResources().openRawResource(R.raw.city_list);
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
//                    String line = "";
//                    String[] data;
//                    SQLiteDatabase db = getWritableDatabase();
//                    while ((line = bufferedReader.readLine()) != null) {
//                        //split with \t and this out put is array
//
//                        data = line.split("\t");
//                        if (data.length < 5) {
//                            //data kamtar az 5 bere dobare bargarde ta data adi she baray rafte errro and exeption
//                            continue;
//                        }
//
//                        //in ravesh konde baray hamin dar bala ye data base object sakhte va ravesh dovomo mirim
////            City_Objects city_objects = new City_Objects();
////            city_objects.setId(Long.valueOf(data[0]));
////            city_objects.setName(data[1]);
////            city_objects.setLat(Double.valueOf(data[2]));
////            city_objects.setLon(Double.valueOf(data[3]));
////            city_objects.setCountryCode(data[4]);
////
//
//
//                    long insertId=    db.insert(TABLE_CITY, null,
//                                City_Objects.createContentValues(Long.valueOf(data[0])
//                                        , data[1]
//                                        , Double.valueOf(data[2])
//                                        , Double.valueOf(data[3])
//                                        , data[4]
//                                        //chon dar halat pishfrz hich shahri entekhab
//                                        // nshde pas inam false mishe
//                                        , false
//                                ));
//
//                        //inaro ke gereftim be in  sorat to data base ghara midimsh
//                        //     insertCityToDB(city_objects);
//                        //hal ke city model ro gerftim bayad insert bknim to data base
//
//
//                        Log.i("INSERTID", insertId + "");
//
////
//// for (String s :data){
////
////        app.L_i(s);
////        }break;
//
//                    }db.close();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//        });
//        thread.start();
//
//    }


    //insert to data base


    public void insertCityToDB(City_Objects city_objects) {
        SQLiteDatabase db = this.getWritableDatabase();
        long insertId =
                db.insert(TABLE_CITY, null, city_objects.getContentValues());
        app.L_i("City Inserted With id :" + insertId);
        db.close();
    }

    //az roye in mishe fahmid toye list sharhi hast ya na
    public List<City_Objects> getCities(String selection, String[] selectionArgs) {
        //agar in dor vorodi ra null bznim yani kole shahahr haro mikhaym
        //daryaft beknim

        List<City_Objects> city_list;


        //dar inja mikhaym faghat az data base etelaat bekhunim
        city_list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        //behesh migim ke ye query be ma  bde mige in etelato takmil kn

        Cursor cursor = db.query(TABLE_CITY, ALL_COLUMNS, selection, selectionArgs, null, null, "countryCode, name");
        //Cursor cursor = db.query(TABLE_CITY, ALL_COLUMNS, selection, selectionArgs, null, null, "countryCode, name  ");
//
        Log.d("cusrsur", cursor.getCount() + " records");
        if (cursor.moveToFirst()) {

            do {
                //dar har seri az in halghe bayad cusr ro be ye item mesl id ya name  ya country COde bayad tabidl kard
                //baray inkar ye tabe static toye objects minevisim

                city_list.add(City_Objects.fromCursor(cursor));


            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();


        return city_list;
    }

}
