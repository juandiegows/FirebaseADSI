package com.example.firebaseadsi.models

data class Pregunta(var name : String,
                    val respuestaCorrecta: String,
                    var listRespuesta : MutableList<Respuesta>)
