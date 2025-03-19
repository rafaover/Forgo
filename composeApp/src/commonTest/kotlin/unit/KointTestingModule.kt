package unit

import com.feeltheboard.forgo.di.sharedModule
import kotlin.test.Test

class KoinTestingModule : KoinTest {

    @Test
    fun checkKoinModules() {
        sharedModule.verify()
    }

}