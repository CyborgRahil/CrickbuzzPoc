

package poc.com.cricbuzzpoc.com.crickbuzzpoc.db


import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry


import org.junit.After
import org.junit.Before
import poc.com.cricbuzzpoc.data.local.DrugRoomDb

abstract class DrugDb {
    protected lateinit var db: DrugRoomDb

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                DrugRoomDb::class.java).build()
    }

    @After
    fun closeDb() {
        db.close()
    }
}
