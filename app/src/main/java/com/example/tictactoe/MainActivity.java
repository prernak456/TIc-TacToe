package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow, 1= red
    int activePlayer = 0;
    boolean gameIsActive = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2,};
    int[][] winningPositions = {{0,1,2}, {3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    public void dropIn(View view){


        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gameIsActive){

            gameState[tappedCounter]= activePlayer;

            counter.setTranslationY(-1000f);
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            if(activePlayer == 0){
                counter.setImageResource(R.drawable.cross);
                activePlayer = 1;
            }
            else{
                counter.setImageResource(R.drawable.ooo);
                activePlayer = 0;
            }

            for(int[] winningPosition: winningPositions){
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                && gameState[winningPosition[0]]!= 2) {
                    System.out.println(gameState[winningPosition[0]]);

                    gameIsActive = false;
                    String winner = "Cross";

                    if(gameState[winningPosition[0]]== 0){
                        winner = "X";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnersText);
                    winnerMessage.setText(winner + " Has Won!");
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
                else {

                    boolean gameIsOver = true;

                    for(int counterState: gameState){
                        if(counterState == 2) gameIsOver = false;
                    }

                    if(gameIsOver){
                        TextView winnerMessage = (TextView) findViewById(R.id.winnersText);
                        winnerMessage.setText("It's a Draw");
                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }


            }



        }


    }

    public void playAgain(View view){

        gameIsActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility((View.VISIBLE));

        activePlayer = 0;

        Arrays.fill(gameState, 2);

        Log.d("TAG", "called");
        TableLayout tableLayout = (TableLayout)findViewById(R.id.gridLayout);
        for (int i = 0; i<tableLayout.getChildCount(); i++){

            TableRow row = (TableRow) tableLayout.getChildAt(i);

            for(int j =0; j<row.getChildCount(); j++){
                ((ImageView) row.getChildAt(j)).setImageResource(0);
            }

        }

        layout.setVisibility((View.INVISIBLE));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}