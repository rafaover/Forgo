package unit

import com.feeltheboard.forgo.di.appModules
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import kotlin.test.Test

class KoinModuleTest : KoinTest {

    @Test
    fun checkKoinModules() {
        checkModules {
            modules(appModules())
        }
    }
}