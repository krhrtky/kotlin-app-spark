package todolist

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import spark.Spark.get

fun main(args: Array<String>) {
    val objectMapper = ObjectMapper().registerKotlinModule()


    val jsonTransformer = JsonTransformer(objectMapper)
    val taskRepository = TaskRepository()
    val taskController = TaslController(taskRepository)

    get("/tasks", taskController.index(), jsonTransformer)

//    get("/tasks", { req, resp ->
//        listOf(
//                Task(1, "クリーニングに出す", false),
//                Task(2, "住民票を取得する", false)
//        )
//    }, objectMapper::writeValueAsString)
}