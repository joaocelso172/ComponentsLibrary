package entity

data class Transaction(
    var title : String,
    var description : String,
    val transactionType: Int,
    val paymentType: Int,
    val parcelAmout: Int,
    val value: Float,
    val date: String
)