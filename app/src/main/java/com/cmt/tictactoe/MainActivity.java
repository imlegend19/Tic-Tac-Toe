package com.cmt.tictactoe;
import android.annotation.SuppressLint;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int player = 0;

    boolean gameIsActive = true;
    // 2 = not played
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningState = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7},
            {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    @SuppressLint("SetTextI18n")
    public void dropIn(View view) throws InterruptedException {

        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = player;
            counter.setTranslationY(-1000f);

            if (player == 0) {
                counter.setImageResource(R.drawable.yellow);
                player = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                player = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);
        }

        for (int[] state : winningState) {
            if (gameState[state[0]] == gameState[state[1]]
                    && gameState[state[1]] == gameState[state[2]]
                    && gameState[state[0]] != 2) {

                String winner = "Red";

                if (gameState[state[0]] == 0) {
                    winner = "Yellow";
                }


                Thread.sleep(500);

                TextView winnerMsg = findViewById(R.id.winningMsg);
                winnerMsg.setText(winner + " has won!");
                ConstraintLayout layout = findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);
                GridLayout arena = findViewById(R.id.arena);
                arena.setVisibility(View.INVISIBLE);

                gameIsActive = false;

                Toast.makeText(getApplicationContext(), "Thanks for playing!",
                        Toast.LENGTH_LONG).show();
            }

            else {

                boolean gameIsOVer = true;

                for (int counterState : gameState) {
                    if (counterState == 2) {
                        gameIsOVer = false;
                    }
                }

                if (gameIsOVer) {

                    TextView winnerMsg = findViewById(R.id.winningMsg);
                    winnerMsg.setText("It's a draw!");
                    ConstraintLayout layout = findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    GridLayout arena = findViewById(R.id.arena);
                    arena.setVisibility(View.INVISIBLE);

                }
            }

        }
    }

    public void playAgain(View view) {

        ConstraintLayout layout = findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        gameIsActive = true;


        GridLayout gridLayout = findViewById(R.id.arena);

        player = 0;

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }


        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

        gridLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.tictactoe);
        text.animate().alpha(1).setDuration(2000);

        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_LONG).show();
    }
}
