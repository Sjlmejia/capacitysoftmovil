package com.tesis.capacitysoft;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.tesis.capacitysoft.servidor.HttpGetData;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import android.widget.TextView;

public class Menu_Capacitaciones extends Activity{
	Button historial_capacitaciones;
	Button capacitaciones_actuales;
	Button cancelar;
	String data2;
	JSONArray jas2;
	String data3;
	JSONArray jas3;
	TextView cedula;
	ImageView iv;
 	Handler h1 = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			Toast.makeText(getApplicationContext(), "No hay Capacitaciones Disponibles", 4000).show();
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consultar_historial);
		capacitaciones_actuales=(Button)findViewById(R.id.btn_CapDispon);
		historial_capacitaciones=(Button)findViewById(R.id.btn_hisCap);
		cancelar=(Button)findViewById(R.id.btn_cancelarmenucapacitaciones);

		 Bundle bolsa=getIntent().getExtras();
		 
		 final String aux=bolsa.getString("Capacitado");
		 
			final HttpGetData v=new HttpGetData();
			cancelar.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			historial_capacitaciones.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					// TODO Auto-generated method stub

					new Thread(new Runnable() {
						@Override
						public void run() {
						
							data2 = v.httpGetData("http://192.168.43.20/ejemplo/recuperacapacitacion.php?id="+aux);
						    Bundle bolsa =new Bundle();
						    Intent i = new Intent(Menu_Capacitaciones.this, Capacitaciones_pasadas.class );
							if (data2.length()>0){
								jas2=null;
							
								//String capacitacion=data2.toString();
									try {
										jas2= new JSONArray(data2);
									
									//JSONObject jo=new JSONObject(data2.toString());
									ArrayList<String> lista =new ArrayList<String>();
									//String capacitacion;
									for ( int j = 0; j <= jas2.length()-1; j ++ ) {
									     
									data3= v.httpGetData("http://192.168.43.20/ejemplo/recuperanombrecapacitacionp.php?id="+jas2.getString(j));
									if(data3.length()>0){
										jas3=new JSONArray(data3);
										
										 lista.add(jas3.getString(0));
										
									}
									}
									
									if(lista.isEmpty()){
										h1.sendEmptyMessage(1);
										}else{
									//lista.add(jas2.toString());
									//capacitacion=jo.getString("tema");
									//lista.add(jas2.toString());
									//capacita=jas2.toString();
										Bundle bolsa1=new Bundle();
										bolsa1.putString("cedula", aux);
										i.putExtras(bolsa1);
									bolsa.putStringArrayList("Nombre",lista);
									i.putExtras(bolsa);
									startActivity(i);
							 
									
								 }
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
							}
							  
						}
					}).start();	
	        	
					
				}
			});
		capacitaciones_actuales.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View view){
				new Thread(new Runnable() {
					@Override
					public void run() {
					
						data2 = v.httpGetData("http://192.168.43.20/ejemplo/recuperacapacitacion.php?id="+aux);
					    Bundle bolsa =new Bundle();
					    Intent i = new Intent(Menu_Capacitaciones.this, Capacitaciones.class );
						if (data2.length()>0){
							jas2=null;
						
							//String capacitacion=data2.toString();
								try {
									jas2= new JSONArray(data2);
								
								//JSONObject jo=new JSONObject(data2.toString());
								ArrayList<String> lista =new ArrayList<String>();
								//String capacitacion;
								for ( int j = 0; j <= jas2.length()-1; j ++ ) {
								     
								data3= v.httpGetData("http://192.168.43.20/ejemplo/recuperanombrecapacitacion.php?id="+jas2.getString(j));
								if(data3.length()>0){
									jas3=new JSONArray(data3);
									
									 lista.add(jas3.getString(0));
									
								}
								}
								
								if(lista.isEmpty()){
									h1.sendEmptyMessage(1);
								}else{
								//lista.add(jas2.toString());
								//capacitacion=jo.getString("tema");
								//lista.add(jas2.toString());
								//capacita=jas2.toString();
									Bundle bolsa1=new Bundle();
									bolsa1.putString("cedula", aux);
									i.putExtras(bolsa1);
								bolsa.putStringArrayList("Nombre",lista);
								i.putExtras(bolsa);
								startActivity(i);
						 
								
							 }
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
