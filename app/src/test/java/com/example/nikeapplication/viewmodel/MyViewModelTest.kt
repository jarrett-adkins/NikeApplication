package com.example.nikeapplication.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.nikeapplication.model.Item
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MyViewModelTest {

    @Rule @JvmField var rule: TestRule = InstantTaskExecutorRule()  // necessary for the post call in MyViewModel.sort()

    private val data: MutableLiveData<List<Item>> = MutableLiveData(
        listOf(
            Item("The word all students fear.\n" +
                    "    A piece of paper that might [screw up] someones life if they don't write anything on it.\n" +
                    "    Something that makes most students wish to die on the weeks before they go into the room and take the test.\n" +
                    "    Something that makes most students have [teamwork] spirit.\n" +
                    "    A paper that teachers love to surprise, [scare] and threat their students with.", 32,5),
            Item("A [test] is something that you take and [try] to [pass].", 6,0),
            Item("1. the main cause of [explosions].\n" +
                    "    2. any thing [dreaded] that your \"teachers\" say is \"good\" for you. soon after, you explode for no reason.\n" +
                    "    3. what scientists do to make stuff explode.\n" +
                    "    4. when a sheet of paper explodes into [flames].", 156,44),
            Item("Everything that is [put in] [front] of you during any given [day]. ", 58,18),
            Item("slang for [testosterone]..[steroid] [hormones].", 158,65)
        )
    )

    @Test
    fun sortIsCorrect() {
        val viewModel = MyViewModel()
//        viewModel.sort(data.value, Item::thumbs_up)
//        assertEquals(viewModel.itemLiveData.value?.get(0)?.thumbs_up, 158)

//        viewModel.sort(data.value, Item::thumbs_down)
//        assertEquals(viewModel.itemLiveData.value?.get(0)?.thumbs_up, 65)
    }
}