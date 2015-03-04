package com.tesis.capacitysoft;


import java.util.ArrayList;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;

import com.tesis.capacitysoft.servidor.HttpGetData;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi") public class Respuesta extends Activity {
	LayoutInflater inf;
	TextView nombre;
	JSONArray ja;
	String data;
	JSONArray ja2;
	String data2;
	JSONArray ja3;
	String data3;
	JSONArray ja4;
	String data4;
	JSONArray ja5;
	String data5;
	JSONArray ja6;
	String data6;
	JSONArray ja7;
	String data7;
	RadioButton ra;
	CheckBox ch;
	TextView cedula;
	Button guardar;
	String aux2;
	 String aux5;
	 Button cancelar;
	Handler h1 = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			Toast.makeText(getApplicationContext(), "Se actualizo la respuesta", 3000).show();
		}
		
	};
	Handler h = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			Toast.makeText(getApplicationContext(), "Se guardo la respuesta", 3000).show();
		}
		
	};
	 ArrayList<CheckBox> listas2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.respuesta);
		//nombre=(TextView)findViewById(R.id.lbl_nombrepregunta);
		//final RadioGroup contrasGrp=new RadioGroup(Respuesta.this);
		cedula=(TextView)findViewById(R.id.lbl_cedula2);
		 Bundle bolsa=getIntent().getExtras();
		 Bundle bolsa1=getIntent().getExtras();
		 Bundle bolsa2=getIntent().getExtras();
		 Bundle bolsa3=getIntent().getExtras();
		 final String aux3=bolsa1.getString("cedula");
		 final String aux4=bolsa2.getString("evaid");
		 final String aux=bolsa.getString("Pregunta");
		 final String aux6=bolsa3.getString("calificacionr");
		 cancelar=(Button)findViewById(R.id.btn_cancelarrespuesta);
	//	 cedula.setText(aux4);
		 guardar=(Button)findViewById(R.id.btn_guardarRespuesta);
		 
		 //final LinearLayout ly=new LinearLayout(Respuesta.this);
		// nombre.setText(aux);
		cancelar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		 ja = null;
		 final LinearLayout pantalla=(LinearLayout)findViewById(R.id.lyt_respuesta);
		new Thread(new Runnable() {
				
				@Override
				public void run() {
					//ly.setOrientation(ly.VERTICAL);
					//final ArrayList<RadioButton> listas=new ArrayList<RadioButton>();
					
					HttpGetData v=new HttpGetData();
					 
					data = v.httpGetData("http://192.168.43.20/ejemplo/recuperaridpreguntita.php?descripcion="+aux);
					if(data.length()>0){
						try {
							ja=new JSONArray(data);
							aux2=ja.getString(0);
							data3 = v.httpGetData("http://192.168.43.20/ejemplo/recuperaidrespuestas.php?id="+aux2);
					data2 = v.httpGetData("http://192.168.43.20/ejemplo/recuperarespuestas.php?id="+aux2);
					if(data2.length()>0){
						listas2=new ArrayList<CheckBox>();
						ja2=new JSONArray(data2);
						ja4=new JSONArray(data3);
						Respuesta.this.runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								  for(int i=0;i<=ja2.length()-1;i++) {
					                    
					                    //RadioButton pswOpcion = new RadioButton(MainActivity.this);
									   ch=new CheckBox(Respuesta.this);
					                	
											try {
												ch.setText( ja2.getString(i));
												ch.setId(ja4.getInt(i));
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										
					                	//contrasGrp.addView(ra);
					                	//listas.add(ra);   
					                	listas2.add(ch);
					                	//ly.addView(ch);
					                	 pantalla.addView(ch);       
					                 } 
							}
						});
						 
						
						
					}
						}catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
						
					}
					 guardar.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View v) {
								
										// TODO Auto-generated method stub
										//HttpGetData l=new HttpGetData();
										final ArrayList<Integer> listasauxiliar=new ArrayList<Integer>();
					    				for(int i=0;i<=listas2.size()-1;i++){
					    					if(listas2.get(i).isChecked()){
					    						final Object aux=listas2.get(i).getId();
					    						cedula.setText(aux4);
					    						
					    						Executors.newSingleThreadExecutor().submit(new Runnable() {
					    				            @Override
					    				            public void run() {
					    				                //You can performed your task here.
					    				            	HttpGetData vd=new HttpGetData();
					    				            	data6 = vd.httpGetData("http://192.168.43.20/ejemplo/recuperarcorrecto.php?id="+aux);
					    				            	if (data6.length()>0){
					    				            		
					    				            		try {
																ja6=new JSONArray(data6);
																if(ja6.getString(0).equals("1")){
																	
																//data5=vd.httpGetData("http://192.168.1.2/ejemplo/recuperaridevacapa.php?capacitado_id="+aux3+
							    				            		//	"&pregunta_id="+aux2+"&respuesta_id="+aux);
							    				            	//if(data5.length()>0){
							    				            		
																	//	ja5=new JSONArray(data5);
																	//	 aux5=ja.getString(0);
																		 data7=vd.httpGetData("http://192.168.43.20/ejemplo/recuperaidrespuescapacitado.php?capacitado_id="+aux3+
										    				            			"&pregunta_id="+aux2+"&evaluacion_id="+aux4);
																		 if(data7.length()>0){
																			 vd.httpGetData("http://192.168.43.20/ejemplo/eliminarregistro.php?capacitado_id="+aux3+
											    				            			"&pregunta_id="+aux2+"&evaluacion_id="+aux4);
																		// vd.httpGetData("http://192.168.1.2/ejemplo/actualizarregistroevaluacion.php?capacitado_id="+aux3+
																				// "&pregunta_id="+aux2+"&respuesta_id="+
												    								//aux+ "&id="+aux5+"&calificacion_estudiante="+aux6+"&evaluacion_id="+aux4);
																			 vd.httpGetData("http://192.168.43.20/ejemplo/registroevaluacion.php?capacitado_id="+aux3+"&pregunta_id="+aux2+"&respuesta_id="
													    				            	+aux+"&calificacion_estudiante="+aux6+"&evaluacion_id="+aux4);
									    				            		h.sendEmptyMessage(1);
									    				            		finish();
																		 }
							    				                   	
							    				            	//}
																		 else{
							    				            		 vd.httpGetData("http://192.168.43.20/ejemplo/eliminarregistro.php?capacitado_id="+aux3+
									    				            			"&pregunta_id="+aux2+"&evaluacion_id="+aux4);
							    				            	vd.httpGetData("http://10.20.20.19/ejemplo/registroevaluacion.php?capacitado_id="+aux3+"&pregunta_id="+aux2+"&respuesta_id="
							    				            	+aux+"&calificacion_estudiante="+aux6+"&evaluacion_id="+aux4);
									    								
							    				            	h.sendEmptyMessage(1);
							    				            	finish();
									    						
							    				            }
																}else{
																	Double califica=-Double.parseDouble(aux6);
																	String calificacion2=califica.toString();
																//data5=vd.httpGetData("http://192.168.1.2/ejemplo/recuperaridevacapa.php?capacitado_id="+aux3+
							    				            		//	"&pregunta_id="+aux2+"&respuesta_id="+aux+"&evaluacion_id="+aux4);
																//if(data5.length()>0){
							    				            		
																	//ja5=new JSONArray(data5);
																	 //aux5=ja.getString(0);
																	 data7=vd.httpGetData("http://192.168.43.20/ejemplo/recuperaidrespuescapacitado.php?capacitado_id="+aux3+
									    				            			"&pregunta_id="+aux2+"&evaluacion_id="+aux4);
																	 if(data7.length()>0){
																		 vd.httpGetData("http://192.168.43.20/ejemplo/eliminarregistro.php?capacitado_id="+aux3+
										    				            			"&pregunta_id="+aux2+"&evaluacion_id="+aux4);
																	// vd.httpGetData("http://192.168.1.2/ejemplo/actualizarregistroevaluacion.php?capacitado_id="+aux3+
																			// "&pregunta_id="+aux2+"&respuesta_id="+
											    								//aux+ "&id="+aux5+"&calificacion_estudiante="+aux6+"&evaluacion_id="+aux4);
																		 vd.httpGetData("http://192.168.43.20/ejemplo/registroevaluacion.php?capacitado_id="+aux3+"&pregunta_id="+aux2+"&respuesta_id="
												    				            	+aux+"&calificacion_estudiante="+calificacion2+"&evaluacion_id="+aux4);
								    				            		h.sendEmptyMessage(1);
								    				            		finish();
																	 //}
						    				                   	
						    				            	}else{
						    				            		 vd.httpGetData("http://192.168.43.20/ejemplo/eliminarregistro.php?capacitado_id="+aux3+
								    				            			"&pregunta_id="+aux2+"&evaluacion_id="+aux4);
						    				            	vd.httpGetData("http://192.168.43.20/ejemplo/registroevaluacion.php?capacitado_id="+aux3+"&pregunta_id="+aux2+"&respuesta_id="
						    				            	+aux+"&calificacion_estudiante="+calificacion2+"&evaluacion_id="+aux4);
								    								
						    				            	h.sendEmptyMessage(1);
						    				            	finish();
								    						
						    				            }
																
																	
																}
															} catch (JSONException e1) {
																// TODO Auto-generated catch block
																e1.printStackTrace();
															}
					    				            	}
					    				            }
					    				        });
					    						listasauxiliar.add(listas2.get(i).getId());
					    					}
					    						
					    				}
								
							//	data3 = l.httpGetData("http://192.168.43.20/ejemplo/guardarRespuesta.php?capacitadoid="+aux3+"&pregunta_id="+tipoCapacitado.getText());

			    				// TODO Auto-generated method stub
			    				
							}
						});

				}
				
				
			}).start();
		
		
			
//pantalla.addView(new CheckBox(ly.));


	} 
	
	}


