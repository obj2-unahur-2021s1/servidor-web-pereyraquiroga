package ar.edu.unahur.obj2.servidorWeb

import io.kotest.core.spec.style.DescribeSpec
import java.time.LocalDateTime

class ServidorWebTest : DescribeSpec({
  val url = URL("HTTPS","/documentos/doc1.html","html")
  val pedido1=Pedido(192.168,url, LocalDateTime.of(2020, 3, 20, 2, 0, 0))
 val respuesta=Respuesta(CodigoHttp.OK,"cuerpo",12,pedido1)
  describe("Un servidor web") {
    it("Verificar si es HTTP"){
      pedido1.verificadorHTTP()
    }
  }
})
