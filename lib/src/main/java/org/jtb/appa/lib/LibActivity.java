package org.jtb.appa.lib;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public abstract class LibActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  protected void onStart() {
    super.onStart();
    check();
  }

  private static final String PERM = "org.jtb.TEST";

  public void onClick(View view) {
    check();
  }

  private void check() {
    TextView text1 = findViewById(R.id.text1);
    if (definesPerm(PERM)) {
      text1.setText("defined: " + PERM);
    } else {
      text1.setText("not defined: " + PERM);
    }

    TextView text2 = findViewById(R.id.text2);
    if (checkCallingOrSelfPermission(PERM) == PackageManager.PERMISSION_GRANTED) {
      text2.setText("PERMISSION_GRANTED: " + PERM);
    } else {
      text2.setText("PERMISSION_DENIED: " + PERM);
    }
  }

  private boolean definesPerm(String permission)  {
    try {
      PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_PERMISSIONS);
      if (info.permissions != null) {
        for (PermissionInfo p : info.permissions) {
          if (p.name.equals(permission)) {
            return true;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

}
