package net.andirc.lteonfoff;


/**@author jcase
 *  
 *  This app was written fast, with little attention paid to it,
 *  and is not a general example of my work. It was written for
 *  a basic need, to toggle PreferredNetworks. The whole app can
 *  be summed up as:
 *  
 *  	   ComponentName intentComponent = new ComponentName("com.android.settings", "com.android.settings.RadioInfo");
 * 	      Intent mainIntent = new Intent("android.intent.action.MAIN");
 *   	  mainIntent.setComponent(intentComponent);
 *   	  startActivity(mainIntent);
 *   	  System.exit(0);
 * 
 */


import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Main extends Activity {
	public static final String PREFS_NAME = "prefrences";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        
        if (settings.getInt("skipdialog", 0) == 1){
        	radioInfo();
        } else {
        
        Dialog dialog = new Dialog(Main.this);
        dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        dialog.setContentView(R.layout.maindialog);
        dialog.setTitle("LTE Settings");
        
        dialog.setCancelable(true);
        //there are a lot of settings, for dialog, check them all out!

        //set up text
        final TextView txtDetails = (TextView) dialog.findViewById(R.id.txtDetails);
        txtDetails.setText("On the next screen, scroll down to \"Set preferred network type\" and" +
   				" choose \"CDMA + LTE/EvDo auto\" to enable 4GLTE + 3G , or \"CDMA auto (PRL)\" for 3G only.");

        
        final CheckBox chkSkip = (CheckBox) dialog.findViewById(R.id.chkSkip);
        chkSkip.setText("Skip This Dialog");
        
        Button btnOpen = (Button) dialog.findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
        		SharedPreferences.Editor editor = settings.edit();
        		if (chkSkip.isChecked()) {
        			editor.putInt("skipdialog", 1);
        		
        			editor.commit();
        		}
        		radioInfo();
            }
        

        });
        Button btnLicense = (Button) dialog.findViewById(R.id.btnLicense);
        btnLicense.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
        		SharedPreferences.Editor editor = settings.edit();
        		txtDetails.setText(R.string.license);
            }
        

        });
        dialog.show();
        dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.icon);
        }
    }
    
    private void radioInfo(){
	      ComponentName intentComponent = new ComponentName("com.android.settings", "com.android.settings.RadioInfo");
  	      Intent mainIntent = new Intent("android.intent.action.MAIN");
     	  mainIntent.setComponent(intentComponent);
     	  startActivity(mainIntent);
     	  System.exit(0);
     	  
    }
}