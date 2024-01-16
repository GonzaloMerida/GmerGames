package com.example.gmergames

object Datasource {
    fun getItemList(): MutableList<Item> {
        val itemList = mutableListOf<Item>(
            Item("Super Mario Odyssey",
            "Nintendo",
            "Videojuego de Mario",
            "Nintendo",
            ),
            Item("Horizont Zero Dawn",
                "Sony",
                "Videojuego de PS4",
                "Sony",
            ),
            Item("World of Warcraft",
                "Blizzard",
                "Videojuego MMO",
                "Blizzard",
            )
        )
        itemList.shuffle()
        return itemList
    }
}