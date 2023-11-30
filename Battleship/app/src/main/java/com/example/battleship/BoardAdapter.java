package com.example.battleship;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class BoardAdapter extends BaseAdapter {
    private final Context context;
    private final SetShipsInterface setShipsInterface;
    private final List<Cell> cells;

    public BoardAdapter(Context context, List<Cell> cells, SetShipsInterface setShipsInterface) {
        this.context = context;
        this.cells = cells;
        this.setShipsInterface = setShipsInterface;
    }
    public List<Cell> getCells()
    {
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
        if(cells.get(position).isOccupied())
        {
            cellButton.setBackgroundResource(R.drawable.cell_with_ship);
        }
        cellButton.setOnClickListener(v -> {
            if(setShipsInterface.setShip(position)) {
                notifyDataSetChanged();
            }
        });

        return cellView;
    }
}
