package extensions

object Extensions {
    fun String.getAlias() : String{
        return this.getFirstAndLastWord().getFirstChar()
    }

    private fun String.getFirstAndLastWord() : List<String> {
        this.trim().split(Regex("\\s+")).apply {
            return if (size == 1 && first().toCharArray().size > 1) (listOf(first(), first().toCharArray()[1].toString())) else listOf(first(), last())
        }
    }

    private fun List<String>.getFirstChar() : String {
        var finalAlias = ""
        for ( name in this ){
            finalAlias += name.first()
        }
        return finalAlias
    }
}