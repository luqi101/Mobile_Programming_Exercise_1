package com.example.mobileshopapp.data

import com.example.mobileshopapp.R
import com.example.mobileshopapp.model.Product

object ProductCatalog {
    const val ALL_CATEGORY = "All"

    val products = listOf(
        Product(
            id = 1,
            name = "Lakehead Hoodie",
            category = "Apparel",
            description = "Midweight fleece hoodie with a soft lining, roomy front pocket, and campus-inspired green trim.",
            priceCents = 5499,
            imageResId = R.drawable.product_hoodie,
            accentColor = 0xFF006B5B
        ),
        Product(
            id = 2,
            name = "Lecture Notebook",
            category = "Study",
            description = "Hard-cover dotted notebook with durable binding, quick-reference dividers, and 160 smooth pages.",
            priceCents = 1299,
            imageResId = R.drawable.product_notebook,
            accentColor = 0xFF2F6FED
        ),
        Product(
            id = 3,
            name = "Campus Backpack",
            category = "Accessories",
            description = "Water-resistant backpack with laptop sleeve, bottle pocket, and balanced straps for long class days.",
            priceCents = 6499,
            imageResId = R.drawable.product_backpack,
            accentColor = 0xFF52636B
        ),
        Product(
            id = 4,
            name = "Ceramic Study Mug",
            category = "Drinkware",
            description = "Comfort-grip ceramic mug for coffee, tea, or late-night study sessions.",
            priceCents = 1599,
            imageResId = R.drawable.product_mug,
            accentColor = 0xFFE85D75
        ),
        Product(
            id = 5,
            name = "Steel Water Bottle",
            category = "Drinkware",
            description = "Insulated stainless bottle that keeps drinks cold through lectures, labs, and gym breaks.",
            priceCents = 2499,
            imageResId = R.drawable.product_bottle,
            accentColor = 0xFF008C7A
        ),
        Product(
            id = 6,
            name = "Varsity Cap",
            category = "Apparel",
            description = "Adjustable cotton cap with a curved brim and clean embroidered campus mark.",
            priceCents = 1999,
            imageResId = R.drawable.product_cap,
            accentColor = 0xFFE0A100
        ),
        Product(
            id = 7,
            name = "Student Lanyard",
            category = "Accessories",
            description = "Soft woven lanyard with quick-release clasp for cards, keys, and transit passes.",
            priceCents = 799,
            imageResId = R.drawable.product_lanyard,
            accentColor = 0xFF6D5BD0
        ),
        Product(
            id = 8,
            name = "Braided USB-C Cable",
            category = "Tech",
            description = "Two-meter braided charging cable for phones, tablets, headphones, and study desk setups.",
            priceCents = 1499,
            imageResId = R.drawable.product_usb_cable,
            accentColor = 0xFF2F6FED
        ),
        Product(
            id = 9,
            name = "Library Tote",
            category = "Accessories",
            description = "Heavy cotton tote sized for books, groceries, sketch pads, and daily essentials.",
            priceCents = 1799,
            imageResId = R.drawable.product_tote,
            accentColor = 0xFF006B5B
        ),
        Product(
            id = 10,
            name = "Desk Lamp",
            category = "Study",
            description = "Compact LED lamp with warm light, flexible neck, and USB power for dorm desks.",
            priceCents = 3299,
            imageResId = R.drawable.product_lamp,
            accentColor = 0xFFE0A100
        )
    )

    val categories = listOf(ALL_CATEGORY) + products.map { it.category }.distinct().sorted()
}
