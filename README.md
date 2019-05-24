# TestRappi
Capas de la aplicación:
  -Application
    Aquí va la clase application.
  -Model
    Aquí van las clases que representan los datos
  -Network
    Todas las interfaces y clases relacionadas con el uso de la red, tales como servicios de retrofit.
  -Repository
    Se encargan de decidir la fuente de datos a utilizar, o de actualizar las fuentes de datos.
  -UserInterface
    Todo lo relacionado con la interacción directa del usuario, aquí van clases como Activity,Fragments,Adapters y ViewHolder, también        interfaces
  -ViewModel
    Los viewModel contienen la lógica para presentar los datos al usuario
  
  
  -Model
    Se encarga de contener la información del modelo
   -Network
    NetworkUtils: Contiene todas las constantes para consumir los servicios, y un método que nos indica si el móvil está conectado a          internet 
    ApiClient: Genera el cliente para consumir los servicios
    -Repository
    MovieDetailRepository: Encargado de consumir el servicio de movieDetails.
     MoviesRepository: Encargado de consumir el servicio de topRated,Popular y upcoming movies
    -UserInterface
        Todo lo relacionado con la interfaz de usuario
     -View Model
      Se encarga de solicitar al repository la información a mostrar al usuario.
      
      
  1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?
  
  Consiste en que un módulo o una clase solo debe tener una única responsabilidad de la funcionalidad del software.
  Su propósito es tener un código más modular y mantenible.

2. Qué características tiene, según su opinión, un “buen” código o código limpio?

  Para mí debería ser fácil de entender,fácil de mantener, y fácil de añadir nuevas funcionalidades sin afectar al resto o sin tener que modificar demasiado código ya realizado.
