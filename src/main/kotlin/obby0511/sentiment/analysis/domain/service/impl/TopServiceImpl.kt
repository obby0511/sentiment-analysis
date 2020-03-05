package obby0511.sentiment.analysis.domain.service.impl

import obby0511.sentiment.analysis.domain.service.TopService
import org.springframework.stereotype.Service

@Service
class TopServiceImpl : TopService {
    override fun top(): String {
        return "Hello World!!"
    }
}