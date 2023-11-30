package com.example.battleship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.battleship.databinding.ActivityMainBinding;
import com.example.battleship.databinding.ActivitySetShipsBinding;

import java.util.ArrayList;
import java.util.List;

public class SetShipsActivity extends AppCompatActivity implements SetShipsInterface {
    private ActivitySetShipsBinding binding;
    int singleCellShips = 4;
    int doubleCellShips = 3;
    int tripleCellShips = 2;
    int quadrupleCellShips = 1;
    BoardAdapter adapter;
    Boolean isHorizontal = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetShipsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.rotateButton.setOnClickListener(view -> {
            isHorizontal = !isHorizontal;
        });

        List<Cell> cells = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            cells.add(new Cell());
        }
        adapter = new BoardAdapter(this, cells, this);
        binding.startButton.setOnClickListener(view -> {
            if (singleCellShips == 0 && doubleCellShips == 0 && tripleCellShips == 0 && quadrupleCellShips == 0) {
                Intent intent = new Intent(this, Game.class);
                intent.putExtra("мій_список", adapter.getCells().toArray(new Cell[0]));
                startActivity(intent);
            } else {
                Toast.makeText(this, "Розставте всі кораблі", Toast.LENGTH_SHORT).show();
            }
        });

        binding.gridView.setAdapter(adapter);
    }

    @Override
    public Boolean setShip(int position) {
        Cell cell = (Cell) adapter.getItem(position);
        if (singleCellShips > 0) {
            if (areNeighboringCellsFree(position, 10)) {
                cell.setOccupied(true);
                singleCellShips--;

                return true;
            }
        }
        if (singleCellShips == 0 && doubleCellShips > 0) {
            int nextposition;
            if (isHorizontal) nextposition = position + 1;
            else nextposition = position - 10;
            int[] positions = {position, nextposition};
            if (canPlaceTwoCellShip(positions, 10, 2)) {
                cell.setOccupied(true);
                Cell cell1 = (Cell) adapter.getItem(positions[1]);
                cell1.setOccupied(true);
                doubleCellShips--;

                return true;
            }
        }
        if (singleCellShips == 0 && doubleCellShips == 0 && tripleCellShips > 0) {
            int[] positions;
            if (isHorizontal) {
                positions = new int[]{position, position + 1, position + 2};
            } else positions = new int[]{position, position - 10, position - 20};

            if (canPlaceTwoCellShip(positions, 10, 3)) {
                cell.setOccupied(true);
                Cell cell1 = (Cell) adapter.getItem(positions[1]);
                Cell cell2 = (Cell) adapter.getItem(positions[2]);
                cell1.setOccupied(true);
                cell2.setOccupied(true);
                tripleCellShips--;

                return true;
            }
        }
        if (singleCellShips == 0 && doubleCellShips == 0 && tripleCellShips == 0 && quadrupleCellShips > 0) {
            int[] positions;
            if (isHorizontal) {
                positions = new int[]{position, position + 1, position + 2, position + 3};
            } else positions = new int[]{position, position - 10, position - 20, position - 30};

            if (canPlaceTwoCellShip(positions, 10, 4)) {
                cell.setOccupied(true);
                Cell cell1 = (Cell) adapter.getItem(positions[1]);
                Cell cell2 = (Cell) adapter.getItem(positions[2]);
                Cell cell3 = (Cell) adapter.getItem(positions[3]);
                cell1.setOccupied(true);
                cell2.setOccupied(true);
                cell3.setOccupied(true);
                quadrupleCellShips--;
                return true;
            }
        }
        return false;
    }

    private boolean areNeighboringCellsFree(int position, int columnsCount) {
        int gridSize = columnsCount * columnsCount;

        int[] neighbors = {
                position - columnsCount - 1,
                position - columnsCount,
                position - columnsCount + 1,
                position - 1,
                position + 1,
                position + columnsCount - 1,
                position + columnsCount,
                position + columnsCount + 1
        };

        for (int neighbor : neighbors) {

            if (neighbor >= 0 && neighbor < gridSize && isCellOccupied(neighbor)) {
                return false;
            }
        }

        return true;
    }

    private boolean canPlaceTwoCellShip(int[] positions, int columnsCount, int shipSize) {
        if (isHorizontal && positions[0] % 10 > columnsCount - shipSize) return false;
        if (!isHorizontal && positions[0] > (columnsCount - shipSize) * 10) return false;

        for (int pos : positions) {
            int[] neighbors = getNeighbor(pos);
            for (int neighbor : neighbors) {
                if (neighbor >= 0 && neighbor < 100 && isCellOccupied(neighbor)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[] getNeighbor(int position) {
        int columnsCount = 10;
        return new int[]{
                position - columnsCount - 1,
                position - columnsCount,
                position - columnsCount + 1,
                position - 1,
                position + 1,
                position + columnsCount - 1,
                position + columnsCount,
                position + columnsCount + 1
        };
    }


    private boolean isCellOccupied(int neighbor) {
        Cell cell = (Cell) adapter.getItem(neighbor);
        return cell.isOccupied();
    }


}