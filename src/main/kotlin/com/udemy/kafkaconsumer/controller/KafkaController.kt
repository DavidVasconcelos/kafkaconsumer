package com.udemy.kafkaconsumer.controller

import com.google.gson.Gson
import com.udemy.kafkaconsumer.model.MoreSimpleModel
import com.udemy.kafkaconsumer.model.SimpleModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.web.bind.annotation.RestController

@RestController
class KafkaController(@Autowired val jsonConverter: Gson) {

    @KafkaListener(topics = ["myTopic"])
    fun getFromKafka(simpleModel: String) {
        val json = jsonConverter.fromJson(simpleModel, SimpleModel::class.java) as SimpleModel
        println(json.toString())
    }

    @KafkaListener(topics = ["myTopic2"])
    fun getFromKafka2(moreSimpleModel: String) {
        val json = jsonConverter
                .fromJson(moreSimpleModel, MoreSimpleModel::class.java) as MoreSimpleModel
        println(json.toString())
    }


}