package ar.edu.unahur.obj2.servidorWeb

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class ServidorWebTest : DescribeSpec({
  describe("Un servidor web") {
  val pedido1=Pedido("198.168.1.5","https://pepito.com.ar/hola.txt", LocalDateTime.now())
  val servidorWeb=ServidorWeb()
      var listaExtension = listOf<String>("jpg","txt" ,"png")
   val moduloTexto=Modulo(listaExtension,Tipo.TEXTO,10)

    it("devuelve 501 si recibe una pedido que NO es HTTP"){
      servidorWeb.realizarPedido(pedido1).codigo.shouldBe(CodigoHttp.NOT_IMPLEMENTED)
    }

      it("Modulo puede atender el pedido"){
          moduloTexto.puedeAtenderElPedido(pedido1).shouldBe(true)
      }
    it("Modulo que puede responder al pedido"){
        val  modulos= mutableListOf<Modulo>()
        servidorWeb.agregarModulo(moduloTexto)
        servidorWeb.moduloQuePuedeResponderAlPedido(pedido1).shouldBe(true)
    }
  }
})
