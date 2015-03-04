package com.tesis.capacitysoft;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import android.content.Intent;
public class Menu_principal extends Activity {
	Button recursos;
	Button prueba;
	Button consultar_capacitado;
	Button capTwi;
	Button informacion_capacitacion;
	EditText recurso;
	JSONArray ja;
	JSONArray jas2;
	JSONArray jas3;
	JSONArray jas4;
	JSONArray jas5;
	JSONArray jas6;
	JSONArray jas7;
	String data;
	String data2;
	String data3;
	String data4;
	String data5;
	String data6;
	String data7;
	String aux;
	String aux1;
	TextView texto;
	TextView Capacitacion_recuperada;
	Button cancelar;
	Handler h = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			texto.setText(ja.toString());
			
		}	
	};
	Handler h1 = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			Toast.makeText(getApplicationContext(), "No existen pruebas disponibles", 3000).show();
		}
		
	};
	Handler h2 = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			Toast.makeText(getApplicationContext(), "No hay capacitadores disponbles", 3000).show();
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_principal);
		recursos=(Button)findViewById(R.id.btn_recurso);
		prueba=(Button)findViewById(R.id.btn_prueba);
		consultar_capacitado=(Button)findViewById(R.id.btn_concap);
		capTwi=(Button)findViewById(R.id.btn_captwi);
		informacion_capacitacion=(Button)findViewById(R.id.btn_iCapacitacion);
		recurso=(EditText)findViewById(R.id.lbl_recursos);
		Capacitacion_recuperada=(TextView)findViewById(R.id.txt_capRecuperada);
		 Bundle bolsa=getIntent().getExtras();
		 Bundle bolsa1=getIntent().getExtras();
		 aux=bolsa.getString("Capacitacion");
		 aux1=bolsa1.getString("cedula");
		 cancelar=(Button)findViewById(R.id.btn_cancelarmenuprincipal);
		 Capacitacion_recuperada.setText( aux);
		 cancelar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		 prueba.setOnClickListener(new View.OnClickListener(){
				
	        	public void onClick(View view){
	        		
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							
							ja = null;
							data = httpGetData("http://192.168.1.8/ejemplo/recuperaidevaluacion.php?tema="+aux);
							if (data.length()>0){
								try {
									ja = new JSONArray(data);
									String aux2=ja.getString(0);
									data7 = httpGetData("http://192.168.1.8/ejemplo/recuperanombreevaluacion.php?id="+aux2);		
									if (data7.length()>0){	
									jas7 = new JSONArray(data7);
									String aux3=jas7.getString(0);
					                Intent i = new Intent(Menu_principal.this, Prueba.class );
								    Bundle bolsa =new Bundle();
								    Bundle bolsa1=new Bundle();
								    bolsa1.putString("cedula", aux1);
									bolsa.putString("Evaluacion", aux3);
									i.putExtras(bolsa);
									i.putExtras(bolsa1);
									startActivity(i);
									}else{
										h1.sendEmptyMessage(1);
									}
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	
							}
						}
					}).start();	
	        	}  													});
		 informacion_capacitacion.setOnClickListener(new View.OnClickListener(){
				
	        	public void onClick(View view){
	        		
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							
							ja = null;
							data = httpGetData("http://192.168.1.8/ejemplo/recuperatodocapacitacion.php?tema="+aux);
							if (data.length()>0){
								try {
									ja = new JSONArray(data);
									String aux2=ja.getString(0);
									ArrayList<String> lista =new ArrayList<String>();
									for ( int j = 0; j <= ja.length()-1; j ++ ) {
									      lista.add(ja.getString(j));
									}
					                Intent i = new Intent(Menu_principal.this, Informacion_capacitacion.class );
								    Bundle bolsa =new Bundle();
									bolsa.putStringArrayList("Capacitacion", lista);
									i.putExtras(bolsa);
									startActivity(i);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	
							}
						}
					}).start();	
	        	}  													});
		recursos.setOnClickListener(new View.OnClickListener(){
			
        	public void onClick(View view){
        		
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						ja = null;
						data = httpGetData("http://192.168.1.8/ejemplo/recuperaidCap.php?tema="+aux);
						if (data.length()>0){
							try {
								ja = new JSONArray(data);
								String aux2=ja.getString(0);
								data2 = httpGetData("http://192.168.1.8/ejemplo/recuperaRecurso.php?capacitacion="+aux2);
								Intent i = new Intent(Menu_principal.this, Recursos.class );
							    Bundle bolsa =new Bundle();
								if (data2.length()>0){
									jas2= new JSONArray(data2);
									ArrayList<String> lista =new ArrayList<String>();
									for ( int j = 0; j <= jas2.length()-1; j ++ ) {
									      lista.add(jas2.getString(j));
									}
									if(lista.isEmpty()){
										 ArrayList<String> lista2 =new ArrayList<String>();
										 lista2.add("No hay Recursos Disponibles");
											bolsa.putStringArrayList("Nombre",lista2);
											i.putExtras(bolsa);
											startActivity(i);
									}else{
									bolsa.putStringArrayList("Nombre",lista);
									i.putExtras(bolsa);
									startActivity(i);
								 }
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
						}
					}
				}).start();	
        	}  													});
		consultar_capacitado.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
						new Thread(new Runnable() {
					@Override
					public void run() {
						Bundle bolsa =new Bundle();
						
						Intent i=new Intent(Menu_principal.this, Consultar_Capacitador1.class);
						jas3 = null;
						jas4=null;
						jas5=null;
						data3 = httpGetData("http://192.168.1.8/ejemplo/recuperaidCap.php?tema="+aux);
						ArrayList<String> lista =new ArrayList<String>();
						if (data3.length()>0){
							try {
								jas3=new JSONArray(data3);
								String aux3=jas3.getString(0);
								data4=httpGetData("http://192.168.1.8/ejemplo/recuperaidCapacitador.php?Capacitacion_id="+aux3);
								if(data4.length()>0){
									jas4=new JSONArray(data4);
								
								for(int j=0;j<=jas4.length()-1;j++){
								data5=httpGetData("http://192.168.1.8/ejemplo/recuperacedulaCapacitador.php?id="+jas4.getString(j));	
									if(data5.length()>0){
										jas5=new JSONArray(data5);
								data6=httpGetData("http://192.168.1.8/ejemplo/recuperanombreCCapacitador.php?cedula="+jas5.getString(0));	
									if(data6.length()>0){
										jas6=new JSONArray(data6);
										lista.add(jas6.getString(2)+" "+jas6.getString(0)+" "+jas6.getString(1));
									
									}
									}
								}
								if(lista.isEmpty()){
									h2.sendEmptyMessage(1);
								}else{
									bolsa.putStringArrayList("Nombre", lista);
									i.putExtras(bolsa);
									startActivity(i);
								}	
									
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
		
	}
	public String httpGetData(String mURL){
		
		String response="";
		mURL= mURL.replace(" ", "%20");
		HttpClient httpclient = new  DefaultHttpClient();
		HttpGet httpget = new HttpGet(mURL);
		
		ResponseHandler<String> responsehandler = new BasicResponseHandler();
		try {
			response = httpclient.execute(httpget, responsehandler);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
		
		
	}
	
	//Clase interna para ejecutar tareas en segundo plano con dialogos
	public class activity_principal extends AsyncTask<Void, Void, Void>{
		//metodo que se va a ejecutar antes del hilo por de debajo
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
		//hilo que se va a ejecutar por debajo lo mismo que tienes en el hilo del boton
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return null;
		}
		//metodo final luego de ejecutar el hilo costoso
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
	}
	
}
