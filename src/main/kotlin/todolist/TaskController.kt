package todolist

import com.fasterxml.jackson.databind.ObjectMapper
import spark.Request
import spark.Route
import spark.Spark.halt

class TaslController(private val objectMapper: ObjectMapper,
                     private val taskRepository: TaskRepository) {

    private val Request.task: Task?
        get() {
            val id = params("id").toLongOrNull()
            return id?.let(taskRepository::findById)
        }

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

    fun show(): Route = Route { req, _ ->
        req.task ?: throw halt(404)
    }

    fun destroy(): Route = Route { req, resp ->
        val task = req.task ?: throw halt(404)
        taskRepository.delete(task)
        resp.status(204)
    }

    fun update(): Route = Route { req, resp ->
        val request: TaskUpdateRequest =
                objectMapper.readValue(req.bodyAsBytes()) ?: throw halt(400)
        val task = req.task ?: throw halt(404)
        val newTask = task.copy(
                content = request.content ?: task.content,
                done = request.done ?: task.done
        )
        taskRepository.update(newTask)
        resp.status(204)
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