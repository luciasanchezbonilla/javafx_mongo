package com.empresa.javafx_mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.bson.Document;

import java.util.function.Consumer;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        try {
            String url = "mongodb+srv://lector:lector@cluster0.hbso5zx.mongodb.net/";
            MongoClient conexion = MongoClients.create(url);
            MongoDatabase database = conexion.getDatabase("practica1");
            MongoCollection<Document> collection = database.getCollection("clientes");

            ObservableList<String> resultado = FXCollections.observableArrayList();

            // Iterar sobre los documentos y agregarlos a la lista
            collection.find().forEach((Consumer<? super Document>) document -> resultado.add(document.toJson()));

            // Mostrar los resultados en la consola
            resultado.forEach(System.out::println);

            // También puedes usar los resultados para actualizar la GUI aquí

            conexion.close(); // Cerrar la conexión después de su uso
        } catch (Exception e) {
            e.printStackTrace(); // Manejar cualquier excepción
        }
    }
}
