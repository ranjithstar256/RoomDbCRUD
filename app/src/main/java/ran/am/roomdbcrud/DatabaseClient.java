package ran.am.roomdbcrud;


import android.content.Context;

import androidx.room.Room;

class DatabaseClientt {

    private Context mCtx;
    private static DatabaseClientt mnInstance;

    //our app database object
    private AppDatabase appDatabase;

    private DatabaseClientt(Context mCtx) {
        this.mCtx = mCtx;
        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "MyToDos").build();
    }

    public static synchronized DatabaseClientt getInstance(Context mCtx) {
        if (mnInstance == null) {
            mnInstance = new DatabaseClientt(mCtx);
        }
        return mnInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
