package ar.edu.unahur.obj2.servidorWeb

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class ServidorWebTest : DescribeSpec({
  describe("Un servidor web") {
    val pedido1=Pedido("198.168.1.5","https://pepito.com.ar/hola.txt", LocalDateTime.now())
    val pedido2=Pedido("198.168.1.5","http://pepito.com.ar/hola.html", LocalDateTime.now())
    val pedido3=Pedido("198.168.1.5","http://pepito.com.ar/hola.jpg", LocalDateTime.of(2021, 6, 20, 23, 59, 0))
    val pedido4=Pedido("198.168.1.5","http://pepito.com.ar/hola.png", LocalDateTime.of(2021, 6, 22, 23, 59, 0))
    val servidorWeb=ServidorWeb()
    val moduloTexto=Modulo(listOf<String>("txt","html"),"documento o pagina",10)
    val moduloImagen=Modulo(listOf<String>("jpg","png"),"que linda foto",10)
    val analizadorIpSospechosa=AnalizadorIpSospechosa()
    val analizadorEstadistic=AnalizadorEstadistica()
    val analizadorDemora=AnalizadorDemora(5)


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
      servidorWeb.agregarAnalizador(analizadorIpSospechosa)
      servidorWeb.agregarModulo(moduloTexto)
      servidorWeb.hayModuloQueRespondaElPedido(pedido1).codigo.shouldBe(CodigoHttp.OK)
      // PUEDE RESPONDER AL PEDIDO ENTONCES LE REENVIA LA RESPUESTA Y EL MODULO AL ANALIZADOR QUE AGREGAMOS
      analizadorIpSospechosa.listaRespuesta.size.shouldBe(1) // LE LLEGO LA RESPUESTA
    }
    it("le llego el modulo"){
      servidorWeb.agregarAnalizador(analizadorIpSospechosa)
      servidorWeb.agregarModulo(moduloTexto)
      servidorWeb.hayModuloQueRespondaElPedido(pedido1).codigo.shouldBe(CodigoHttp.OK)
      analizadorIpSospechosa.listaModulo.size.shouldBe(1) // LE LLEGO EL MODULO
    }
    describe("Analizador de Demoras") {
      it("cantidad de respuestas con demora para un modulo") {
        servidorWeb.agregarModulo(moduloTexto)
        servidorWeb.hayModuloParaElPedido(pedido1)
        analizadorDemora.cantidadDeRespuestasDemoradas(moduloTexto).shouldBe(0)
      }
    }
    describe("Analizador de Ip Sospechosas") {
      it("Registrar pedido ip sospechosa y cuantos pedidos realizo esa ip "){
        analizadorIpSospechosa.registrarPedidoIpSospechosa(pedido1,"198.168.1.5")
        analizadorIpSospechosa.cuantosPedidosRealizoLaip().shouldBe(1)
      }
    }

 }
})

