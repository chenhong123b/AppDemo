package com.example.appdemo.control;

import com.example.appdemo.model.EliminateService;
import com.example.appdemo.model.EliminateServiceImpl;
import com.example.appdemo.model.RandomService;
import com.example.appdemo.model.RandomServiceImpl;

/**
 * Created by 李泽阳 on 2017/11/8.
 */

public class GameControlImpl implements GameControl {
    private RandomService randomService;
    private EliminateService eliminateService;
    private int[][] gridData;
    private int columnCount;

    public GameControlImpl(int columnCount) {
        randomService = new RandomServiceImpl();
        eliminateService = new EliminateServiceImpl();
        this.columnCount = columnCount;
    }

    @Override
    public void initNewGame() {
        gridData = randomService.initIcons(columnCount);
    }

    @Override
    public int[] getItemIds() {
        int[] data = new int[gridData.length * gridData.length];
        for(int i = 0; i < gridData.length; i++) {
            for (int j = 0; j < gridData[i].length; j++) {
                data[i * gridData.length + j] = gridData[i][j];
            }
        }
        return data;
    }

    @Override
    public int[] eliminate() {
        return new int[0];
    }

    @Override
    public int[] eliminate(int position1, int position2) {
        int[] list = new int[0];
        int x1, y1, x2, y2;
        x1 = position1 % columnCount;
        y1 = position1 / columnCount;
        x2 = position2 % columnCount;
        y2 = position2 / columnCount;
        if(isAdjacent(x1, y1, x2, y2)) {
            changeBlock(x1, y1, x2, y2);
            list = eliminate();
            if(list.length == 0) {
                changeBlock(x1, y1, x2, y2);
            }
        }
        return list;
    }

    protected void changeBlock(int x1, int y1, int x2, int y2) {
        int temp = gridData[x1][y1];
        gridData[x1][y1] = gridData[x2][y2];
        gridData[x2][y2] = temp;
    }

    protected boolean isAdjacent(int x1, int y1, int x2, int y2) {
        if ((x1 == x2 && Math.abs(y1 - y2) == 1) || (Math.abs(x1 - x2) == 1 && y1 == y2)) {
            return true;
        }
        return false;
    }
}
