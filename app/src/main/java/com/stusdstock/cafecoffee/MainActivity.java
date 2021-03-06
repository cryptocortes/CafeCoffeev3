package com.stusdstock.cafecoffee;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private IntroFragment frag1;
    private ComidasFragment frag2;
    private ContactsFragment frag3;
    private ShopFragment fragShop;
    private FragmentTransaction fTrans;

    String TAG = "DataBase";
    ArrayList<Comida> listaComidas;
    String pho = "https://turboportal.ru/uploads/posts/2014-05/thumbs/1399971466__.jpg";

    public void setMenu(){
        listaComidas = new ArrayList<>();
        adicionarComida(new Comida(pho,"Эспрессо ", "Espresso / Крепкий, яркий, насыщенный, основа всех кофейных напитков","Buy 60р/30мл",0));
        adicionarComida(new Comida(pho, "Латте ", "Latte / Эспрессо, молоко, взбитое на пару и немного молочной пены","Buy 130р/300мл",1));
        adicionarComida(new Comida(pho, "Капуччино  ", "Cappuccino / Эспрессо, молоко, взбитое на пару и плотная молочная пена","Buy 110р/200мл",2));
        adicionarComida(new Comida(pho, "Флэт Уайт ", "Приготовлен на основе двойного ристретто и молока, подогретого на пару, с небольшим количеством пены, молоко очень жидкое. ","Buy 150р/220",3));
        adicionarComida(new Comida(pho, "Латте карамель  ", "Эспрессо п/ф, молоко, карамельный соус п/ф (сахар, молоко, растительные сливки, ванилин)","Buy 180р/250мл",4));
        adicionarComida(new Comida(pho, "Латте сингапур  ", "Новый авторский латте с добавлением фирменного карамельно-лимонного топпинга","Buy 180р/250мл",5));
        adicionarComida(new Comida(pho, "Американо  ", "Americano / Двойная порция классического эспрессо, смягченная добавлением горячей воды","Buy 110р/250мл",6));
        adicionarComida(new Comida(pho, "Горячий шоколад  ", "Горячий шоколад ","Buy 130р/300мл",7));
        Data.Menu =listaComidas;
    }

    public void adicionarComida(Comida comida){
        if (!listaComidas.contains(comida)) listaComidas.add(comida);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello, World!");
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });

        frag1 = new IntroFragment();
        frag2 = new ComidasFragment();
        frag3 = new ContactsFragment();


        fragShop = new ShopFragment();
        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.frgmCont, frag1);
        fTrans.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setMenu();
    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fTrans = getFragmentManager().beginTransaction();
                    fTrans.replace(R.id.frgmCont, frag1);
                    fTrans.commit();
                    Log.d("Click", "History");
                    return true;
                case R.id.navigation_dashboard:
                    fTrans = getFragmentManager().beginTransaction();
                    fTrans.replace(R.id.frgmCont, frag2);
                    fTrans.commit();
                    Log.d("Click", "Dashboard");
                    return true;
                case R.id.navigation_notifications:
                    fTrans = getFragmentManager().beginTransaction();
                    fTrans.replace(R.id.frgmCont, frag3);
                    fTrans.commit();
                    Log.d("Click", "Settings");
                    return true;
                default:
                    fTrans = getFragmentManager().beginTransaction();
                    fTrans.add(R.id.frgmCont, frag1);
                    fTrans.commit();
                    break;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_shop) {
            Intent i = new Intent(getApplicationContext(), ShopActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.activity_open_enter,0);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_close_bottom,0);
    }

}
