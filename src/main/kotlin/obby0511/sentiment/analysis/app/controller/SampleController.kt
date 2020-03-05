package obby0511.sentiment.analysis.app.controller

import obby0511.sentiment.analysis.domain.service.TopService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController {

    @Autowired
    lateinit var topService: TopService

    @GetMapping("/")
    fun getHello() :String {
        return topService.top()
    }
}