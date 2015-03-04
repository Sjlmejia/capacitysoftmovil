package com.tesis.capacitysoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class Enviar_correocapacitador extends Activity {

	EditText contenido;
	Button enviar;
	EditText asunto;
	Button cancelar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enviar_correocapacitador);
		Bundle bolsa=getIntent().getExtras();
		final String aux=bolsa.getString("Capacitador");
	    enviar=(Button)findViewById(R.id.btn_enviarcorreo); 
	    contenido=(EditText)findViewById(R.id.txt_enviarcorreo);
	    asunto=(EditText)findViewById(R.id.txt_asunto);
	    cancelar=(Button)findViewById(R.id.btn_cancelar_enviarcc);
	    cancelar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		enviar.setOnClickListener(new OnClickListener() {
			  
			@Override
			public void onClick(View v) {
				
				if(!aux.equals("")){
					Intent i=new Intent(android.content.Intent.ACTION_SEND);
					i.setType("message/rfc822");
					i.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{aux});
					i.putExtra(android.content.Intent.EXTRA_SUBJECT, asunto.getText().toString());
					i.putExtra(android.content.Intent.EXTRA_TEXT, contenido.getText().toString());
					startActivity(i.createChooser(i, "Enviando Correo"));
				}
			}
			 
			
		 });

}


}
