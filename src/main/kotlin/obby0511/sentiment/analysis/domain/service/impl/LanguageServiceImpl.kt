package obby0511.sentiment.analysis.domain.service.impl

import obby0511.sentiment.analysis.domain.service.AnalyzeEntitySentimentRequest
import obby0511.sentiment.analysis.domain.service.AnalyzeEntitySentimentResponse
import obby0511.sentiment.analysis.domain.service.LanguageService
import org.springframework.stereotype.Service

@Service
class LanguageServiceImpl : LanguageService {
  override fun analyzeEntitySentiment(request: AnalyzeEntitySentimentRequest): AnalyzeEntitySentimentResponse {
    return AnalyzeEntitySentimentResponse(0.0f, 0.0f)
  }
}
