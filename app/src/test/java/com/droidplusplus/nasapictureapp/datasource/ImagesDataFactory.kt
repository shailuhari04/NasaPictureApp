package com.droidplusplus.nasapictureapp.datasource

import com.droidplusplus.nasapictureapp.data.model.DataItem
import java.util.*

fun createDataItemListResult(): List<DataItem> {
    val result: ArrayList<DataItem>
    val item1 = DataItem(
        title = "Starburst Galaxy M94 from Hubble",
        copyright = "ESA/HubbleNASA",
        date = "2019-12-01",
        explanation = "Why does this galaxy have a ring of bright blue stars?",
        url = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg"
    )
    val item2 = DataItem(
        title = "M27: The Dumbbell Nebula",
        copyright = "Steve Mazlin",
        date = "2019-12-03",
        explanation = "Is this what will become of our Sun? Quite possibly.",
        url = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_960.jpg"
    )
    val item3 = DataItem(
        title = "Starburst Galaxy M94 from Hubble",
        copyright = "ESA/HubbleNASA",
        date = "2019-12-01",
        explanation = "Why does this galaxy have a ring of bright blue stars?",
        url = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg"
    )
    val item4 = DataItem(
        title = "Lines of Time",
        copyright = "Anton Komle",
        date = "2019-12-07",
        explanation = "Why does this galaxy have a ring of bright blue stars?",
        url = "https://apod.nasa.gov/apod/image/1912/LinesOfTimeKomlev1100.jpg"
    )
    result = arrayListOf(item1, item2, item3, item4)

    return result
}