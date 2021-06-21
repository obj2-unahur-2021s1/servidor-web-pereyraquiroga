package ar.edu.unahur.obj2.servidorWeb

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class ServidorWebTest : DescribeSpec({
  describe("Un servidor web") {
    val pedido1=Pedido("198.168.1.5","https://pepito.com.ar/hola.txt", LocalDateTime.now())
    val servidorWeb=ServidorWeb()
    val moduloTexto=Modulo(listOf<String>("txt","html"),"documento o pagina",10)
    val moduloImagen=Modulo(listOf<String>("jpg","png"),"que linda foto",10)
    val analizador=Analizador()

    it("devuelve 501 si recibe una pedido que NO es HTTP"){
      servidorWeb.realizarPedido(pedido1).codigo.shouldBe(CodigoHttp.NOT_IMPLEMENTED)
    }

    it("Modulo puede atender el pedido"){
      moduloTexto.puedeAtenderElPedido(pedido1.url).shouldBe(true)
    }
    it("Hay Modulo que puede responder al pedido"){
      //val  modulos= mutableListOf<Modulo>()
      servidorWeb.agregarModulo(moduloTexto)
      servidorWeb.hayModuloParaElPedido(pedido1).shouldBe(true)
    }

    it("Si no hay modulo que pueda responder al pedido"){
      servidorWeb.hayModuloQueRespondaElPedido(pedido1).codigo.shouldBe(CodigoHttp.NOT_FOUND)
    }

    it ("Si  hay modulo que pueda responder al pedido"){
      servidorWeb.agregarModulo(moduloTexto)
      servidorWeb.hayModuloQueRespondaElPedido(pedido1).codigo.shouldBe(CodigoHttp.OK)
    }

    it("el servidor le reenvia esa respuesta a sus analizadores"){
      servidorWeb.agregarAnalizador(analizador)
      servidorWeb.agregarModulo(moduloTexto)
      servidorWeb.hayModuloQueRespondaElPedido(pedido1).codigo.shouldBe(CodigoHttp.OK)
      // PUEDE RESPONDER AL PEDIDO ENTONCES LE REENVIA LA RESPUESTA AL ANALIZADOR QUE AGREGAMOS
      analizador.listaRespuestas.size.shouldBe(1) // LE LLEGO LA RESPUESTA
    }
    it("le llego el modulo"){
      servidorWeb.agregarAnalizador(analizador)
      servidorWeb.agregarModulo(moduloTexto)
      servidorWeb.hayModuloQueRespondaElPedido(pedido1).codigo.shouldBe(CodigoHttp.OK)
      analizador.listaModulos.size.shouldBe(1) // LE LLEGO EL MODULO
    }

  }
})

