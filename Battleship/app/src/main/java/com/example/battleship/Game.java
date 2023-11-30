package com.example.battleship;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.battleship.databinding.ActivityGameBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity implements HitShipInterface {
    private ActivityGameBinding binding;
    GameBoardAdapter adapter;
    GameBoardEnemyAdapter enemyAdapter;
    List<Cell> gameBoard = new ArrayList<>();
    int mysScore = 0, enemyScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();


        Cell[] receivedArray = (Cell[]) intent.getSerializableExtra("мій_список");
        ArrayList<Cell> receivedList = new ArrayList<>(Arrays.asList(receivedArray));

        adapter = new GameBoardAdapter(this, receivedList);
        binding.gridView.setAdapter(adapter);
        generateEnemyBoard();
        enemyAdapter = new GameBoardEnemyAdapter(this, gameBoard, this);
        binding.gridViewEnemy.setAdapter(enemyAdapter);
    }

    public void generateEnemyBoard() {
        int rows = 10, cols = 10;
        for (int i = 0; i < rows * cols; i++) {
            gameBoard.add(new Cell());
        }
        int[] shipSizes = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        for (int size : shipSizes) {
            boolean shipPlaced = false;

            while (!shipPlaced) {
                Random rand = new Random();
                int orientation = rand.nextInt(2);
                int position = rand.nextInt(rows * cols);

                int row = position / cols;
                int col = position % cols;

                if (orientation == 0 && col + size <= cols) {
                    boolean canPlace = true;

                    for (int k = col; k < col + size; k++) {
                        if (gameBoard.get(row * cols + k).isOccupied()) {
                            canPlace = false;
                            break;
                        }
                    }

                    if (canPlace) {
                        for (int k = col; k < col + size; k++) {
                            gameBoard.get(row * cols + k).setOccupied(true);
                        }
                        shipPlaced = true;
                    }
                } else if (orientation == 1 && row + size <= rows) {
                    boolean canPlace = true;

                    for (int k = row; k < row + size; k++) {
                        if (gameBoard.get(k * cols + col).isOccupied()) {
                            canPlace = false;
                            break;
                        }
                    }

                    if (canPlace) {
                        for (int k = row; k < row + size; k++) {
                            gameBoard.get(k * cols + col).setOccupied(true);
                        }
                        shipPlaced = true;
                    }
                }
            }
        }
    }

    void enemyMove() {
        Random rand = new Random();
        int pos;
        do {
            do {
                pos = rand.nextInt(100);
            } while (adapter.getCells().get(pos).isHit());
            adapter.getCells().get(pos).setHit(true);
            if (adapter.getCells().get(pos).isOccupied()) {
                enemyScore++;
                if (enemyScore == 20) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Поразка")
                            .setMessage("Ти програв")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .show();
                }
            }
        } while (adapter.getCells().get(pos).isOccupied());

        adapter.notifyDataSetChanged();
    }


    @Override
    public void hitShip(int position) {
        Cell cell = (Cell) enemyAdapter.getItem(position);
        if (!cell.isHit()) {
            if (cell.isOccupied()) {
                mysScore++;
                if (mysScore == 20) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Перемога")
                            .setMessage("Ти переміг")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .show();
                }
            } else enemyMove();
        }
    }
}