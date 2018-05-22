package poc.com.cricbuzzpoc.testUtility

import io.reactivex.schedulers.TestScheduler
import poc.com.cricbuzzpoc.utility.SchedulerProvider

class TestSchedulerProvider() : SchedulerProvider {

    val testScheduler: TestScheduler = TestScheduler()

    override fun uiScheduler() = testScheduler
    override fun ioScheduler() = testScheduler
}