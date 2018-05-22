package poc.com.cricbuzzpoc.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import poc.com.cricbuzzpoc.MedicalApp
import poc.com.cricbuzzpoc.di.component.ActivityComponent


open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * Creates and return the activity scoped component for dependency injection.
     *
     * @return Bali activity component for dependency injection.
     */
    var mActivityComponent: ActivityComponent? = null

    fun getBaliActivityComponent(): ActivityComponent {
        if (mActivityComponent == null) {
            mActivityComponent = getMainApplication().createActivityComponent(this)
        }
        return mActivityComponent as ActivityComponent
    }

    private fun getMainApplication(): MedicalApp {
        return application as MedicalApp
    }
}