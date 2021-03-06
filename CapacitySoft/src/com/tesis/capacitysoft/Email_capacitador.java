package com.tesis.capacitysoft;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Email_capacitador extends Activity {
	EditText contenido;
	Button enviar;
	Button cancelar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email_capacitador);
		Bundle bolsa=getIntent().getExtras();
		final String aux=bolsa.getString("Capacitador");
		
	    enviar=(Button)findViewById(R.id.btn_enviarMensaje); 
	    contenido=(EditText)findViewById(R.id.txt_correoCapacitador);
	    cancelar=(Button)findViewById(R.id.btn_emailcapacitador);
	    cancelar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		enviar.setOnClickListener(new OnClickListener() {
			  
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				sendSMSMessage();
			}
			 
			protected void sendSMSMessage() {
			      Log.i("Send SMS", "");

			      String phoneNo = aux;
			      
			      String message = contenido.getText().toString();

			      try {
			         SmsManager smsManager = SmsManager.getDefault();
			         smsManager.sendTextMessage(phoneNo, null, message, null, null);
			         Toast.makeText(getApplicationContext(), "SMS sent.",
			         Toast.LENGTH_LONG).show();
			      } catch (Exception e) {
			         Toast.makeText(getApplicationContext(),
			         "SMS faild, please try again.",
			         Toast.LENGTH_LONG).show();
			         e.printStackTrace();
			      }
			   }
		 });

}
}