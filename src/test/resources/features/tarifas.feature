# language: es
Característica: Cobro de estadías en EstacionaYa
  Como dueño del parqueadero
  Quiero cobrar según el tiempo de permanencia y el tipo de vehículo
  Para respetar la gracia, las tarifas y los descuentos de mis clientes

  Esquema del escenario: Cobrar la estadía respetando la gracia y el tope diario
    Dado que un "<vehiculo>" permanece <minutos> minutos en el parqueadero
    Cuando se calcula el cobro de la estadía
    Entonces debe pagar <valor> pesos

    Ejemplos:
      | vehiculo | minutos | valor |
      | carro    | 0       | 0     |
      | moto     | 0       | 0     |
      | carro    | 15      | 0     |
      | moto     | 15      | 0     |
      | carro    | 16      | 3000  |
      | moto     | 16      | 1500  |
      | carro    | 60      | 3000  |
      | moto     | 60      | 1500  |
      | carro    | 61      | 6000  |
      | moto     | 61      | 3000  |
      | carro    | 480     | 24000 |
      | carro    | 481     | 25000 |
      | moto     | 420     | 10500 |
      | moto     | 421     | 12000 |
      | carro    | 1440    | 25000 |
      | moto     | 1440    | 12000 |

  Escenario: Un cliente frecuente recibe un descuento por su estadía
    Dado que un "carro" permanece 60 minutos en el parqueadero
    Cuando se calcula el cobro de la estadía
    Y se aplica el descuento de cliente "frecuente"
    Entonces debe pagar 2700 pesos

  Escenario: El convenio se aplica sobre el tope diario
    Dado que un "carro" permanece 1440 minutos en el parqueadero
    Cuando se calcula el cobro de la estadía
    Y se aplica el descuento de cliente "convenio"
    Entonces debe pagar 20000 pesos
