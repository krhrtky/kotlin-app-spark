package todolist

import com.fasterxml.jackson.databind.ObjectMapper
import spark.Route
import spark.Spark.halt

class TaslController(private val objectMapper: ObjectMapper,
        private val taskRepository: TaskRepository) {

    fun index(): Route = Route{ req, resp ->
        taskRepository.findAll()

    }

    fun create(): Route = Route { req, resp ->
        val request: TaskCreateRequest =
                objectMapper.readValue(req.bodyAsBytes()) ?: throw halt(400)
        val task = taskRepository.create(request.content)
        resp.status(201)
        task

    }
//class TaslController {
//    fun index(): Route = Route { req, resp ->
//           //ダミーを返す
//           listOf(
//                   Task(1, "クリーニングに出す", false),
//                   Task(2, "住民票を取得する", false)
//           )
//    }
//    fun index(): Route = object : Route {
//        override fun handle(request: Request?, response: Response?): Any =
//                listOf(
//                        Task(1, "クリーニングに出す", false),
//                        Task(2, "住民票を取得する", false)
//                )
//    }
}