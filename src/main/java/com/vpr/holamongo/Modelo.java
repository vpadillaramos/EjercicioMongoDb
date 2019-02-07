package com.vpr.holamongo;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.vpr.base.Coche;
import com.vpr.util.Constantes;
import com.vpr.util.Metodos;

public class Modelo {
	//Atributos
	private MongoClient mongoClient;
	private MongoDatabase db;
	private List<Coche> listaCoches;
	
	//Constructor
	public Modelo() {
		listaCoches = new ArrayList<Coche>();
	}
	
	//Metodos
	public void conectar() {
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		mongoClient = new MongoClient(Constantes.HOST,
				MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
		db = mongoClient.getDatabase(Constantes.DB);
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		desconectar();
	}

	public void desconectar() {
		mongoClient.close();
	}
	
	//OTRA OPCION MAS COMPLEJA
	/*public void guardar(Coche coche) {
		Document documento = new Document()
				.append("modelo", coche.getModelo())
				.append("marca", coche.getMarca())
				.append("precio", coche.getPrecio())
				.append("fechaCompra", coche.getFechaCompra());
		db.getCollection(Constantes.DB).insertOne(documento);
	}*/
	
	public void guardar(Coche coche) {
		MongoCollection<Coche> coleccionCoches = db.getCollection("coches", Coche.class);
		coleccionCoches.insertOne(coche);
	}
	
	public void modificar(Coche coche) {
		/*Document antiguo = new Document()
				.append("modelo", coche.getModelo())
				.append("marca", coche.getMarca())
				.append("precio", coche.getPrecio())
				.append("fechaCompra", coche.getFechaCompra());*/
		MongoCollection<Coche> coleccionCoches = db.getCollection("coches", Coche.class);
		coleccionCoches.replaceOne(eq("_id", coche.getId()), coche);
	}
	
	/*public void buscar(Coche coche) {
		Document documento = new Document()
				.append("modelo", coche.getModelo())
				.append("marca", coche.getMarca())
				.append("precio", coche.getPrecio())
				.append("fechaCompra", coche.getFechaCompra());
		FindIterable<Document> findIterable = db.getCollection(Constantes.DB)
				.find(documento)
				.limit(10);
		
		List<Coche> coches = new ArrayList<Coche>();
		Coche coche2 = null;
		Iterator<Document> iter = findIterable.iterator();
		
		while(iter.hasNext()) {
			coche2 = new Coche();
			coche2.setId(documento.getObjectId("_id"));
			coche2.setMarca(documento.getString("marca"));
			coche2.setModelo(documento.getString("modelo"));
			coche2.setPrecio(Float.parseFloat(documento.getDouble("precio").toString()));
			coche2.setFechaCompra(documento.getDate("fechaCompra"));
			coches.add(coche2);
		}
	}*/
	
	public void borrar(Coche coche) {
		MongoCollection<Coche> coleccionCoches = db.getCollection("coches", Coche.class);
		coleccionCoches.deleteOne(eq("_id", coche.getId()));
	}
	
	public List<Coche> getCoches(){
		MongoCollection<Coche> coleccionCoches = db.getCollection("coches", Coche.class);
		return coleccionCoches.find().into(new ArrayList<Coche>());
	}
	
	//OTRA OPCION MAS COMPLEJA
	/*public List<Coche> getCoches(String marca){
		Document document = new Document("marca", marca);
		FindIterable<Document> resultado = db.getCollection("taller").find(document);
		
		List<Coche> coches = new ArrayList<>();
		resultado.forEach(new Consumer<Document>() {

			@Override
			public void accept(Document d) {
				Coche coche = new Coche();
				coche.setMarca(document.getString("marca"));
				coche.setModelo(document.getString("modelo"));
				coche.setPrecio(new Double(document.getDouble("precio")).floatValue());
				coche.setFechaCompra(Metodos.toLocalDate(document.getDate("fechaCompra")));
				coches.add(coche);
			}
			
		});
		return coches;
	}*/
}
