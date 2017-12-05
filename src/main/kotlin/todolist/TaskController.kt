package todolist

import spark.Request
import spark.Response
import spark.Route

class TaslController(private val taskRepository: TaskRepository) {

    fun index(): Route = Route{ req, resp ->
        taskRepository.findAll()

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