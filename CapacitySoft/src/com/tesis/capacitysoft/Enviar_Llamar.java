package com.tesis.capacitysoft;


import org.json.JSONArray;
import org.json.JSONException;

import com.tesis.capacitysoft.servidor.HttpGetData;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Enviar_Llamar extends Activity {
	Button correo;
	Button llamar;
	Button sms;
	JSONArray ja;
	String data;
	Button cancelar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email_llamar);
		llamar=(Button)findViewById(R.id.btn_llamar);
		correo=(Button)findViewById(R.id.btn_correo);
		sms=(Button)findViewById(R.id.btn_sms);
		cancelar=(Button)findViewById(R.id.btn_cancelaremaillamar);
		 Bundle bolsa=getIntent().getExtras();
		 final String aux=bolsa.getString("Capacitador");
		 cancelar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		 llamar.setOnClickListener(new View.OnClickListener(){
	        	public void onClick(View view){
					new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							ja = null;
							HttpGetData va=new HttpGetData();
							data = va.httpGetData("http://192.168.1.8/ejemplo/recuperartelefono.php?cedula="+aux);
							if (data.length()>0){
								try {
									ja = new JSONArray(data);
									String aux2=ja.getString(0);
									 Intent intent = new Intent(Intent.ACTION_CALL);
									   intent.setData(Uri.parse("tel:"+aux2));
									   startActivity(intent);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	
							}
						}
					}).start();	
	        	}  	});
		 correo.setOnClickListener(new View.OnClickListener(){
	        	public void onClick(View view){
					new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							ja = null;
							HttpGetData va=new HttpGetData();
							data = va.httpGetData("http://192.168.1.8/ejemplo/recuperarCorreoCapacitador.php?cedula="+aux);
							if (data.length()>0){
								try {
									ja = new JSONArray(data);
									String aux2=ja.getString(0);
									 //Intent intent = new Intent(Intent.ACTION_CALL);
									 // intent.setData(Uri.parse("tel:"+aux2));
									 //startActivity(intent);
									Intent i = new Intent(Enviar_Llamar.this, Enviar_correocapacitador.class );
									 Bundle bolsa=new Bundle();
									 bolsa.putString("Capacitador",aux2);
									 i.putExtras(bolsa);
									 startActivity(i);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	
							}
						}
					}).start();	
	        	}  	});
		 sms.setOnClickListener(new View.OnClickListener(){
	        	public void onClick(View view){
					new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							ja = null;
							HttpGetData va=new HttpGetData();
							data = va.httpGetData("http://192.168.1.8/ejemplo/recuperartelefono.php?cedula="+aux);
							if (data.length()>0){
								try {
									ja = new JSONArray(data);
									String aux2=ja.getString(0);
							Intent i = new Intent(Enviar_Llamar.this, Email_capacitador.class );
								 Bundle bolsa=new Bundle();
								 bolsa.putString("Capacitador",aux2);
								 i.putExtras(bolsa);
								 startActivity(i);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	
							}
						}
					}).start();	
	        	}  	});
		 
	}

}
