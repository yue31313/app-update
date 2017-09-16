package com.zeng.update;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
	private Button btn_check;
	private MyApp app;
	private int currentVersionCode;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		app = (MyApp) getApplication();
		btn_check = (Button) findViewById(R.id.check);
		btn_check.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PackageManager manager = Main.this.getPackageManager();
				try {
					PackageInfo info = manager.getPackageInfo(Main.this.getPackageName(), 0);
					String appVersion = info.versionName; // 版本名
					currentVersionCode = info.versionCode; // 版本号
					System.out.println(currentVersionCode + " " + appVersion);
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch blockd
					e.printStackTrace();
				}
				//上面是获取manifest中的版本数据，我是使用versionCode
				//在从服务器获取到最新版本的versionCode,比较
				showUpdateDialog();
			}
		});
	}
	private void showUpdateDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("检测到新版本");
		builder.setMessage("是否下载更新?");
		builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent it = new Intent(Main.this, NotificationUpdateActivity.class);
				startActivity(it);
//				MapApp.isDownload = true;
				app.setDownload(true);
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		builder.show();
	}
}
