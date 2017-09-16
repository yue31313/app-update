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
					String appVersion = info.versionName; // �汾��
					currentVersionCode = info.versionCode; // �汾��
					System.out.println(currentVersionCode + " " + appVersion);
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch blockd
					e.printStackTrace();
				}
				//�����ǻ�ȡmanifest�еİ汾���ݣ�����ʹ��versionCode
				//�ڴӷ�������ȡ�����°汾��versionCode,�Ƚ�
				showUpdateDialog();
			}
		});
	}
	private void showUpdateDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("��⵽�°汾");
		builder.setMessage("�Ƿ����ظ���?");
		builder.setPositiveButton("����", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent it = new Intent(Main.this, NotificationUpdateActivity.class);
				startActivity(it);
//				MapApp.isDownload = true;
				app.setDownload(true);
			}
		}).setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		builder.show();
	}
}
