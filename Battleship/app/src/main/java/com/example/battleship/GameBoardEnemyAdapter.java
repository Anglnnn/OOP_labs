package com.example.battleship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

public class GameBoardEnemyAdapter extends BaseAdapter {
    private final Context context;
    private final List<Cell> cells;
    private final HitShipInterface hitShipInterface;
    public GameBoardEnemyAdapter(Context context, List<Cell> cells, HitShipInterface hitShipInterface) {
        this.context = context;
        this.cells = cells;
        this.hitShipInterface = hitShipInterface;
    }

    public List<Cell> getCells() {
        return cells;
    }

    @Override
    public int getCount() {
        return cells.size();
    }

    @Override
    public Object getItem(int position) {
        return cells.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View cellView = convertView;
        if (cellView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cellView = inflater.inflate(R.layout.grid_item, null);
        }
        Button cellButton = cellView.findViewById(R.id.cellButton);

        cellButton.setOnClickListener(v -> {
            hitShipInterface.hitShip(position);
            if (!cells.get(position).isHit())
                cells.get(position).setHit(true);
            if (cells.get(position).isOccupied())
                cellButton.setBackgroundResource(R.drawable.hit_shim_image);
            else cellButton.setBackgroundResource(R.drawable.missed_cell_image);
        });


        return cellView;
    }
}
