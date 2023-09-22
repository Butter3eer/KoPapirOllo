package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView gepHeart1;
    private ImageView gepHeart2;
    private ImageView gepHeart3;

    private ImageView jatekosHeart1;
    private ImageView jatekosHeart2;
    private ImageView jatekosHeart3;

    private TextView dontetlenTextview;
    private ImageView gepValasztasaKep;
    private ImageView jatekosValasztasaKep;
    private ImageView jatekosOpcioRock;
    private ImageView jatekosOpcioPaper;
    private ImageView jatekosOpcioScissors;
    private int gepElet;
    private int jatekosElet;
    private int dontetlen;
    private int gepDontes;
    private int jatekosDontes;
    private final Random random = new Random();
    private AlertDialog.Builder alertGameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        jatekosOpcioRock.setOnClickListener(view -> {
            jatekosDontes = 0;
            gepChoice();
            jatekosValasztasaKep.setImageResource(R.drawable.rock);
            ellenorzes();
        });

        jatekosOpcioPaper.setOnClickListener(view -> {
            jatekosDontes = 1;
            gepChoice();
            jatekosValasztasaKep.setImageResource(R.drawable.paper);
            ellenorzes();
        });

        jatekosOpcioScissors.setOnClickListener(view -> {
            jatekosDontes = 2;
            gepChoice();
            jatekosValasztasaKep.setImageResource(R.drawable.scissors);
            ellenorzes();
        });
    }

    public void init() {
        gepHeart1 = findViewById(R.id.gepHeart1);
        gepHeart2 = findViewById(R.id.gepHeart2);
        gepHeart3 = findViewById(R.id.gepHeart3);

        jatekosHeart1 = findViewById(R.id.jatekosHeart1);
        jatekosHeart2 = findViewById(R.id.jatekosHeart2);
        jatekosHeart3 = findViewById(R.id.jatekosHeart3);

        dontetlenTextview = findViewById(R.id.dontetlenTextview);
        gepValasztasaKep = findViewById(R.id.gepValasztasaKep);
        jatekosValasztasaKep = findViewById(R.id.jatekosValasztasaKep);
        jatekosOpcioRock = findViewById(R.id.jatekosOpcioRock);
        jatekosOpcioPaper = findViewById(R.id.jatekosOpcioPaper);
        jatekosOpcioScissors = findViewById(R.id.jatekosOpcioScissors);

        gepElet = 3;
        jatekosElet = 3;
        dontetlen = 0;

        alertGameOver = new AlertDialog.Builder(this);
        alertGameOver.setTitle("Játék vége")
                .setMessage("Újra akarod kezdeni?")
                .setNegativeButton("Nem", (dialogInterface, i) -> {
                    finish();
                })
                .setPositiveButton("Igen", (dialogInterface, i) -> {
                    newGame();
                })
                .setCancelable(false)
                .create();
    }

    public void gepChoice() {
        int kepIndex = random.nextInt(3);

        switch (kepIndex) {
            case 0:
                gepValasztasaKep.setImageResource(R.drawable.rock);
                gepDontes = 0;
                break;
            case 1:
                gepValasztasaKep.setImageResource(R.drawable.paper);
                gepDontes = 1;
                break;
            case 2:
                gepValasztasaKep.setImageResource(R.drawable.scissors);
                gepDontes = 2;
                break;
        }
    }

    public void ellenorzes() {
        if (gepDontes == jatekosDontes) {
            dontetlen++;
            dontetlenTextview.setHint("Döntetlenek száma: " + dontetlen);
        }

        if (gepDontes == 0 && jatekosDontes == 1) {
            gepElet--;
            eletek();
        }
        else if (gepDontes == 1 && jatekosDontes == 2) {
            gepElet--;
            eletek();
        }
        else if (gepDontes == 2 && jatekosDontes == 0) {
            gepElet--;
            eletek();
        }
        if (gepElet == 0) {
            alertGameOver.setTitle("Győzelem").create();
            alertGameOver.show();
        }

        if (gepDontes == 1 && jatekosDontes == 0) {
            jatekosElet--;
            eletek();
        }
        else if (gepDontes == 0 && jatekosDontes == 2) {
            jatekosElet--;
            eletek();
        }
        else if (gepDontes == 2 && jatekosDontes == 1) {
            jatekosElet--;
            eletek();
        }
        if (jatekosElet == 0) {
            alertGameOver.setTitle("Vereség").create();
            alertGameOver.show();
        }
    }

    public void eletek() {
        switch (jatekosElet) {
            case 2:
                jatekosHeart1.setImageResource(R.drawable.heart1);
                break;
            case 1:
                jatekosHeart2.setImageResource(R.drawable.heart1);
                break;
            case 0:
                jatekosHeart3.setImageResource(R.drawable.heart1);
                break;
        }

        switch (gepElet) {
            case 2:
                gepHeart1.setImageResource(R.drawable.heart1);
                break;
            case 1:
                gepHeart2.setImageResource(R.drawable.heart1);
                break;
            case 0:
                gepHeart3.setImageResource(R.drawable.heart1);
                break;
        }
    }

    public void newGame() {
        gepHeart1.setImageResource(R.drawable.heart2);
        gepHeart2.setImageResource(R.drawable.heart2);
        gepHeart3.setImageResource(R.drawable.heart2);

        jatekosHeart1.setImageResource(R.drawable.heart2);
        jatekosHeart2.setImageResource(R.drawable.heart2);
        jatekosHeart3.setImageResource(R.drawable.heart2);

        dontetlenTextview.setHint("Döntetlenek száma: 0");

        dontetlen = 0;
        jatekosElet = 3;
        gepElet = 3;
    }
}