package com.example.pokedex.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokedex.R;
import com.example.pokedex.control.MainController;

public class MainActivity extends AppCompatActivity {

    private ImageView pokeImageIV;

    private TextView pokeNameTv;
    private TextView pokeTypeTV;

    private TextView pokeHab1TV;
    private TextView pokeHab2TV;
    private TextView pokeHab3TV;
    private TextView pokeHab4TV;

    private TextView pokeSpeedTV;
    private TextView pokeAttackTV;
    private TextView pokeDefenseTV;
    private TextView pokeHpTV;

    private TextView myPokemonsTV;
    private EditText idNameET;

    private Button catchBtn;
    private Button searchBtn;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokeImageIV =  findViewById(R.id.pokeImageIV);
        pokeNameTv = findViewById(R.id.pokeNameTV);
        pokeTypeTV = findViewById(R.id.pokeTypeTV);

        pokeHab1TV = findViewById(R.id.pokeHab1TV);
        pokeHab2TV = findViewById(R.id.pokeHab2TV);
        pokeHab3TV = findViewById(R.id.pokeHab3TV);
        pokeHab4TV = findViewById(R.id.pokeHab4TV);

        pokeSpeedTV = findViewById(R.id.pokeSpeedTV);
        pokeAttackTV = findViewById(R.id.pokeAttackTV);
        pokeDefenseTV = findViewById(R.id.pokeDefenseTV);
        pokeHpTV = findViewById(R.id.pokeHpTV);

        myPokemonsTV = findViewById(R.id.myPokemonsTV);
        myPokemonsTV.setMovementMethod(new ScrollingMovementMethod());

        idNameET = findViewById(R.id.idNameET);

        catchBtn = findViewById(R.id.catchBtn);
        searchBtn = findViewById(R.id.searchBtn);

        controller= new MainController(this);
    }

    public ImageView getPokeImageIV() {
        return pokeImageIV;
    }

    public TextView getPokeNameTv() {
        return pokeNameTv;
    }

    public TextView getPokeTypeTV() {
        return pokeTypeTV;
    }

    public TextView getPokeHab1TV() {
        return pokeHab1TV;
    }

    public TextView getPokeHab2TV() {
        return pokeHab2TV;
    }

    public TextView getPokeHab3TV() {
        return pokeHab3TV;
    }

    public TextView getPokeHab4TV() {
        return pokeHab4TV;
    }

    public TextView getPokeSpeedTV() {
        return pokeSpeedTV;
    }

    public TextView getPokeAttackTV() {
        return pokeAttackTV;
    }

    public TextView getPokeDefenseTV() {
        return pokeDefenseTV;
    }

    public TextView getPokeHpTV() {
        return pokeHpTV;
    }

    public TextView getMyPokemonsTV() {
        return myPokemonsTV;
    }

    public EditText getIdNameET() {
        return idNameET;
    }

    public Button getCatchBtn() {
        return catchBtn;
    }

    public Button getSearchBtn() {
        return searchBtn;
    }
}
