package obby0511.sentiment.analysis.domain.service

interface LanguageService {

  fun analyzeEntitySentiment(request: AnalyzeEntitySentimentRequest): AnalyzeEntitySentimentResponse

}

data class AnalyzeEntitySentimentRequest(val document: String, val encodingType: Charsets) {
}

data class AnalyzeEntitySentimentResponse(val magnitude: Float, val score: Float) {
}
