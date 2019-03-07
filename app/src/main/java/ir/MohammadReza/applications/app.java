package ir.MohammadReza.applications;

import android.util.Log;
import android.widget.Toast;

public class app {


    private static final String TAG = "WEATHER";


    public static void L_i(String message) {

        Log.i(TAG, message);

    }


    public static void T_short(String message) {

        Toast.makeText(application.getContext(), message, Toast.LENGTH_SHORT).show();

    }


    public static String getTag() {


        return TAG;

    }

}
