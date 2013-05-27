package my.serviceCR.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import my.serviceCR.service.MyService;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startService(new Intent(this, MyService.class));
    }
}
