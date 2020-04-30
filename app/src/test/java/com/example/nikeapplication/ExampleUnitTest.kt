package com.example.nikeapplication

import com.example.nikeapplication.model.Item
import com.example.nikeapplication.model.Response
import com.example.nikeapplication.model.networking.UrbanDictionaryService
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Rule
    @JvmField
    var rule = MockitoJUnit.rule()

    @Mock
    lateinit var urbanDictionaryService: UrbanDictionaryService

    @Test
    fun testWordList() {
        val itemList = mutableListOf(
            Item(0,"term","definition1", 1, 2),
            Item(1,"term","definition2", 2, 2),
            Item(3,"term","definition3", 3, 1))
        val response = Response(itemList)
        Mockito.`when`(urbanDictionaryService.getItems("country")).thenReturn(
            Single.just(response)
        )

        val testObserver: TestObserver<Response> =
            urbanDictionaryService.getItems("country").test()

        testObserver.awaitTerminalEvent()
        testObserver.assertNoErrors()
            .assertValue{
                it.list.size == 3

                it.list[0].definition == "definition1"
                it.list[0].thumbs_up == 1
                it.list[0].thumbs_down == 2

                it.list[1].definition == "definition2"
                it.list[1].thumbs_up == 2
                it.list[1].thumbs_down == 2

                it.list[2].definition == "definition3"
                it.list[2].thumbs_up == 3
                it.list[2].thumbs_down == 1
            }
    }
}
