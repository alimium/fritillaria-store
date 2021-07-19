package com.example.onlinestore.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserEntity.class, ProductEntity.class}, version = 1, exportSchema = false)
@TypeConverters({ListConverter.class, UserConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract DataAccessObject dao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    // Populate the database in the background.
                    // If you want to start with more words, just add them.
                    DataAccessObject dao = INSTANCE.dao();
//                    dao.deleteAllUsers();
//                    dao.deleteAllProducts();
                    UserEntity admin = new UserEntity("admin", "admin", "admin", "admin", "admin", null, new ArrayList<>());
                    ProductEntity productDemo = new ProductEntity(null, "demoTitle", "demoDescription", "100", "0",
                            "Accessory", "Men", "L", "Tehran", admin, 1);
                    dao.insertUser(admin);
                    dao.insertProduct(productDemo);
                }
            });
        }
    };
}
