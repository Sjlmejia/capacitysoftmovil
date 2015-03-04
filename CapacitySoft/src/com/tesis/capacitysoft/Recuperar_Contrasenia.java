package com.tesis.capacitysoft;
import java.util.Random;
import java.util.concurrent.Executors;

import org.json.JSONArray;

import com.tesis.capacitysoft.servidor.HttpGetData;
import com.tesis.capacitysoft.validacion.validacion;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.telephony.SmsManager;


public class Recuperar_Contrasenia extends Activity {
	Button Enviar;
	Button cancelar;
	EditText email;
	String data1;
	JSONArray ja1;
	String pwd;
	  String celular1;
	ProgressDialog pdialog = null;
	Context context = null;
	
	String rec, subject, textMessage;
	Handler h = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			Toast.makeText(getApplicationContext(), "Mensaje Enviado", 3000).show();
		}
		
	};
	Handler h2 = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			Toast.makeText(getApplicationContext(), "Mensaje Fallido intente mas tarde", 3000).show();
		}
		
	};
	Handler h1 = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			Toast.makeText(getApplicationContext(), "No se encuentra el celular en nuestra base de datos", 3000).show();
		}
		
	};
	String getCadenaAlfanumAleatoria (int longitud){
		String cadenaAleatoria = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		int i = 0;
		while ( i < longitud){
		char c = (char)r.nextInt(255);
		if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
		cadenaAleatoria += c;
		i ++;
		}
		}
		return cadenaAleatoria;
		}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recuperar_contrasenia);
		email=(EditText)findViewById(R.id.txt_recuperarCont);
		Enviar=(Button)findViewById(R.id.btn_enviar);
		 cancelar=(Button)findViewById(R.id.btn_atrasrec);
		    cancelar.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
		Enviar.setOnClickListener(new OnClickListener() {
		String aux= getCadenaAlfanumAleatoria(5);
		
			@Override
			public void onClick(View v) {
				sendSMSMessage();
			}
			
			 
			protected void sendSMSMessage() {
			      Log.i("Send SMS", "");
			      final String aux1,aux2,aux3,telefono;
			    
			    final  String phoneNo = email.getText().toString();
			    validacion vd=new validacion();
				if (!vd.isValidPhone(phoneNo)) {
					email.setError("Campo Invalido");
				}else{
					celular1=phoneNo;
				}
				if(celular1!=null){
				
			     final String message =aux;
			     aux1=phoneNo.substring(0,4);
			     aux2=phoneNo.substring(4,7);
			     aux3=phoneNo.substring(7);
			     telefono=aux1+"-"+aux2+"-"+aux3;
			  	Executors.newSingleThreadExecutor().submit(new Runnable() {
			  	  @Override
		            public void run() {
			  		  HttpGetData hg=new HttpGetData();
			  	 	data1=  hg.httpGetData("http://192.168.1.8/ejemplo/concelular.php?celular="+telefono
	            			);
		    	if(data1.length()>0){
			      try {
			   
			         SmsManager smsManager = SmsManager.getDefault();
			         hg.httpGetData("http://192.168.1.8/ejemplo/actualizarcontraseniaC.php?celular="+telefono+
		            			"&contrasenia="+message);
			         smsManager.sendTextMessage(telefono, null, message, null, null);
			        
			        h.sendEmptyMessage(1);
			      } catch (Exception e) {
			        h2.sendEmptyMessage(1);
			         e.printStackTrace();
			      }
			  	  }else{
			  		h1.sendEmptyMessage(1);
						     
			  	  }
		    	
			  	  }});
			  	}
				
			}
		 });
	}

}
