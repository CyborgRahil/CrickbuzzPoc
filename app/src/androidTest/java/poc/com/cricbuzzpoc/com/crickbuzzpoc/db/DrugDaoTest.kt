
package poc.com.cricbuzzpoc.com.crickbuzzpoc.db


import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4


import org.junit.Test
import org.junit.runner.RunWith


import io.reactivex.functions.Consumer
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import poc.com.cricbuzzpoc.TestUtil
import poc.com.cricbuzzpoc.data.local.DrugEntity
import poc.com.cricbuzzpoc.data.local.DrugRoomDb
import java.util.ArrayList

@RunWith(AndroidJUnit4::class)
class DrugDaoTest {

    lateinit var db: DrugRoomDb

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                DrugRoomDb::class.java).build()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertAndRead() {
        val repo = TestUtil.createEntity()
        db.getDrugDao().addDrugData(repo)
        var list: List<DrugEntity> = ArrayList<DrugEntity>()

        db.getDrugDao().getDrugList().subscribe({ list = it })
        assertThat(list, notNullValue())
        assert(list.isNotEmpty())
    }

}
