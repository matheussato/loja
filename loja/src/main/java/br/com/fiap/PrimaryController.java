package br.com.fiap;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import br.com.fiap.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable {

    @FXML TextField textFieldNome;
    @FXML TextField textFieldEmail;
    @FXML PasswordField passwordField;
    @FXML ChoiceBox<String> choiceBoxPerfil;
    @FXML CheckBox checkBoxAceita;
    @FXML Button buttonSalvar;

    String server = "185.211.7.205";
        String database = "u553405907_fiap";
        String username = "u553405907_fiap";
        String pass = "Fiap@2022";
        String url = "jdbc:mysql://" + server + ":3306/" + database;


    public void salvar(){
       
        var usuario = carregarUsuarioDoFormulario();
        System.out.println(usuario);

        try{

            Connection con = DriverManager.getConnection(url, username, pass);
            Statement stm = con.createStatement();
            String sql = String.format("INSET INTO USUARIO (id , nome, email, senha, perfil)" + 
                        "VALUES (0, '%s', '%s', '%s', '%s')",
                            usuario.getNome(),
                            usuario.getEmail(),
                            usuario.getSenha(),
                            usuario.getPerfil()
                        );
            System.out.println(sql);
            stm.execute(sql);
            con.close();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }


    private Usuario carregarUsuarioDoFormulario(){

        String nome = textFieldNome.getText();
        String email = textFieldEmail.getText();
        String senha = passwordField.getText();
        String perfil = choiceBoxPerfil.getValue();
        Usuario usuario = new Usuario(nome, email, senha, perfil);
        return usuario;
    }

    public void handle(){
        buttonSalvar.setDisable(!checkBoxAceita.isSelected());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        choiceBoxPerfil.getItems().addAll(List.of("USUARIO", "VENDEDOR","GERENTE" ));
    }
   
}
