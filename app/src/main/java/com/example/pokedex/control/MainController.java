package com.example.pokedex.control;

import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.pokedex.R;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.util.Constants;
import com.example.pokedex.util.HTTPSWebUtilDomi;
import com.example.pokedex.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;


public class MainController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener{

    private MainActivity activity;
    private HTTPSWebUtilDomi utilDomi;
    private Pokemon pokemon;

    public MainController(MainActivity activity){

        this.activity = activity;
        this.activity.getSearchBtn().setOnClickListener(this);
        this.activity.getCatchBtn().setOnClickListener(this);
        utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);
        LoadMyPokemons();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.searchBtn:
                String idName = activity.getIdNameET().getText().toString();

                new Thread(
                        ()->{
                            utilDomi.GETrequest(Constants.SEARCH_CALLBACK,"https://pokeapi.co/api/v2/pokemon/"+idName);
                        }
                ).start();

                break;

            case R.id.catchBtn:

                new Thread(
                        ()->{
                            Gson gson = new Gson();
                            String json = gson.toJson(pokemon);
                            utilDomi.POSTrequest(Constants.CATCH_CALLBACK,"https://pokedex-52db1.firebaseio.com/pokemons.json",json);
                        }

                ).start();
                break;
        }
    }

    @Override
    public void onResponse(int callbackID, String response) {
        switch (callbackID){
            case Constants.SEARCH_CALLBACK:
                Gson gson = new Gson();
                pokemon = gson.fromJson(response,Pokemon.class);
                activity.runOnUiThread(
                        ()->{
                            activity.getPokeNameTv().setText(pokemon.getForms()[0].getName());
                            activity.getPokeTypeTV().setText(pokemon.getTypes()[0].getType().getName());

                            activity.getPokeHab1TV().setText(pokemon.getMoves()[0].getMove().getName());
                            activity.getPokeHab2TV().setText(pokemon.getMoves()[1].getMove().getName());
                            activity.getPokeHab3TV().setText(pokemon.getMoves()[2].getMove().getName());
                            activity.getPokeHab4TV().setText(pokemon.getMoves()[3].getMove().getName());

                            activity.getPokeSpeedTV().setText("Speed: "+pokemon.getStats()[0].getBase_stat());
                            activity.getPokeDefenseTV().setText("Defense: "+pokemon.getStats()[3].getBase_stat());
                            activity.getPokeAttackTV().setText("Attack: "+ pokemon.getStats()[4].getBase_stat());
                            activity.getPokeHpTV().setText("Health: "+pokemon.getStats()[5].getBase_stat());

                            Glide.with(activity).load(pokemon.getSprites().getFront_default()).centerCrop().into(activity.getPokeImageIV());
                        }
                );
                Log.e(">>>",pokemon.getForms()[0].getName());
                break;

            case Constants.CATCH_CALLBACK:
                LoadMyPokemons();
                break;

            case Constants.GET_MY_POKEMON:
                Gson g = new Gson();
                Type type = new TypeToken<HashMap<String,Pokemon>>(){}.getType();
                HashMap<String,Pokemon> myPoke = g.fromJson(response,type);
                activity.runOnUiThread(
                        ()->{
                            activity.getMyPokemonsTV().setText("");
                            for (String key: myPoke.keySet()) {
                                Pokemon pokemon = myPoke.get(key);
                                activity.getMyPokemonsTV().append(pokemon.getForms()[0].getName()+"\n");
                            }
                        }
                );
                break;
        }
    }

    private void LoadMyPokemons() {
        new Thread(
                ()->{
                    utilDomi.GETrequest(Constants.GET_MY_POKEMON,"https://pokedex-52db1.firebaseio.com/pokemons.json");
                }
        ).start();
    }
}
