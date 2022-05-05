package com.birthday.paradox.endpoint

import com.birthday.paradox.service.GraphGenerator
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class GraphDataController(
    private val graphGenerator: GraphGenerator
) {
    @GetMapping("/graph")
    fun graph(): ModelAndView {
        val model = mutableMapOf<String, List<List<Number>>>()
        val data = graphGenerator.generate()

        model["data"] = data.map { listOf(it.numberOfRandomBirthdays, it.probabilityOfSameBirthdays) }
        return ModelAndView("index", model)
    }
}