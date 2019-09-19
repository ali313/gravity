package com.gravity.oncepayment.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Wallet.WALLET_TABLE_NAME)
public class Wallet {

    public static final String WALLET_TABLE_NAME = "wallet_table";

    public static final String ID_KEY = "id";
    public static final String NAME_KEY = "name";
    public static final String COLOR_KEY = "color";
    public static final String AMOUNT_KEY = "amount";
    public static final String CREATED_DATE_KEY = "createdDate";

    @ColumnInfo(name = ID_KEY)
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = NAME_KEY)
    private String name;
    @ColumnInfo(name = COLOR_KEY)
    private int color;
    @ColumnInfo(name = AMOUNT_KEY)
    private long amount;
    @ColumnInfo(name = CREATED_DATE_KEY)
    private String createdDate;
}
