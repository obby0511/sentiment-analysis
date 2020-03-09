package obby0511.sentiment.analysis

import com.atilika.kuromoji.ipadic.Tokenizer
import java.io.File
import java.nio.charset.Charset

interface LanguageService {
    fun analyzeSentiment(request: LanguageRequest): LanguageResponse

    fun get(): LanguageService = DefaultLanguageService()
}

data class LanguageRequest(
        val text: String,
        val encodingType: Charset = Charsets.UTF_8
)

data class LanguageResponse(
        val magnitude: Double
) {
    @Suppress("unused")
    val score: Float
        get() = when {
            magnitude > 0 -> 1f
            magnitude < 0 -> -1f
            else -> 0f
        }
}

class DefaultLanguageService(path: String = "/pn.csv.m3.120408.trim.csv") : LanguageService {
    private val scores: Map<String, Float> = loadDictionary(path)

    override fun analyzeSentiment(request: LanguageRequest): LanguageResponse = try {
        val tokenizer = Tokenizer.Builder().build()
        val score = tokenizer.tokenize(request.text).asSequence()
                .map { it.surface }
                .map { scores[it] ?: 0f }
                .sum()
                .toDouble()
        LanguageResponse(score)
    } catch (e: Exception) {
        throw LanguageServiceException(e.message, e)
    }
}

@Suppress("UNUSED_PARAMETER", "unused")
class LanguageServiceException(message: String?, e: Exception) : RuntimeException(
)


fun loadDictionary(path: String): Map<String, Float> {
    val dict = DefaultLanguageService::class.java.getResource(path).path
    File(dict).useLines {
        return it.filter { it.isNotBlank() }
                .map { it.split("\t") }
                .map {
                    val word = it[0]
                    val score = when (it[1].trim()) {
                        "p" -> 1f
                        "n" -> -1f
                        else -> 0f
                    }
                    Pair(word, score)
                }
                .toMap()
    }
}
