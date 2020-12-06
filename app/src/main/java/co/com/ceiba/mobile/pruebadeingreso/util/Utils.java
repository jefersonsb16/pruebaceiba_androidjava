package co.com.ceiba.mobile.pruebadeingreso.util;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import co.com.ceiba.mobile.pruebadeingreso.R;

public class Utils {
    public static Boolean validateNetwork(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    public static void showDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(context.getString(R.string.generic_error));
        builder.setPositiveButton(context.getString(R.string.text_ok), null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
