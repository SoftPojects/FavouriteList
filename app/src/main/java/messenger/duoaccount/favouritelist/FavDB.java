package messenger.duoaccount.favouritelist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavDB extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "CofeeDB";
    private static String TABLE_NAME = "favouriteTable";
    public static String KEY_ID = "id";
    public static String ITEM_TITLE = "itemTitle";
    public static String ITEM_IMAGE = "itemImage";
    public static String FAVOURITE_STATUS = "fStatus";
    private static String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "("
            + KEY_ID + " TEXT," + ITEM_TITLE + " TEXT,"
            + ITEM_IMAGE + " TEXT," + FAVOURITE_STATUS + " TEXT)";

    public FavDB(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
