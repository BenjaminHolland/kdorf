package land.generic.kdorf.utility

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class SuspendLazySuspendingImpl<out T>(
    private val coroutineScope: CoroutineScope,
    val context: CoroutineContext,
    private val initializer: suspend CoroutineScope.() -> T
) : SuspendLazy<T> {
    private var deferred: Deferred<T>? = null

    override suspend operator fun invoke(): T {
        if (deferred == null) {
            deferred = coroutineScope.async(context, block = initializer)
        }

        return deferred!!.await()
    }
}

interface SuspendLazy<out T> {
    suspend operator fun invoke(): T

}
