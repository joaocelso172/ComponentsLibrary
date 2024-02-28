package commons.domain

interface Callback<T> {
    fun onSuccess(result: T)

    fun onError(throwable: Throwable)
}